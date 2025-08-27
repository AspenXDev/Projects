package com.library.lms.model.enums;

public enum BookStatus {
    AVAILABLE, // DB value: "Available"
    BORROWED,  // DB value: "Borrowed"
    RESERVED;  // DB value: "Reserved"

    public String toDbValue() {
        switch (this) {
            case AVAILABLE: return "Available";
            case BORROWED: return "Borrowed";
            case RESERVED: return "Reserved";
            default: throw new IllegalStateException("Unknown BookStatus: " + this);
        }
    }

    public static BookStatus fromDbValue(String dbValue) {
        if (dbValue == null) return null;
        switch (dbValue) {
            case "Available": return AVAILABLE;
            case "Borrowed": return BORROWED;
            case "Reserved": return RESERVED;
            default: throw new IllegalArgumentException("Unknown book status DB value: " + dbValue);
        }
    }
}
