package com.hbe.ms.integration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hbe.ms.common.core.HBEConstants;
import com.hbe.ms.common.utils.HBEUtils;
import com.hbe.ms.common.vo.DHSID;
import com.hbe.ms.vo.VLP1Request;
import gov.hhs.cms.dsh.sim.ee.vlp.CertOfCitDocumentID23Type;
import gov.hhs.cms.dsh.sim.ee.vlp.DHSIDType;
import gov.hhs.cms.dsh.sim.ee.vlp.DS2019DocumentID27Type;
import gov.hhs.cms.dsh.sim.ee.vlp.I20DocumentID26Type;
import gov.hhs.cms.dsh.sim.ee.vlp.I327DocumentID3Type;
import gov.hhs.cms.dsh.sim.ee.vlp.I551DocumentID4Type;
import gov.hhs.cms.dsh.sim.ee.vlp.I571DocumentID5Type;
import gov.hhs.cms.dsh.sim.ee.vlp.I766DocumentID9Type;
import gov.hhs.cms.dsh.sim.ee.vlp.I94DocumentID2Type;
import gov.hhs.cms.dsh.sim.ee.vlp.I94UnexpForeignPassportDocumentID10Type;
import gov.hhs.cms.dsh.sim.ee.vlp.InitialVerificationRequestSetType;
import gov.hhs.cms.dsh.sim.ee.vlp.InitialVerificationRequestType;
import gov.hhs.cms.dsh.sim.ee.vlp.MacReadI551DocumentID22Type;
import gov.hhs.cms.dsh.sim.ee.vlp.NatrOfCertDocumentID20Type;
import gov.hhs.cms.dsh.sim.ee.vlp.ObjectFactory;
import gov.hhs.cms.dsh.sim.ee.vlp.OtherCase1DocumentID1Type;
import gov.hhs.cms.dsh.sim.ee.vlp.PassportCountryType;
import gov.hhs.cms.dsh.sim.ee.vlp.TempI551DocumentID21Type;
import gov.hhs.cms.dsh.sim.ee.vlp.UnexpForeignPassportDocumentID30Type;


@Component
public class VLP1InputTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(VLP1InputTransformer.class);
	
	@Value("${vlp1.CasePOCFullName}")
	private String casePOCFullName;

	@Value("${vlp1.CasePOCPhoneNumber}")
	private String casePOCPhoneNumber;

	@Value("${vlp1.CasePOCPhoneNumberExtension}")
	private String casePOCPhoneNumberExt;

	public InitialVerificationRequestType transformInput(VLP1Request request) {
		InitialVerificationRequestType initialVerificationRequestType = new InitialVerificationRequestType();
		InitialVerificationRequestSetType initialVerificationRequestSetType = new InitialVerificationRequestSetType();

		initialVerificationRequestSetType.setDHSID(this.populateDHSIDType(request));
		initialVerificationRequestSetType.setFirstName(request.getFirstName());
		if (request.getMiddleName() != null) {
		initialVerificationRequestSetType.setMiddleName(request.getMiddleName());
		}
		initialVerificationRequestSetType.setLastName(request.getLastName());
		initialVerificationRequestSetType.setDateOfBirth(HBEUtils.toXmlGregorianCalendarFormat(request.getDateOfBirth()));
		LOGGER.info("XMLGregorian calender format2:: {} ",HBEUtils.toXmlGregorianCalendarFormat(request.getDateOfBirth()));
		initialVerificationRequestSetType.setFiveYearBarApplicabilityIndicator(true);
		initialVerificationRequestSetType.setRequestGrantDateIndicator(request.isRequestGrantDateIndicator());
		
		initialVerificationRequestSetType.setCasePOCFullName(casePOCFullName);
		initialVerificationRequestSetType.setCasePOCPhoneNumber(casePOCPhoneNumber);
		if (casePOCPhoneNumberExt != null && !HBEConstants.CASEPOC_NO_EXTN.equalsIgnoreCase(casePOCPhoneNumberExt)) {
		initialVerificationRequestSetType.setCasePOCPhoneNumberExtension(casePOCPhoneNumberExt);
		}
		initialVerificationRequestType.getInitialVerificationRequestSet().add(initialVerificationRequestSetType);
		return initialVerificationRequestType;
	}

	private DHSIDType populateDHSIDType(VLP1Request request) {
		DHSIDType dhsidType = new DHSIDType();

		if (request.getNaturalizationNumber() != null) {
			// NatrOfCertDocumentID: NaturalizationNumber is required. AlienNumber is optional
			NatrOfCertDocumentID20Type natrOfCertDocumentID20Type = new NatrOfCertDocumentID20Type();
			if (request.getAlienNumber() != null) {
				natrOfCertDocumentID20Type.setAlienNumber(request.getAlienNumber());
			} else {
				natrOfCertDocumentID20Type.setAlienNumber("999999999");
			}
			natrOfCertDocumentID20Type.setNaturalizationNumber(request.getNaturalizationNumber());
			dhsidType.setNatrOfCertDocumentID(natrOfCertDocumentID20Type);
            LOGGER.error("DHS Type: NatrOfCertDocumentID");

		} else if (request.getCitizenshipNumber() != null) {
			// CertOfCitDocumentID: CitizenshipNumber is required. AlienNumber is optional
			CertOfCitDocumentID23Type certOfCitDocumentID23Type = new CertOfCitDocumentID23Type();
			if (request.getAlienNumber() != null) {
				certOfCitDocumentID23Type.setAlienNumber(request.getAlienNumber());
			} else {
				certOfCitDocumentID23Type.setAlienNumber("999999999");
			}
			certOfCitDocumentID23Type.setCitizenshipNumber(request.getCitizenshipNumber());
			dhsidType.setCertOfCitDocumentID(certOfCitDocumentID23Type);
            LOGGER.error("DHS Type: CertOfCitDocumentID");
            
		} else if (DHSID.IMG_DOC_TYP_1.equals(request.getDhsid())) {
			// I551DocumentID: AlienNumber and ReceiptNumber are required. DocExpirationDate is optional
			I551DocumentID4Type i551DocumentID4Type = new I551DocumentID4Type();
			i551DocumentID4Type.setAlienNumber(request.getAlienNumber());
			i551DocumentID4Type.setReceiptNumber(request.getReceiptNumber());
			if (request.getDocExpirationDate() != null) {
				i551DocumentID4Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
			}
			dhsidType.setI551DocumentID(i551DocumentID4Type);
            LOGGER.error("DHS Type: I551DocumentID");
            
		} else if (DHSID.IMG_DOC_TYP_2.equals(request.getDhsid())) {
			// I20DocumentID: SEVISID is required. I94Number, PassportCountry and DocExpirationDate are optional
			I20DocumentID26Type i20DocumentID26Type = new I20DocumentID26Type();
			i20DocumentID26Type.setSEVISID(request.getSevisId());
			
            if (null != request.getI94Number()) {
                i20DocumentID26Type.setI94Number(request.getI94Number());
            }

            i20DocumentID26Type.setPassportCountry(this.getPassportCountryType(request));
            
            if (null != request.getDocExpirationDate()) {
                i20DocumentID26Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
            }
            dhsidType.setI20DocumentID(i20DocumentID26Type);
            LOGGER.error("DHS Type: I20DocumentID");
            
		} else if (DHSID.IMG_DOC_TYP_3.equals(request.getDhsid())) {
			// I327DocumentID: AlienNumber is required. DocExpirationDate is optional
			I327DocumentID3Type i327DocumentID3Type = new I327DocumentID3Type();
            i327DocumentID3Type.setAlienNumber(request.getAlienNumber());
            if (null != request.getDocExpirationDate()) {
                i327DocumentID3Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
            }
            dhsidType.setI327DocumentID(i327DocumentID3Type);
            LOGGER.error("DHS Type: I327DocumentID");
            
		} else if (DHSID.IMG_DOC_TYP_4.equals(request.getDhsid())) {
			// I571DocumentID5Type: AlienNumber is required. DocExpirationDate is optional
			I571DocumentID5Type i571DocumentID5Type = new I571DocumentID5Type();
			i571DocumentID5Type.setAlienNumber(request.getAlienNumber());
            if (null != request.getDocExpirationDate()) {
            	i571DocumentID5Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
            }
            dhsidType.setI571DocumentID(i571DocumentID5Type);
            LOGGER.error("DHS Type: I571DocumentID5Type");
            
		} else if (DHSID.IMG_DOC_TYP_6.equals(request.getDhsid())) {
			// DS2019DocumentID: SEVISID is required. I94Number, PassportCountry and DocExpirationDate are optional
			DS2019DocumentID27Type ds2019DocumentID27Type = new DS2019DocumentID27Type();
			ds2019DocumentID27Type.setSEVISID(request.getSevisId());
			
            if (request.getI94Number() != null) {
                ds2019DocumentID27Type.setI94Number(request.getI94Number());
            }

            ds2019DocumentID27Type.setPassportCountry(this.getPassportCountryType(request));
            
            if (request.getDocExpirationDate() != null) {
                ds2019DocumentID27Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
            }
            dhsidType.setDS2019DocumentID(ds2019DocumentID27Type);
            LOGGER.error("DHS Type: DS2019DocumentID");
            
		} else if (DHSID.IMG_DOC_TYP_7.equals(request.getDhsid())) {
			// OtherCase1DocumentID: AlienNumber and DocDescReq are required. PassportCountry and DocExpirationDate are optional
			OtherCase1DocumentID1Type otherCase1DocumentID1Type = new OtherCase1DocumentID1Type();
            otherCase1DocumentID1Type.setAlienNumber(request.getAlienNumber());
            otherCase1DocumentID1Type.setDocDescReq(request.getDocDescReq());

            otherCase1DocumentID1Type.setPassportCountry(this.getPassportCountryType(request));
            if (request.getDocExpirationDate() != null) {
                otherCase1DocumentID1Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
            }
            
            dhsidType.setOtherCase1DocumentID(otherCase1DocumentID1Type);
            LOGGER.error("DHS Type: OtherCase1DocumentID");
            
		} else if (DHSID.IMG_DOC_TYP_8.equals(request.getDhsid())) {
			// I766DocumentID: AlienNumber, ReceiptNumber and DocExpirationDate are required
			I766DocumentID9Type i766DocumentID9Type = new I766DocumentID9Type();
            i766DocumentID9Type.setAlienNumber(request.getAlienNumber());
            i766DocumentID9Type.setReceiptNumber(request.getReceiptNumber());
            i766DocumentID9Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
            dhsidType.setI766DocumentID(i766DocumentID9Type);
            LOGGER.error("DHS Type: I766DocumentID");
            
		} else if (DHSID.IMG_DOC_TYP_9.equals(request.getDhsid())) {
			// TempI551DocumentID: AlienNumber is required. PassportCountry and DocExpirationDate are optional
			TempI551DocumentID21Type tempI551DocumentID21Type = new TempI551DocumentID21Type();
            tempI551DocumentID21Type.setAlienNumber(request.getAlienNumber());

            tempI551DocumentID21Type.setPassportCountry(this.getPassportCountryType(request));
            if(request.getDocExpirationDate() != null) {
            	tempI551DocumentID21Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
            }           
            
            dhsidType.setTempI551DocumentID(tempI551DocumentID21Type);
            LOGGER.error("DHS Type: TempI551DocumentID");
            
		} else if (DHSID.IMG_DOC_TYP_10.equals(request.getDhsid())) {
			
			if (null != request.getPassportNumber()) {
				// I94UnexpForeignPassportDocumentID: I94Number, PassportNumber, CountryOfIssuance and DocExpirationDate are required. VisaNumber and SEVISID are optional
                I94UnexpForeignPassportDocumentID10Type i94UnexpForeignPassportDocumentID10Type = new I94UnexpForeignPassportDocumentID10Type();
                i94UnexpForeignPassportDocumentID10Type.setI94Number(request.getI94Number());
                i94UnexpForeignPassportDocumentID10Type.setPassportNumber(request.getPassportNumber());
                i94UnexpForeignPassportDocumentID10Type.setCountryOfIssuance(request.getCountryOfIssuance());
                i94UnexpForeignPassportDocumentID10Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getPassportExpirationDate()));
                
                if (request.getVisaNumber() != null) {
                    i94UnexpForeignPassportDocumentID10Type.setVisaNumber(request.getVisaNumber());
                }
                if (request.getSevisId() != null) {
                    i94UnexpForeignPassportDocumentID10Type.setSEVISID(request.getSevisId());
                }
                
                dhsidType.setI94UnexpForeignPassportDocumentID(i94UnexpForeignPassportDocumentID10Type);
                LOGGER.error("DHS Type: I94UnexpForeignPassportDocumentID");
                
            } else {
            	// I94DocumentID: I94Number is required. SEVISID and DocExpirationDate are optional
                I94DocumentID2Type i94DocumentID2Type = new I94DocumentID2Type();
                i94DocumentID2Type.setI94Number(request.getI94Number());
                if (request.getSevisId() != null) {
                    i94DocumentID2Type.setSEVISID(request.getSevisId());
                }
                if (request.getDocExpirationDate() != null) {
                    i94DocumentID2Type.setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
                }
                dhsidType.setI94DocumentID(i94DocumentID2Type);
                LOGGER.error("DHS Type: I94DocumentID");
                
            }
		} else if (DHSID.IMG_DOC_TYP_11.equals(request.getDhsid())) {
        	// MacReadI551DocumentID: AlienNumber, PassportNumber and CountryOfIssuance are required. VisaNumber and DocExpirationDate are optional
        	MacReadI551DocumentID22Type macReadI551DocumentID22Type = new MacReadI551DocumentID22Type();
            macReadI551DocumentID22Type.setAlienNumber(request.getAlienNumber());
            macReadI551DocumentID22Type.setPassportNumber(request.getPassportNumber());
            macReadI551DocumentID22Type.setCountryOfIssuance(request.getCountryOfIssuance());
            
            if (request.getVisaNumber() != null) {
                macReadI551DocumentID22Type.setVisaNumber(request.getVisaNumber());
            }
            if (request.getDocExpirationDate() != null) {
                macReadI551DocumentID22Type
                        .setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getDocExpirationDate()));
            }
            
            dhsidType.setMacReadI551DocumentID(macReadI551DocumentID22Type);
            LOGGER.error("DHS Type: MacReadI551DocumentID");
            
        } else if (request.getPassportNumber() != null) {
			// UnexpForeignPassportDocumentID: PassportNumber, CountryOfIssuance and DocExpirationDate are required. I94Number and SEVISID are optional
			UnexpForeignPassportDocumentID30Type unexpForeignPassportDocumentID30Type = new UnexpForeignPassportDocumentID30Type();
            
            unexpForeignPassportDocumentID30Type.setPassportNumber(request.getPassportNumber());
            unexpForeignPassportDocumentID30Type.setCountryOfIssuance(request.getCountryOfIssuance());
            unexpForeignPassportDocumentID30Type .setDocExpirationDate(HBEUtils.toXmlGregorianCalendarFormat(request.getPassportExpirationDate()));
            
            if(request.getI94Number() != null) {
            	unexpForeignPassportDocumentID30Type.setI94Number(request.getI94Number());
            }
            if (request.getSevisId() != null) {
                unexpForeignPassportDocumentID30Type.setSEVISID(request.getSevisId());
            }
            dhsidType.setUnexpForeignPassportDocumentID(unexpForeignPassportDocumentID30Type);
            LOGGER.error("DHS Type: UnexpForeignPassportDocumentID");
            
        }
		
		return dhsidType;
	}
	
	private PassportCountryType getPassportCountryType(VLP1Request request) {

		if (StringUtils.isNotBlank(request.getPassportNumber()) && StringUtils.isNotBlank(request.getCountryOfIssuance())) {
			// Set PassportCountry if both passport number and country are present
			PassportCountryType passportCountry = new ObjectFactory().createPassportCountryType();
			passportCountry.setPassportNumber(request.getPassportNumber());
			passportCountry.setCountryOfIssuance(request.getCountryOfIssuance());
			return passportCountry;
		}
		
		return null;
	}

}