package com.jamesni.dev.signaling;

/**
 * Created by james.ni on 1/28/14.
 */
public class BusyWaitSpinning {

    public static void main(String[] args) {
        SharedData signal = new SharedData();
        Thread dataProvider = new Thread(new DataProvider(signal), "dataProvider");
        Thread dataProcessor = new Thread(new DataProcessor(signal), "dataProcessor");

        dataProvider.start();
        dataProcessor.start();
    }

    static class DataProcessor implements Runnable {
        private SharedData sharedData = null;

        DataProcessor(SharedData sharedData) {
            this.sharedData = sharedData;
        }

        public void run() {
            // busy waiting
            while (!sharedData.isReady()) {
                // if sharedData is not ready, just wait and print out
                System.out.println(Thread.currentThread().getName() + " SharedData is not ready, waiting ...");
                try {
                    Thread.currentThread().sleep(1000);
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

    static class DataProvider implements Runnable {
        private SharedData sharedData = null;

        DataProvider(SharedData sharedData) {
            this.sharedData = sharedData;
        }

        public void run() {
            // preparing sharedData
            if (!sharedData.isReady()) {
                System.out.println(Thread.currentThread().getName() + " preparing sharedData ...");
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
                sharedData.setReady(true);
                System.out.println(Thread.currentThread().getName() + " sharedData is ready");
            }
        }
    }

}
