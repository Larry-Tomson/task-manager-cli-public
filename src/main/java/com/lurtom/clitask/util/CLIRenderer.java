package com.lurtom.clitask.util;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.TimeFormat;
=======
import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.TimeFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
>>>>>>> main

public class CLIRenderer {
    private static final int DEFAULT_PADDING = 80;
    private static final Logger logger = new Logger();

<<<<<<< HEAD
    private CLIRenderer() {

=======
    public static void error(String message) {
        String messageStatus = CLIColor.BOLD_RED + "✕ FAILED " + CLIColor.RESET + message;
        log(messageStatus, false);
>>>>>>> main
    }

    public static String formatTime(LocalDateTime ldt, TimeFormat tf) {
        return ldt.format(DateTimeFormatter.ofPattern(tf.getFormat()));
    }

<<<<<<< HEAD
=======
    public static void info(String message) {
        String msg = CLIColor.BOLD_BLUE + message + CLIColor.RESET;
        log(msg, true);
    }

    public static void message(String message) {
        String msg = "  " + message.replace("\n", "\n  ");
        log(msg, false);
    }

    public static String padding(String message) {
        return "\u001B[2m" + String.format("%-" + (DEFAULT_PADDING - message.length()) + "s ", "").replace(" ", "*")
                        + "\u001B[0m";
    }

    public static void success(String message) {
        String msg = CLIColor.BOLD_GREEN + "✓ SUCCESS " + CLIColor.RESET + message;
        log(msg, true);
    }

    public static void warn(String message) {
        String msg = CLIColor.YELLOW + "▽ WARNING " + CLIColor.RESET + message;
        log(msg, false);
    }

>>>>>>> main
    private static void log(String msg, Boolean padding) {
        if (msg == null || msg.isEmpty()) {
            logger.warn("print to screen is null or empty");
            return;
        }
        if (padding && padding != null) {
            System.out.println(msg + " " + padding(msg));
        } else {
            System.out.println(String.format(msg));
        }
    }

<<<<<<< HEAD
    public static void info(String message) {
        String msg = CLIColor.BOLD_BLUE + message + CLIColor.RESET;
        log(msg, true);
    }

    public static void message(String message) {
        String msg = "  " + message.replace("\n", "\n  ");
        log(msg, false);
    }

    public static void success(String message) {
        String msg = CLIColor.BOLD_GREEN + "✓ SUCCESS " + CLIColor.RESET + message;
        log(msg, true);
    }

    public static void warn(String message) {
        String msg = CLIColor.YELLOW + "▽ WARNING " + CLIColor.RESET + message;
        log(msg, false);

    }

    public static void error(String message) {
        String messageStatus = CLIColor.BOLD_RED + "✕ FAILED " + CLIColor.RESET + message;
        log(messageStatus, false);
    }

    public static String padding(String message) {
        return "\u001B[2m" + String.format("%-" + (DEFAULT_PADDING - message.length()) + "s ", "").replace(" ", "*")
                + "\u001B[0m";
    }
}
=======
    private CLIRenderer() {}
}
>>>>>>> main
