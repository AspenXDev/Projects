package com.library.lms.model.converters;

import com.library.lms.model.enums.ReservationStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class ReservationStatusConverter implements AttributeConverter<ReservationStatus, String> {

    @Override
    public String convertToDatabaseColumn(ReservationStatus attribute) {
        return attribute == null ? null : attribute.getDbValue();
    }

    @Override
    public ReservationStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : ReservationStatus.fromDb(dbData);
    }
}