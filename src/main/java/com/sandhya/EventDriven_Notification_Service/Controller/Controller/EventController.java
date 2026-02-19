package com.sandhya.EventDriven_Notification_Service.Controller.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandhya.EventDriven_Notification_Service.DTO.EventRegisterDto;
import com.sandhya.EventDriven_Notification_Service.DTO.EventResponseDto;
import com.sandhya.EventDriven_Notification_Service.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/event")
public class EventController {
	
	private EventService eventService;
	
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	
	
	@PostMapping("/register")
	@Operation(summary="This is a post method used to register Event",description="Takes event details and saves them to the database. Returns the generated ID and status.")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Event created successfully"),
		    @ApiResponse(responseCode = "400", description = "Invalid input data (Validation failed)"),
		    @ApiResponse(responseCode = "500", description = "Internal server error")
		})
	public ResponseEntity<EventResponseDto> saveEvent(@RequestHeader("IdempotencyKey") String idempotencyKey, @RequestBody EventRegisterDto eventRegister){
	      EventResponseDto response =  eventService.createEvent(eventRegister,idempotencyKey);
	      return new ResponseEntity<EventResponseDto>(response,HttpStatus.CREATED);
}

}
