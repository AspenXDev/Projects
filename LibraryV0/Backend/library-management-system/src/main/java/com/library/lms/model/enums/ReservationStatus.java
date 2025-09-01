package com.library.lms.model.enums;

public enum ReservationStatus {
    Waiting("Waiting"),
    OnHold("On Hold"),
    Collected("Collected"),
    Cancelled("Cancelled");

    private final String dbValue;

    ReservationStatus(String dbValue) { this.dbValue = dbValue; }

    public String getDbValue() { return dbValue; }

    public static ReservationStatus fromDb(String v) {
        for (ReservationStatus s : values()) {
            if (s.dbValue.equalsIgnoreCase(v)) return s;
        }
        throw new IllegalArgumentException("Unknown reservation status: " + v);
    }
}