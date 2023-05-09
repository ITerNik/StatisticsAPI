package ru.ifmo.statisticsapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.statisticsapi.model.EventRecord;
import ru.ifmo.statisticsapi.service.EventService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/events")
public class EventController {
    private final EventService eventService;
    @GetMapping(value = "/simple")
    public String simple() {
        return "Easy";
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody EventRecord eventRecord) {
        eventService.save(eventRecord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/{ip}")
    public int countByIp(@PathVariable int ip) {
        int res = eventService.countEventsByIp(ip);
        return res;//new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Integer> countByName(@PathVariable String name) {
        return new ResponseEntity<>(eventService.countEventsByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/{status}")
    public ResponseEntity<Integer> countByStatus(@PathVariable boolean status) {
        return new ResponseEntity<>(eventService.countEventsByStatus(status), HttpStatus.OK);
    }
}
