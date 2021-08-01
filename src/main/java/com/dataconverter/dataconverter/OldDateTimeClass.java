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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date dateWithTime2;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
   private Date dateWithTime3;
}
