package com.jamesni.dev.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by james.ni on 2/24/14.
 */
public class Account {
    private double balance = 0;
    private Lock accountLock = null;
    private Condition sufficientFund = null;

    public Account(double balance) {
        this.balance = balance;
        accountLock = new ReentrantLock();
        sufficientFund = accountLock.newCondition();
    }

    public void deposit(double amount) {
        accountLock.lock();
        balance += amount;
        sufficientFund.signalAll();
        System.out.println(Thread.currentThread().getName() + ">>> +$" + amount + " is deposited! New balance: " + balance);
        accountLock.unlock();
    }

    public void withdraw(double amount) throws InterruptedException{
        accountLock.lock();
        while (balance < amount) {
            System.out.println(Thread.currentThread().getName() + ">>> !!!Insufficient fund!!! Need: " + amount + "; Balance: " + balance);
            sufficientFund.await();
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + ">>> -$" + amount + " is withdrawn! New balance: " + balance);
        accountLock.unlock();
    }

    public synchronized static void setAlarm() {

    }

    public synchronized void resetAlarm() {}
}
