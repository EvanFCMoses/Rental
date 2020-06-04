package com.evan.rental;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        try {
            Rental rental = new Rental(tool, LocalDate.now(), LocalDate.now().plusDays(10), BigDecimal.valueOf(0));

            assertTrue(rental.getTool().getBrand().equalsIgnoreCase("Ridgid"));
            assertEquals(LocalDate.now(), rental.getRentalDateRange().getRentalStartDate());
            assertEquals(LocalDate.now().plusDays(10), rental.getRentalDateRange().getRentalEndDate());
        }catch (Exception e) {
            fail();
        }


    }

    @Test
    public void testRentalCalculation() {
        Tool tool = createTool();

        try {
            Rental rental = new Rental(tool, LocalDate.of(2020, 6, 2), LocalDate.of(2020,
                    6, 5), BigDecimal.valueOf(0));

            assertEquals(BigDecimal.valueOf(3).multiply(BigDecimal.valueOf(2.99)), rental.calculateAmountDue());
        }catch (Exception e){
            fail();
        }
    }




    @Test
    public void testRentalWithAWeekendAndAToolThatIsNotChargedOnTheWeekend() {
        Tool tool = createTool();

        try {
            Rental rental = new Rental(tool, LocalDate.of(2020, 6, 5), LocalDate.of(2020,
                    6, 8), BigDecimal.valueOf(0));

            assertEquals(BigDecimal.valueOf(1).multiply(BigDecimal.valueOf(2.99)), rental.calculateAmountDue());
        }catch(Exception e) {
            fail();
        }
    }

    @Test
    public void testRentalWithAToolOverAHoliday() {
        Tool tool = createTool();

        try {
            Rental rental = new Rental(tool, LocalDate.of(2020, 9, 7), LocalDate.of(2020,
                    9, 10), BigDecimal.valueOf(0));

            assertEquals(BigDecimal.valueOf(3).multiply(BigDecimal.valueOf(2.99)), rental.calculateAmountDue());
        }catch(Exception e) {
            fail();
        }
    }

    @Test
    public void testApplyingADiscount() {
        Tool tool = createTool();

        try {
            Rental rental = new Rental(tool, LocalDate.of(2020, 9, 7), LocalDate.of(2020,
                    9,10), BigDecimal.valueOf(35));
            assertEquals(BigDecimal.valueOf(5.83).setScale(2, RoundingMode.HALF_EVEN), rental.calculateAmountDue());
        } catch (Exception e) {
            fail();
        }
    }
}
