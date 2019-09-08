package com.hbe.ms.common.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hbe.ms.common.utils.CustomTimestampSerializer;
import com.hbe.ms.common.utils.CustomTimestampDeserializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing a Interface Tran Details.")
public class InterfaceTransDetails {

    @ApiModelProperty(example = "12434567")
    private long intfcTransDetailId;

    @ApiModelProperty(example = "2018-12-12", position = 1)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp createdDate;

    @ApiModelProperty(example = "<xml> ..</xml>", position = 72)
    private String responsePayLoad;

    @ApiModelProperty(example = "<xml> ..</xml>", position = 3)
    private String requestPayLoad;

    @ApiModelProperty(example = "00", position = 4)
    private int statusCode;

    @ApiModelProperty(example = "2018-12-12", position = 5)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp updateDate;

    @ApiModelProperty(example = "1234", position = 6)
    private String uuid;

    @ApiModelProperty(example = "2018-12-12", position = 7)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp archivalDate;

    @ApiModelProperty(example = "7", position = 8)
    private BigDecimal versionNumber;

    @ApiModelProperty(example = "VLP 1A", position = 79)
    private String vlpServerName;

    public long getIntfcTransDetailId() {
        return intfcTransDetailId;
    }
    public void setIntfcTransDetailId(long intfcTransDetailId) {
        this.intfcTransDetailId = intfcTransDetailId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getResponsePayLoad() {
        return responsePayLoad;
    }

    public void setResponsePayLoad(String responsePayLoad) {
        this.responsePayLoad = responsePayLoad;
    }

    public String getRequestPayLoad() {
        return requestPayLoad;
    }

    public void setRequestPayLoad(String requestPayLoad) {
        this.requestPayLoad = requestPayLoad;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public Timestamp getArchivalDate() {
        return archivalDate;
    }

    public void setArchivalDate(Timestamp archivalDate) {
        this.archivalDate = archivalDate;
    }

    public BigDecimal getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(BigDecimal versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVlpServerName() {
        return vlpServerName;
    }

    public void setVlpServerName(String vlpServerName) {
        this.vlpServerName = vlpServerName;
    }

    @Override
    public String toString() {
        return "InterfaceTransDetails [intfcTransDetailId=" + intfcTransDetailId + ", createdDate=" + createdDate
                + ", responsePayLoad=" + responsePayLoad + ", requestPayLoad=" + requestPayLoad + ", statusCode="
                + statusCode + ", updateDate=" + updateDate + ", uuid=" + uuid + ", archivalDate=" + archivalDate
                + ", versionNumber=" + versionNumber + ", vlpServerName=" + vlpServerName + "]";
    }


}
