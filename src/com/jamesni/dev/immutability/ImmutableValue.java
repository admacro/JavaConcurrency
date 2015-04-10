package com.jamesni.dev.immutability;

/**
 * Created by james.ni on 2/14/14.
 */
public class ImmutableValue {

    private int value = 0;

    public ImmutableValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public ImmutableValue add(int valueToAdd) {
        return new ImmutableValue(this.value + valueToAdd);
    }
}
