package com.hbe.ms.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hbe.ms.common.utils.CustomDateDeserializer;
import com.hbe.ms.common.utils.CustomDateSerializer;
import com.hbe.ms.common.utils.CustomTimestampDeserializer;
import com.hbe.ms.common.utils.CustomTimestampSerializer;
import com.hbe.ms.common.vo.ArrayOfErrorResponseMetaData;
import com.hbe.ms.common.vo.ArrayOfSponsorshipData;
import com.hbe.ms.common.vo.ErrorRespLog;
import com.hbe.ms.common.vo.ResponseMetaData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing a Person Lawful Detail Response.")
public class VLP1Response {

	@JsonProperty("lawfulPresenceDetailId")
	@ApiModelProperty(example = "4563", position = 1)
	private String lawfulPrsncDetailId;
	
	@ApiModelProperty(example = "IMG_DOC_TYP_7", position = 2)
	private String dhsDocType;

	@ApiModelProperty(example = "HS000000", position = 3)
	private String responseCode;

	@ApiModelProperty(example = "Unexpected Response Code", position = 4)
	private String responseDescription;

	@ApiModelProperty(example = "37", position = 5)
	private String eligibilityStatementCode;
	
	@ApiModelProperty(example = "Lawful permanent Resident", position = 6)
	private String eligStatementTxt;	

	@ApiModelProperty(example = "P", position = 7)
	private String lpVerfdCd;
	
	@ApiModelProperty(example = "VLP1", position = 8)
	private String vlpCallType;
	
	@ApiModelProperty(example = "Success", position = 9)
	private String tdsResponseDescriptionText;

	@ApiModelProperty(example = "Invoke CloseCase Web method to close the case", position = 10)
	private String agencyAction;

	@ApiModelProperty(example = "F383468876012VE", position = 11)
	private String caseNumber;

	@ApiModelProperty(example = "F37", position = 12)
	private String nonCitCoaCode;

	@ApiModelProperty(example = "1", position = 13)
	private BigDecimal createdtdBy;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 14)
	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	@JsonSerialize(using = CustomTimestampSerializer.class)
	@JsonIgnore
	private Timestamp createdDate;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 15)
	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private Timestamp entryDate;

	@ApiModelProperty(example = "false", position = 16)
	private String fiveYearBarApplicabilityIndicator;//

	@ApiModelProperty(example = "1981-06-06 00:00:00", position = 17)
	private String grantDate;

	@ApiModelProperty(example = "P/Y/N", position = 18)
	private String qualifiedCitizenCode;

	@ApiModelProperty(example = "SUCCESS", position = 19)
	private BigDecimal step2InvocationStatus;

	@ApiModelProperty(example = "1", position = 20)
	private BigDecimal updatedBy;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 21)
	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	@JsonSerialize(using = CustomTimestampSerializer.class)
	private Timestamp updatedDate;

	@ApiModelProperty(example = "X", position = 22)
	private String usCitizenCode;

	@ApiModelProperty(example = "1", position = 23)
	private BigDecimal versionNumber;

	@ApiModelProperty(example = "8aed1d6d115ca035", position = 24)
	private String uuid;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 25)
	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	@JsonSerialize(using = CustomTimestampSerializer.class)
	@JsonIgnore
	private Timestamp effectiveDate;

	@ApiModelProperty(example = "firstName", position = 26)
	private String firstName;

	@ApiModelProperty(example = "lastName", position = 27)
	private String lastName;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 28)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp dateOfBirth;

	@ApiModelProperty(example = "B75855686647", position = 29)
	private String passportNumber;

	@ApiModelProperty(example = "USA", position = 30)
	private String passportCountry;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 31)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp passportExpirationDate;

	@ApiModelProperty(example = "227197928", position = 32)
	private String alienNumber;

	@ApiModelProperty(example = "AED6578766654", position = 33)
	private String receiptNumber;

	@ApiModelProperty(example = "7658647586", position = 34)
	private String sevisId;

	@ApiModelProperty(example = "docDescReqTest", position = 35)
	private String docDescReq;

	@ApiModelProperty(example = "27008347287", position = 36)
	private String i94Number;

	@ApiModelProperty(example = "87273919", position = 37)
	private String visaNumber;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 38)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp docExpirationDate;

	@ApiModelProperty(example = "714999714999", position = 39)
	private String naturalizationNumber;

	@ApiModelProperty(example = "PA9876432", position = 40)
	private String citizenshipNumber;

	@ApiModelProperty(example = "Smith", position = 41)
	private String nonCitLastName;

	@ApiModelProperty(example = "John", position = 42)
	private String nonCitFirstName;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 43)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp nonCitBirthDate;

	@ApiModelProperty(example = "1981-06-06 00:00:00", position = 44)
	private String nonCitEntryDate;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 45)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp admittedToDate;

	@ApiModelProperty(example = "WA", position = 46)
	private String admittedToText;

	@ApiModelProperty(example = "ENG", position = 47)
	private String nonCitCountryBirthCd;

	@ApiModelProperty(example = "ENG", position = 48)
	private String nonCitCountryCitCd;

	@ApiModelProperty(example = "2012-06-06 00:00:00", position = 49)
	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	@JsonSerialize(using = CustomTimestampSerializer.class)
	private Timestamp nonCitEadsExpireDate;

	@ApiModelProperty(example = "OTHER", position = 50)
	private String iavTypeCode;

	@ApiModelProperty(example = "IAV Text", position = 51)
	private String iavTypeText;

	@ApiModelProperty(example = "Sprint 7", position = 52)
	private String webServSftwrVer;

	@ApiModelProperty(example = "F12", position = 53)
	private String grantDateReasonCd;

	@ApiModelProperty(example = "M14", position = 54)
	private String sponsorshipReasonCd;

	@ApiModelProperty(example = "X/Y/N/P", position = 55)
	private String fiveYearBarApplyCode;

	@ApiModelProperty(example = "Y", position = 56)
	private String qualifiedNonCitizenCode;

	@ApiModelProperty(example = "N", position = 57)
	private String fiveYearBarMetCode;

	@ApiModelProperty(example = "HPF_MS_BS001", position = 58)
	private String errorCodeTx;

	@ApiModelProperty(example = "Received exception in VLP1 service process method in VLP1 service", position = 59)
	private String errorMsgTx;

	@ApiModelProperty(example = "VLP1", position = 60)
	private String errorSourceTx;

	@ApiModelProperty(example = "java.lang.NullPointerException", position = 61)
	private String errorStackTraceTx;
	
	private List<ErrorRespLog> errorRespLogs;

	private ResponseMetaData responseMetaData;

	private ArrayOfErrorResponseMetaData arrayOfErrorResponseMetaData;

	private ArrayOfSponsorshipData arrayOfSponsorshipData;


	public String getErrorMsgTx() {
		return errorMsgTx;
	}

	public void setErrorMsgTx(String errorMsgTx) {
		this.errorMsgTx = errorMsgTx;
	}

	public String getLawfulPrsncDetailId() {
		return lawfulPrsncDetailId;
	}

	public void setLawfulPrsncDetailId(String lawfulPrsncDetailId) {
		this.lawfulPrsncDetailId = lawfulPrsncDetailId;
	}

	public String getAgencyAction() {
		return agencyAction;
	}

	public void setAgencyAction(String agencyAction) {
		this.agencyAction = agencyAction;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getNonCitCoaCode() {
		return nonCitCoaCode;
	}

	public void setNonCitCoaCode(String nonCitCoaCode) {
		this.nonCitCoaCode = nonCitCoaCode;
	}

	public BigDecimal getCreatedtdBy() {
		return createdtdBy;
	}

	public void setCreatedtdBy(BigDecimal createdtdBy) {
		this.createdtdBy = createdtdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getEligibilityStatementCode() {
		return eligibilityStatementCode;
	}

	public void setEligibilityStatementCode(String eligibilityStatementCode) {
		this.eligibilityStatementCode = eligibilityStatementCode;
	}

	public Timestamp getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	public String getFiveYearBarApplicabilityIndicator() {
		return fiveYearBarApplicabilityIndicator;
	}

	public void setFiveYearBarApplicabilityIndicator(String fiveYearBarApplicabilityIndicator) {
		this.fiveYearBarApplicabilityIndicator = fiveYearBarApplicabilityIndicator;
	}

	public String getGrantDate() {
		return grantDate;
	}

	public void setGrantDate(String grantDate) {
		this.grantDate = grantDate;
	}

	public String getLpVerfdCd() {
		return lpVerfdCd;
	}

	public void setLpVerfdCd(String lpVerfdCd) {
		this.lpVerfdCd = lpVerfdCd;
	}

	public String getQualifiedCitizenCode() {
		return qualifiedCitizenCode;
	}

	public void setQualifiedCitizenCode(String qualifiedCitizenCode) {
		this.qualifiedCitizenCode = qualifiedCitizenCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public BigDecimal getStep2InvocationStatus() {
		return step2InvocationStatus;
	}

	public void setStep2InvocationStatus(BigDecimal step2InvocationStatus) {
		this.step2InvocationStatus = step2InvocationStatus;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUsCitizenCode() {
		return usCitizenCode;
	}

	public void setUsCitizenCode(String usCitizenCode) {
		this.usCitizenCode = usCitizenCode;
	}

	public BigDecimal getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(BigDecimal versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getVlpCallType() {
		return vlpCallType;
	}

	public void setVlpCallType(String vlpCallType) {
		this.vlpCallType = vlpCallType;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Timestamp getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassportCountry() {
		return passportCountry;
	}

	public void setPassportCountry(String passportCountry) {
		this.passportCountry = passportCountry;
	}

	public Timestamp getPassportExpirationDate() {
		return passportExpirationDate;
	}

	public void setPassportExpirationDate(Timestamp passportExpirationDate) {
		this.passportExpirationDate = passportExpirationDate;
	}

	public String getDhsDocType() {
		return dhsDocType;
	}

	public void setDhsDocType(String dhsDocType) {
		this.dhsDocType = dhsDocType;
	}

	public String getAlienNumber() {
		return alienNumber;
	}

	public void setAlienNumber(String alienNumber) {
		this.alienNumber = alienNumber;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getSevisId() {
		return sevisId;
	}

	public void setSevisId(String sevisId) {
		this.sevisId = sevisId;
	}

	public String getDocDescReq() {
		return docDescReq;
	}

	public void setDocDescReq(String docDescReq) {
		this.docDescReq = docDescReq;
	}

	public String getI94Number() {
		return i94Number;
	}

	public void setI94Number(String i94Number) {
		this.i94Number = i94Number;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public Timestamp getDocExpirationDate() {
		return docExpirationDate;
	}

	public void setDocExpirationDate(Timestamp docExpirationDate) {
		this.docExpirationDate = docExpirationDate;
	}

	public String getNaturalizationNumber() {
		return naturalizationNumber;
	}

	public void setNaturalizationNumber(String naturalizationNumber) {
		this.naturalizationNumber = naturalizationNumber;
	}

	public String getCitizenshipNumber() {
		return citizenshipNumber;
	}

	public void setCitizenshipNumber(String citizenshipNumber) {
		this.citizenshipNumber = citizenshipNumber;
	}

	public List<ErrorRespLog> getErrorRespLogs() {
		return errorRespLogs;
	}

	public void setErrorRespLogs(List<ErrorRespLog> errorRespLogs) {
		this.errorRespLogs = errorRespLogs;
	}

	public String getTdsResponseDescriptionText() {
		return tdsResponseDescriptionText;
	}

	public void setTdsResponseDescriptionText(String tdsResponseDescriptionText) {
		this.tdsResponseDescriptionText = tdsResponseDescriptionText;
	}

	public String getNonCitLastName() {
		return nonCitLastName;
	}

	public void setNonCitLastName(String nonCitLastName) {
		this.nonCitLastName = nonCitLastName;
	}

	public String getNonCitFirstName() {
		return nonCitFirstName;
	}

	public void setNonCitFirstName(String nonCitFirstName) {
		this.nonCitFirstName = nonCitFirstName;
	}

	public Timestamp getNonCitBirthDate() {
		return nonCitBirthDate;
	}

	public void setNonCitBirthDate(Timestamp nonCitBirthDate) {
		this.nonCitBirthDate = nonCitBirthDate;
	}

	public String getNonCitEntryDate() {
		return nonCitEntryDate;
	}

	public void setNonCitEntryDate(String nonCitEntryDate) {
		this.nonCitEntryDate = nonCitEntryDate;
	}

	public Timestamp getAdmittedToDate() {
		return admittedToDate;
	}

	public void setAdmittedToDate(Timestamp admittedToDate) {
		this.admittedToDate = admittedToDate;
	}

	public String getAdmittedToText() {
		return admittedToText;
	}

	public void setAdmittedToText(String admittedToText) {
		this.admittedToText = admittedToText;
	}

	public String getNonCitCountryBirthCd() {
		return nonCitCountryBirthCd;
	}

	public void setNonCitCountryBirthCd(String nonCitCountryBirthCd) {
		this.nonCitCountryBirthCd = nonCitCountryBirthCd;
	}

	public String getNonCitCountryCitCd() {
		return nonCitCountryCitCd;
	}

	public void setNonCitCountryCitCd(String nonCitCountryCitCd) {
		this.nonCitCountryCitCd = nonCitCountryCitCd;
	}

	public String getEligStatementTxt() {
		return eligStatementTxt;
	}

	public void setEligStatementTxt(String eligStatementTxt) {
		this.eligStatementTxt = eligStatementTxt;
	}

	public String getIavTypeCode() {
		return iavTypeCode;
	}

	public void setIavTypeCode(String iavTypeCode) {
		this.iavTypeCode = iavTypeCode;
	}

	public String getIavTypeText() {
		return iavTypeText;
	}

	public void setIavTypeText(String iavTypeText) {
		this.iavTypeText = iavTypeText;
	}

	public String getWebServSftwrVer() {
		return webServSftwrVer;
	}

	public void setWebServSftwrVer(String webServSftwrVer) {
		this.webServSftwrVer = webServSftwrVer;
	}

	public String getGrantDateReasonCd() {
		return grantDateReasonCd;
	}

	public void setGrantDateReasonCd(String grantDateReasonCd) {
		this.grantDateReasonCd = grantDateReasonCd;
	}

	public String getSponsorshipReasonCd() {
		return sponsorshipReasonCd;
	}

	public void setSponsorshipReasonCd(String sponsorshipReasonCd) {
		this.sponsorshipReasonCd = sponsorshipReasonCd;
	}

	public String getFiveYearBarApplyCode() {
		return fiveYearBarApplyCode;
	}

	public void setFiveYearBarApplyCode(String fiveYearBarApplyCode) {
		this.fiveYearBarApplyCode = fiveYearBarApplyCode;
	}

	public String getQualifiedNonCitizenCode() {
		return qualifiedNonCitizenCode;
	}

	public void setQualifiedNonCitizenCode(String qualifiedNonCitizenCode) {
		this.qualifiedNonCitizenCode = qualifiedNonCitizenCode;
	}

	public String getFiveYearBarMetCode() {
		return fiveYearBarMetCode;
	}

	public void setFiveYearBarMetCode(String fiveYearBarMetCode) {
		this.fiveYearBarMetCode = fiveYearBarMetCode;
	}

	public ResponseMetaData getResponseMetaData() {
		return responseMetaData;
	}

	public void setResponseMetaData(ResponseMetaData responseMetaData) {
		this.responseMetaData = responseMetaData;
	}

	public ArrayOfErrorResponseMetaData getArrayOfErrorResponseMetaData() {
		return arrayOfErrorResponseMetaData;
	}

	public void setArrayOfErrorResponseMetaData(ArrayOfErrorResponseMetaData arrayOfErrorResponseMetaData) {
		this.arrayOfErrorResponseMetaData = arrayOfErrorResponseMetaData;
	}

	public ArrayOfSponsorshipData getArrayOfSponsorshipData() {
		return arrayOfSponsorshipData;
	}

	public void setArrayOfSponsorshipData(ArrayOfSponsorshipData arrayOfSponsorshipData) {
		this.arrayOfSponsorshipData = arrayOfSponsorshipData;
	}

	public String getErrorCodeTx() {
		return errorCodeTx;
	}

	public void setErrorCodeTx(String errorCodeTx) {
		this.errorCodeTx = errorCodeTx;
	}

	public String getErrorSourceTx() {
		return errorSourceTx;
	}

	public void setErrorSourceTx(String errorSourceTx) {
		this.errorSourceTx = errorSourceTx;
	}

	public String getErrorStackTraceTx() {
		return errorStackTraceTx;
	}

	public void setErrorStackTraceTx(String errorStackTraceTx) {
		this.errorStackTraceTx = errorStackTraceTx;
	}

	public Timestamp getNonCitEadsExpireDate() {
		return nonCitEadsExpireDate;
	}

	public void setNonCitEadsExpireDate(Timestamp nonCitEadsExpireDate) {
		this.nonCitEadsExpireDate = nonCitEadsExpireDate;
	}

}
