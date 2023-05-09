package ru.ifmo.statisticsapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "event_record")
public class EventRecord {

    private @Id
    @GeneratedValue Long id;
    private Integer clientIp;
    private String name;
    private boolean status;
    private LocalDateTime time = LocalDateTime.now();

    public EventRecord() {
    }

    public EventRecord(int clientIp, String name, boolean status) {
        this.status = status;
        this.name = name;
        this.clientIp = clientIp;
    }
}
