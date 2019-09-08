package com.hbe.ms.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing sponsorship data.")
public class SponsorshipData {

	@ApiModelProperty(example = "Smith", position = 1)
    private String lastName;
	@ApiModelProperty(example = "John", position = 2)
    private String firstName;
	@ApiModelProperty(example = "Miley", position = 3)
    private String middleName;

	@ApiModelProperty(example = "711 Capitol Way S", position = 4)
    private String addr1;
	@ApiModelProperty(example = "Washington", position = 5)
    private String addr2;
	@ApiModelProperty(example = "Olympia", position = 6)
    private String city;
	@ApiModelProperty(example = "WA", position = 7)
    private String stateProvince;

	@ApiModelProperty(example = "98501", position = 8)
    private String zipPostalCode;
	@ApiModelProperty(example = "190-38-9531", position = 9)
    private String ssn;
	@ApiModelProperty(example = "USA", position = 10)
    private String countryCode;
	@ApiModelProperty(example = "US", position = 11)
    private String countryName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
