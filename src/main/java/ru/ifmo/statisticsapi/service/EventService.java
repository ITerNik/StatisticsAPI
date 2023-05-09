package ru.ifmo.statisticsapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifmo.statisticsapi.model.EventRecord;
import ru.ifmo.statisticsapi.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public int countEventsByIp(int ip) {
        return eventRepository.countEventRecordsByClientIp(ip);
    }

    public int countEventsByName(String name) {
        return eventRepository.countEventRecordsByName(name);
    }

    public int countEventsByStatus(boolean status) {
        return eventRepository.countEventRecordsByStatus(status);
    }


    public void save(EventRecord eventRecord) {
        eventRepository.save(eventRecord);
    }
}
