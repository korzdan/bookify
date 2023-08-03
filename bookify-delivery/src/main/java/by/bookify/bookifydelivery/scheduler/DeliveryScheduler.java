package by.bookify.bookifydelivery.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DeliveryScheduler {

    @Scheduled(cron = "*/5 * * * *")
    public void get() {

    }
}
