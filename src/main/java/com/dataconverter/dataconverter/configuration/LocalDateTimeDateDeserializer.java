package com.dataconverter.dataconverter.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTimeDateDeserializer extends JsonDeserializer<Date> {

    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Warsaw");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        ObjectCodec oc = jp.getCodec();
        TextNode node = oc.readTree(jp);
        String dateString = node.textValue();

        LocalDateTime parse = LocalDateTime.parse(dateString, formatter);

        return java.util.Date.from(parse.atZone(ZONE_ID).toInstant());
    }
}