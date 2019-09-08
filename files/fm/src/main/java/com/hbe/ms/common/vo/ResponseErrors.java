package com.hbe.ms.common.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing a Error Values.")
public class ResponseErrors {
    @ApiModelProperty(example = "HPF_MS_BS001", position = 1)
    private String name;
    @ApiModelProperty(example = "Received error while accesing the FedHub service endpoint in VLP1 service", position = 2)
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
