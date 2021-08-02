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

		LocalDateTime localDateTimeWinter = LocalDateTime.of(localDateWinter, LocalTime.of(22, 0, 0));

		ZonedDateTime zonedDateTimeWinter = ZonedDateTime.of(localDateTimeWinter, zoneId);

		OffsetDateTime offsetDateTimeWinter = OffsetDateTime.of(localDateTimeWinter, zonedDateTimeWinter.getOffset());

		NewDateTimeClass newDateTimeClassWinter   = NewDateTimeClass.builder()
				.date(localDateWinter)
				.dateWithTime1(localDateTimeWinter)
				.dateWithTime2(zonedDateTimeWinter)
				.dateWithTime3(zonedDateTimeWinter.withZoneSameInstant(ZoneOffset.UTC))
				.dateWithTime4(offsetDateTimeWinter)
				.build();

		String newDatesStringWinterString = serializingObjectMapper.writeValueAsString(newDateTimeClassWinter);

		OldDateTimeClass oldDateTimeClassWinter = serializingObjectMapper.readValue(newDatesStringWinterString, OldDateTimeClass.class);

		assertEquals(oldDateTimeClassWinter.getDate().toInstant(), ZonedDateTime.of(localDateWinter, LocalTime.MIDNIGHT, zoneId).toInstant());
		assertEquals(oldDateTimeClassWinter.getDateWithTime1().toInstant(),zonedDateTimeWinter.toInstant());
		assertEquals(oldDateTimeClassWinter.getDateWithTime2().toInstant(),zonedDateTimeWinter.toInstant());
		assertEquals(oldDateTimeClassWinter.getDateWithTime3().toInstant(),zonedDateTimeWinter.toInstant());

		System.out.println("NEW " +  newDateTimeClassWinter);
		System.out.println("NEW string" + newDatesStringWinterString);
		System.out.println("OLD" + oldDateTimeClassWinter);
		System.out.println(oldDateTimeClassWinter.getDate());
		System.out.println(oldDateTimeClassWinter.getDateWithTime1());
		System.out.println(oldDateTimeClassWinter.getDateWithTime2());
		System.out.println(oldDateTimeClassWinter.getDateWithTime3());
		System.out.println(oldDateTimeClassWinter.getDateWithTime4());
	}


	@Test
	public void testConvertDateSummer() throws JsonProcessingException {
		ZoneId zoneId = ZoneId.of("Europe/Warsaw");

		//UTC +2

		LocalDate localDateSummer = LocalDate.of(2020, 7, 12);

		LocalDateTime localDateTimeSummer = LocalDateTime.of(localDateSummer, LocalTime.of(22, 0, 0));

		ZonedDateTime zonedDateTimeSummer = ZonedDateTime.of(localDateTimeSummer, zoneId);

		OffsetDateTime offsetDateTimeSummer = OffsetDateTime.of(localDateTimeSummer, zonedDateTimeSummer.getOffset());

		NewDateTimeClass newDateTimeClassSummer = NewDateTimeClass.builder()
				.date(localDateSummer)
				.dateWithTime1(localDateTimeSummer)
				.dateWithTime2(zonedDateTimeSummer)
				.dateWithTime3(zonedDateTimeSummer.withZoneSameInstant(ZoneOffset.UTC))
				.dateWithTime4(offsetDateTimeSummer)
				.build();


		String newDateTimeClassSummerString = serializingObjectMapper.writeValueAsString(newDateTimeClassSummer);

		OldDateTimeClass oldDateTimeClassSummer = serializingObjectMapper.readValue(newDateTimeClassSummerString, OldDateTimeClass.class);

		assertEquals(oldDateTimeClassSummer.getDate().toInstant(), ZonedDateTime.of(localDateSummer, LocalTime.MIDNIGHT, zoneId).toInstant());
		assertEquals(oldDateTimeClassSummer.getDateWithTime1().toInstant(),zonedDateTimeSummer.toInstant());
		assertEquals(oldDateTimeClassSummer.getDateWithTime2().toInstant(),zonedDateTimeSummer.toInstant());
		assertEquals(oldDateTimeClassSummer.getDateWithTime3().toInstant(),zonedDateTimeSummer.toInstant());


		System.out.println("NEW " +  newDateTimeClassSummer);
		System.out.println("NEW string" + newDateTimeClassSummerString);
		System.out.println("OLD" + oldDateTimeClassSummer);
		System.out.println(oldDateTimeClassSummer.getDate());
		System.out.println(oldDateTimeClassSummer.getDateWithTime1());
		System.out.println(oldDateTimeClassSummer.getDateWithTime2());
		System.out.println(oldDateTimeClassSummer.getDateWithTime3());
		System.out.println(oldDateTimeClassSummer.getDateWithTime4());
	}
}
