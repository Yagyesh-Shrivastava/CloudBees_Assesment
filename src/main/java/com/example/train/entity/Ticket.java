package com.example.train.entity;

public class Ticket {

	private String from;
	private String to;
	private User user;
	private double pricePaid;
	private String seatSection;

	public Ticket(String from, String to, User user, double pricePaid, String seatSection) {
		this.from = from;
		this.to = to;
		this.user = user;
		this.pricePaid = pricePaid;
		this.seatSection = seatSection;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}

	public String getSeatSection() {
		return seatSection;
	}

	public void setSeatSection(String seatSection) {
		this.seatSection = seatSection;
	}

}
