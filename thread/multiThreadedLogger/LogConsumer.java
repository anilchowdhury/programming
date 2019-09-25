package thread.multiThreadedLogger;

/**
 * @author Anil Chowdhury
 * Created on 9/14/2019
 */
abstract class LogConsumer {

    private LogFormatter formatter;

    LogConsumer(LogFormatter formatter) {
        this.formatter = formatter;
    }

    void consume(LogRecord record) {
        String message = formatter.format(record);
        consume(message);
    }

    abstract void consume(String message);
}
