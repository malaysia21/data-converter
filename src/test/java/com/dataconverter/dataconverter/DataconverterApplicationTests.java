package com.dataconverter.dataconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DataConverterApplicationTests {

	@Autowired
	private ObjectMapper serializingObjectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void testConvertDateWinter() throws JsonProcessingException {
		ZoneId zoneId = ZoneId.of("Europe/Warsaw");

		//UTC +1
		LocalDate localDateWinter = LocalDate.of(2020, 12, 12);

		LocalDateTime localDateTimeWinter = LocalDateTime.of(localDateWinter, LocalTime.of(22, 0));

		ZonedDateTime zonedDateTimeWinter = ZonedDateTime.of(localDateTimeWinter, zoneId);

		OffsetDateTime offsetDateTimeWinter = OffsetDateTime.of(localDateTimeWinter, zonedDateTimeWinter.getOffset());

		NewDateTimeClass newDatesWinter = NewDateTimeClass.builder()
				.date(localDateWinter)
				.dateWithTime1(localDateTimeWinter)
				.dateWithTime2(zonedDateTimeWinter)
				.dateWithTime3(offsetDateTimeWinter)
				.build();

		String newDatesStringWinter = serializingObjectMapper.writeValueAsString(newDatesWinter);

		OldDateTimeClass oldDateTimeClassWinter = serializingObjectMapper.readValue(newDatesStringWinter, OldDateTimeClass.class);

		assertEquals(oldDateTimeClassWinter.getDate().toInstant(), ZonedDateTime.of(localDateWinter, LocalTime.MIDNIGHT, zoneId).toInstant());
		assertEquals(oldDateTimeClassWinter.getDateWithTime1().toInstant(),zonedDateTimeWinter.toInstant());
		assertEquals(oldDateTimeClassWinter.getDateWithTime2().toInstant(),zonedDateTimeWinter.toInstant());
		assertEquals(oldDateTimeClassWinter.getDateWithTime3().toInstant(),zonedDateTimeWinter.toInstant());

		System.out.println(newDatesStringWinter);
		System.out.println(oldDateTimeClassWinter);
	}


	@Test
	public void testConvertDateSummer() throws JsonProcessingException {
		ZoneId zoneId = ZoneId.of("Europe/Warsaw");

		//UTC +2

		LocalDate localDateSummer = LocalDate.of(2020, 7, 12);

		LocalDateTime localDateTimeSummer = LocalDateTime.of(localDateSummer, LocalTime.of(22, 0));

		ZonedDateTime zonedDateTimeSummer = ZonedDateTime.of(localDateTimeSummer, zoneId);

		OffsetDateTime offsetDateTimeSummer = OffsetDateTime.of(localDateTimeSummer, zonedDateTimeSummer.getOffset());

		NewDateTimeClass newDatesSummer = NewDateTimeClass.builder()
				.date(localDateSummer)
				.dateWithTime1(localDateTimeSummer)
				.dateWithTime2(zonedDateTimeSummer)
				.dateWithTime3(offsetDateTimeSummer)
				.build();


		String newDatesStringSummer = serializingObjectMapper.writeValueAsString(newDatesSummer);

		OldDateTimeClass oldDateTimeClassSummer = serializingObjectMapper.readValue(newDatesStringSummer, OldDateTimeClass.class);

		assertEquals(oldDateTimeClassSummer.getDate().toInstant(), ZonedDateTime.of(localDateSummer, LocalTime.MIDNIGHT, zoneId).toInstant());
		assertEquals(oldDateTimeClassSummer.getDateWithTime1().toInstant(),zonedDateTimeSummer.toInstant());
		assertEquals(oldDateTimeClassSummer.getDateWithTime2().toInstant(),zonedDateTimeSummer.toInstant());
		assertEquals(oldDateTimeClassSummer.getDateWithTime3().toInstant(),zonedDateTimeSummer.toInstant());

		System.out.println(newDatesStringSummer);
		System.out.println(oldDateTimeClassSummer);
	}
}
