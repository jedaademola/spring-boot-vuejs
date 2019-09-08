package com.hbe.ms.common.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing meta data of error Response.")
public class ErrorResponseMetaData {

	@ApiModelProperty(example = "HE001023", position = 1)
    private String errorResponseCode;
	
	@ApiModelProperty(example = "Alien number is required", position = 2)
    private String errorResponseDescriptionText;
	
	@ApiModelProperty(example = "The required parameter A-Number was not received by the Web service method", position = 3)
    private String errorTDSResponseDescriptionText;

    public String getErrorResponseCode() {
        return errorResponseCode;
    }

    public void setErrorResponseCode(String errorResponseCode) {
        this.errorResponseCode = errorResponseCode;
    }

    public String getErrorResponseDescriptionText() {
        return errorResponseDescriptionText;
    }

    public void setErrorResponseDescriptionText(String errorResponseDescriptionText) {
        this.errorResponseDescriptionText = errorResponseDescriptionText;
    }

    public String getErrorTDSResponseDescriptionText() {
        return errorTDSResponseDescriptionText;
    }

    public void setErrorTDSResponseDescriptionText(String errorTDSResponseDescriptionText) {
        this.errorTDSResponseDescriptionText = errorTDSResponseDescriptionText;
    }
}
