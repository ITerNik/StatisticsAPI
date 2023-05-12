package ru.ifmo.statisticsapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.statisticsapi.model.AggregationType;
import ru.ifmo.statisticsapi.model.EventRecord;
import ru.ifmo.statisticsapi.model.PostQuery;
import ru.ifmo.statisticsapi.service.EventService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping(value = "/events")
    public ResponseEntity<?> save(@RequestBody PostQuery query, HttpServletRequest request) {
        EventRecord eventRecord = new EventRecord(request.getRemoteAddr(), query.getName(), query.isStatus());
        eventService.save(eventRecord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/statistics")
    public ResponseEntity<?> filter(@RequestParam String name,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
                                                       @RequestParam AggregationType filter_by) {
        Map<String, Integer> counter = new HashMap<>();
        ArrayList<EventRecord> events = new ArrayList<>(eventService.findByNameAndDate(name, date));
        if (events.isEmpty()) return new ResponseEntity<>("Can't find events with name: " + name , HttpStatus.NOT_FOUND);
        switch (filter_by) {
            case EVENT:
                counter.put(name, events.size());
                break;
            case USER:
                for (EventRecord event: events) {
                    counter.put(event.getClientIp(),
                            counter.getOrDefault(event.getClientIp(), 0) + 1);
                }
                break;
            case STATUS:
                for (EventRecord event: events) {
                    String status = event.getStatus() ? "authorized" : "unauthorized";
                    counter.put(status, counter.getOrDefault(status, 0) + 1);
                }
        }
        return ResponseEntity.ok(counter);
    }
}
