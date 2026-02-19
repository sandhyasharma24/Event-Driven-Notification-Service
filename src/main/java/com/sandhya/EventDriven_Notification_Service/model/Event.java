package com.sandhya.EventDriven_Notification_Service.model;
import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import com.sandhya.EventDriven_Notification_Service.Enums.EventStatus;
import com.sandhya.EventDriven_Notification_Service.Enums.EventType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Event {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
		
	@Email
	@NotBlank
	@Column(nullable=false)
	private String toEmail;
	
	@Column(nullable=false)
	@NotBlank
	private String message;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private EventStatus status;
	
	@Email
	@NotBlank
	@Column(nullable=false)
	private String fromEmail;
	
	private LocalDateTime createdAt;
	
	@Column(nullable=false)
	private Integer retryCount ;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private EventType eventType;
	
	
	
	@PrePersist
	protected void created() {
		createdAt = LocalDateTime.now();
		retryCount = 0 ;
	}
	
	
	 private LocalDateTime lastAttempted;
	 
	 @Column(nullable=false,unique=true)
	 private String idempotencyKey;

	 public String getIdempotencyKey() {
		return idempotencyKey;
	}

	 public void setIdempotencyKey(String idempotencyKey) {
		 this.idempotencyKey = idempotencyKey;
	 }

	 public Integer getId() {
		 return id;
	 }

	 public void setId(Integer id) {
		 this.id = id;
	 }

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

	 public EventStatus getStatus() {
		 return status;
	 }

	 public void setStatus(EventStatus status) {
		 this.status = status;
	 }

	 public String getFromEmail() {
		 return fromEmail;
	 }

	 public void setFromEmail(String fromEmail) {
		 this.fromEmail = fromEmail;
	 }

	 public LocalDateTime getCreatedAt() {
		 return createdAt;
	 }

	 public void setCreatedAt(LocalDateTime createdAt) {
		 this.createdAt = createdAt;
	 }

	 public Integer getRetryCount() {
		 return retryCount;
	 }

	 public void setRetryCount(Integer retryCount) {
		 this.retryCount = retryCount;
	 }

	 public EventType getEventType() {
		 return eventType;
	 }

	 public void setEventType(EventType eventType) {
		 this.eventType = eventType;
	 }

	 public LocalDateTime getLastAttempted() {
		 return lastAttempted;
	 }

	 public void setLastAttempted(LocalDateTime lastAttempted) {
		 this.lastAttempted = lastAttempted;
	 }
	 
	 
	
	
	
}
