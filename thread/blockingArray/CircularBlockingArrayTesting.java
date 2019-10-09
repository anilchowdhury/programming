package thread.blockingArray;

import java.util.Random;

/**
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public class CircularBlockingArrayTesting {
    public static void main(String[] args) {
//      singleProducerSingleConsumer(5);
//      singleProducerMultipleConsumers(5);
        multipleProducerMultipleConsumers(5);
    }

    private static void multipleProducerMultipleConsumers(int capacity) {
        CircularBlockingQueue<Integer> array = getBlockingArray(capacity, 2, Integer[].class);
        Random random = new Random();
        Thread producer1 = new Thread(() -> {
            for (int i = 0; i < capacity; i++) {
                try {
                    array.add(2 * i + 1);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer1");
        Thread producer2 = new Thread(() -> {
            for (int i = 0; i < capacity; i++) {
                try {
                    array.add(2 * i + 2);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer2");

        Thread consumer1 = new Thread(() -> {
            for (int i = 1; i <= capacity; i++) {
                try {
                    Thread.sleep(random.nextInt(1000));
                    array.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("%s exiting as it has consumed it quota of %d items",
                    Thread.currentThread().getName(), capacity));
        }, "Consumer1");
        Thread consumer2 = new Thread(() -> {
            for (int i = 1; i <= capacity; i++) {
                try {
                    Thread.sleep(random.nextInt(1000));
                    array.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("%s exiting as it has consumed it quota of %d items",
                    Thread.currentThread().getName(), capacity));
        }, "Consumer2");
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void singleProducerMultipleConsumers(int input) {
        CircularBlockingQueue<Integer> array = getBlockingArray(input, 1, Integer[].class);
        Random random = new Random();
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 2 * input; i++) {
                try {
                    array.add(i);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer1");

        Thread consumer1 = new Thread(() -> {
            for (int i = 1; i <= input; i++) {
                try {
                    Thread.sleep(random.nextInt(10000));
                    array.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("%s exiting as it has consumed it quota of %d items",
                    Thread.currentThread().getName(), input));
        }, "Consumer1");
        Thread consumer2 = new Thread(() -> {
            for (int i = 1; i <= input; i++) {
                try {
                    Thread.sleep(random.nextInt(10000));
                    array.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("%s exiting as it has consumed it quota of %d items",
                    Thread.currentThread().getName(), input));
        }, "Consumer2");
        producer.start();
        consumer1.start();
        consumer2.start();
        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void singleProducerSingleConsumer(int input) {
        CircularBlockingQueue<Integer> array = getBlockingArray(input, 0, Integer[].class);
        Random random = new Random();
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 2 * input; i++) {
                try {
                    array.add(i);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer");
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 2 * input; i++) {
                try {
                    Thread.sleep(random.nextInt(5000));
                    array.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static <T> CircularBlockingQueue<T> getBlockingArray(int capacity, int type, Class<T[]> elementType) {
        if (type == 0) {
            return new WaitNotifyBlockingArray<>(capacity, elementType);
        } else if (type == 1) {
            return new SemaphoreBlockingArray<>(capacity, elementType);
        } else if (type == 2) {
            return new SemaphoreBlockingList<>(capacity);
        }
        return new WaitNotifyBlockingArray<>(capacity, elementType);
    }
}
