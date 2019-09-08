package com.hbe.ms.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbe.ms.common.core.HBEConstants;
import com.hbe.ms.common.core.MessageConstants;
import com.hbe.ms.common.exception.BadCredentialsException;
import com.hbe.ms.common.exception.BadRequestException;
import com.hbe.ms.common.service.RestCommonService;
import com.hbe.ms.common.vo.Error;
import com.hbe.ms.common.vo.Response;
import com.hbe.ms.service.CustomValidator;
import com.hbe.ms.service.VLP1Service;
import com.hbe.ms.vo.VLP1Request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/v1/vlp1")
@Api("Endpoint for SOA call for Initial Verification Request")
public class VLP1Controller {
	@Autowired
	private VLP1Service vlp1Service;

    private static final Logger LOGGER = LoggerFactory.getLogger(VLP1Controller.class);
    
	@Autowired
	private RestCommonService restCommonService;
    @Autowired
    CustomValidator customValidator;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("VLP 1 service call to SOA")
    public ResponseEntity<?> createPerson(
            @ApiParam("Initial Verification Request for a new Federal Person Lawful Detail to be created.")
            @Valid @RequestBody VLP1Request request,
            @RequestHeader("TOKEN") String token) {

        LOGGER.error("in VLP1 VLP1Controller.createPerson");

        if (token == null || token.isEmpty()) {
            throw new BadRequestException(HBEConstants.HPF_MS_TC_001, "Invalid token");
        }

        // Validating Token Service..
        Response validateToken = restCommonService.validateToken(token);
        if (null != validateToken && HBEConstants.HTTP_200 != validateToken.getCode()) {
            LOGGER.error(HBEConstants.TOKEN_NOTVALIDTED);
            throw new BadCredentialsException(HBEConstants.HPF_MS_TC_001, MessageConstants.TOKENNOTVALIDATE);
        }
        
        LOGGER.error("in VLP1 VLP1Controller.createPerson: custom validation");
        List<Error> errorList = customValidator.validateRequest(request);
        if (!errorList.isEmpty()) {
            LOGGER.error("in VLP1 VLP1Controller.createPerson: custom validation failure");
            Response customError = new Response();
            customError.setCode(HBEConstants.HPF_MS_TC_001);
            customError.setDescription("Input Validation failed");
            customError.setErrors(errorList);

            return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
        }

        LOGGER.error("Token is validated by Token Service at VLP1");
      
        return new ResponseEntity<>(vlp1Service.process(request, token), HttpStatus.OK);
	}

}
