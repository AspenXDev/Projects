package com.library.lms.model.converters;

import com.library.lms.model.enums.BookStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, String> {

    @Override
    public String convertToDatabaseColumn(BookStatus status) {
        return status == null ? null : status.name();
    }

    @Override
    public BookStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : BookStatus.valueOf(dbData);
    }
}
