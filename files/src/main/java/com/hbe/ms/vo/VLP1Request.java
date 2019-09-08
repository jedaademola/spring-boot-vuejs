package com.hbe.ms.vo;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.hbe.ms.common.utils.CustomDateDeserializer;
import com.hbe.ms.common.utils.CustomDateSerializer;

import com.hbe.ms.common.utils.CustomTimestampDeserializer;
import com.hbe.ms.common.utils.CustomTimestampSerializer;

import com.hbe.ms.common.vo.DHSID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing a Initial Verification Request.")
public class VLP1Request {
	
	@ApiModelProperty(example = "IMG_DOC_TYP_7", position = 1)
	private DHSID dhsid;
	
	@ApiModelProperty(example = "4893", position = 2)
	private String lawfulPresenceDetailId;

    @Size(min = 1, max = 25, message = "First Name must have 1 characters")
    @Pattern(regexp = "[a-zA-Z '-]{1,25}", message = "Invalid First Name format")
    @NotBlank(message = "First Name must not be blank!")
	@ApiModelProperty(example = "Lemuel", position = 3)
	private String firstName;

	@ApiModelProperty(example = "smith", position = 4)
	private String middleName;

    @Size(min = 1, max = 40, message = "Last Name must have 1 characters")
    @Pattern(regexp = "[a-zA-Z '-]{1,40}", message = "Invalid Last Name format")
    @NotBlank(message = "Last Name must not be blank!")
	@ApiModelProperty(example = "Merritt", position = 5)
	private String lastName;

	@ApiModelProperty(example = "1981-06-06", position = 6)
    @NotNull(message = "DateOfBirth must not be blank!")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp dateOfBirth;

    @ApiModelProperty(example = "true", position = 7)
	private boolean fiveYearBarApplicabilityIndicator;

	@ApiModelProperty(example = "false", position = 8)
	private boolean requestGrantDateIndicator;

    @ApiModelProperty(example = "requesterCommentsForHub01", position = 9)
	private String requesterCommentsForHub;

	@ApiModelProperty(example = "Lemuel Merritt", position = 10)
	private String casePOCFullName;


    @ApiModelProperty(example = "7172768756", position = 11)
	private String casePOCPhoneNumber;

	@ApiModelProperty(example = "310", position = 12)
	private String casePOCPhoneNumberExtension;	

	@ApiModelProperty(example = "false", position = 13)
	private boolean requestSponsorDataIndicator;

	@ApiModelProperty(example = "2471654789", position = 14)
	private String alienNumber;

	@ApiModelProperty(example = "2019-06-06", position = 15)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp docExpirationDate;

	@ApiModelProperty(example = "2021-06-06", position = 16)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp passportExpirationDate;

	@ApiModelProperty(example = "AZR123764545", position = 17)
	private String receiptNumber;

	@ApiModelProperty(example = "PA9876432", position = 18)
	private String citizenshipNumber;

	@ApiModelProperty(example = "714999714999", position = 19)
	private String naturalizationNumber;

	@ApiModelProperty(example = "24174715", position = 20)
	private String visaNumber;

	@ApiModelProperty(example = "PLW", position = 21)
	private String countryOfIssuance;

	@ApiModelProperty(example = "B75855686647", position = 22)
	private String passportNumber;

	@ApiModelProperty(example = "PLW", position = 23)
	private String passportCountry;

	@ApiModelProperty(example = "69000888062", position = 24)
	private String i94Number;

	@ApiModelProperty(example = "2780020089", position = 25)
	private String sevisId;

	@ApiModelProperty(example = "Testing.", position = 26)
	private String docDescReq;

	@ApiModelProperty(example = "2011-06-06", position = 27)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Timestamp entryDate;

	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	@JsonSerialize(using = CustomTimestampSerializer.class)
	@ApiModelProperty(example = "2018-06-06 00:00:00", position = 28)
	private Timestamp systemDate;

	@ApiModelProperty(example = "2345621", position = 29)
	private String aka;

	@ApiModelProperty(example = "true", position = 30)
	private boolean suspectedCounterfeitAlteredDocumentIndicator;

	@ApiModelProperty(example = "false", position = 31)
	private boolean requestCubanHaitianEntrantIndicator;

	@ApiModelProperty(example = "ZGVmYXVsdA==", position = 32)
	private String documentBinaryAttachment;

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isFiveYearBarApplicabilityIndicator() {
		return fiveYearBarApplicabilityIndicator;
	}

	public void setFiveYearBarApplicabilityIndicator(boolean fiveYearBarApplicabilityIndicator) {
		this.fiveYearBarApplicabilityIndicator = fiveYearBarApplicabilityIndicator;
	}

	public boolean isRequestGrantDateIndicator() {
		return requestGrantDateIndicator;
	}

	public void setRequestGrantDateIndicator(boolean requestGrantDateIndicator) {
		this.requestGrantDateIndicator = requestGrantDateIndicator;
	}

	public String getRequesterCommentsForHub() {
		return requesterCommentsForHub;
	}

	public void setRequesterCommentsForHub(String requesterCommentsForHub) {
		this.requesterCommentsForHub = requesterCommentsForHub;
	}

	public String getCasePOCFullName() {
		return casePOCFullName;
	}

	public void setCasePOCFullName(String casePOCFullName) {
		this.casePOCFullName = casePOCFullName;
	}

	public String getCasePOCPhoneNumber() {
		return casePOCPhoneNumber;
	}

	public void setCasePOCPhoneNumber(String casePOCPhoneNumber) {
		this.casePOCPhoneNumber = casePOCPhoneNumber;
	}

	public String getCasePOCPhoneNumberExtension() {
		return casePOCPhoneNumberExtension;
	}

	public void setCasePOCPhoneNumberExtension(String casePOCPhoneNumberExtension) {
		this.casePOCPhoneNumberExtension = casePOCPhoneNumberExtension;
	}

	public String getAlienNumber() {
		return alienNumber;
	}

	public void setAlienNumber(String alienNumber) {
		this.alienNumber = alienNumber;
	}

	public Timestamp getPassportExpirationDate() {
		return passportExpirationDate;
	}

	public void setPassportExpirationDate(Timestamp passportExpirationDate) {
		this.passportExpirationDate = passportExpirationDate;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getCitizenshipNumber() {
		return citizenshipNumber;
	}

	public void setCitizenshipNumber(String citizenshipNumber) {
		this.citizenshipNumber = citizenshipNumber;
	}

	public String getNaturalizationNumber() {
		return naturalizationNumber;
	}

	public void setNaturalizationNumber(String naturalizationNumber) {
		this.naturalizationNumber = naturalizationNumber;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public String getCountryOfIssuance() {
		return countryOfIssuance;
	}

	public void setCountryOfIssuance(String countryOfIssuance) {
		this.countryOfIssuance = countryOfIssuance;
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

	public String getI94Number() {
		return i94Number;
	}

	public void setI94Number(String i94Number) {
		this.i94Number = i94Number;
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

	public String getLawfulPresenceDetailId() {
		return lawfulPresenceDetailId;
	}

	public void setLawfulPresenceDetailId(String lawfulPresenceDetailId) {
		this.lawfulPresenceDetailId = lawfulPresenceDetailId;
	}

	public String getAka() {
		return aka;
	}

	public void setAka(String aka) {
		this.aka = aka;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Timestamp getDocExpirationDate() {
		return docExpirationDate;
	}

	public void setDocExpirationDate(Timestamp docExpirationDate) {
		this.docExpirationDate = docExpirationDate;
	}

	public Timestamp getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	public Timestamp getSystemDate() {
		return systemDate;
	}

	public void setSystemDate(Timestamp systemDate) {
		this.systemDate = systemDate;
	}

	public String getDocumentBinaryAttachment() {
		return documentBinaryAttachment;
	}

	public void setDocumentBinaryAttachment(String documentBinaryAttachment) {
		this.documentBinaryAttachment = documentBinaryAttachment;
	}

	public DHSID getDhsid() {
		return dhsid;
	}

	public void setDhsid(DHSID dhsid) {
		this.dhsid = dhsid;
	}

	public boolean isRequestSponsorDataIndicator() {
		return requestSponsorDataIndicator;
	}

	public void setRequestSponsorDataIndicator(boolean requestSponsorDataIndicator) {
		this.requestSponsorDataIndicator = requestSponsorDataIndicator;
	}

	public boolean isSuspectedCounterfeitAlteredDocumentIndicator() {
		return suspectedCounterfeitAlteredDocumentIndicator;
	}

	public void setSuspectedCounterfeitAlteredDocumentIndicator(boolean suspectedCounterfeitAlteredDocumentIndicator) {
		this.suspectedCounterfeitAlteredDocumentIndicator = suspectedCounterfeitAlteredDocumentIndicator;
	}

	public boolean isRequestCubanHaitianEntrantIndicator() {
		return requestCubanHaitianEntrantIndicator;
	}

	public void setRequestCubanHaitianEntrantIndicator(boolean requestCubanHaitianEntrantIndicator) {
		this.requestCubanHaitianEntrantIndicator = requestCubanHaitianEntrantIndicator;
	}

}
