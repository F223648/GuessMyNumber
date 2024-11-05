package edu.nwmissouri.guessmynumber;

import javax.swing.*;

/**
 * “Guess a Number” game that allows the user to guess a random number. Uses Swing
 * to make a nice graphical user interface.
 *
 * Original Source: https://hackr.io/blog/java-projects
 *
 * Read about jOptionPane:
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
 * .showInputDialog() .showMessageDialog()
 *
 */
public class GuessingGameCase {

    public static void main(String[] args) {
        GuessingGameCase game = new GuessingGameCase(); 
        int secretNumber = (int) (Math.random() * 100 + 1);
        int userAnswer = 0;
        System.out.println("The correct guess would be " + secretNumber);
        int count = 1;

        while (userAnswer != secretNumber) {
            String message = "Enter a guess between 1 and 100";  
            String title = "Guessing Game"; 
            int messageType = JOptionPane.INFORMATION_MESSAGE;

            String response = JOptionPane.showInputDialog(null, message, title, messageType);
            if (response == null) {
                JOptionPane.showMessageDialog(null, "Game Cancelled");
                return; 
            }

            try {
                userAnswer = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number!");
                continue; // Skip to the next iteration of the loop
            }

            JOptionPane.showMessageDialog(null, game.calculateMessage(userAnswer, secretNumber, count));
            count++;
        }
    }

    public String calculateMessage(int userAnswer, int computerNumber, int count) {
        if (userAnswer <= 0 || userAnswer > 100) {
            return "Your guess is invalid. Please guess a number between 1 and 100.";
        } else if (userAnswer == computerNumber) {
            return "Correct!\nTotal Guesses: " + count;
        } else if (userAnswer > computerNumber) {
            return "Your guess is too high, try again.\nYou've used " + count + " guesses.";
        } else {
            return "Your guess is too low, try again.\nYou've used " + count + " guesses.";
        }
    }
}