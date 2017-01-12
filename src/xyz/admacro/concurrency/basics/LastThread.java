package xyz.admacro.concurrency.basics;

/**
 * Created by james.ni on 2/13/14.
 */

/*
 * Program exits when the last non-demon threads finishes.
 */
public class LastThread {

    public static void main(String[] args) {
        System.out.println("Main thread started, default name: " + Thread.currentThread().getName());
        new Thread() {
            @Override
            public void run() {
                System.out.println("Second thread started, default name: " + Thread.currentThread().getName());
//                Runtime.getRuntime().exit(0);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
                System.out.println("Second thread about to finish");
            }
        }.start();
        System.out.println("Main thread about to finish");
    }
}
