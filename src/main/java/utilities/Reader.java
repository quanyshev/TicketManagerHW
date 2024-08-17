package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    public static int chooseSource() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 0 or 1 to choose the source (0 - from console, 1 - from file): ");
        int choice = scanner.nextInt();
        return choice;
    }

    // to read tickets from console
    public static String readConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input:");
        return scanner.nextLine();
    }

    // to read tickets from file
    public static Scanner readFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file name from only from resources folder: ");
        String fileName = scanner.nextLine();
        Scanner file_sc = null;

        try {
            file_sc = new Scanner(new File("/Users/quanyshev/IdeaProjects/TicketManager/src/main/resources/" + fileName));
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        }
        return file_sc;
    }
}