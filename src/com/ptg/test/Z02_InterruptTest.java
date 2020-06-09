package com.ptg.test;

import java.util.concurrent.TimeUnit;

public class Z02_InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread sleep = new Thread(new SleepRunner(), "sleep");
        sleep.setDaemon(true);

        Thread busy = new Thread(new BusyRuuner(), "busy'");
        busy.setDaemon(true);
        TimeUnit.SECONDS.sleep(5);
        sleep.interrupt();
        busy.interrupt();;

        System.out.println("sleep : " + sleep.isInterrupted());
        System.out.println("busy : " + busy.isInterrupted());

        TimeUnit.SECONDS.sleep(2);

    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRuuner implements Runnable {

        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
