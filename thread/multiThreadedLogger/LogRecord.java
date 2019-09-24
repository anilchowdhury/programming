package thread.multiThreadedLogger;

/**
 * @author Anil Chowdhury
 * Created on 9/14/2019
 */
public class LogRecord {

    private String loggerName;
    private long timestamp;
    private String message;
    private String logLevel;

    private LogRecord(String loggerName, String message, String logLevel) {
        this.loggerName = loggerName;
        this.timestamp = System.currentTimeMillis();
        this.message = message;
        this.logLevel = logLevel;
    }

    String getLoggerName() {
        return loggerName;
    }

    long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    String getLogLevel() {
        return logLevel;
    }

    static LogRecord create(String loggerName, String message, String logLevel) {
        return new LogRecord(loggerName, message, logLevel);
    }
}
