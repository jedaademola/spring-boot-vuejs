package com.hbe.ms.common.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrayOfErrorResponseMetaData {

    List<ErrorResponseMetaData> errorResponseMetaData;

    public List<ErrorResponseMetaData> getErrorResponseMetaData() {
        return errorResponseMetaData;
    }

    public void setErrorResponseMetaData(List<ErrorResponseMetaData> errorResponseMetaData) {
        this.errorResponseMetaData = errorResponseMetaData;
    }
}
