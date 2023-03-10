package org.scheduler;

public interface DailyNotification extends Runnable {
    @Override
    void run();
}
