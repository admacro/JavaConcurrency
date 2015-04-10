package com.jamesni.dev.basics;

/**
 * Created by james.ni on 3/6/14.
 */
public class MySingletonTest {

    public static void main(String[] args) {
        MySingleton instance1 = MySingleton.getInstance();
        MySingleton instance2 = MySingleton.getInstance();

        System.out.println("instance1" + (instance1 == instance2 ? " == " : " != ") + "instance2");
    }
}
