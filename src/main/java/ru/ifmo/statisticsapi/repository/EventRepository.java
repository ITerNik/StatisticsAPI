package ru.ifmo.statisticsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.statisticsapi.model.EventRecord;

@Repository
public interface EventRepository extends JpaRepository<EventRecord, Long> {
    int countEventRecordsByClientIp(int ip);
    int countEventRecordsByName(String name);
    int countEventRecordsByStatus(boolean status);
}
