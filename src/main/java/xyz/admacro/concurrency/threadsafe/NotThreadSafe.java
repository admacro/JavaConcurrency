package xyz.admacro.concurrency.threadsafe;

/**
 * Created by james.ni on 2/13/14.
 */
public class NotThreadSafe {

    private StringBuilder builder = new StringBuilder();

    public void add(String s) {
        builder.append(s);
    }

    public String getString() {
        return builder.toString();
    }

}
