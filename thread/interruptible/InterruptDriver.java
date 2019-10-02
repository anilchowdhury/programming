package thread.interruptible;

import java.util.Random;

/**
 * @author Anil Chowdhury
 * Created on 9/29/2019
 */
public class InterruptDriver {

    public static void main(String[] args) {

        Thread orderService = new Thread(new InterruptService("Order Service"), "Order Service");

        StoppableService emailServiceRunnable = new StoppableService("Email Service");
        Thread emailService = new Thread(emailServiceRunnable, "Email Service");

        Thread stopService = new Thread(new ServiceStopper(5000, emailServiceRunnable, orderService));

        orderService.start();
        emailService.start();
        stopService.start();
        try {
            orderService.join();
            emailService.join();
            stopService.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    static void sleep() throws InterruptedException {
        Random random = new Random();
        sleep(random.nextInt(5000));
    }

    static void sleep(int sleepTime) throws InterruptedException {
        Thread.sleep(sleepTime);
    }
}
