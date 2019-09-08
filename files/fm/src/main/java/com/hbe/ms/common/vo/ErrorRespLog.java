package com.hbe.ms.common.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hbe.ms.common.utils.CustomTimestampDeserializer;
import com.hbe.ms.common.utils.CustomTimestampSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing a Error in Person Lawful Detail Request.")
public class ErrorRespLog {


    @ApiModelProperty(example = "12434567")
    private long errorRespLogId;

    @ApiModelProperty(example = "2018-12-12", position = 1)


    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp createdDate;

    @ApiModelProperty(example = "HS0000", position = 2)
    private String responseCode;

    @ApiModelProperty(example = "Success", position = 3)
    private String responseDescription;


    @ApiModelProperty(example = "2018-03-24", position = 4)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp effectiveDate;

    @ApiModelProperty(example = "2018-03-24", position = 5)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp archivalDate;

    @ApiModelProperty(example = "Success", position = 6)
    private String tdsResponseDescription;


    public String getTdsResponseDescription() {
        return tdsResponseDescription;
    }

    public void setTdsResponseDescription(String tdsResponseDescription) {
        this.tdsResponseDescription = tdsResponseDescription;
    }

    public Timestamp getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Timestamp effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Timestamp getArchivalDate() {
        return archivalDate;
    }

    public void setArchivalDate(Timestamp archivalDate) {
        this.archivalDate = archivalDate;
    }

    public long getErrorRespLogId() {
        return errorRespLogId;
    }

    @JsonIgnore
    public void setErrorRespLogId(long errorRespLogId) {
        this.errorRespLogId = errorRespLogId;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }


}
