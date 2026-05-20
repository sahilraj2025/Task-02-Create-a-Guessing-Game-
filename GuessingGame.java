import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int targetNumber;
    private int attempts;

    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JButton restartButton;

    public GuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        instructionLabel = new JLabel("Guess a number between 1 and 100:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(instructionLabel, gbc);

        guessField = new JTextField(10);
        guessField.setFont(new Font("Arial", Font.PLAIN, 14));
        guessField.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(guessField, gbc);

        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 14));
        guessButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(guessButton, gbc);

        feedbackLabel = new JLabel("Enter your guess to start!", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(feedbackLabel, gbc);

        restartButton = new JButton("Play Again");
        restartButton.setFont(new Font("Arial", Font.BOLD, 14));
        restartButton.setBackground(new Color(60, 179, 113)); // Medium Sea Green
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(restartButton, gbc);

        initGame();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        // Allow user to press Enter to submit guess
        guessField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initGame();
            }
        });
    }

    private void initGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1; // 1 to 100
        attempts = 0;
        guessField.setText("");
        guessField.setEditable(true);
        guessButton.setEnabled(true);
        feedbackLabel.setText("Enter your guess to start!");
        feedbackLabel.setForeground(Color.BLACK);
        restartButton.setVisible(false);
        guessField.requestFocus();
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText().trim());
            attempts++;

            if (guess < 1 || guess > 100) {
                feedbackLabel.setText("Please enter a number between 1 and 100.");
                feedbackLabel.setForeground(Color.RED);
            } else if (guess < targetNumber) {
                feedbackLabel.setText("Too low! Try again.");
                feedbackLabel.setForeground(new Color(255, 140, 0)); // Dark Orange
                guessField.setText("");
                guessField.requestFocus();
            } else if (guess > targetNumber) {
                feedbackLabel.setText("Too high! Try again.");
                feedbackLabel.setForeground(new Color(255, 140, 0));
                guessField.setText("");
                guessField.requestFocus();
            } else {
                feedbackLabel.setText("Correct! You guessed it in " + attempts + " attempts.");
                feedbackLabel.setForeground(new Color(34, 139, 34)); // Forest Green
                guessField.setEditable(false);
                guessButton.setEnabled(false);
                restartButton.setVisible(true);
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input! Please enter a valid number.");
            feedbackLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        // Run GUI construction on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessingGame().setVisible(true);
            }
        });
    }
}
