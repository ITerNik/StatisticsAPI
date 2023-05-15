package ru.ifmo.statisticsapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifmo.statisticsapi.model.EventRecord;
import ru.ifmo.statisticsapi.repository.EventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<EventRecord> findByNameAndDate(String name, LocalDate date) {
        return eventRepository.findEventRecordsByNameAndDate(name, date);
    }
    public void save(EventRecord eventRecord) {
        eventRepository.save(eventRecord);
    }

    public List<EventRecord> findAll () {
        return eventRepository.findAll();
    }
}
