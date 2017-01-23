package xyz.admacro.concurrency.sync;

/**
 * Created by james.ni on 2/14/14.
 */
public class SynchronizedCounter {

    private int count = 0;

    public void add() {
        System.out.println(Thread.currentThread().getName() + " running");
        synchronized (this) {
            this.count = this.count + 1;
        }
        try {
            System.out.println(Thread.currentThread().getName() + " is working hard ...");
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " is exiting");
    }

    public synchronized void synchronizedAdd() {
        System.out.println(Thread.currentThread().getName() + " running");
        this.count = this.count + 1;
        try {
            System.out.println(Thread.currentThread().getName() + " is working hard ...");
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " is exiting");
    }

    public int getCount() {
        return this.count;
    }
}
