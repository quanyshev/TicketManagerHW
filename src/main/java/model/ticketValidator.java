package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ticketValidator {
    static LocalDate today = LocalDate.now();
    public static int errorCounterStartDate = 0;
    public static int errorCounterTicketType = 0;
    public static int errorCounterPrice = 0;
    static int ticketCounter = 0;
    static int invalidTicketCounter = 0;

    public static void validate(BusTicket busTicket) {
        List<String> errors = new ArrayList<>();

        ticketCounter++;
        // Check for valid startDate
        if (busTicket.getStartDate() == null) {
            errorCounterStartDate++;
            errors.add("BusTicket start date can't be null!");
        } else if (LocalDate.parse(busTicket.getStartDate()).isAfter(today)) {
            errorCounterStartDate++;
            errors.add("BusTicket start date can't be future!");
        }

        // Check for valid ticket type
        if (busTicket.getTicketType() == null) {
            errorCounterStartDate++;
            errors.add("BusTicket type can't be null!");
        } else if (!busTicket.getTicketType().equals("DAY") &&
                !busTicket.getTicketType().equals("WEEK") &&
                !busTicket.getTicketType().equals("YEAR")) {
            errorCounterTicketType++;
            errors.add("Invalid ticketType. Allowed only: DAY, WEEK, YEAR");
        }

        // Check for price not being zero
        if (busTicket.getPrice() == null) {
            errorCounterStartDate++;
            errors.add("BusTicket price can't be null!");
        }else if (busTicket.getPrice().equals("0")) {
            errorCounterPrice++;
            errors.add("Price can't be zero");
        }

        // throw error
        if (!errors.isEmpty()) {
            invalidTicketCounter++;
            throw new IllegalArgumentException("Error with Ticket " + ticketCounter + ": "+ String.join(" || ", errors));
        }
    }

    public static void showErrorStats() {
        int mostPopularViolation = Math.max(Math.max(errorCounterStartDate, errorCounterTicketType), errorCounterPrice);
        String mostPopularViolationType = "";
        if (mostPopularViolation == errorCounterStartDate) {
            mostPopularViolationType = "StartDate";
        } else if (mostPopularViolation == errorCounterTicketType) {
            mostPopularViolationType = "TicketType";
        } else {
            mostPopularViolationType = "Price";
        }

        System.out.println("Total number of tickets: " + ticketCounter);
        System.out.println("Number of valid tickets: " + (ticketCounter-invalidTicketCounter));
        System.out.println("Most popular violated field: " + mostPopularViolationType);
    }
}
