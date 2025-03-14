package com.lurtom.clitask.logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
<<<<<<< HEAD

public class Logger {
    private static final DateTimeFormatter FILE_TIME_FORMAT = DateTimeFormatter.ofPattern("yy-MM-dd");
    private static final String LOG_FORMAT = "[ %-30s] [%-5s] [%-35s] %s";
    private static final String DEFAULT_LOG_PATH = "./logs";
    private static final int DEFAULT_REMOVAL_DAY = 30;
=======
import java.util.stream.Stream;

public class Logger {
    private static final String DEFAULT_LOG_PATH = "./logs";
    private static final int DEFAULT_REMOVAL_DAY = 30;
    private static final DateTimeFormatter FILE_TIME_FORMAT = DateTimeFormatter.ofPattern("yy-MM-dd");
    private static final String LOG_FORMAT = "[ %-30s] [%-5s] [%-35s] %s";
>>>>>>> main
    private static boolean isDebug = false;
    private static boolean isTrace = false;

    public static void logDebug() {
        Logger.isDebug = !Logger.isDebug;
    }

    public static void logTrace() {
        Logger.isTrace = !Logger.isTrace;
    }

<<<<<<< HEAD
    /**
     * I though this would be cool to implement
=======
    private static void cleanUpHelper(Path file) {
        try {
            LocalDateTime time = LocalDateTime.ofInstant( //
                            Files.getLastModifiedTime(file).toInstant(), ZoneId.systemDefault());

            if (time.isBefore(LocalDateTime.now().minusDays(DEFAULT_REMOVAL_DAY))) {
                System.out.println("deleting: " + file.getFileName());
                Files.delete(file);
            }

        } catch (IOException e) {
            System.out.println("Failed to deleted log file(s)" + e.getMessage());
        }
    }

    /**
     * Create new directory if not exist and return its absolute path
     *
     * @return absolute path default value is DEFAULT_LOG_PATH
     * @throws IOException I once saw something about security, folder only allow readonly, prevent from
     *         writing.
     */
    private static Path getPath() throws IOException {
        try {
            Path path = Paths.get(DEFAULT_LOG_PATH);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            return path.toAbsolutePath();

        } catch (IOException e) {
            throw new IOException("Failed to create log directory" + e.getStackTrace());
        }
    }

    /**
     * I though this would be cool to implement
     *
>>>>>>> main
     * @param message original message with placeholder
     * @param args stuffs
     * @return replaced placeholder with arguments
     */
    private static String logMessageHelper(String message, Object... args) {
        if (message == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] components = message.split("\\{}", -1);
        int argIndex = 0;

        for (int i = 0; i < components.length; i++) {
            sb.append(components[i]);
            if (i < components.length - 1 && argIndex < args.length) {
                sb.append(args[argIndex] != null ? args[argIndex].toString() : "");
                argIndex++;
            }
        }
        return sb.toString().trim();
    }

<<<<<<< HEAD
    private static void cleanUpHelper(Path file) {
        try {
            LocalDateTime time = LocalDateTime.ofInstant(
                    Files.getLastModifiedTime(file).toInstant(),
                    ZoneId.systemDefault());
            if (time.isBefore(LocalDateTime.now().minusDays(DEFAULT_REMOVAL_DAY))) {
                System.out.println("deleting: " + file.getFileName());
                Files.delete(file);
            }
        } catch (IOException e) {
            System.out.println("Failed to deleted log file(s)" + e.getMessage());
        }
    }

    /**
    * Create new directory if not exist and return its absolute path
    *
    * @return absolute path default value is DEFAULT_LOG_PATH
    * @throws IOException I once saw something about security, folder only allow
    *                     readonly, prevent from writing.
    */

    private static Path getPath() {
        try {
            Path path = Paths.get(DEFAULT_LOG_PATH);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            return path.toAbsolutePath();

        } catch (IOException e) {
            throw new RuntimeException("Failed to create log directory" + e.getStackTrace());
        }
    }

    public void info(String message, Object... args) {
        log(Level.INFO, message, args);
    }

=======
    public void cleanup() {
        try (Stream<Path> entries = Files.walk(Paths.get(DEFAULT_LOG_PATH))) {
            System.out.println("Cleaning up log files");
            entries.filter(Files::isRegularFile) //
                            .filter(file -> file.toString().endsWith((".log"))) //
                            .forEach(Logger::cleanUpHelper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

>>>>>>> main
    public void debug(String message, Object... args) {
        log(Level.DEBUG, message, args);
    }

<<<<<<< HEAD
    public void trace(String message, Object... args) {
        log(Level.TRACE, message, args);
    }

    public void warn(String message, Object... args) {
        log(Level.WARN, message, args);
    }

=======
>>>>>>> main
    public void error(String message, Throwable throwable) {
        try (StringWriter sw = new StringWriter()) {
            try (PrintWriter pw = new PrintWriter(sw)) {
                throwable.printStackTrace(pw);
            }
            log(Level.ERROR, message + "\n" + sw.toString());
        } catch (Exception e) {
            System.out.println("Failed to log error" + e.getMessage());
        }
    }

<<<<<<< HEAD
    public void cleanup() {
        try {
            Files.walk(Paths.get(DEFAULT_LOG_PATH))
                    .filter(Files::isRegularFile)
                    .filter(file -> file
                            .toString()
                            .endsWith((".log")))
                    .forEach(Logger::cleanUpHelper);
        } catch (Exception e) {
            e.printStackTrace();
        }
=======
    public void info(String message, Object... args) {
        log(Level.INFO, message, args);
    }

    public void trace(String message, Object... args) {
        log(Level.TRACE, message, args);
    }

    public void warn(String message, Object... args) {
        log(Level.WARN, message, args);
    }

    private String getSource() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        for (StackTraceElement e : ste) {
            if (!e.getClassName().equals(this.getClass().getName()) && //
                            !e.getMethodName().equals("getStackTrace") && //
                            !e.getMethodName().startsWith("lambda") && //
                            !e.getClassName().startsWith("java.util")) {
                // remove artifact & org from method name
                return e.getClassName().split("[.]", 4)[3] + "." + e.getMethodName();
            }
        }
        return "Unknown";
>>>>>>> main
    }

    /**
     * Logs events on runtime and initialization process
<<<<<<< HEAD
     * -----------------------------------------------------
     * | Files.write(path, (logMessage + "\n").getBytes(), |
     * | StandardOpenOption.CREATE,						   *
     * | StandardOpenOption.APPEND);					   *
     * ----------> java 8						           *
     *
     * @param level   log levels (INFO | DEBUG | TRACE | ERROR )
     * @param message
     */

=======
     * ----------------------------------------------------- | Files.write(path, (logMessage +
     * "\n").getBytes(), | | StandardOpenOption.CREATE, * | StandardOpenOption.APPEND); * ---------->
     * java 8 *
     *
     * @param level log levels (INFO | DEBUG | TRACE | ERROR )
     * @param message
     */
>>>>>>> main
    private void log(Level level, String message, Object... args) {
        if (!isDebug && level == Level.DEBUG) {
            return;
        }
        if (!isTrace && level == Level.TRACE) {
            return;
        }
        String today = LocalDateTime.now().format(FILE_TIME_FORMAT);

        try {
            Path pathName = getPath();
            String source = getSource();
            String parsedMessage = logMessageHelper(message, args);
            String logMessage = String.format(LOG_FORMAT, LocalDateTime.now(), level, source, parsedMessage);
            Path path = pathName.resolve(today + ".log");
<<<<<<< HEAD
            Files.writeString(path, logMessage + "\n",
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
=======
            Files.writeString(path, logMessage + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
>>>>>>> main
        } catch (IOException e) {
            System.out.println("Log failed" + e.getMessage());
        }
    }
<<<<<<< HEAD

    private String getSource() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        for (StackTraceElement e : ste) {
            if (!e.getClassName().equals(this.getClass().getName()) &&
                    !e.getMethodName().equals("getStackTrace") &&
                    !e.getMethodName().startsWith("lambda") &&
                    !e.getClassName().startsWith("java.util")) {
                //remove artifact & org from name
                return e.getClassName().split("[.]", 4)[3] + "." + e.getMethodName();
            }
        }
        return "Unknown";
    }
}
=======
}
>>>>>>> main
