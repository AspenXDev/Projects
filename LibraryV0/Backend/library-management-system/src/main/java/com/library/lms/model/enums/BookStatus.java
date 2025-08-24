package com.library.lms.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BookStatus {
	Available,
    Borrowed,
    Reserved;

//    @JsonCreator
//    public static BookStatus from(String value) {
//        if (value == null) return AVAILABLE;
//       return BookStatus.valueOf(value.toUpperCase());
//    }
}
