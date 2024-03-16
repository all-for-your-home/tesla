package com.example.allforyourhome.component;

import com.example.allforyourhome.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final SmsService smsService;
    private static final long TWENTY_EIGHT_DAYS = 28L * 24 * 60 * 60 * 1000;

    @Override
    public void run(String... args) throws Exception {
        smsService.authorize();
    }

    @Scheduled(fixedDelay = TWENTY_EIGHT_DAYS, initialDelay = TWENTY_EIGHT_DAYS)
    public void refresh() {
        smsService.refresh();
    }
}
