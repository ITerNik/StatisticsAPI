package ru.ifmo.statisticsapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
public class EventRecord {

    private @Id
    @GeneratedValue Long id;
    private String clientIp;
    private String name;
    private Boolean status;

    private LocalDate date = LocalDate.now();

    public EventRecord() {
    }

    public EventRecord(String clientIp, String name, boolean status) {
        this.status = status;
        this.name = name;
        this.clientIp = clientIp;
    }
}
