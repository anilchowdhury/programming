package thread.multiThreadedLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Anil Chowdhury
 * Created on 9/14/2019
 */
class FileConsumer extends LogConsumer {

    private FileOutputStream outputStream;

    FileConsumer(LogFormatter formatter, String serviceName) {
        super(formatter);
        initializeLogFile(serviceName);
    }

    private void initializeLogFile(String serviceName) {
        String workingDirectory = System.getProperty("user.dir");
        File logFile = new File(workingDirectory + File.separator + serviceName + ".log");
        try {
            if (!logFile.exists() && logFile.createNewFile()) {
                outputStream = new FileOutputStream(logFile);
            } else {
                outputStream = new FileOutputStream(logFile, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void consume(String message) {
        try {
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
