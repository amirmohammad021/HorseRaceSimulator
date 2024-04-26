import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the number of staff members
        System.out.print("How many staff members would you like to add? ");
        int numberOfStaff = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Loop to gather details for each staff member
        for (int i = 0; i < numberOfStaff; i++) {
            System.out.println("Staff Member " + (i + 1) + ":");

            // Ask for the name of the staff member
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            // Ask for the position of the staff member
            System.out.print("Enter position: ");
            String position = scanner.nextLine();

            // Ask for the age of the staff member
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Ask for the experience of the staff member
            System.out.print("Enter years of experience: ");
            int experience = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Output the gathered information
            System.out.println("Staff Member " + (i + 1) + " Details:");
            System.out.println("Name: " + name);
            System.out.println("Position: " + position);
            System.out.println("Age: " + age);
            System.out.println("Experience: " + experience);
        }

        // Close the scanner
        scanner.close();
    }
}
