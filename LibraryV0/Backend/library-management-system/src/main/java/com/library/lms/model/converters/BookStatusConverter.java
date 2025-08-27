package com.library.lms.model.converters;

import com.library.lms.model.enums.BookStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class BookStatusConverter implements AttributeConverter<BookStatus, String> {

    @Override
    public String convertToDatabaseColumn(BookStatus attribute) {
        return attribute == null ? null : attribute.toDbValue();
    }

    @Override
    public BookStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : BookStatus.fromDbValue(dbData);
    }
}
