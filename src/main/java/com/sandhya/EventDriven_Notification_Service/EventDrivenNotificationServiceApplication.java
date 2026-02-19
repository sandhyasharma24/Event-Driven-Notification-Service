package com.sandhya.EventDriven_Notification_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class EventDrivenNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventDrivenNotificationServiceApplication.class, args);
	}

}
