package com.sandhya.EventDriven_Notification_Service.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandhya.EventDriven_Notification_Service.DTO.AdminStatsResponse;
import com.sandhya.EventDriven_Notification_Service.DTO.EventRegisterDto;
import com.sandhya.EventDriven_Notification_Service.DTO.EventResponseDto;
import com.sandhya.EventDriven_Notification_Service.Enums.EventStatus;
import com.sandhya.EventDriven_Notification_Service.model.Event;
import com.sandhya.EventDriven_Notification_Service.repository.EventRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EmailService emailService;

    public EventService(EventRepository eventRepository,
                        EmailService emailService) {
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    // ---------------------------------------------------
    // 1️⃣ Create Event (API entry point)
    // ---------------------------------------------------
    public EventResponseDto createEvent(EventRegisterDto dto,String idempotencyKey) {
    		if(eventRepository.findByIdempotencyKey(idempotencyKey).isPresent()) {
    		Event eventIsPresent = eventRepository.findByIdempotencyKey(idempotencyKey).get();
    		EventResponseDto response = new EventResponseDto();
    		response.setEventId(eventIsPresent.getId());
            response.setStatus("Already Created");
            return response;
    	}
    		else {
    			Event event = new Event();
    	        event.setToEmail(dto.getToEmail());
    	        event.setFromEmail(dto.getFromEmail());
    	        event.setMessage(dto.getMessage());
    	        event.setEventType(dto.getEventType());
    	        event.setIdempotencyKey(idempotencyKey);

    	        event.setStatus(EventStatus.PENDING);
    	        event.setRetryCount(0);

    	        eventRepository.save(event);

    	        EventResponseDto response = new EventResponseDto();
    	        response.setEventId(event.getId());
    	        response.setStatus("SUCCESS");

    	        return response;
    			
    		}
        
    }

    // ---------------------------------------------------
    // 2️⃣ Fetch events for scheduler
    // ---------------------------------------------------
    public List<Event> getPendingEvents() {

        List<EventStatus> statuses = new ArrayList<>();
        statuses.add(EventStatus.PENDING);
        statuses.add(EventStatus.FAILED);

        return eventRepository.findByStatusIn(statuses);
    }

    // ---------------------------------------------------
    // 3️⃣ Scheduler processing entry (PUBLIC)
    // ---------------------------------------------------
    @Transactional
    public void processPendingEvent(Event event) {
        if (!isEligibleForRetry(event)) {
            LocalDateTime nextRetry =
                    event.getLastAttempted()
                         .plusSeconds(getRetryDelay(event.getRetryCount()))
                         .truncatedTo(ChronoUnit.SECONDS);

            System.out.println(
                "Event id " + event.getId()
                + " is not eligible for retry. Next attempt allowed at "
                + nextRetry
            );
            return;
        }

        try {
            emailService.sendEmail(
                    event.getToEmail(),
                    event.getMessage(),
                    event.getEventType().name()
            );

            event.setStatus(EventStatus.SUCCESS);

        } catch (Exception ex) {

            event.setRetryCount(event.getRetryCount() + 1);

            if (event.getRetryCount() >= 3) {
                event.setStatus(EventStatus.DEAD);
            } else {
                event.setStatus(EventStatus.FAILED);
            }
        }

        // ALWAYS update lastAttempted
        event.setLastAttempted(LocalDateTime.now());

        eventRepository.save(event);
    }

    // ---------------------------------------------------
    // 4️⃣ Retry eligibility check
    // ---------------------------------------------------
    private boolean isEligibleForRetry(Event event) {

        if (event.getLastAttempted() == null) {
            return true;
        }

        long delaySeconds = getRetryDelay(event.getRetryCount());

        LocalDateTime nextAllowedAttempt =
                event.getLastAttempted().plusSeconds(delaySeconds);

        return LocalDateTime.now().isAfter(nextAllowedAttempt);
    }

    // ---------------------------------------------------
    // 5️⃣ Retry backoff logic
    // ---------------------------------------------------
    private long getRetryDelay(int retryCount) {

        return switch (retryCount) {
            case 0 -> 0;
            case 1 -> 10;
            case 2 -> 20;
            case 3 -> 40;
            default -> 0;
        };
    }

    // ---------------------------------------------------
    // 6️ Admin APIs
    // ---------------------------------------------------
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event findEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<Event> getEventByStatus(EventStatus status) {
        return eventRepository.findByStatus(status);
    }

    public AdminStatsResponse allStats() {

        return new AdminStatsResponse(
                eventRepository.count(),
                eventRepository.findByStatus(EventStatus.FAILED).size(),
                eventRepository.findByStatus(EventStatus.SUCCESS).size(),
                eventRepository.findByStatus(EventStatus.PENDING).size(),
                eventRepository.findByStatus(EventStatus.DEAD).size()
        );
    }
}
