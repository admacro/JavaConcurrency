package xyz.admacro.concurrency.lock;

/**
 * Created by james.ni on 2/24/14.
 */
public class LockAndCondition {

    public static void main(String[] args) {
        Account account = new Account(25);
        CashMachineRunnable cashMachineRunnable = new CashMachineRunnable(account);
        Thread cashMachine1 = new Thread(cashMachineRunnable, "ATM-1");
        Thread cashMachine2 = new Thread(cashMachineRunnable, "ATM-2");
        Thread depositMachine = new Thread(new DepositMachineRunnable(account), "Agent");

        depositMachine.start();
        cashMachine1.start();
        cashMachine2.start();
    }
}
