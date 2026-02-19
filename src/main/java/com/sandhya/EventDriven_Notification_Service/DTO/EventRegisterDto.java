package com.sandhya.EventDriven_Notification_Service.DTO;
import com.sandhya.EventDriven_Notification_Service.Enums.EventType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Details required to register and send a notification event")
public class EventRegisterDto {

	
	@NotBlank
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", 
             message = "Invalid email format")
	private String toEmail;
	
	
	@Schema(description = "The content of the notification message", example = "Your order #123 has been shipped!")
	@NotBlank
	private String message;
	
	@Schema(description = "The category of the event", example = "EMAIL_NOTIFICATION")
	@Enumerated(EnumType.STRING)
	private EventType eventType;
	
	
	@NotBlank
	@Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", 
    message = "Invalid email format")
	@Schema(description = "Sender email address", example = "no-reply@nithin.com")
	private String fromEmail;


	public String getToEmail() {
		return toEmail;
	}


	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public EventType getEventType() {
		return eventType;
	}


	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}


	public String getFromEmail() {
		return fromEmail;
	}


	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	
	
	
	
}
