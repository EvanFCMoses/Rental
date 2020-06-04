package com.evan.rental;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class SolutionValidationTests {

    @Test
    public void testCase1() {
        Tool tool = new Tool(Tool.ToolCode.JAKR, "Ridgid", "Jackhammer", BigDecimal.valueOf(2.99),
                false, false);
        try {
            Rental rental = new Rental(tool, LocalDate.of(2015, Month.SEPTEMBER, 15),
                    LocalDate.of(2015, Month.SEPTEMBER, 19), BigDecimal.valueOf(101));
        } catch (Exception e) {
            assertEquals("Discount was entered as greater than 100 which is not allowed. Enter an amount between 0 and 100", e.getMessage());
        }
    }

    @Test
    public void testCase2() {
        Tool tool = new Tool(Tool.ToolCode.LADW, "Werner", "Ladder", BigDecimal.valueOf(1.99),
                true, false);

        try{
            Rental rental = new Rental(tool, LocalDate.of(2020, Month.JULY, 2),
                    LocalDate.of(2020, Month.JULY, 4), BigDecimal.valueOf(10));

            assertEquals(BigDecimal.valueOf(1.79).setScale(2, RoundingMode.HALF_EVEN),
                    rental.calculateAmountDueAndDisplay());
        } catch (Exception e) {
            fail();
        }


    }

    @Test
    public void testCase3() {
        Tool tool = new Tool(Tool.ToolCode.CHNS, "Stihl", "Chainsaw", BigDecimal.valueOf(1.49),
                false, true);

        try{
            Rental rental = new Rental(tool, LocalDate.of(2015, Month.JULY, 2),
                    LocalDate.of(2015, Month.JULY, 7), BigDecimal.valueOf(25));

            assertEquals(BigDecimal.valueOf(3.35).setScale(2, RoundingMode.HALF_EVEN),
                    rental.calculateAmountDueAndDisplay());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCase4() {
        Tool tool = new Tool(Tool.ToolCode.JAKD, "DeWalt", "Jackhammer", BigDecimal.valueOf(2.99),
                false, false);

        try{
            Rental rental = new Rental(tool, LocalDate.of(2015, Month.SEPTEMBER, 15),
                    LocalDate.of(2015, Month.SEPTEMBER, 20), BigDecimal.valueOf(0));

            assertEquals(BigDecimal.valueOf(8.97).setScale(2, RoundingMode.HALF_EVEN),
                    rental.calculateAmountDueAndDisplay());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCase5() {
        Tool tool = new Tool(Tool.ToolCode.JAKR, "Ridgid", "Jackhammer", BigDecimal.valueOf(2.99),
                false, false);

        try{
            Rental rental = new Rental(tool, LocalDate.of(2015, Month.JULY, 2),
                    LocalDate.of(2015, Month.JULY, 10), BigDecimal.valueOf(0));

            assertEquals(BigDecimal.valueOf(14.95).setScale(2, RoundingMode.HALF_EVEN),
                    rental.calculateAmountDueAndDisplay());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCase6() {
        Tool tool = new Tool(Tool.ToolCode.JAKR, "Ridgid", "Jackhammer", BigDecimal.valueOf(2.99),
                false, false);

        try{
            Rental rental = new Rental(tool, LocalDate.of(2020, Month.JULY, 2),
                    LocalDate.of(2020, Month.JULY, 5), BigDecimal.valueOf(50));

            assertEquals(BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_EVEN),
                    rental.calculateAmountDueAndDisplay());
        } catch (Exception e) {
            fail();
        }
    }


}
