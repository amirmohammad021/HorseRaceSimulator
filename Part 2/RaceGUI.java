import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceGUI extends JFrame {
    private Race race;
    private BettingSystem bettingSystem;

    public RaceGUI() {
        race = new Race(100); // Change the race length to 100 meters
        bettingSystem = new BettingSystem();

        // Set up the JFrame
        setTitle("Horse Race and Betting System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        JTextArea raceTrack = new JTextArea();
        raceTrack.setEditable(false);
        JButton startRaceButton = new JButton("Start Race");
        JButton displayOddsButton = new JButton("Display Current Odds");
        JButton placeBetButton = new JButton("Place Bet");
        JButton displayBalanceButton = new JButton("Display User Balance");

        // Add components to the JFrame
        add(raceTrack, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startRaceButton);
        buttonPanel.add(displayOddsButton);
        buttonPanel.add(placeBetButton);
        buttonPanel.add(displayBalanceButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to buttons
        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                race.startRace();
            }
        });

        displayOddsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bettingSystem.updateBettingOdds(race);
                bettingSystem.displayCurrentOdds();
            }
        });

        placeBetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = JOptionPane.showInputDialog("Enter your username:");
                String horseName = JOptionPane.showInputDialog("Enter the horse name you want to bet on:");
                double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount you want to bet:"));
                Horse selectedHorse = new Horse(horseName);
                bettingSystem.placeBet(userName, selectedHorse, amount);
            }
        });

        displayBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = JOptionPane.showInputDialog("Enter your username:");
                bettingSystem.displayUserBalance(userName);
            }
        });

        // Pack and display the JFrame
        pack();
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RaceGUI();
            }
        });
    }
}
