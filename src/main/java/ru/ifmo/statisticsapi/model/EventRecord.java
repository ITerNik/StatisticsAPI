package ru.ifmo.statisticsapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class EventRecord {

    private @Id
    @GeneratedValue Long id;
    private Integer clientIp;
    private String name;
    private Boolean status;

    private LocalDate date = LocalDate.now();

    public EventRecord() {
    }

    public EventRecord(int clientIp, String name, boolean status) {
        this.status = status;
        this.name = name;
        this.clientIp = clientIp;
    }
}
