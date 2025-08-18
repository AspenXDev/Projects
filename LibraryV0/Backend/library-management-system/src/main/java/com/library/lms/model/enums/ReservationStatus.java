package com.library.lms.model.enums;
// helper for Reservation.java
public enum ReservationStatus {
    Waiting("Waiting"),
    OnHold("On Hold"),
    Collected("Collected"),
    Cancelled("Cancelled");

    private final String dbValue;
    ReservationStatus(String dbValue) { this.dbValue = dbValue; }
    public String getDbValue() { return dbValue; }

    public static ReservationStatus fromDb(String v) {
        for (var s : values()) if (s.dbValue.equals(v)) return s;
        throw new IllegalArgumentException("Unknown reservation status: " + v);
    }
}
