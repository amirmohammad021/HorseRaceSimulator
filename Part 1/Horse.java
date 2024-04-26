
/**
 * Write a description of class Horse here.
 * 
 * @author Amirmohammad Khazaei Poul
 * @version (a version number or a date)
 */
public class Horse {
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private double confidence;

    public Horse(String horseName) {
        name = horseName;
        symbol = 'H';
        distanceTravelled = 0;
        hasFallen = false;
        confidence = 0.5;
    }

    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        symbol = horseSymbol;
        name = horseName;
        setConfidence(horseConfidence); // Use setter to add checks
    }

    // Accessor methods
    public double getConfidence() {
        return confidence;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean hasFallen() {
        return hasFallen;
    }

    // Mutator methods
    public void fall() {
        hasFallen = true;
    }

    public void standUp() {
        hasFallen = false;
    }

    public void goBackToStart() {
        distanceTravelled = 0;
    }

    public void moveForward() {
        distanceTravelled++;
    }

    public void setConfidence(double newConfidence) {
        if (newConfidence < 0 || newConfidence > 1) {
            throw new IllegalArgumentException("Confidence must be between 0 and 1");
        }
        confidence = newConfidence;
    }

    public void setSymbol(char newSymbol) {
        symbol = newSymbol;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setDistanceTravelled(int newDistanceTravelled) {
        if (newDistanceTravelled < 0) {
            throw new IllegalArgumentException("Distance travelled cannot be negative");
        }
        distanceTravelled = newDistanceTravelled;
    }
}
