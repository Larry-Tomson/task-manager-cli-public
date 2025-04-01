package com.lurtom.clitask.model;

public enum TimeFormat {
    LONG("yy/MM/dd HH:mm"),
    SHORT("MM/dd HH:mm");

    private String format;

    TimeFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
