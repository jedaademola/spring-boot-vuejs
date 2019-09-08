package com.hbe.ms.service;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.hbe.ms.common.core.HBEConstants;
import com.hbe.ms.common.service.AsyncCommonService;
import com.hbe.ms.common.service.RestCommonService;
import com.hbe.ms.common.utils.HBEUtils;
import com.hbe.ms.common.vo.InterfaceTransDetails;
import com.hbe.ms.common.vo.VlpServErrorLog;
import com.hbe.ms.common.vo.VlpServResultRequest;
import com.hbe.ms.integration.VLP1InputTransformer;
import com.hbe.ms.integration.VLP1OutputTransformer;
import com.hbe.ms.vo.VLP1Request;
import com.hbe.ms.vo.VLP1Response;

import brave.Tracer;
import gov.hhs.cms.dsh.services.ee.vlp.wsdl.VerifyLawfulPresenceServiceV37Stub;
import gov.hhs.cms.dsh.sim.ee.vlp.InitialVerificationIndividualResponseType;
import gov.hhs.cms.dsh.sim.ee.vlp.InitialVerificationRequestType;

@Service
public class VLP1Service {

	@Autowired
	private RestCommonService restCommonService;

	@Autowired
	private AsyncCommonService asyncCommonService;

	@Autowired
	private VLP1InputTransformer inputTransformer;

	@Autowired
	private VLP1OutputTransformer outputTransformer;
	@Autowired
	private Tracer tracer;

	private static final Logger LOGGER = LoggerFactory.getLogger(VLP1Service.class);

    @Value("${soap.lawfulpresence.url}")
    private String soapUrl;

	@Value("${archival.month}")
	private int archivalMonth;
	
	private Marshaller responseMarshaller;
	private Marshaller requestMarshaller;
	//use since no XMLRootElement
    private QName qName = new QName("gov.hhs.cms.dsh.sim.ee.vlp", "InitialVerificationIndividualResponse");
    private QName requestQName = new QName("gov.hhs.cms.dsh.sim.ee.vlp", "InitialVerificationRequest");
     
    @PostConstruct
    public void init() {
        LOGGER.error("property data being loaded (in VLP1Service) is ### soapUrl::: {}", soapUrl);
        
        JAXBContext responseContext;
        JAXBContext context;
		try {
			responseContext = JAXBContext.newInstance(InitialVerificationIndividualResponseType.class);
			responseMarshaller = responseContext.createMarshaller();
			context = JAXBContext.newInstance(InitialVerificationRequestType.class);
		    requestMarshaller = context.createMarshaller();
		} catch (JAXBException e) {
			LOGGER.error("Exception happened when Marshaller and context creation happens");
		}
        
     }
    
    /**This method accepts JSON request and calls the FedHub service 
	 * Once response is received , the response is processed and sent back as 
	 * JSON response
	 * 
	 * @param request
	 * @param token
	 * @return
	 */
	public VLP1Response process(VLP1Request request, String token)  {
				
		StopWatch stopWatch=new StopWatch();
		stopWatch.start("VLP1 service invocation process() method");
		
		VLP1Response response = null;
		VerifyLawfulPresenceServiceV37Stub stub = null;
		InitialVerificationRequestType initialVerificationRequest = null;

        LOGGER.info("Entering process in VLP1service");
		String jsonRequest = HBEUtils.convertToJsonString(request);
		String jsonResponse = null;
		LOGGER.info("VLP1 service Json request: {}", jsonRequest);
		String errorMsg = null;
		String errorCode = null;
		List<VlpServErrorLog> vlpServErrorLogs =null;

		String vlp1XmlRequestSTR = "";
		InitialVerificationIndividualResponseType initialVerificationResponse = null;
		try {
		
			initialVerificationRequest = inputTransformer.transformInput(request);
			LOGGER.error("VLP1 inputTransformer.transformInput completed");
			vlp1XmlRequestSTR = convertRequestToXMLString(initialVerificationRequest); 
			LOGGER.error("VLP1 vlp1XmlRequestSTR creation completed");
			
			LOGGER.error("CMS url (in VLP1Service) is ### soapUrl::: {}", soapUrl);
			LOGGER.error("START LOADING Stub");
	        stub = new VerifyLawfulPresenceServiceV37Stub(soapUrl);
	        LOGGER.error("END LOADING Stub");
	    
		    initialVerificationResponse = stub.initialVerificationV37(initialVerificationRequest)
					.getInitialVerificationResponseSet().getInitialVerificationIndividualResponse().get(0);

			LOGGER.error("VLP1 Response received successfully.");
			
			if (null != initialVerificationResponse) {
				response = outputTransformer.transformOutput(initialVerificationResponse);
				LOGGER.error("Processed the VLP1 response back the response from CMS");
			}
		} catch (org.apache.axis2.AxisFault e) {
			errorMsg = "Received error while transforming the XML request/response";
			LOGGER.error(errorMsg, e);
			//check if CMS service is not available, then set errorCode = HPF_MS_BS_002
			errorCode = handleCMSException(e);
			response = buildCustomErrorResponse(errorMsg, errorCode, e);
			vlpServErrorLogs = prepareVlpServErrorData(errorMsg, errorCode, e,HBEConstants.VLP1);
		} catch (RemoteException e) {			
			errorMsg = "Received error while accesing the FedHub service endpoint in VLP1 service";
			LOGGER.error(errorMsg, e);
			errorCode = HBEConstants.HPF_MS_BS001;
			response = buildCustomErrorResponse(errorMsg, errorCode, e);
			vlpServErrorLogs = prepareVlpServErrorData(errorMsg, errorCode, e, HBEConstants.VLP1);	
		} catch (Exception e) {
			errorMsg = "Received exception in VLP1 service process method in VLP1 service";
			LOGGER.error(errorMsg, e);
			errorCode = HBEConstants.HPF_MS_BS001;
			response = buildCustomErrorResponse(errorMsg, errorCode, e);
			vlpServErrorLogs = prepareVlpServErrorData(errorMsg, errorCode, e,HBEConstants.VLP1);				
			
		}finally {

			String xmlResponse = convertResponseToXMLString(initialVerificationResponse);
			LOGGER.info("Request from VLP1 Service {} ", vlp1XmlRequestSTR);
			LOGGER.info("Response from VLP1 Service {} ", xmlResponse);
			LOGGER.error("Logging response to Interfacing transaction table");
			saveInterfaceTransDetail(vlp1XmlRequestSTR, xmlResponse, token, HBEConstants.VLP1);
			LOGGER.info("Logging Completed response to Interfacing transaction table");
			
			jsonResponse = HBEUtils.convertToJsonString(response);	
			LOGGER.info("VLP 1 service Json response: {}", jsonResponse);
			logVlpServResultRequest(jsonRequest, jsonResponse, token, HBEConstants.VLP1, vlpServErrorLogs);
			//Clean up resources
			if(stub!=null){
                try{
                	stub._getServiceClient().cleanupTransport();//timeout waiting for connection:Release resources allocated 
                	//by the transport during the last service invocation.
        			stub._getServiceClient().cleanup();//Read timed out: Cleanup associated resources/Clean up configuration created with this client.
                }catch(Exception e){
                	errorMsg = "Received exception in VLP1 service:Clean up resources";
        			LOGGER.error(errorMsg, e);
                }
            }
								
		}	
		stopWatch.stop();
		LOGGER.error("VLP1 service Overall invocation Time : {}", stopWatch.getTotalTimeMillis());
		LOGGER.error("Returning VLP1 Response back");
		return response;

	}

	/**
	 * check for connection time out or CMS service down
	 *
	 * @param AxisFault e
	 * @return
	 */

	private String handleCMSException(org.apache.axis2.AxisFault e) {
		String errorCode = null;
        if (null != e.getMessage() &&
                (e.getMessage().contains(HBEConstants.CONNECTION_TIMED_OUT)
                        || e.getMessage().contains(HBEConstants.TRANSPORT_ERROR))
                || e.getMessage().contains(HBEConstants.CONNECTION_REFUSED))
			errorCode = HBEConstants.HPF_MS_BS_002;
		else
			errorCode = HBEConstants.HPF_MS_BS_003;

		return errorCode;
	}
	/**This method saves the XML request and response in VLP transaction detail table.
	 * 
	 * @param request
	 * @param response
	 * @param token
	 * @param vlpServiceName
	 * 
	 * @return
	 */
	private void saveInterfaceTransDetail(String xmlRequest, String xmlResponse, String token, String vlpServiceName) {
				
		try {
			InterfaceTransDetails log = new InterfaceTransDetails();
			log.setRequestPayLoad(xmlRequest);
			log.setResponsePayLoad(xmlResponse);
			log.setCreatedDate(HBEUtils.currentTimestamp());
			log.setUuid(tracer.currentSpan().context().traceIdString());
			log.setStatusCode(HBEConstants.HTTP_STATUS_200);
			log.setArchivalDate(HBEUtils.addMonths(HBEUtils.currentTimestamp(), archivalMonth));
			log.setVlpServerName(vlpServiceName);
			restCommonService.logInterfaceTransDetail(log, token);
		} catch (Exception e) {
			String msg = "Exception in saveInterfaceTransDetail Vlp 1 Servive";
			LOGGER.error(msg, e);
		}
		
	}
	/**This method builds the error response in the event exception happens
	 * 
	 * @param msg
	 * @param code
	 * @param e
	 * @return
	 */
	private VLP1Response buildCustomErrorResponse(String msg, String code, Exception e) {
		VLP1Response msresponse = new VLP1Response();
		msresponse.setErrorCodeTx(code);
		msresponse.setErrorMsgTx(msg);
		msresponse.setErrorSourceTx(HBEConstants.VLP1);

		String[] strArr = ExceptionUtils.getStackFrames(e);
		StringBuilder sb = new StringBuilder();
		for (int position = 0; position < 5; position++) {
			sb.append(strArr[position]);
		}
		msresponse.setErrorStackTraceTx(sb.toString());
		msresponse.setResponseCode(code);
		return msresponse;
	}
	
	/**This method logs the VLP Service JSON Request and JSON Response
	 * 
	 * @param requestJson
	 * @param responseJson
	 * @param token
	 * @param sourceName
	 * @param errorstackTrace
	 * @param errorCode
	 */
	public void logVlpServResultRequest(String requestJson, String responseJson, String token, String sourceName,
			 List<VlpServErrorLog> vlpServErrorLogs) {
		try {

			VlpServResultRequest log = new VlpServResultRequest();
			log.setRequestPayLoad(requestJson);
			log.setResponsePayLoad(responseJson);
			log.setCreatedDate(HBEUtils.currentTimestamp());
			log.setUuid(tracer.currentSpan().context().traceIdString());
			log.setArchivalDate(HBEUtils.addMonths(HBEUtils.currentTimestamp(), archivalMonth));

			log.setVersionNumber(BigDecimal.ONE);
			log.setServerName(sourceName);
			if(vlpServErrorLogs != null && !vlpServErrorLogs.isEmpty()) {
				log.setVlpServErrorLogs(vlpServErrorLogs);
			}
			asyncCommonService.logServerResult(log, token);

		} catch (Exception exc) {
			String msg = "Exception in logVlpServResultRequest Vlp in VLP 1 Service";
			LOGGER.error(msg, exc);
		}

	}
	
	/**
	 * This method logs the error data when the exception happens in the VLP Service
	 * invocation in the Error Log table for triaging purpose
	 * 
	 * @param errorstackTrace
	 * @param errorCode
	 * @param sourceName
	 * @return
	 */
	private List<VlpServErrorLog> prepareVlpServErrorData(String errorMessage, String errorCode, Throwable exception, String sourceName) {
        List<VlpServErrorLog> vlpServErrorLogs = new ArrayList<>();
        
        VlpServErrorLog vlpError = new VlpServErrorLog();        
        vlpError.setErrorCode(errorCode);
        vlpError.setServerName(sourceName);
        vlpError.setErrorStackTrace(StringUtils.substring(ExceptionUtils.getStackTrace(exception), HBEConstants.INT_ZERO, HBEConstants.INT_TWO_THOUSAND));
        vlpError.setServerErrorMsg(StringUtils.substring(errorMessage, HBEConstants.INT_ZERO, HBEConstants.INT_TWO_HUNDRED));
		vlpError.setArchivalDate(HBEUtils.addMonths(HBEUtils.currentTimestamp(), archivalMonth));
        vlpError.setCreatedDate(HBEUtils.currentTimestamp());
        vlpError.setUuid(tracer.currentSpan().context().traceIdString());
        vlpServErrorLogs.add(vlpError);

        return vlpServErrorLogs;

    }
	/** This method is used to pretty print the XML Request
	 * 
	 * @param initialVerificationRequestType
	 * @return
	 */
	private String convertRequestToXMLString(InitialVerificationRequestType initialVerificationRequestType) {
		StopWatch stopWatch=new StopWatch();
		stopWatch.start("VLP1 service convertRequestToXMLString() method");
        try {
        	if (initialVerificationRequestType != null) {
	 	        requestMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
	 	        JAXBElement<InitialVerificationRequestType> root = new JAXBElement<>(requestQName, InitialVerificationRequestType.class, initialVerificationRequestType);
	 	        StringWriter sw = new StringWriter();
	 	        requestMarshaller.marshal(root, sw);
	 	        
	 			stopWatch.stop();
	 			LOGGER.error("VLP1 :: convertRequestToXMLString API invocation Time : {}", stopWatch.getTotalTimeMillis());
	 	        return sw.toString();
        	}

        } catch (JAXBException  e) {
			LOGGER.error("Error in convertRequestToXMLString: Unable to convert XML Object '%s' to an XML String: " + e.getMessage(), e);
		}
        return null;
	}
	
	/** This method is used to pretty print the XML Response
	 * 
	 * @param initialVerificationIndividualResponseType
	 * @return
	 */
	private String convertResponseToXMLString(InitialVerificationIndividualResponseType initialVerificationIndividualResponseType) {
		StopWatch stopWatch=new StopWatch();
		stopWatch.start("VLP1 service convertResponseToXMLString() method");
        try {
        	if (initialVerificationIndividualResponseType != null) {
	 	        responseMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
				JAXBElement<InitialVerificationIndividualResponseType> root = new JAXBElement<>(qName,
						InitialVerificationIndividualResponseType.class, initialVerificationIndividualResponseType);

	 	        StringWriter sw = new StringWriter();
	 	        responseMarshaller.marshal(root, sw);
	 	        
	 			stopWatch.stop();
	 			LOGGER.error("VLP1 :: convertResponseToXMLString API invocation Time : {}", stopWatch.getTotalTimeMillis());
	 	        
	 	        return sw.toString();
        	}

        } catch (JAXBException  e) {
			LOGGER.error("Error in convertResponseToXMLString: Unable to convert XML Object '%s' to an XML String: " + e.getMessage(), e);
		}
        return null;
	}

}
