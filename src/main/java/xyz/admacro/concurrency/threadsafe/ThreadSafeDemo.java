package xyz.admacro.concurrency.threadsafe;

/**
 * Created by james.ni on 2/13/14.
 */
public class ThreadSafeDemo {

    public static void main(String[] args) throws InterruptedException {
        NotThreadSafe sharedInstance1 = new NotThreadSafe();
        NotThreadSafe sharedInstance2 = new NotThreadSafe();
        Runnable unsafeRunnable1 = new UnsafeRunnable(sharedInstance1);
        Runnable unsafeRunnable2 = new UnsafeRunnable(sharedInstance2);

        Thread thread1 = new Thread(unsafeRunnable1);
        Thread thread2 = new Thread(unsafeRunnable2);
        thread1.start();
        thread2.start();

        Thread.sleep(1000);
        System.out.println(thread1.getName() + " -> " + sharedInstance1.getString());
        System.out.println(thread2.getName() + " -> " + sharedInstance2.getString());
    }

    static class UnsafeRunnable implements Runnable {
        NotThreadSafe notThreadSafe = null;

        UnsafeRunnable(NotThreadSafe notThreadSafe) {
            this.notThreadSafe = notThreadSafe;
        }

        public void run() {
            notThreadSafe.add("a ");
        }
    }
}
