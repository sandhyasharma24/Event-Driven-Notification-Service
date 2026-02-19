package com.sandhya.EventDriven_Notification_Service.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exception {
	
	
	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity<String> EventNotFoundException(EventNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	

}
