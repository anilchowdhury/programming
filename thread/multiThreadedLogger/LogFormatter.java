package thread.multiThreadedLogger;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Anil Chowdhury
 * Created on 9/14/2019
 */
public class LogFormatter {

    public String format(@NotNull LogRecord record) {
        String formattedTimeStamp = getFormattedTimeStamp(record.getTimestamp());
        String logLevel = record.getLogLevel();
        String loggerName = record.getLoggerName();
        String message = record.getMessage();
        return String.format("%s %s [%s] %s\n", formattedTimeStamp, logLevel, loggerName, message);
    }

    private String getFormattedTimeStamp(long time) {
        LocalDateTime localDateTime = new Timestamp(time).toLocalDateTime();
        return getFormattedString(localDateTime);
    }

    private String getFormattedString(LocalDateTime localDateTime) {
        int dayOfMonth = localDateTime.getDayOfMonth();
        int month = localDateTime.getMonth().ordinal() + 1;
        int year = localDateTime.getYear();
        String hour = localDateTime.getHour() < 10 ? String.format("0%d", localDateTime.getHour()) :
                String.valueOf(localDateTime.getHour());
        String minute = localDateTime.getMinute() < 10 ? String.format("0%d", localDateTime.getMinute()) :
                String.valueOf(localDateTime.getMinute());
        String second = localDateTime.getSecond() < 10 ? String.format("0%d", localDateTime.getSecond()) :
                String.valueOf(localDateTime.getSecond());
        int nanoSecond = localDateTime.getNano();

        return String.format("%d-%d-%d %s:%s:%s:%d", dayOfMonth, month, year, hour, minute, second, nanoSecond);
    }

    /*private String getFormattedTimeStamp(long time) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return getFormattedString(localDateTime);
    }*/
}
