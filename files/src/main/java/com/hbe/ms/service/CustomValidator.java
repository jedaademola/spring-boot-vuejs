package com.hbe.ms.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hbe.ms.common.core.HBEConstants;
import com.hbe.ms.common.utils.CustomPatternSelector;
import com.hbe.ms.common.utils.HBEUtils;
import com.hbe.ms.common.vo.DHSID;
import com.hbe.ms.common.vo.Error;
import com.hbe.ms.integration.VLP1InputTransformer;
import com.hbe.ms.vo.VLP1Request;

@Component
public class CustomValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(VLP1InputTransformer.class);

    /**
     * This method is used to validate VLP1Request
     *
     * @param value
     * @param VLP1Request request
     * @return list of Error if any
     */
    public List<Error> validateRequest(VLP1Request request) {
        List<Error> errorList = new ArrayList<>();
        Error error = null;

        if (request.getNaturalizationNumber() != null) {
            // NatrOfCertDocumentID: NaturalizationNumber is required. AlienNumber is optional
            //required field check
            if (!HBEUtils.hasValidValue(request.getNaturalizationNumber(),
                    CustomPatternSelector.getRegPattern(HBEConstants.NATURALIZATIONNUMBER))) {
                error = new Error(HBEConstants.NATURALIZATIONNUMBER, "Invalid NaturalizationNumber");
                errorList.add(error);
            }
            //optional field checks
            error = HBEUtils.validateField(request.getAlienNumber(), HBEConstants.ALIENNUMBER, false);
            if (null != error)
                errorList.add(error);
            LOGGER.error("DHS Type: NatrOfCertDocumentID");
        }
        if (request.getCitizenshipNumber() != null) {
            // CertOfCitDocumentID: CitizenshipNumber is required. AlienNumber is optional
            //required field check
            if (!HBEUtils.hasValidValue(request.getCitizenshipNumber(),
                    CustomPatternSelector.getRegPattern(HBEConstants.CITIZENSHIPNUMBER))) {
                error = new Error(HBEConstants.CITIZENSHIPNUMBER, "Invalid CitizenshipNumber");
                errorList.add(error);
            }
            //optional field checks
            error = HBEUtils.validateField(request.getAlienNumber(), HBEConstants.ALIENNUMBER, false);
            if (null != error)
                errorList.add(error);

            LOGGER.error("DHS Type: CertOfCitDocumentID");

        }
        if (DHSID.IMG_DOC_TYP_1.equals(request.getDhsid())) {
            // I551DocumentID: AlienNumber and ReceiptNumber are required. DocExpirationDate is optional
            error = HBEUtils.validateField(request.getAlienNumber(), HBEConstants.ALIENNUMBER, true);
            if (null != error)
                errorList.add(error);

            error = HBEUtils.validateField(request.getReceiptNumber(), HBEConstants.RECEIPTNUMBER, true);
            if (null != error)
                errorList.add(error);
            // optional field checks
           // if (request.getDocExpirationDate() == null) {
             //   error = new Error(HBEConstants.DOCEXPIRATIONDATE, HBEConstants.DOCEXPIRATIONDATE_MSG);
               // errorList.add(error);
           // }

            LOGGER.error("DHS Type: I551DocumentID");

        }
        if (DHSID.IMG_DOC_TYP_2.equals(request.getDhsid())) {
            // I20DocumentID: SEVISID is required. I94Number, PassportCountry and
            // DocExpirationDate are optional
            // required field check
            error = HBEUtils.validateField(request.getSevisId(), HBEConstants.SEVISID, true);
            if (null != error)
                errorList.add(error);
            // optional field checks
            error = HBEUtils.validateField(request.getI94Number(), HBEConstants.I94NUMBER, false);
            if (null != error)
                errorList.add(error);
            error = HBEUtils.validateField(request.getPassportNumber(), HBEConstants.PASSPORTNUMBER, false);
            if (null != error)
                errorList.add(error);

            error = HBEUtils.validateField(request.getCountryOfIssuance(), HBEConstants.COUNTRYOFISSUANCE, false);
            if (null != error)
                errorList.add(error); 
            LOGGER.error("DHS Type: I20DocumentID");

        }
        if (DHSID.IMG_DOC_TYP_3.equals(request.getDhsid())) {
            // I327DocumentID: AlienNumber is required. DocExpirationDate is optional
            // required field check
            error = HBEUtils.validateField(request.getAlienNumber(), HBEConstants.ALIENNUMBER, true);
            if (null != error)
                errorList.add(error);
            // optional field checks
           // if (request.getDocExpirationDate() == null) {
             //   error = new Error(HBEConstants.DOCEXPIRATIONDATE, HBEConstants.DOCEXPIRATIONDATE_MSG);
              //  errorList.add(error);
            //}
            LOGGER.error("DHS Type: I327DocumentID");

        }
        if (DHSID.IMG_DOC_TYP_4.equals(request.getDhsid())) {
            // I571DocumentID5Type: AlienNumber is required. DocExpirationDate is optional
            error = HBEUtils.validateField(request.getAlienNumber(), HBEConstants.ALIENNUMBER, true);
            if (null != error)
                errorList.add(error);
           LOGGER.error("DHS Type: I571DocumentID5Type");

        }
        if (DHSID.IMG_DOC_TYP_6.equals(request.getDhsid())) {
            // DS2019DocumentID: SEVISID is required. I94Number, PassportCountry and
            // DocExpirationDate are optional
            error = HBEUtils.validateField(request.getSevisId(), HBEConstants.SEVISID, true);
            if (null != error)
                errorList.add(error);
            error = HBEUtils.validateField(request.getPassportNumber(), HBEConstants.PASSPORTNUMBER, false);
            if (null != error)
                errorList.add(error);

            error = HBEUtils.validateField(request.getCountryOfIssuance(), HBEConstants.COUNTRYOFISSUANCE, false);
            if (null != error)
                errorList.add(error); 

            error = HBEUtils.validateField(request.getI94Number(), HBEConstants.I94NUMBER, false);
            if (null != error)
                errorList.add(error);
            
           LOGGER.error("DHS Type: DS2019DocumentID");

        }
        if (DHSID.IMG_DOC_TYP_7.equals(request.getDhsid())) {
            // OtherCase1DocumentID: AlienNumber and DocDescReq are required.
            // PassportCountry and DocExpirationDate are optional
            // required field check
            error = HBEUtils.validateField(request.getAlienNumber(), HBEConstants.ALIENNUMBER, true);
            if (null != error)
                errorList.add(error);

            
            if (request.getDocDescReq() == null) {
                error = new Error(HBEConstants.DOCDESCREQ, HBEConstants.DOCDESCREQE_MSG);
                errorList.add(error);
            }
          
            error = HBEUtils.validateField(request.getPassportNumber(), HBEConstants.PASSPORTNUMBER, false);
            if (null != error)
                errorList.add(error);

            error = HBEUtils.validateField(request.getCountryOfIssuance(), HBEConstants.COUNTRYOFISSUANCE, false);
            if (null != error)
                errorList.add(error); 

            LOGGER.error("DHS Type: OtherCase1DocumentID");

        }
        if (DHSID.IMG_DOC_TYP_8.equals(request.getDhsid())) {
            // I766DocumentID: AlienNumber, ReceiptNumber and DocExpirationDate are required
            // required field check
            error = HBEUtils.validateField(request.getAlienNumber(), HBEConstants.ALIENNUMBER, true);
            if (null != error)
                errorList.add(error);

            error = HBEUtils.validateField(request.getReceiptNumber(), HBEConstants.RECEIPTNUMBER, true);
            if (null != error)
                errorList.add(error);

            if (request.getDocExpirationDate() == null) {
                error = new Error(HBEConstants.DOCEXPIRATIONDATE, HBEConstants.DOCEXPIRATIONDATE_MSG);
                errorList.add(error);
            }

            LOGGER.error("DHS Type: I766DocumentID");

        }
        if (DHSID.IMG_DOC_TYP_9.equals(request.getDhsid())) {
            // TempI551DocumentID: AlienNumber is required. PassportCountry and
            // DocExpirationDate are optional
            // required field check
            error = HBEUtils.validateField(request.getAlienNumber(), HBEConstants.ALIENNUMBER, true);
            if (null != error)
                errorList.add(error);
           
            error = HBEUtils.validateField(request.getPassportNumber(), HBEConstants.PASSPORTNUMBER, false);
            if (null != error)
                errorList.add(error);

            error = HBEUtils.validateField(request.getCountryOfIssuance(), HBEConstants.COUNTRYOFISSUANCE, false);
            if (null != error)
                errorList.add(error);          

            LOGGER.error("DHS Type: TempI551DocumentID");
        }
        if (DHSID.IMG_DOC_TYP_10.equals(request.getDhsid())) {

            if (null != request.getPassportNumber()) {
                // I94UnexpForeignPassportDocumentID: I94Number, PassportNumber,
                // CountryOfIssuance and DocExpirationDate are required. VisaNumber and SEVISID
                // are optional
                // required field check
                error = HBEUtils.validateField(request.getI94Number(), HBEConstants.I94NUMBER, true);
                if (null != error)
                    errorList.add(error);

                error = HBEUtils.validateField(request.getPassportNumber(), HBEConstants.PASSPORTNUMBER, true);
                if (null != error)
                    errorList.add(error);

                error = HBEUtils.validateField(request.getCountryOfIssuance(), HBEConstants.COUNTRYOFISSUANCE, true);
                if (null != error)
                    errorList.add(error);

                if (request.getPassportExpirationDate() == null) {
                    error = new Error(HBEConstants.PASSPORTXPIRATIONDATE, HBEConstants.PASSPORTXPIRATIONDATE_MSG);
                    errorList.add(error);
                }

                // optional field checks
                error = HBEUtils.validateField(request.getVisaNumber(), HBEConstants.VISANNUMBER, false);
                if (null != error)
                    errorList.add(error);
                error = HBEUtils.validateField(request.getSevisId(), HBEConstants.SEVISID, false);
                if (null != error)
                    errorList.add(error);

                LOGGER.error("DHS Type: I94UnexpForeignPassportDocumentID");

            } else {
                // I94DocumentID: I94Number is required. SEVISID and DocExpirationDate are
                // optional
                // required field check
                error = HBEUtils.validateField(request.getI94Number(), HBEConstants.I94NUMBER, true);
                if (null != error)
                    errorList.add(error);
                
                error = HBEUtils.validateField(request.getSevisId(), HBEConstants.SEVISID, false);
                if (null != error)
                    errorList.add(error);

                LOGGER.error("DHS Type: I94DocumentID");

            }
        }
        if (DHSID.IMG_DOC_TYP_11.equals(request.getDhsid())) {
            // MacReadI551DocumentID: AlienNumber, PassportNumber and CountryOfIssuance are
            // required. VisaNumber and DocExpirationDate are optional
            // required field check
            error = HBEUtils.validateField(request.getAlienNumber(), HBEConstants.ALIENNUMBER, true);
            if (null != error)
                errorList.add(error);

            error = HBEUtils.validateField(request.getCountryOfIssuance(), HBEConstants.COUNTRYOFISSUANCE, true);
            if (null != error)
                errorList.add(error);

            error = HBEUtils.validateField(request.getPassportNumber(), HBEConstants.PASSPORTNUMBER, true);
            if (null != error)
                errorList.add(error);
         
            error = HBEUtils.validateField(request.getVisaNumber(), HBEConstants.VISANNUMBER, false);
            if (null != error)
                errorList.add(error);

            LOGGER.error("DHS Type: MacReadI551DocumentID");

        }
        if (request.getDhsid() == null && request.getPassportNumber() != null) {
            // UnexpForeignPassportDocumentID: PassportNumber, CountryOfIssuance and
            // DocExpirationDate are required. I94Number and SEVISID are optional
            // required field check
            error = HBEUtils.validateField(request.getPassportNumber(), HBEConstants.PASSPORTNUMBER, true);
            if (null != error)
                errorList.add(error);

            error = HBEUtils.validateField(request.getCountryOfIssuance(), HBEConstants.COUNTRYOFISSUANCE, true);
            if (null != error)
                errorList.add(error);

            if (request.getPassportExpirationDate() == null) {
                error = new Error(HBEConstants.PASSPORTXPIRATIONDATE, HBEConstants.PASSPORTXPIRATIONDATE_MSG);
                errorList.add(error);
            }

            // optional field checks
            error = HBEUtils.validateField(request.getI94Number(), HBEConstants.I94NUMBER, false);
            if (null != error)
                errorList.add(error);
            error = HBEUtils.validateField(request.getSevisId(), HBEConstants.SEVISID, false);
            if (null != error)
                errorList.add(error);

            LOGGER.error("DHS Type: UnexpForeignPassportDocumentID");

        }
        LOGGER.error("returning to Controller");
        return errorList;
    }

}
