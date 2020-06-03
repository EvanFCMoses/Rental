package com.evan.rental;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Rental {

	private final Tool tool;
	private final LocalDate rentalStartDate;
	private final LocalDate rentalEndDate;


	Rental(Tool tool, LocalDate rentalStartDate, LocalDate rentalEndDate) {
		this.tool = tool;
		this.rentalStartDate = rentalStartDate;
		this.rentalEndDate = rentalEndDate;
	}

	public Tool getTool() {
		return this.tool;
	}

	public LocalDate getRentalStartDate() {
		return this.rentalStartDate;
	}

	public LocalDate getRentalEndDate() {
		return this.rentalEndDate;
	}

	public BigDecimal calculateAmountDue() {
		return this.tool.getRentalRate().
				multiply(BigDecimal.valueOf(calculateDaysBetweenRentalDates()));
	}

	public int calculateDaysBetweenRentalDates() {
		return this.rentalEndDate.compareTo(this.rentalStartDate)+1 - numberOfWeekendsAndHolidayExceptions();
	}

	public int numberOfWeekendsAndHolidayExceptions() {
		return (int) Stream.iterate(rentalStartDate, date -> date.plusDays(1))
				.limit(rentalEndDate.compareTo(rentalStartDate)+1)
				.filter(d -> d.getDayOfWeek().getValue()>5 && this.tool.getWeekendCharge())
				.filter(this::isDateAHoliday)
				.count();
	}

	public boolean isDateAHoliday(LocalDate date) {
		return true;
	}
}