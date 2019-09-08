package com.hbe.ms.common.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbe.ms.common.core.HBEConstants;
import com.hbe.ms.common.core.MessageConstants;
import com.hbe.ms.common.exception.HBEMicroServiceException;
import com.hbe.ms.common.exception.RestException;
import com.hbe.ms.common.vo.Error;
import com.hbe.ms.common.vo.InterfaceTransDetails;
import com.hbe.ms.common.vo.Response;
import com.hbe.ms.common.vo.VlpServResultRequest;


@Service
@PropertySource("file:${EXTERNAL_CONFIGURATION_FILES}/vlp-common.properties")
public class RestCommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestCommonService.class);

	@Autowired
	private RestTemplateConfig restTemplateFactory;

	protected HttpHeaders httpHeaders;

	@Value("${vlp.data.service.url}")
	private String vlpDataService;

	@Value("${vlp.token.service.url}")
	private String tokenServiceURL;

	@Value("${vlp.token.clientname}")
	private String clientName;

	@Value("${vlp.token.secret}")
	private String clientsecret;

	@PostConstruct
	public void init() {
		this.httpHeaders = new HttpHeaders();
		this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		this.httpHeaders.set("Accept", "application/json");
        this.setErrorHandler();
    }

    private void setErrorHandler() {
        this.restTemplateFactory.restTemplate().setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
				LOGGER.error("Response Status Code: " + response.getRawStatusCode());
				LOGGER.error("Response Status Text: " + response.getStatusText());
                if (response.getRawStatusCode() == 404) {//usually there is no response to parse in this case
					LOGGER.error("Response Body: " + new String(getResponseBody(response)));
                    return true;
                }
                return false; //we want to be able to parse the error response
            }
        });
    }

    public Response validateToken(String token) {
		this.httpHeaders.set(MessageConstants.CONTENT_TYPE, MessageConstants.APPLICATION_JSON);
		HttpEntity<?> httpRequest = new HttpEntity<>(null, this.httpHeaders);
		ResponseEntity<?> responseTemp = null;
        Response response = null;
		try {
			String url = this.tokenServiceURL + "users/validateToken?" + "access_token=" + token;
			responseTemp = this.restTemplateFactory.restTemplate().exchange(url, HttpMethod.GET, httpRequest,
					String.class);

			if (responseTemp.getStatusCode().is2xxSuccessful()) {
				LOGGER.info("Return response from data service is {}" , responseTemp.getBody().toString());
                response = new Response();
                response.setCode(HBEConstants.HTTP_200);
                response.setDescription(HBEConstants.SUCCESS);
			} else {
				LOGGER.error("Token is not valid");
                //Handle other Response Codes
                response = handleOtherResponseCodes(responseTemp);
			}
        } catch (ResourceAccessException e) {
            String msg = e.getMessage();
            LOGGER.error(msg, e);
            throw new RestException(HBEConstants.HPF_MS_BS_003, e.getMessage());
        } catch (Exception e) {
			String msg = e.getMessage();
            LOGGER.error(msg, e);
			throw new HBEMicroServiceException(HBEConstants.HPF_MS_BS001, e.getMessage());
		}
        return response;
	}

	public <T> ResponseEntity<T> invokeClient(String url, HttpMethod method, HttpEntity<?> httpRequest,
			Class<T> object) {
		return this.restTemplateFactory.restTemplate().exchange(url, method, httpRequest, object);
	}

	public TokenResponse validateHPFRequest(String username, String password, String granttype)  {

		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(clientName, clientsecret);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(HBEConstants.USERNAME, username);
		map.add(HBEConstants.PASS_LABLE, password);
		map.add(HBEConstants.GRANT_TYPE, granttype);

		String url = this.tokenServiceURL + "oauth/token";
		LOGGER.info("in validateHPFRequest  calling URl: {}", url);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		TokenResponse tokenResponse = null;
		ResponseEntity<?> responseTemp = null;
		try {
			LOGGER.error("in validateHPFRequest  start calling token service ");
			responseTemp = this.restTemplateFactory.restTemplate().exchange(url, HttpMethod.POST, request,
					TokenResponse.class);
			LOGGER.error("in validateHPFRequest  end calling token service ");

			if (responseTemp.getStatusCode().is2xxSuccessful()) {

				LOGGER.error("in validateHPFRequest  response from token service : {}",
						responseTemp.getBody().toString());
				tokenResponse = (TokenResponse) responseTemp.getBody();
				LOGGER.error("Return response from Token Service {}", tokenResponse.getAccess_token());

			} else {

				LOGGER.error("in validateHPFRequest  response from token service in ELSE : {}",
						responseTemp.getBody().toString());
				LOGGER.error(responseTemp.getBody().toString());
				LOGGER.error("Token validation failed");
			}
        } catch (ResourceAccessException e) {
            String msg = e.getMessage();
            LOGGER.error(msg, e);
            throw new RestException(HBEConstants.HPF_MS_BS_003, e.getMessage());
        } catch (Exception e) {
			String msg = e.getMessage();
            LOGGER.error(msg, e);
			throw new HBEMicroServiceException(HBEConstants.HPF_MS_TC_001, e.getMessage());
		}
		return tokenResponse;
	}

	public Response logInterfaceTransDetail(InterfaceTransDetails request, String token) {
		Response response = null;
		this.httpHeaders.set(MessageConstants.TOKEN, token);
		this.httpHeaders.set(MessageConstants.CONTENT_TYPE, MessageConstants.APPLICATION_JSON);

		HttpEntity<?> httpRequest = new HttpEntity<>(request, this.httpHeaders);
		ResponseEntity<?> responseTemp = null;
		try {
			String url = this.vlpDataService + "/interfacetransaction";
			LOGGER.error("Interface Tran Details calling  URL: {}", url);
			responseTemp = invokeClient(url, HttpMethod.POST, httpRequest, String.class);

			if (responseTemp.getStatusCode().is2xxSuccessful()) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.readValue(responseTemp.getBody().toString(), Response.class);

			} else {
                //Handle other Response Codes
                response = handleOtherResponseCodes(responseTemp);
            }
        } catch (ResourceAccessException e) {
			String msg = e.getMessage();
            LOGGER.error(msg, e);
			throw new RestException(HBEConstants.HPF_MS_BS_003, e.getMessage());
		} catch (Exception e) {
			String msg = e.getMessage();
            LOGGER.error(msg, e);
            throw new HBEMicroServiceException(HBEConstants.HPF_MS_BS001, e);
		}
		return response;

	}

	public Response logServerResult(VlpServResultRequest request, String token) {
		Response response = null;
		this.httpHeaders.set(MessageConstants.TOKEN, token);
		this.httpHeaders.set(MessageConstants.CONTENT_TYPE, MessageConstants.APPLICATION_JSON);

		HttpEntity<?> httpRequest = new HttpEntity<>(request, this.httpHeaders);
		ResponseEntity<?> responseTemp = null;
		try {
			String url = this.vlpDataService + "/serverresult";
			LOGGER.error("Server Result calling  URL: {}", url);
			responseTemp = invokeClient(url, HttpMethod.POST, httpRequest, String.class);

			if (responseTemp.getStatusCode().is2xxSuccessful()) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.readValue(responseTemp.getBody().toString(), Response.class);

            } else {
                //Handle other Response Codes
                response = handleOtherResponseCodes(responseTemp);
            }
        } catch (ResourceAccessException e) {
			String msg = e.getMessage();
            LOGGER.error(msg, e);
			throw new RestException(HBEConstants.HPF_MS_BS_003, e.getMessage());
		} catch (Exception e) {
			String msg = e.getMessage();
            LOGGER.error(msg, e);
			throw new HBEMicroServiceException(HBEConstants.HPF_MS_BS001, e.getMessage());
		}
		return response;

	}

    private Response handleOtherResponseCodes(ResponseEntity<?> responseTemp) {
        Response errorResp = null;
        try {
            LOGGER.error("Handle Other ResponseCodes");

            if (responseTemp.getStatusCode() == HttpStatus.BAD_REQUEST) {// handle 400
                errorResp = formatBadRequestCodes(responseTemp.getBody().toString());
            } else if (responseTemp.getStatusCode() == HttpStatus.UNAUTHORIZED) {// handle 401
                ObjectMapper mapper = new ObjectMapper();
                errorResp = mapper.readValue(responseTemp.getBody().toString(), Response.class);

            } else { //handle others basically 500 and 404

                ObjectMapper mapper = new ObjectMapper();
                errorResp = mapper.readValue(responseTemp.getBody().toString(), Response.class);
            }

        } catch (IOException e) {
            String msg = "Exception in Handle Other ResponseCodes: " + e.getMessage();
            LOGGER.error(msg, e);
        }
        return errorResp;
    }

    private Response formatBadRequestCodes(String responseString) {

        LOGGER.error("In formatOtherResponseCodes");

        Response response = new Response();
        JSONObject jsonObj;
        List<Error> errorList = new ArrayList<>();

        try {

            jsonObj = new JSONObject(responseString);
            response.setCode(jsonObj.getString("code"));
            response.setDescription(jsonObj.getString("description"));
            JSONArray errors = jsonObj.getJSONArray("errors");

            if (errors != null) {
                for (int i = 0; i < errors.length(); i++) {

                    JSONObject eachError = errors.getJSONObject(i);

                    LOGGER.info("In formatOtherResponseCodes  FieldName: {} and Message: {}",
                            eachError.getString("fieldName"), eachError.getString("message"));

                    Error e = new Error(eachError.getString("fieldName"), eachError.getString("message"));
                    errorList.add(e);

                    LOGGER.error("In formatOtherResponseCodes  FieldName: {} and Message: {}", e.getFieldName(),
                            e.getMessage());
                }
            }

            response.setErrors(errorList);

        } catch (JSONException e) {
            String msg = "Json Exception in formatOtherResponseCodes:" + e.getMessage();
            LOGGER.error(msg, e);
        }

        return response;
    }
}
