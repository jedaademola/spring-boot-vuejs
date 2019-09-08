package com.hbe.ms.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hbe.ms.common.utils.CustomTimestampSerializer;
import com.hbe.ms.common.utils.CustomTimestampDeserializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing a VlpServer Result Request.")
public class VlpServResultRequest {

    @ApiModelProperty(example = "3478")
    private long vlpServResultId;

    @ApiModelProperty(example = "2018-12-06", position = 1)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp createdDate;
    @ApiModelProperty(example = "<xml> :::<xml>", position = 2)
    private String responsePayLoad;
    @ApiModelProperty(example = "<xml> ..</xml>", position = 3)
    private String requestPayLoad;
    @ApiModelProperty(example = "Smith John", position = 4)
    private String requestSentBy;
    @ApiModelProperty(example = "VLP1", position = 5)
    private String serverName;
    @ApiModelProperty(example = "12434567", position = 6)
    private String uuid;
    @ApiModelProperty(example = "7", position = 7)
    private BigDecimal versionNumber;
    @ApiModelProperty(example = "54", position = 8)
    private BigDecimal createdBy;

    @ApiModelProperty(example = "2018-12-06", position = 9)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp archivalDate;
    private List<VlpServErrorLog> vlpServErrorLogs;

    public Timestamp getArchivalDate() {
        return archivalDate;
    }

    public void setArchivalDate(Timestamp archivalDate) {
        this.archivalDate = archivalDate;
    }


    public long getVlpServResultId() {
        return vlpServResultId;
    }

    @JsonIgnore
    public void setVlpServResultId(long vlpServResultId) {
        this.vlpServResultId = vlpServResultId;
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

    public String getRequestSentBy() {
        return requestSentBy;
    }

    public void setRequestSentBy(String requestSentBy) {
        this.requestSentBy = requestSentBy;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(BigDecimal versionNumber) {
        this.versionNumber = versionNumber;
    }

    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    public List<VlpServErrorLog> getVlpServErrorLogs() {
        return vlpServErrorLogs;
    }

    public void setVlpServErrorLogs(List<VlpServErrorLog> vlpServErrorLogs) {
        this.vlpServErrorLogs = vlpServErrorLogs;
    }

    @Override
    public String toString() {
        return "VlpServResultRequest [vlpServResultId=" + vlpServResultId + ", createdDate=" + createdDate
                + ", responsePayLoad=" + responsePayLoad + ", requestPayLoad=" + requestPayLoad + ", requestSentBy="
                + requestSentBy + ", serverName=" + serverName + ", uuid=" + uuid + ", versionNumber=" + versionNumber
                + ", createdBy=" + createdBy + ", archivalDate=" + archivalDate + ", vlpServErrorLogs="
                + vlpServErrorLogs + "]";
    }


}
