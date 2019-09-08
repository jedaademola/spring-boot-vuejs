package com.hbe.ms.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hbe.ms.common.core.HBEConstants;
import com.hbe.ms.common.exception.HBEMicroServiceException;
import com.hbe.ms.common.utils.HBEUtils;
import com.hbe.ms.common.vo.ArrayOfErrorResponseMetaData;
import com.hbe.ms.common.vo.ErrorResponseMetaData;
import com.hbe.ms.common.vo.ResponseMetaData;
import com.hbe.ms.vo.VLP1Response;

import gov.hhs.cms.dsh.sim.ee.vlp.ErrorResponseMetadataType;
import gov.hhs.cms.dsh.sim.ee.vlp.InitialVerificationIndividualResponseType;

@Component
public class VLP1OutputTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(VLP1OutputTransformer.class);
	
	public VLP1Response transformOutput(InitialVerificationIndividualResponseType soapResponse) {
		VLP1Response response = new VLP1Response();

		try {

            //ResponseMetaData
            ResponseMetaData responseMetaData = new ResponseMetaData();
            responseMetaData.setResponseCode(soapResponse.getResponseMetadata().getResponseCode());
            responseMetaData.setResponseDescriptionText(soapResponse.getResponseMetadata().getResponseDescriptionText());
            responseMetaData.settDSResponseDescriptionText(soapResponse.getResponseMetadata().getTDSResponseDescriptionText());
            
			response.setResponseCode(soapResponse.getResponseMetadata().getResponseCode());
			response.setResponseDescription(soapResponse.getResponseMetadata().getResponseDescriptionText());

			response.setTdsResponseDescriptionText(soapResponse.getResponseMetadata().getTDSResponseDescriptionText());

			if (null != soapResponse.getArrayOfErrorResponseMetadata()) {
				response.setArrayOfErrorResponseMetaData(this.prepareErrorResponseMedatData(soapResponse));
			}
			response.setLpVerfdCd(soapResponse.getLawfulPresenceVerifiedCode());

			if (null != soapResponse.getInitialVerificationIndividualResponseSet()) {

				response.setCaseNumber(soapResponse.getInitialVerificationIndividualResponseSet().getCaseNumber());
				if (null != soapResponse.getInitialVerificationIndividualResponseSet().getNonCitEntryDate()) {
					response.setNonCitEntryDate(HBEUtils.convertXmlGregorianToSTRTimeStampFormat(
							soapResponse.getInitialVerificationIndividualResponseSet().getNonCitEntryDate()));
				}

				response.setNonCitCoaCode(soapResponse.getInitialVerificationIndividualResponseSet().getNonCitCoaCode());
				if (null != soapResponse.getInitialVerificationIndividualResponseSet().getEligStatementCd()) {
					response.setEligibilityStatementCode(
							soapResponse.getInitialVerificationIndividualResponseSet().getEligStatementCd().toString());
				}
				response.setEligStatementTxt(
						soapResponse.getInitialVerificationIndividualResponseSet().getEligStatementTxt());
                response.setIavTypeCode(soapResponse.getInitialVerificationIndividualResponseSet().getIAVTypeCode());
                response.setIavTypeText(soapResponse.getInitialVerificationIndividualResponseSet().getIAVTypeTxt());
				response.setWebServSftwrVer(
						soapResponse.getInitialVerificationIndividualResponseSet().getWebServSftwrVer());
				if (null != soapResponse.getInitialVerificationIndividualResponseSet().getGrantDate()) {
					response.setGrantDate(HBEUtils.convertXmlGregorianToSTRTimeStampFormat(
							soapResponse.getInitialVerificationIndividualResponseSet().getGrantDate()));
				}
				response.setGrantDateReasonCd(
						soapResponse.getInitialVerificationIndividualResponseSet().getGrantDateReasonCd());
				response.setUsCitizenCode(soapResponse.getInitialVerificationIndividualResponseSet().getUSCitizenCode());
				response.setAgencyAction(soapResponse.getInitialVerificationIndividualResponseSet().getAgencyAction());
				response.setFiveYearBarApplyCode(
						soapResponse.getInitialVerificationIndividualResponseSet().getFiveYearBarApplyCode());
				response.setQualifiedNonCitizenCode(
						soapResponse.getInitialVerificationIndividualResponseSet().getQualifiedNonCitizenCode());
				response.setFiveYearBarMetCode(
						soapResponse.getInitialVerificationIndividualResponseSet().getFiveYearBarMetCode());
				
				response.setNonCitEadsExpireDate(HBEUtils.convertXmlGregorianToTimeStamp(
						soapResponse.getInitialVerificationIndividualResponseSet().getNonCitEadsExpireDate()));
			}
		} catch (Exception e) {
			String msg = e.getMessage();
			LOGGER.error(msg, new Throwable(e));
			throw new HBEMicroServiceException(HBEConstants.HPF_MS_BS001, e.getMessage());
		} 
		return response;
	}


	private ArrayOfErrorResponseMetaData prepareErrorResponseMedatData(
			InitialVerificationIndividualResponseType soapResponse) {
		ArrayOfErrorResponseMetaData arrayOfErrorResponseMetaData = new ArrayOfErrorResponseMetaData();
		List<ErrorResponseMetaData> errorResponseMetaData = new ArrayList<>();

		if (null != soapResponse.getArrayOfErrorResponseMetadata().getErrorResponseMetadata()) {
			for (ErrorResponseMetadataType error : soapResponse.getArrayOfErrorResponseMetadata()
					.getErrorResponseMetadata()) {
				ErrorResponseMetaData err = new ErrorResponseMetaData();
				err.setErrorResponseCode(error.getErrorResponseCode());
				err.setErrorResponseDescriptionText(error.getErrorResponseDescriptionText());
				err.setErrorTDSResponseDescriptionText(error.getErrorTDSResponseDescriptionText());
				errorResponseMetaData.add(err);
			}

			arrayOfErrorResponseMetaData.setErrorResponseMetaData(errorResponseMetaData);
		}
		return arrayOfErrorResponseMetaData;
	}

}
