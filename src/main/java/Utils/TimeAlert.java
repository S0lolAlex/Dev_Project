package Utils;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimeAlert {
    class MyRunnableTask implements Runnable {
        private long chatId;

        public MyRunnableTask(long chatId) {
            this.chatId = chatId;
        }

        @Override
        public void run() {
            String answer = BANK.getCurrency("USD", df);
            getInfo(chatId, answer);
        }
    }
    class Timer {
        private int hours;
        private int minutes;

        private ScheduledFuture<?> scheduledFuture;

        public Timer(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }

        public void start(long chatId) {
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime nextRun = now.withHour(hours).withMinute(minutes).withSecond(0);
            if(now.compareTo(nextRun) > 0)
                nextRun = nextRun.plusDays(1);

            Duration duration = Duration.between(now, nextRun);
            long initialDelay = duration.getSeconds();

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduledFuture = scheduler.scheduleAtFixedRate(new MyRunnableTask(chatId),
                    initialDelay,
                    TimeUnit.DAYS.toSeconds(1),
                    TimeUnit.SECONDS);
        }

        public void stop() {
            scheduledFuture.cancel(true);
        }
    }

    class TestThread extends Thread {
        private long chatId;
        private long setTime;
        private boolean isRunning = true;

        public void setRunning(boolean running) {
            isRunning = running;
        }

        public TestThread(long chatId, int setTime) {
            this.chatId = chatId;
            this.setTime = setTime;
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(setTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String answer = BANK.getCurrency("USD", df);
                getInfo(chatId, answer);
            }
        }
    }
}
