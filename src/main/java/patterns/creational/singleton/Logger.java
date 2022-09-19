package patterns.creational.singleton;

import java.time.LocalTime;

public class Logger {

    private int count = 1;

    private static Logger logger;

    private Logger() {
    }

    void log(String msg) {
        System.out.println("[" + LocalTime.now() + " " + count++ + "] " + msg);
    }

    public static Logger getInstance(){
        if (logger == null) logger = new Logger();
        return logger;
    }
}
