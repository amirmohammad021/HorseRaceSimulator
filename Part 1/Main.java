public class Main {
    public static void main(String[] args) {
        // Create a race with a distance of 50 meters
        Race race = new Race(10);

        // Create three horses
        Horse horse1 = new Horse('1', "Thunder", 0.8);
        Horse horse2 = new Horse('2', "Lightning", 0.7);
        Horse horse3 = new Horse('3', "Storm", 0.9);

        // Add horses to the race lanes
        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        // Start the race
        race.startRace();

        // Create a betting system
        BettingSystem bettingSystem = new BettingSystem();

        // Add some users with initial balances
        bettingSystem.addUser("Alice", 100);
        bettingSystem.addUser("Bob", 200);

        // Update betting odds based on horse performance
        bettingSystem.updateBettingOdds(race);

        // Display current odds
        bettingSystem.displayCurrentOdds();

        // Users place bets
        bettingSystem.placeBet("Alice", horse1, 50); // Alice bets on horse1
        bettingSystem.placeBet("Bob", horse2, 100); // Bob bets on horse2

        // Display user balances before settling bets
        bettingSystem.displayUserBalance("Alice");
        bettingSystem.displayUserBalance("Bob");

        // Display user balances after settling bets
        bettingSystem.displayUserBalance("Alice");
        bettingSystem.displayUserBalance("Bob");
    }
}
