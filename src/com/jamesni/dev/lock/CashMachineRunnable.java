package com.jamesni.dev.lock;

/**
 * Created by james.ni on 2/24/14.
 */
public class CashMachineRunnable implements Runnable {
    private Account account = null;

    public CashMachineRunnable(Account account) {
        this.account = account;
    }

    public void run() {
        try {
            while (true) {
                double amount = Math.round(25 * Math.random());
                account.withdraw(amount);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {

        }
    }
}
