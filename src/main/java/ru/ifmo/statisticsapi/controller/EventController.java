package ru.ifmo.statisticsapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.statisticsapi.model.AggregationType;
import ru.ifmo.statisticsapi.model.EventRecord;
import ru.ifmo.statisticsapi.service.EventService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping(value = "/events")
    public ResponseEntity<?> save(@RequestBody EventRecord eventRecord) {
        eventService.save(eventRecord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/statistics")
    public ResponseEntity<Map<String, Integer>> filter(@RequestParam String name,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
                                                       @RequestParam AggregationType filter_by) {
        Map<String, Integer> counter = new HashMap<>();
        ArrayList<EventRecord> events = new ArrayList<>(eventService.findByNameAndDate(name, date));
        switch (filter_by) {
            case EVENT:
                counter.put(name, events.size());
                break;
            case USER:
                for (EventRecord event: events) {
                    counter.put(event.getClientIp().toString(),
                            counter.getOrDefault(event.getClientIp().toString(), 0) + 1);
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
