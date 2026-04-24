package com.cleancode.martinfowler.videostore;

import java.util.ArrayList;
import java.util.List;

public class Statement
{
	private String name;
	private List<Rental> rentals = new ArrayList<> ();
	private double totalAmount;
	private int frequentRenterPoints;

	public Statement (String name) {
		this.name = name;
	}
	
	public void addRental (Rental rental) {
		rentals.add (rental);
	}
	
	public String getName () {
		return name;
	}
	
	public String statement () {
		return generate();
	}

	public String generate() {
		clearTotals();
		return createHeader() + createRentalLines() + createFooter();
	}

	public double getTotal() {
		return totalAmount;
	}

	public int getFrequentRenterPoints() {
		return frequentRenterPoints;
	}

	private void clearTotals() {
		totalAmount = 0;
		frequentRenterPoints = 0;
	}

	private String createHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String createRentalLines() {
		String rentalLines = "";

		for (Rental rental : rentals) {
			rentalLines += createRentalLine(rental);
		}

		return rentalLines;
	}

	private String createRentalLine(Rental rental) {
		double rentalAmount = rental.getMovie().determineAmount(rental.getDaysRented());
		frequentRenterPoints += rental.getMovie().determineFrequentRenterPoints(rental.getDaysRented());
		totalAmount += rentalAmount;
		return formatRentalLine(rental, rentalAmount);
	}

	private String formatRentalLine(Rental rental, double rentalAmount) {
		return "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rentalAmount) + "\n";
	}

	private String createFooter() {
		return "You owed " + String.valueOf(getTotal()) + "\n"
				+ "You earned " + String.valueOf(getFrequentRenterPoints()) + " frequent renter points\n";
	}
}
