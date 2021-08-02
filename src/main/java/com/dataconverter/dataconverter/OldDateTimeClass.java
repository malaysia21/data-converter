package com.dataconverter.dataconverter;

import com.dataconverter.dataconverter.configuration.LocalDateDateDeserializer;
import com.dataconverter.dataconverter.configuration.LocalDateTimeDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OldDateTimeClass {

    @JsonDeserialize(using = LocalDateDateDeserializer.class)
    private Date date;

    @JsonDeserialize(using = LocalDateTimeDateDeserializer.class)
    private Date dateWithTime1;

    private Date dateWithTime2;

    private Date dateWithTime3;

    private Date dateWithTime4;
}
