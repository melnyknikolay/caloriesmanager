package ru.javawebinar.topjava.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Created by nik_PC on 27.11.2016.
 */
@Converter(autoApply = true)
public class LocalDatePersisteceConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    public LocalDatePersisteceConverter() {
    }

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
        return Timestamp.valueOf(entityValue);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {
        return databaseValue.toLocalDateTime();
    }

}