public class HorseTest {
    public static void main(String[] args) {
        // Create a new Horse object
        Horse horse = new Horse('H', "Black Beauty", 0.7);

        // Test the accessor methods
        System.out.println("Name: " + horse.getName()); // Expected: Black Beauty
        System.out.println("Symbol: " + horse.getSymbol()); // Expected: H
        System.out.println("Confidence: " + horse.getConfidence()); // Expected: 0.7
        System.out.println("Has Fallen: " + horse.hasFallen()); // Expected: false
        System.out.println("Distance Travelled: " + horse.getDistanceTravelled()); // Expected: 0

        // Test the mutator methods
        horse.moveForward();
        System.out.println("Distance Travelled: " + horse.getDistanceTravelled()); // Expected: 1

        horse.fall();
        System.out.println("Has Fallen: " + horse.hasFallen()); // Expected: true

        horse.standUp();
        System.out.println("Has Fallen: " + horse.hasFallen()); // Expected: false

        horse.goBackToStart();
        System.out.println("Distance Travelled: " + horse.getDistanceTravelled()); // Expected: 0

        try {
            horse.setConfidence(1.5); // This should throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Expected: Confidence must be between 0 and 1
        }

        try {
            horse.setDistanceTravelled(-5); // This should throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Expected: Distance travelled cannot be negative
        }
    }
}