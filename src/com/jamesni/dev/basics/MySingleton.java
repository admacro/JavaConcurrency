package com.jamesni.dev.basics;


/**
 * Created by james.ni on 3/6/14.
 */
public class MySingleton {

    private static final class MySingletonInstance {
        private static final MySingleton instance = new MySingleton();
    }

    public static final MySingleton getInstance() {
        return MySingletonInstance.instance;
    }

    private MySingleton() {
    }

    public static void main(String[] args) {
        new MySingleton();
    }
}
