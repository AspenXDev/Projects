package com.library.lms.model.enums;

public enum LoanStatus {
    Active("Active"),
    Returned("Returned");

    private final String dbValue;

    LoanStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static LoanStatus fromDb(String v) {
        for (LoanStatus s : values()) {
            if (s.dbValue.equals(v)) return s;
        }
        throw new IllegalArgumentException("Unknown loan status: " + v);
    }
}
