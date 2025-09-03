package com.library.lms.model.converters;

import com.library.lms.model.enums.LoanStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LoanStatusConverter implements AttributeConverter<LoanStatus, String> {

    @Override
    public String convertToDatabaseColumn(LoanStatus status) {
        return status == null ? null : status.getDbValue();
    }

    @Override
    public LoanStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : LoanStatus.fromDb(dbData);
    }
}
