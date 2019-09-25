package thread.multiThreadedLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anil Chowdhury
 * Created on 9/14/2019
 */
class LoggerRepository {
    private Map<String, Logger> repository = new HashMap<>();

    Logger getLogger(String name) {
        return repository.get(name);
    }

    void addLogger(String name, Logger logger) {
        repository.put(name, logger);
    }
}
