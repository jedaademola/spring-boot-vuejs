package com.hbe.ms.common.utils;

import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Converts String in format MM/dd/yyyy to Date
 */
public class CustomDateDeserializer extends JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonParser jsonparser, DeserializationContext context) {
        try {
            return DateUtils.formatStringToTimestamp(jsonparser.getText(), DateUtils.CENTURY_DATE_FORMAT_WITH_DASHES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
