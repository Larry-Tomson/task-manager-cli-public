package com.lurtom.clitask.model;

public enum TimeFormat {
<<<<<<< HEAD

    SHORT("MM/dd HH:mm"),
    LONG("yy/MM/dd HH:mm");
=======
    LONG("yy/MM/dd HH:mm"), SHORT("MM/dd HH:mm");
>>>>>>> main

    private String format;

    TimeFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> main
