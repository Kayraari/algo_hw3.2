
// Title: Main class - Entry point for TasteChain restaurant hierarchy system
// Author: Kayra Arı-Elçin Karagül
// ID: 10001507-10885319050
// Section: 4
// Assignment: 3
/* Description: This class reads the filename from the user, builds the general
                tree from the file, and provides a menu-driven interface for
                performing operations on the organizational hierarchy.
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        GeneralTree tree = new GeneralTree();

        // get filename from user and build the tree
        System.out.println("Enter the filename: ");
        String fileName = keyboard.nextLine().trim();
        tree.buildTreeFromFile(fileName);

        // menu loop
        boolean running = true;
        while (running) {
            // print menu options
            System.out.println("\nMenu:");
            System.out.println("1. Print Subordinates");
            System.out.println("2. Find Common Manager");
            System.out.println("3. Calculate Total Salary");
            System.out.println("4. Check Manager");
            System.out.println("5. Find Path");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(keyboard.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            if (choice == 1) {
                // Print all direct subordinates of the given employee
                System.out.print("Enter employee name: ");
                String subName = keyboard.nextLine().trim();
                tree.printSubordinates(subName);

            } else if (choice == 2) {
                // Find the lowest common ancestor (common manager) of two employees
                System.out.print("Enter first employee name: ");
                String emp1 = keyboard.nextLine().trim();
                System.out.print("Enter second employee name: ");
                String emp2 = keyboard.nextLine().trim();
                tree.findCommonManager(emp1, emp2);

            } else if (choice == 3) {
                // Calculate the total salary of an employee and all under them
                System.out.print("Enter employee name: ");
                String salName = keyboard.nextLine().trim();
                tree.printTotalSalary(salName);

            } else if (choice == 4) {
                // Check if the first employee is a manager of the second
                System.out.print("Enter first employee name: ");
                String managerName = keyboard.nextLine().trim();
                System.out.print("Enter second employee name: ");
                String subordinateName = keyboard.nextLine().trim();
                tree.checkManager(managerName, subordinateName);

            } else if (choice == 5) {
                // Find and print the path from root to the given employee
                System.out.print("Enter employee name: ");
                String pathName = keyboard.nextLine().trim();
                tree.findPath(pathName);

            } else if (choice == 6) {
                // Exit the program
                System.out.println("Goodbye!");
                running = false;

            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        keyboard.close();
    }
}