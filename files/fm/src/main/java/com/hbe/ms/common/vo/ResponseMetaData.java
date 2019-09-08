package com.hbe.ms.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing Response meta data")
public class ResponseMetaData {
	
	@ApiModelProperty(example = "HS000000", position = 1)
    private String responseCode;
	@ApiModelProperty(example = "SUCCESS", position = 2)
    private String responseDescriptionText;
	@ApiModelProperty(example = "Success", position = 3)
    private String tDSResponseDescriptionText;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescriptionText() {
        return responseDescriptionText;
    }

    public void setResponseDescriptionText(String responseDescriptionText) {
        this.responseDescriptionText = responseDescriptionText;
    }

    public String gettDSResponseDescriptionText() {
        return tDSResponseDescriptionText;
    }

    public void settDSResponseDescriptionText(String tDSResponseDescriptionText) {
        this.tDSResponseDescriptionText = tDSResponseDescriptionText;
    }
}
