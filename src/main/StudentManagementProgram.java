package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import management.StudentManagement;
import model.Biz;
import model.IT;
import model.GD;
import util.InputHelper;

/**
 * The StudentManagementProgram class contains the console-based menu interface and handles user interactions.
 */
public class StudentManagementProgram {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            // Display main menu
            System.out.println("===== STUDENT MANAGEMENT MENU =====");
            System.out.println("1. Display student list");
            System.out.println("2. Search student");
            System.out.println("3. Sort students by name");
            System.out.println("4. Sort students by average mark");
            System.out.println("5. Add a new student");
            System.out.println("6. Remove a student");
            System.out.println("7. Update student information");
            System.out.println("8. Exit program");
            System.out.print("Choose an option: ");

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                sc.nextLine(); // Clear the buffer
                continue;
            }

            switch (choice) {
                case 1:
                    management.displayAll();
                    break;
                case 2:
                    System.out.println("🔍 Choose search method:");
                    System.out.println("1. Search by ID");
                    System.out.println("2. Search by Name");
                    System.out.print("Your choice: ");
                    int searchChoice = sc.nextInt();
                    sc.nextLine(); // Clear newline

                    switch (searchChoice) {
                        case 1:
                            System.out.print("Enter student ID to search: ");
                            String searchId = sc.nextLine().trim();
                            management.searchById(searchId); // Using HashMap
                            break;

                        case 2:
                            System.out.print("Enter name to search: ");
                            String name = sc.nextLine().trim();
                            management.binarySearchByName(name); 
                            break;

                        default:
                            System.out.println("❌ Invalid choice.");
                            break;
                    }
                    break;

                case 3:
                    management.sortByName();
                    break;
                case 4:
                    management.sortByAverageMark();
                    break;
                case 5:
                    System.out.println("Choose the student's major: ");
                    System.out.println("1. Biz");
                    System.out.println("2. IT");
                    System.out.println("3. GD");
                    System.out.print("Your choice: ");
                    int type = 0;
                    try {
                        type = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Please enter a valid number.");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("Enter student ID: ");
                    String studentId = sc.next();
                    sc.nextLine(); // Clear newline
                    System.out.print("Enter full name: ");
                    String fullName = sc.nextLine();

                    try {
                        if (type == 1) { // Biz student
                            double sale = InputHelper.inputSingleMark(sc, "Enter Sale mark: ");
                            double marketing = InputHelper.inputSingleMark(sc, "Enter Marketing mark: ");
                            management.addStudent(new Biz(studentId, fullName, sale, marketing));
                        } else if (type == 2) { // IT student
                            double html = InputHelper.inputSingleMark(sc, "Enter HTML mark: ");
                            double css = InputHelper.inputSingleMark(sc, "Enter CSS mark: ");
                            double math = InputHelper.inputSingleMark(sc, "Enter Math mark: ");
                            management.addStudent(new IT(studentId, fullName, html, css, math));
                        } else if (type == 3) { // GD student
                            double color = InputHelper.inputSingleMark(sc, "Enter Color mark: ");
                            double pts = InputHelper.inputSingleMark(sc, "Enter PTS mark: ");
                            double ai = InputHelper.inputSingleMark(sc, "Enter AI mark: ");
                            management.addStudent(new GD(studentId, fullName, color, pts, ai));
                        } else {
                            System.out.println("Invalid major choice.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid input data. Please try again.");
                        sc.nextLine();
                    }
                    break;
                case 6:
                    System.out.print("Enter student ID to remove: ");
                    String removeId = sc.next();
                    management.removeStudent(removeId);
                    break;
                case 7:
                    System.out.print("Enter student ID to update: ");
                    String updateId = sc.next();
                    management.updateStudent(updateId, sc);
                    break;
                case 8:
                    System.out.println("Exiting the program. See you next time!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println(); // Empty line for better UI
        } while (choice != 8);

        sc.close();
    }
}
