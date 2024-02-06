package com.example.train.controller;

import com.example.train.entity.Ticket;
import com.example.train.entity.User;
import com.example.train.service.TrainTicketService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TrainController {
	private final TrainTicketService ticketService;

	public TrainController(TrainTicketService ticketService) {
		this.ticketService = ticketService;
	}

	@PostMapping("/purchase")
	public String purchaseTicket(@RequestParam String from, @RequestParam String to, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String email, @RequestParam double pricePaid,
			@RequestParam String seatSection) {
		User user = new User(firstName, lastName, email);
		ticketService.purchaseTicket(from, to, user, pricePaid, seatSection);
		return "Ticket purchased successfully!";
	}

	@GetMapping("/receipt/{email}")
	public Ticket getReceiptDetails(@PathVariable String email) {
		return ticketService.getReceiptDetails(email);
	}

	@GetMapping("/users/{section}")
	public Map<String, Ticket> getUsersBySection(@PathVariable String section) {
		return ticketService.getUsersBySection(section);
	}

	@DeleteMapping("/remove/{email}")
	public String removeUser(@PathVariable String email) {
		ticketService.removeUser(email);
		return "User removed successfully!";
	}

	@PutMapping("/modify/{email}")
	public String modifyUserSeat(@PathVariable String email, @RequestParam String newSeatSection) {
		ticketService.modifyUserSeat(email, newSeatSection);
		return "User seat modified successfully!";
	}
}
