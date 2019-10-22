package thread.multiThreadedLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anil Chowdhury
 * Created on 9/14/2019
 */
public class Logger {

    private LogLevel level;
    private String name;
    private static final List<LogConsumer> logConsumers = new ArrayList<>();
    private static final LoggerRepository repository = new LoggerRepository();

    private Logger(String name, LogLevel level) {
        this.level = level;
        this.name = name;
    }

    static Logger getLogger(String name, LogLevel level) {
        Logger logger = repository.getLogger(name);
        if (logger == null) {
            logger = createLogger(name, level);
        }
        return logger;
    }

    static void addConsumer(LogConsumer consumer) {
        logConsumers.add(consumer);
    }

    void info(String message) {
        logRecord(LogLevel.INFO, message);
    }

    public void warn(String message) {
        logRecord(LogLevel.WARN, message);
    }

    public void debug(String message) {
        logRecord(LogLevel.DEBUG, message);
    }

    public void error(String message) {
        logRecord(LogLevel.ERROR, message);
    }

    public void setLogLevel(LogLevel level) {
        this.level = level;
    }

    private boolean isLoggable(LogLevel level) {
        return this.level.ordinal() >= level.ordinal();
    }

    private void logRecord(LogLevel requestedLogLevel, String message) {
        if (!isLoggable(requestedLogLevel)) {
            return;
        }
        LogRecord logRecord = LogRecord.create(name, message, level.name());
        for (LogConsumer consumer : logConsumers) {
            consumer.consume(logRecord);
        }
    }

    private static Logger createLogger(String name, LogLevel level) {
        Logger logger;
        synchronized (Logger.class) {
            logger = new Logger(name, level);
            repository.addLogger(name, logger);
        }
        return logger;
    }
}
