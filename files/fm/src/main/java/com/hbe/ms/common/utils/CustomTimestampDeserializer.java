package com.hbe.ms.common.utils;

import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Converts String in format YYYYMMDDHHMMSS to Timestamp
 */
public class CustomTimestampDeserializer extends JsonDeserializer<Timestamp> {

	@Override
	public Timestamp deserialize(JsonParser jsonparser, DeserializationContext context) {
		try {
            return DateUtils.formatStringToTimestamp(jsonparser.getText(), DateUtils.YYYYMMDDHHMMSS);
			} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
