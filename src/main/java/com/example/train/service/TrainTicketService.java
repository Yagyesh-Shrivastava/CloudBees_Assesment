package com.example.train.service;

import com.example.train.entity.Ticket;
import com.example.train.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TrainTicketService {
	private Map<String, Ticket> tickets = new HashMap<>();

	public void purchaseTicket(String from, String to, User user, double pricePaid, String seatSection) {
		Ticket ticket = new Ticket(from, to, user, pricePaid, seatSection);
		tickets.put(user.getEmail(), ticket);
	}

	public Ticket getReceiptDetails(String userEmail) {
		return tickets.get(userEmail);
	}

	public Map<String, Ticket> getUsersBySection(String seatSection) {
		Map<String, Ticket> usersInSection = new HashMap<>();
		for (Map.Entry<String, Ticket> entry : tickets.entrySet()) {
			Ticket ticket = entry.getValue();
			if (ticket.getSeatSection().equals(seatSection)) {
				usersInSection.put(entry.getKey(), ticket);
			}
		}
		return usersInSection;
	}

	public void removeUser(String userEmail) {
		tickets.remove(userEmail);
	}

	public void modifyUserSeat(String userEmail, String newSeatSection) {
		if (tickets.containsKey(userEmail)) {
			Ticket ticket = tickets.get(userEmail);
			ticket.setSeatSection(newSeatSection);
		}
	}
}
