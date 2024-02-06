package com.example.train.service;

import com.example.train.entity.Ticket;
import com.example.train.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TrainTicketServiceTest {

	private TrainTicketService ticketService;

	@BeforeEach
	void setUp() {
		ticketService = new TrainTicketService();
	}

	@Test
	void purchaseTicket() {
		User user = new User("John", "Doe", "john@example.com");
		double pricePaid = 20.0;
		String seatSection = "A";

		ticketService.purchaseTicket("London", "France", user, pricePaid, seatSection);

		assertNotNull(ticketService.getReceiptDetails(user.getEmail()));
	}

	@Test
	void getReceiptDetails() {
		User user = new User("Jane", "Doe", "jane@example.com");
		Ticket ticket = new Ticket("Paris", "Berlin", user, 25.0, "B");
		ticketService.purchaseTicket("Paris", "Berlin", user, 25.0, "B");

		Ticket retrievedTicket = ticketService.getReceiptDetails("jane@example.com");

		assertNotNull(retrievedTicket);
		assertEquals("Paris", retrievedTicket.getFrom());
		assertEquals("Berlin", retrievedTicket.getTo());
		assertEquals("Jane", retrievedTicket.getUser().getFirstName());
		assertEquals(25.0, retrievedTicket.getPricePaid());
		assertEquals("B", retrievedTicket.getSeatSection());
	}

	@Test
	void getUsersBySection() {
		User user1 = new User("Alice", "Smith", "alice@example.com");
		User user2 = new User("Bob", "Johnson", "bob@example.com");
		ticketService.purchaseTicket("London", "Paris", user1, 20.0, "A");
		ticketService.purchaseTicket("Berlin", "Rome", user2, 30.0, "B");

		Map<String, Ticket> usersInSection = ticketService.getUsersBySection("A");

		assertNotNull(usersInSection);
		assertEquals(1, usersInSection.size());
		assertTrue(usersInSection.containsKey("alice@example.com"));
		assertFalse(usersInSection.containsKey("bob@example.com"));
	}

	@Test
	void removeUser() {
		User user = new User("John", "Doe", "john@example.com");
		ticketService.purchaseTicket("London", "Paris", user, 20.0, "A");

		ticketService.removeUser("john@example.com");

		assertNull(ticketService.getReceiptDetails("john@example.com"));
	}

	@Test
	void modifyUserSeat() {
		User user = new User("Jane", "Doe", "jane@example.com");
		ticketService.purchaseTicket("Paris", "Berlin", user, 25.0, "B");

		ticketService.modifyUserSeat("jane@example.com", "C");

		assertEquals("C", ticketService.getReceiptDetails("jane@example.com").getSeatSection());
	}
}
