package ru.ifmo.statisticsapi.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.ifmo.statisticsapi.model.EventRecord;
import ru.ifmo.statisticsapi.service.EventService;

import java.net.InetAddress;


@Service
@RequiredArgsConstructor
public class InitUtil implements CommandLineRunner {

    private final EventService eventService;

    @Override
    public void run(String... args) throws Exception {
        eventService.save(new EventRecord(InetAddress.getLocalHost().getHostAddress(), "Initial_event", false));
    }
}
