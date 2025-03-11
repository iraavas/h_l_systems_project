package ru.hpclab.hl.module1.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

public class StatisticsService {

    final int delay;

    private final UserService userService;

    public StatisticsService(int delay, UserService userService) {
        this.delay = delay;
        this.userService = userService;
    }

    @Async
    @Scheduled(fixedRateString = "1000")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " - Fixed rate task async - "+ delay + " - "
                        + userService.getAllUsers().size());
        Thread.sleep(delay);
    }
}
