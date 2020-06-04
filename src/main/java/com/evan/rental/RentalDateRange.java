package com.evan.rental;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;

public class RentalDateRange {

    private final LocalDate rentalStartDate;
    private final LocalDate rentalEndDate;
    private final boolean chargeForWeekend;
    private final boolean chargeForHoliday;

    RentalDateRange(LocalDate rentalStartDate, LocalDate rentalEndDate, boolean chargeForWeekend, boolean chargeForHoliday) throws Exception{
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = validateRentalEndDate(rentalEndDate);
        this.chargeForWeekend = chargeForWeekend;
        this.chargeForHoliday = chargeForHoliday;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    LocalDate validateRentalEndDate(LocalDate rentalEndDate) throws Exception {
        if(rentalEndDate.compareTo(rentalStartDate) < 0) {
            throw new Exception("End rental date is before starting rental date.");
        }
        return rentalEndDate;
    }

    public String getFormattedRentalStartDate() {
        return formatDate(rentalStartDate);
    }

    public String getFormattedRentalEndDate() {
        return formatDate(rentalEndDate);
    }

    public String formatDate(LocalDate date) {
        try {
            SimpleDateFormat inputParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
            Date toFormate = inputParse.parse(date.toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return simpleDateFormat.format(toFormate);
        }catch (Exception e) {
            return date.toString();
        }
    }

    public int numberOfChargeableDays() {

        return (int) Stream.iterate(rentalStartDate.plusDays(1), date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(rentalStartDate.plusDays(1), rentalEndDate)+1)
                .filter(this::isChargeableAfterCheckingDateForHolidayException)
                .filter(this::isChargeableAfterCheckingWeekendException)
                .count();
    }

    public boolean isChargeableAfterCheckingDateForHolidayException(LocalDate date) {
        if (date.getDayOfMonth()==whenShouldJulyFourthBeApplied().getDayOfMonth() && date.getMonth()== Month.JULY) {
            return chargeForHoliday;
        } else if (date.getDayOfWeek() == DayOfWeek.MONDAY && date.getMonth() == Month.SEPTEMBER) {
            return chargeForHoliday;
        } else return true;
    }

    public boolean isChargeableAfterCheckingWeekendException(LocalDate date) {
        if(date.getDayOfWeek().getValue()>5) {
            return chargeForWeekend;
        }
        return true;
    }

    public LocalDate whenShouldJulyFourthBeApplied() {
        int dayToApplyJulyFourth;
        switch(LocalDate.of(LocalDate.now().getYear(), 7, 4).getDayOfWeek()) {
            case SATURDAY:
                dayToApplyJulyFourth = 3;
                break;
            case SUNDAY:
                dayToApplyJulyFourth = 5;
                break;
            default:
                dayToApplyJulyFourth = 4;
        }
        return LocalDate.of(LocalDate.now().getYear(), 7, dayToApplyJulyFourth);

    }
}
