package thread.multiThreadedLogger;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Anil Chowdhury
 * Created on 9/14/2019
 */
class ConsoleConsumer extends LogConsumer {

    private OutputStream writer;

    ConsoleConsumer(LogFormatter formatter) {
        this(formatter, System.out);
    }

    private ConsoleConsumer(LogFormatter formatter, OutputStream writer) {
        super(formatter);
        this.writer = writer;
    }

    @Override
    void consume(String message) {
        try {
            writer.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
