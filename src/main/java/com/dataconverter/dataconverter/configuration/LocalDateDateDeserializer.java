package com.dataconverter.dataconverter.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateDateDeserializer extends JsonDeserializer<Date> {

    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Warsaw");
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        TextNode node = oc.readTree(jp);
        String dateString = node.textValue();

        LocalDate parse = LocalDate.parse(dateString, LOCAL_DATE_FORMATTER);

        return Date.from(parse.atStartOfDay().atZone(ZONE_ID).toInstant());
    }
}