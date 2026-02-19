package com.sandhya.EventDriven_Notification_Service.schedular;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sandhya.EventDriven_Notification_Service.model.Event;
import com.sandhya.EventDriven_Notification_Service.service.EventService;

@Component
public class Scheduler {

    private final EventService eventService;

    public Scheduler(EventService eventService) {
        this.eventService = eventService;
    }

    @Scheduled(fixedDelay = 10000) // runs 10s AFTER previous execution completes
    public void runScheduler() {

        System.out.println("Scheduler woke up at " + LocalDateTime.now().withNano(0));

        List<Event> events = eventService.getPendingEvents();

        if (events.isEmpty()) {
            System.out.println("No Pending event");
            return;
        }

        for (Event event : events) {
            eventService.processPendingEvent(event);
        }
    }
}
