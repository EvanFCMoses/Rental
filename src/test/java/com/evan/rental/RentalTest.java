package com.evan.rental;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class RentalTest {


    private Tool createTool() {
        return new Tool(Tool.ToolCode.JAKR, "Ridgid", "Jackhammer", BigDecimal.valueOf(2.99), false,
                false);
    }

    @Test
    public void testRentalCreation() {
        Tool tool = createTool();
        Rental rental = new Rental(tool, LocalDate.now(), LocalDate.now().plusDays(10));

        assertTrue(rental.getTool().getBrand().equalsIgnoreCase("Ridgid"));
        assertEquals(rental.getRentalStartDate(), LocalDate.now());
        assertEquals(rental.getRentalEndDate(), LocalDate.now().plusDays(10));
    }

    @Test
    public void testRentalCalculation() {
        Tool tool = createTool();

        Rental rental = new Rental(tool, LocalDate.of(2020, 6, 2), LocalDate.of(2020, 6,5));

        assertEquals(rental.calculateAmountDue(), BigDecimal.valueOf(4).multiply(BigDecimal.valueOf(2.99)));
    }

    @Test
    public void testRentalWithAWeekendAndAToolThatIsNotChargedOnTheWeekend() {
        Tool tool = createTool();
        Rental rental = new Rental(tool, LocalDate.of(2020, 6, 5), LocalDate.of(2020, 6,8));

        assertEquals(rental.calculateAmountDue(), BigDecimal.valueOf(2).multiply(BigDecimal.valueOf(2.99)));
    }
}
