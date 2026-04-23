package com.cleancode.martinfowler.videostore;

public class RegularMovie extends Movie
{
	public RegularMovie(String title) {
		super(title);
	}

	@Override
	public double determineAmount(int daysRented) {
		double amount = 2;
		if (daysRented > 2)
			amount += (daysRented - 2) * 1.5;
		return amount;
	}
}
