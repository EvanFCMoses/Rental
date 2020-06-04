package com.evan.rental;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class RentalDateRangeTest {

    @Test
    public void testWhetherDateShouldBeAChargeableHoliday() {
        Tool chargeableTool = new Tool(Tool.ToolCode.CHNS, "Stihl", "Chainsaw", BigDecimal.valueOf(1.49), false,
                true);
        Tool nonChargeableTool = new Tool(Tool.ToolCode.CHNS, "Stihl", "Chainsaw", BigDecimal.valueOf(1.49), false,
                false);

        try {
            Rental holidayChargeableRental = new Rental(chargeableTool, LocalDate.of(2020, 7, 4),
                    LocalDate.of(2020, 7, 5), BigDecimal.valueOf(0));
            Rental nonHolidayChargeableRental = new Rental(nonChargeableTool, LocalDate.of(2020, 7, 4),
                    LocalDate.of(2020, 7, 5), BigDecimal.valueOf(0));

            assertFalse(nonHolidayChargeableRental.getRentalDateRange()
                    .isChargeableAfterCheckingDateForHolidayException(LocalDate.of(2020, 7, 3)));
            assertFalse(nonHolidayChargeableRental.getRentalDateRange()
                    .isChargeableAfterCheckingDateForHolidayException(LocalDate.of(2020, 9, 7)));
            assertTrue(holidayChargeableRental.getRentalDateRange()
                    .isChargeableAfterCheckingDateForHolidayException(LocalDate.of(2020, 6, 20)));
            assertFalse(nonHolidayChargeableRental.getRentalDateRange()
                    .isChargeableAfterCheckingDateForHolidayException(LocalDate.of(2020, 7, 3)));
        }catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNumberOfChargeableDaysAfterWeekendAndHolidayExceptions() {
        Tool tool = new Tool(Tool.ToolCode.CHNS, "Stihl", "Chainsaw", BigDecimal.valueOf(1.49), false,
                false);

        try {
            Rental rental = new Rental(tool, LocalDate.of(2020, 6, 5), LocalDate.of(2020,
                    6, 8), BigDecimal.valueOf(0));

            assertEquals(1, rental.getRentalDateRange().numberOfChargeableDays());
        }catch (Exception e) {
            fail();
        }
    }
}
