import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell
 * @version 1.1
 */
public class Race {
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;

    // Statistics and Analytics module
    private static Map<Horse, List<Integer>> horsePerformance = new HashMap<>(); // Map to store each horse's performance metrics    

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Race distance must be positive");
        }
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
    }

    // Method to get the list of horses participating
    public List<Horse> getHorses() {
        List<Horse> horses = new ArrayList<>();
        if (lane1Horse != null) {
            horses.add(lane1Horse);
        }
        if (lane2Horse != null) {
            horses.add(lane2Horse);
        }
        if (lane3Horse != null) {
            horses.add(lane3Horse);
        }
        return horses;
    }

    // Method to update horse performance after each race
    private void updatePerformance(Horse horse) {
        if (!horsePerformance.containsKey(horse)) {
            horsePerformance.put(horse, new ArrayList<>());
        }
        horsePerformance.get(horse).add(horse.getDistanceTravelled());
    }

    // Method to calculate and display performance metrics for each horse
    public void displayPerformanceMetrics() {
        System.out.println("Performance Metrics:");
        System.out.println();
        for (Horse horse : horsePerformance.keySet()) {
            List<Integer> performances = horsePerformance.get(horse);
            int totalDistance = performances.stream().mapToInt(Integer::intValue).sum();
            double averageSpeed = (double) totalDistance / performances.size();
            int bestPerformance = performances.stream().max(Integer::compareTo).orElse(0);
            
            // Initialize worstPerformance with the first recorded performance
            int worstPerformance = performances.isEmpty() ? 0 : performances.get(0);
    
            for (int performance : performances) {
                if (performance < worstPerformance) {
                    worstPerformance = performance;
                }
            }
    
            System.out.println("Horse: " + horse.getName());
            System.out.println("Total races: " + performances.size());
            System.out.println("Average speed: " + averageSpeed);
            System.out.println("Best performance: " + bestPerformance);
            System.out.println();
        }
    }    
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber) {
        switch (laneNumber) {
            case 1:
                if (lane1Horse != null) {
                    throw new IllegalArgumentException("Lane 1 already has a horse");
                }
                lane1Horse = theHorse;
                break;
            case 2:
                if (lane2Horse != null) {
                    throw new IllegalArgumentException("Lane 2 already has a horse");
                }
                lane2Horse = theHorse;
                break;
            case 3:
                if (lane3Horse != null) {
                    throw new IllegalArgumentException("Lane 3 already has a horse");
                }
                lane3Horse = theHorse;
                break;
            default:
                throw new IllegalArgumentException("Invalid lane number: " + laneNumber);
        }
    }
    
    /**
     * Start the race
     * The horses are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace() {
        // Reset all the lanes (all horses not fallen and back to 0).
        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();

        Horse winner = null; // Initialize the winner

        // Loop until a winner is found
        while (true) {
            moveHorse(lane1Horse);
            moveHorse(lane2Horse);
            moveHorse(lane3Horse);

            printRace();

            if (raceWonBy(lane1Horse)) {
                winner = lane1Horse;
                break;
            } else if (raceWonBy(lane2Horse)) {
                winner = lane2Horse;
                break;
            } else if (raceWonBy(lane3Horse)) {
                winner = lane3Horse;
                break;
            }

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Display the winner
        if (winner != null) {
            System.out.println("The winner is: " + winner.getName());
        } else {
            System.out.println("No winner found.");
        }

        // Update performance metrics after the race
        for (Horse horse : getHorses()) {
            updatePerformance(horse);
        }
        displayPerformanceMetrics();
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse) {
        if (theHorse != null && !theHorse.hasFallen()) {
            Random rand = new Random();
            double probabilityForward = theHorse.getConfidence();
            double probabilityFall = 0.1 * theHorse.getConfidence() * theHorse.getConfidence();

            if (rand.nextDouble() < probabilityForward) {
                theHorse.moveForward();
            }

            if (rand.nextDouble() < probabilityFall) {
                theHorse.fall();
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse) {
        return theHorse != null && theHorse.getDistanceTravelled() >= raceLength;
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace() {
        clearConsole();

        printTrackEdge();
        System.out.println();
        
        printLane(lane1Horse);
        System.out.println();
        
        printLane(lane2Horse);
        System.out.println();
        
        printLane(lane3Horse);
        System.out.println();
        
        printTrackEdge();
        System.out.println();    
    }

    /**
     * Print the top or bottom edge of the racetrack
     */
    private void printTrackEdge() {
        multiplePrint('=', raceLength + 3);
    }

    /**
     * Print a horse's lane during the race
     */
    private void printLane(Horse theHorse) {
        if (theHorse == null) {
            return;
        }
        
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        System.out.print('|');
        multiplePrint(' ', spacesBefore);
        System.out.print(theHorse.hasFallen() ? '\u2322' : theHorse.getSymbol());
        multiplePrint(' ', spacesAfter);
        System.out.print('|');
    }

    /***
     * Print a character a given number of times.
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print(aChar);
        }
    }

    /**
     * Clear the console
     */
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
