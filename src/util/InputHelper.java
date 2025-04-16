package util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 Utility class for handling input methods.
**/

public class InputHelper {

    // Static way to get a mark and check if it is okay (0-10)
    public static double inputSingleMark(Scanner sc, String prompt) {
        double mark;
        while (true) {
            try {
                System.out.print(prompt);
                mark = sc.nextDouble();
                sc.nextLine(); 
                if (mark >= 0 && mark <= 10) {
                    return mark;
                } else {
                    System.out.println("Mark must be between 0 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // take bad input
            }
        }
    }
    
    // (Optional) Add a function to input an integer in the range if needed
    public static int inputIntInRange(Scanner sc, String prompt, int min, int max) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = sc.nextInt();
                sc.nextLine();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("❌ Number must be between %d and %d.%n", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }
    }
    
    // (Optional) Enter a non-empty string
    public static String inputNonEmptyString(Scanner sc, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("❌ Input cannot be empty.");
            }
        }
    }
}

