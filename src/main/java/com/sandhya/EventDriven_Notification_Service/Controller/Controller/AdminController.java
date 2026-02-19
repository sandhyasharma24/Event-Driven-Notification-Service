package com.sandhya.EventDriven_Notification_Service.Controller.Controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandhya.EventDriven_Notification_Service.DTO.AdminStatsResponse;
import com.sandhya.EventDriven_Notification_Service.Enums.EventStatus;
import com.sandhya.EventDriven_Notification_Service.model.Event;
import com.sandhya.EventDriven_Notification_Service.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/admin/events")
@Tag(name="Admin Operations")
public class AdminController {
	
	private EventService eventService;
	public AdminController(EventService eventService) {
		this.eventService = eventService;
	}
	
	
	@GetMapping("/stats")
	@Operation(summary="Get overall statistics",description="Returns a summary of total events engagement metrics.")
	public ResponseEntity<AdminStatsResponse> findAllStats(){
		return new ResponseEntity<AdminStatsResponse>(eventService.allStats(),HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Event>> showAllEvents(){
		return new ResponseEntity<List<Event>>(eventService.findAllEvents(),HttpStatus.OK);
	}
	
	
	public ResponseEntity<Event> findEventById(@PathVariable("id") Integer id) throws Exception{
		return new ResponseEntity<Event>(eventService.findEventById(id),HttpStatus.OK);
		
	}
	
	
	public ResponseEntity<List<Event>> findEventByStatus(@RequestParam EventStatus status) throws Exception{
		return new ResponseEntity<List<Event>>(eventService.getEventByStatus(status),HttpStatus.OK);
		
	}
	
	

	
	
}
