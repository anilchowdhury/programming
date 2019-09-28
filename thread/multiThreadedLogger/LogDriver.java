package thread.multiThreadedLogger;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author Anil Chowdhury
 * Created on 9/14/2019
 */
public class LogDriver {

    public static void main(String[] args) {
      testConsole(5);
//    testConsoleAndXmlFile(5);
    }

    private static void testConsole(int numberOfMessages) {
        Logger.addConsumer(new ConsoleConsumer(new LogFormatter()));
        initializeTestSuite(numberOfMessages);
    }

    private static void testConsoleAndXmlFile(int numberOfMessages) {
        Logger.addConsumer(new ConsoleConsumer(new LogFormatter()));
        Logger.addConsumer(new FileConsumer(new XmlFormatter(), "File_Service_Xml"));
        Logger.addConsumer(new FileConsumer(new LogFormatter(), "File_Service_Plain"));
        initializeTestSuite(numberOfMessages);
    }

    private static void initializeTestSuite(int numberOfMessages) {
        CountDownLatch latch = new CountDownLatch(4);

        Thread trackingService1 = new Thread(new Service("Tracking Service-1", LogLevel.INFO, numberOfMessages,
                10000, "Tracking Request", 0, latch));
        Thread trackingService2 = new Thread(new Service("Tracking Service-2", LogLevel.INFO, numberOfMessages,
                10000, "Tracking Request", numberOfMessages + numberOfMessages, latch));
        Thread orderService1 = new Thread(new Service("Order Service-1", LogLevel.INFO, numberOfMessages,
                10000, "Order Request", numberOfMessages + numberOfMessages, latch));
        Thread orderService2 = new Thread(new Service("Order Service-2", LogLevel.INFO, numberOfMessages,
                10000, "Order Request", 0, latch));

        try {
            startServices(trackingService1, trackingService2, orderService1, orderService2);
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static class Service implements Runnable {

        private Logger logger;
        private int numberOfTimeToPrint;
        private int sleep;
        private String messagePrefix;
        private int delta;
        private CountDownLatch latch;

        Service(String name, LogLevel level, int numberOfTimeToPrint, int sleep, String messagePrefix,
                int delta, CountDownLatch latch) {
            this.logger = Logger.getLogger(name, level);
            this.numberOfTimeToPrint = numberOfTimeToPrint;
            this.sleep = sleep;
            this.messagePrefix = messagePrefix;
            this.delta = delta;
            this.latch = latch;
        }

        @Override
        public void run() {
            for (int index = 0; index < numberOfTimeToPrint; index++) {
                sleep();
                logger.info(String.format("%s - %d", messagePrefix, index + delta));
            }
            latch.countDown();
        }

        private void sleep() {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(sleep));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startServices(Thread... services) {
        for (Thread service : services) {
            service.start();
        }
    }
}
