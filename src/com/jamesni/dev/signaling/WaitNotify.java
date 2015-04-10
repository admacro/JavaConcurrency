package com.jamesni.dev.signaling;

/**
 * Created by james.ni on 2/17/14.
 */
public class WaitNotify {

    public static void main(String[] args) {
        SharedData signal = new SharedData();
        Thread dataProvider = new Thread(new DataProvider(signal), "dataProvider");
        Thread dataProcessor = new Thread(new DataProcessor(signal), "dataProcessor");

        dataProcessor.start(); // start processor first to let it have a good chance to check sharedData first
        dataProvider.start();
    }

    static class DataProcessor implements Runnable {
        private SharedData sharedData = null;

        DataProcessor(SharedData sharedData) {
            this.sharedData = sharedData;
        }

        public void run() {
            synchronized (sharedData) {
                // if sharedData is not ready, just wait and print out
                while (!sharedData.isReady()) {
                    System.out.println(Thread.currentThread().getName() + " SharedData is not ready, waiting ...");
                    try {
                        sharedData.wait();
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }

                if (sharedData.isReady()) {
                    System.out.println(Thread.currentThread().getName() + " SharedData is ready, processing sharedData.");
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println(Thread.currentThread().getName() + " SharedData processing completed.");
                }
            }
        }
    }

    static class DataProvider implements Runnable {
        private SharedData sharedData = null;

        DataProvider(SharedData sharedData) {
            this.sharedData = sharedData;
        }

        public void run() {
            // preparing sharedData
            synchronized (sharedData) {
                while (!sharedData.isReady()) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + " preparing sharedData ...");
                        try {
                            Thread.currentThread().sleep(1000);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    sharedData.setReady(true);
                    System.out.println(Thread.currentThread().getName() + " sharedData is ready");
                }
                sharedData.notify();
            }
        }
    }
}
