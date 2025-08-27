package com.library.lms.model.converters;

import com.library.lms.model.enums.LoanStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class LoanStatusConverter implements AttributeConverter<LoanStatus, String> {

    @Override
    public String convertToDatabaseColumn(LoanStatus attribute) {
        return attribute == null ? null : attribute.toDbValue();
    }

    @Override
    public LoanStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : LoanStatus.fromDbValue(dbData);
    }
}
