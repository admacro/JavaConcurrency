package xyz.admacro.concurrency.lock;

/**
 * Created by james.ni on 2/24/14.
 */
public class DepositMachineRunnable implements Runnable {
    private Account account = null;

    public DepositMachineRunnable(Account account) {
        this.account = account;
    }

    public void run() {
        try {
            while (true) {
                double amount = Math.round(100 * Math.random());
                account.deposit(amount);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {

        }
    }
}
