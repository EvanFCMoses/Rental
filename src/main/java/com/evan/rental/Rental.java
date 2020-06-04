package com.evan.rental;

import java.math.BigDecimal;

import java.math.RoundingMode;
import java.time.LocalDate;

class Rental {

	private final Tool tool;
	private final RentalDateRange rentalDateRange;
	private final BigDecimal discount;


	Rental(Tool tool, LocalDate rentalStartDate, LocalDate rentalEndDate, BigDecimal discount) throws Exception{
		this.tool = tool;
		this.rentalDateRange = new RentalDateRange(rentalStartDate, rentalEndDate, tool.getWeekendCharge(), tool.getHolidayCharge());
		this.discount = validateDiscount(discount);
	}

	public Tool getTool() {
		return this.tool;
	}

	public RentalDateRange getRentalDateRange() {
		return this.rentalDateRange;
	}

	public BigDecimal validateDiscount(BigDecimal discount) throws Exception {
		if (discount.compareTo(BigDecimal.valueOf(100)) > 0) {
			throw new Exception("Discount was entered as greater than 100 which is not allowed. Enter an amount between 0 and 100");
		} else if (discount.compareTo(BigDecimal.valueOf(0)) < 0) {
			throw new Exception("Discount was entered as less than 0 which is not allowed. Enter an amount between 0 and 100");
		}
		return discount;
	}

	public BigDecimal calculateAmountDue() {

		return calculateAmountDueBeforeDiscount()
				.subtract(calculateDiscountAmount())
				.setScale(2, RoundingMode.HALF_EVEN);
	}

	public BigDecimal calculateAmountDueBeforeDiscount() {
		return tool.getRentalRate().
				multiply(BigDecimal.valueOf(rentalDateRange.numberOfChargeableDays()));
	}

	public BigDecimal calculateDiscountAmount() {
		return calculateAmountDueBeforeDiscount()
				.multiply(discount)
				.multiply(BigDecimal.valueOf(.01));
	}

	public BigDecimal calculateAmountDueAndDisplay() {
		System.out.println("Tool code: " + tool.getToolCode());
		System.out.println("Tool type: " + tool.getToolType());
		System.out.println("Tool brand: " + tool.getBrand());
		System.out.println("Check out date: " + rentalDateRange.getFormattedRentalStartDate());
		System.out.println("Due date: " + rentalDateRange.getFormattedRentalEndDate());
		System.out.println("Daily rental charge: $" + tool.getRentalRate());
		System.out.println("Charge days: " + rentalDateRange.numberOfChargeableDays());
		System.out.println("Pre-discount charge: $" + calculateAmountDueBeforeDiscount().setScale(2, RoundingMode.HALF_EVEN));
		System.out.println("Discount amount: $" + calculateDiscountAmount().setScale(2, RoundingMode.HALF_EVEN));
		System.out.println("Final charge: $" + calculateAmountDue());
		return calculateAmountDue();
	}


}