package com.hbe.ms.common.utils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class CustomDateSerializer extends JsonSerializer<Timestamp> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.CENTURY_DATE_FORMAT_WITH_DASHES);

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String formattedDate = dateFormat.format(timestamp);
        jsonGenerator.writeString(formattedDate);
    }
}