package com.sandhya.EventDriven_Notification_Service.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandhya.EventDriven_Notification_Service.Enums.EventStatus;
import com.sandhya.EventDriven_Notification_Service.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event ,Integer>{
	List<Event> findByStatusIn(List<EventStatus>status);
	List<Event> findByStatus(EventStatus status);
	
	Optional<Event> findByIdempotencyKey(String idempotencyKey);
}
