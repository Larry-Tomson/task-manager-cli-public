package com.lurtom.clitask.model;

import com.lurtom.clitask.util.CLIColor;

public enum Status {
    COMPLETED("completed", CLIColor.GREEN), IN_PROGRESS("in progress", CLIColor.MAGENTA), ON_HOLD("on hold",
                    CLIColor.BLUE), PENDING("pending", CLIColor.YELLOW);

    public static Status getEnum(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Expected non null Status value");
        }
        if (value.contains("_")) {

            value = value.replace("_", " ");
        }
        for (Status status : Status.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }

    private final String color;

    private final String value;

    Status(String value, String color) {
        this.value = value;
        this.color = color;
    }

    /**
     * Get enum from string fixed number of command and fixed number of Status, this can't go wrong...
     * right ?
     *
     * @param value Value of status string
     * @return Status enum type
     */
    public String getColorStr() {
        return this.color + this.value + CLIColor.RESET;
    }

    public String getValueStr() {
        return this.value;
    }
}
