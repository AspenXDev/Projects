package com.library.lms.model.enums;

public enum ReservationStatus {
    WAITING,   // DB "Waiting"
    ON_HOLD,   // DB "On Hold"
    COLLECTED, // DB "Collected"
    CANCELLED; // DB "Cancelled"

    public String toDbValue() {
        switch (this) {
            case WAITING: return "Waiting";
            case ON_HOLD: return "On Hold";
            case COLLECTED: return "Collected";
            case CANCELLED: return "Cancelled";
            default: throw new IllegalStateException("Unknown ReservationStatus: " + this);
        }
    }

    public static ReservationStatus fromDbValue(String dbValue) {
        if (dbValue == null) return null;
        switch (dbValue) {
            case "Waiting": return WAITING;
            case "On Hold": return ON_HOLD;
            case "Collected": return COLLECTED;
            case "Cancelled": return CANCELLED;
            default: throw new IllegalArgumentException("Unknown reservation status DB value: " + dbValue);
        }
    }
}
