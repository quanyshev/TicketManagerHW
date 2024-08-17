import model.BusTicket;
import utilities.Reader;

import jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Scanner;

import static model.ticketValidator.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<BusTicket> busTickets = new ArrayList<>();
        int source = Reader.chooseSource();
        int ticketsForCheck = 5;
        int counter = 0;

        if (source == 0) {
            do {
                String input = Reader.readConsole();
                BusTicket busTicket = null;
                try {
                    busTicket = new ObjectMapper().readValue(input, BusTicket.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                busTickets.add(busTicket);
                System.out.println(busTicket);
                counter++;

            } while (counter < ticketsForCheck);
        } else {
            Scanner input = Reader.readFile();
            while (input.hasNextLine() && counter < ticketsForCheck) {
                BusTicket busTicket = null;
                try {
                    busTicket = new ObjectMapper().readValue(input.nextLine(), BusTicket.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(busTicket);
                busTickets.add(busTicket);
                counter++;
            }
        }

        //validate and show errors
        for (BusTicket busTicket : busTickets) {
            try {
                validate(busTicket);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        //show error stat
        showErrorStats();
    }
}