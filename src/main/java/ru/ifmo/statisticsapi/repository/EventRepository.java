package ru.ifmo.statisticsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.statisticsapi.model.EventRecord;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventRecord, Long> {
    List<EventRecord> findEventRecordsByNameAndDate(String name, LocalDate date);
}
