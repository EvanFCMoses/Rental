package com.evan.rental;

import java.math.BigDecimal;

class Tool {

	enum ToolCode {
		LADW,
		CHNS,
		JAKD,
		JAKR
	}


	private final ToolCode toolCode;
	private final String brand;
	private final String toolType;
	private final BigDecimal rentalRate;
	private final boolean weekendCharge;
	private final boolean holidayCharge;

	public Tool(ToolCode toolCode, String brand, String toolType, BigDecimal rentalRate, boolean weekendCharge,
				boolean holidayCharge) {
		this.toolCode = toolCode;
		this.brand = brand;
		this.toolType = toolType;
		this.rentalRate = rentalRate;
		this.weekendCharge = weekendCharge;
		this.holidayCharge = holidayCharge;
	}

	public ToolCode getToolCode() {
		return this.toolCode;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getToolType() {
		return this.toolType;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public boolean getWeekendCharge() {
		return this.weekendCharge;
	}

	public boolean getHolidayCharge() {
		return this.holidayCharge;
	}
}