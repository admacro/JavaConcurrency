package xyz.admacro.concurrency.sync;

/**
 * Created by james.ni on 2/14/14.
 */
public class SynchronizedCounterDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Demo of synchronized instance method");
        synchronizedMethodDemo();

        System.out.println("Demo of synchronized code block of instance method");
        synchronizedBlockDemo();
    }

    private static void synchronizedMethodDemo() throws InterruptedException {
        SynchronizedCounter sCounter = new SynchronizedCounter();
        Runnable runnableCounter = new RunnableCounter(sCounter);

        Thread thread1 = new Thread(runnableCounter, "D1 Thread 1");
        Thread thread2 = new Thread(runnableCounter, "D1 Thread 2");
        Thread thread3 = new Thread(runnableCounter, "D1 Thread 3");
        Thread thread4 = new Thread(runnableCounter, "D1 Thread 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(5000);
        System.out.println("Final result is " + sCounter.getCount());
    }

    private static void synchronizedBlockDemo() throws InterruptedException {
        SynchronizedCounter sCounter = new SynchronizedCounter();
        Runnable runnableCounter = new RunnableCounterSynced(sCounter);

        Thread thread1 = new Thread(runnableCounter, "D2 Thread 1");
        Thread thread2 = new Thread(runnableCounter, "D2 Thread 2");
        Thread thread3 = new Thread(runnableCounter, "D2 Thread 3");
        Thread thread4 = new Thread(runnableCounter, "D2 Thread 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(5000);
        System.out.println("Final result is " + sCounter.getCount());
    }

    static class RunnableCounter implements Runnable {

        private SynchronizedCounter sCounter = null;
        RunnableCounter(SynchronizedCounter sCounter) {
            this.sCounter = sCounter;
        }

        public void run() {
            sCounter.add();
        }
    }

    static class RunnableCounterSynced implements Runnable {

        private SynchronizedCounter sCounter = null;
        RunnableCounterSynced(SynchronizedCounter sCounter) {
            this.sCounter = sCounter;
        }

        public void run() {
            sCounter.synchronizedAdd();
        }
    }
}
