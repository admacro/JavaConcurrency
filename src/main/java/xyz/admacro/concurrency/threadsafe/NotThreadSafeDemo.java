package xyz.admacro.concurrency.threadsafe;

/**
 * Created by james.ni on 2/13/14.
 */
public class NotThreadSafeDemo {

    public static void main(String[] args) throws InterruptedException {
        NotThreadSafe sharedInstance = new NotThreadSafe();
        Runnable unsafeRunnable = new UnsafeRunnable(sharedInstance);

        Thread thread1 = new Thread(unsafeRunnable);
        Thread thread2 = new Thread(unsafeRunnable);
        thread1.start();
        thread2.start();

        Thread.sleep(1000);
        System.out.println(sharedInstance.getString());
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
