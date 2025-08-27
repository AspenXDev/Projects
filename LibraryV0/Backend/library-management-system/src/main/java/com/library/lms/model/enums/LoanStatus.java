package com.library.lms.model.enums;

public enum LoanStatus {
    ACTIVE,    // DB value: "Active"
    RETURNED;  // DB value: "Returned"

    public String toDbValue() {
        switch (this) {
            case ACTIVE: return "Active";
            case RETURNED: return "Returned";
            default: throw new IllegalStateException("Unknown LoanStatus: " + this);
        }
    }

    public static LoanStatus fromDbValue(String dbValue) {
        if (dbValue == null) return null;
        switch (dbValue) {
            case "Active": return ACTIVE;
            case "Returned": return RETURNED;
            default: throw new IllegalArgumentException("Unknown loan status DB value: " + dbValue);
        }
    }
}
