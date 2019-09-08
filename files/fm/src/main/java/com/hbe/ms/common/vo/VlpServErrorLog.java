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
@ApiModel(description = "Class representing a Error in VLP Server Log.")
public class VlpServErrorLog {

    @ApiModelProperty(example = "12434567")
    private long vlpServErrorLogId;

    @ApiModelProperty(example = "2018-12-12", position = 1)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp createdDate;

    @ApiModelProperty(example = "1000", position = 2)
    private String errorCode;

    @ApiModelProperty(example = "VLP Service", position = 3)
    private String errorSource;

    @ApiModelProperty(example = "null pointer", position = 4)
    private String errorStackTrace;


    @ApiModelProperty(example = "server not available", position = 6)
    private String serverErrorData;

    @ApiModelProperty(example = "unknown host", position = 7)
    private String serverErrorMsg;

    @ApiModelProperty(example = "unknown host", position = 8)
    private String serverErrorValue;

    @ApiModelProperty(example = "VLP1", position = 9)
    private String serverName;

    @ApiModelProperty(example = "1324", position = 10)
    private String uuid;


    @ApiModelProperty(example = "2018-12-12", position = 12)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    @JsonSerialize(using = CustomTimestampSerializer.class)
    private Timestamp archivalDate;


    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public long getVlpServErrorLogId() {
        return vlpServErrorLogId;
    }

    @JsonIgnore
    public void setVlpServErrorLogId(long vlpServErrorLogId) {
        this.vlpServErrorLogId = vlpServErrorLogId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }

    public String getErrorStackTrace() {
        return errorStackTrace;
    }

    public void setErrorStackTrace(String errorStackTrace) {
        this.errorStackTrace = errorStackTrace;
    }


    public String getServerErrorData() {
        return serverErrorData;
    }

    public void setServerErrorData(String serverErrorData) {
        this.serverErrorData = serverErrorData;
    }

    public String getServerErrorMsg() {
        return serverErrorMsg;
    }

    public void setServerErrorMsg(String serverErrorMsg) {
        this.serverErrorMsg = serverErrorMsg;
    }

    public String getServerErrorValue() {
        return serverErrorValue;
    }

    public void setServerErrorValue(String serverErrorValue) {
        this.serverErrorValue = serverErrorValue;
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

    @Override
    public String toString() {
        return "VlpServErrorLog [vlpServErrorLogId=" + vlpServErrorLogId + ", createdDate=" + createdDate
                + ", errorCode=" + errorCode + ", errorSource=" + errorSource + ", errorStackTrace=" + errorStackTrace
                + ", serverErrorData=" + serverErrorData + ", serverErrorMsg=" + serverErrorMsg + ", serverErrorValue="
                + serverErrorValue + ", serverName=" + serverName + ", uuid=" + uuid + ", archivalDate=" + archivalDate
                + "]";
    }


}
