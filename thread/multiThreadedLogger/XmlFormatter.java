package thread.multiThreadedLogger;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Anil Chowdhury
 * Created on 9/14/2019
 */
public class XmlFormatter extends LogFormatter {

    private static final String LOG_RECORD_START_TAG = "<LoggerRecord>";
    private static final String LOG_RECORD_END_TAG = "</LoggerRecord>";
    private static final String LOGGER_NAME_START_TAG = "<LoggerName>";
    private static final String LOGGER_NAME_END_TAG = "</LoggerName>";
    private static final String TIME_STAMP_START_TAG = "<TimeStamp>";
    private static final String TIME_STAMP_END_TAG = "</TimeStamp>";
    private static final String LOG_MESSAGE_START_TAG = "<Message>";
    private static final String LOG_MESSAGE_END_TAG = "</Message>";
    private static final String NEW_LINE_TAG = "\n";
    private static final String TAB_TAG = "\t";

    @Override
    public String
    format(@NotNull LogRecord record) {
        StringBuilder formattedMessage = new StringBuilder();
        formattedMessage.append(LOG_RECORD_START_TAG).append(NEW_LINE_TAG);

        formattedMessage.append(TAB_TAG);
        String loggerName = record.getLoggerName();
        formattedMessage.append(LOGGER_NAME_START_TAG).append(loggerName).
                append(LOGGER_NAME_END_TAG).append(NEW_LINE_TAG);

        formattedMessage.append(TAB_TAG);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(record.getTimestamp());
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedTime = cal.getTime().toString();
        formattedMessage.append(TIME_STAMP_START_TAG).append(formattedTime).
                append(TIME_STAMP_END_TAG).append(NEW_LINE_TAG);

        formattedMessage.append(TAB_TAG);
        String message = record.getMessage();
        formattedMessage.append(LOG_MESSAGE_START_TAG).append(message).
                append(LOG_MESSAGE_END_TAG).append(NEW_LINE_TAG);

        formattedMessage.append(LOG_RECORD_END_TAG).append(NEW_LINE_TAG);
        return formattedMessage.toString();
    }
}
