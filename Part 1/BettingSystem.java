import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BettingSystem {
    private Map<Horse, Double> odds; // Map to store betting odds for each horse
    private Map<String, Double> userBalances; // Map to store users' virtual balances
    private Scanner scanner;

    public BettingSystem() {
        odds = new HashMap<>();
        userBalances = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    // Method to calculate and update betting odds based on horse performance metrics
    public void updateBettingOdds(Race race) {
        // Implement actual logic to calculate odds based on horse performance metrics
        // For demonstration purposes, let's assign random odds
        for (Horse horse : race.getHorses()) {
            odds.put(horse, Math.random() * 10); // Assign random odds (between 0 and 10)
        }
    }

    // Method to display current odds before the start of each race
    public void displayCurrentOdds() {
        System.out.println("\nCurrent Betting Odds:\n");
        for (Horse horse : odds.keySet()) {
            System.out.println(horse.getName() + ": " + odds.get(horse));
        }
    }

    // Method to allow users to place bets
    public void placeBet(String userName, Horse horse, double amount) {
        if (userBalances.containsKey(userName)) {
            double balance = userBalances.get(userName);
            if (balance >= amount) {
                // Deduct bet amount from user's balance
                userBalances.put(userName, balance - amount);
                // Update odds dynamically (for demonstration purposes, we won't implement dynamic odds updating)
                // For simplicity, let's assume the odds remain the same after placing the bet
                System.out.println("Bet placed successfully.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("User does not exist.");
        }
    }

    // Method to display user's current balance
    public void displayUserBalance(String userName) {
        if (userBalances.containsKey(userName)) {
            System.out.println("\nCurrent balance for user " + userName + ": " + userBalances.get(userName));
        } else {
            System.out.println("User does not exist.");
        }
    }

    // Method to settle bets and update user balances based on race outcome
    public void settleBets(Race race, Horse winner) {
        for (String userName : userBalances.keySet()) {
            double balance = userBalances.get(userName);
            // If user bet on the winning horse, calculate winnings based on odds
            if (race.getHorses().contains(winner)) {
                double betAmount = balance - userBalances.get(userName); // Bet amount
                double winnings = betAmount * odds.get(winner); // Winnings based on odds
                userBalances.put(userName, balance + winnings); // Update user balance with winnings
                System.out.println("Congratulations! You won " + winnings + " virtual currency.");
            } else {
                System.out.println("Sorry, you lost your bet.");
            }
        }
    }

    // Method to add new users to the betting system with initial balance
    public void addUser(String userName, double initialBalance) {
        if (!userBalances.containsKey(userName)) {
            userBalances.put(userName, initialBalance);
            System.out.println("User " + userName + " added with initial balance: " + initialBalance);
        } else {
            System.out.println("User already exists.");
        }
    }

    // Method to simulate a simple user interface for betting
    public void simulateBettingInterface() {
        System.out.println("\nWelcome to the Betting System!");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Current Odds");
            System.out.println("2. Place Bet");
            System.out.println("3. Display User Balance");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayCurrentOdds();
                    break;
                case 2:
                    scanner.nextLine(); // Consume newline
                    System.out.print("\nEnter your username: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter the horse name you want to bet on: ");
                    String horseName = scanner.nextLine();
                    System.out.print("Enter the amount you want to bet: ");
                    double amount = scanner.nextDouble();
                    Horse selectedHorse = new Horse(horseName); // Assume Horse class is available
                    placeBet(userName, selectedHorse, amount);
                    break;
                case 3:
                    scanner.nextLine(); // Consume newline
                    System.out.print("\nEnter your username: ");
                    String user = scanner.nextLine();
                    displayUserBalance(user);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
