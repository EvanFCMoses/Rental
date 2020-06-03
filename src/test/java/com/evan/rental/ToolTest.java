package com.evan.rental;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ToolTest {
	
	@Test
	public void createdToolShouldMatchInputValues() {
		Tool tool = new Tool(Tool.ToolCode.JAKR, "Ridgid", "Jackhammer", BigDecimal.valueOf(2.99), false,
				false);

		assertTrue(tool.getBrand().equalsIgnoreCase("Ridgid"));
		assertTrue(tool.getToolType().equalsIgnoreCase("Jackhammer"));
		assertEquals(tool.getToolCode(), Tool.ToolCode.JAKR);
		assertEquals(tool.getRentalRate(), BigDecimal.valueOf(2.99));
		assertFalse(tool.getWeekendCharge());
		assertFalse(tool.getHolidayCharge());
	}
}