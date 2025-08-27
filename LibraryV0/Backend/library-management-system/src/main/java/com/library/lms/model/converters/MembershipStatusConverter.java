package com.library.lms.model.converters;

import com.library.lms.model.enums.MembershipStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class MembershipStatusConverter implements AttributeConverter<MembershipStatus, String> {

    @Override
    public String convertToDatabaseColumn(MembershipStatus attribute) {
        return attribute == null ? null : attribute.toDbValue();
    }

    @Override
    public MembershipStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : MembershipStatus.fromDbValue(dbData);
    }
}
