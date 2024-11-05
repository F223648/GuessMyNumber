package testCases;

import edu.nwmissouri.guessmynumber.GuessingGameCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GuessingGameTest {

    GuessingGameCase game = new GuessingGameCase();

  
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("Test messages for valid guesses from CSV")
    void testCalculateMessageWithValidGuesses(int userAnswer, int secretNumber, int count, String expectedMessage) {
        String result = game.calculateMessage(userAnswer, secretNumber, count);
        assertEquals(expectedMessage, result);
    }

    @Test
    @DisplayName("Test message when guess is incorrect")
    void testCalculateMessageWhenGuessIsIncorrect() {
        assertEquals("Your guess is too low, try again.\nYou've used 7 guesses.", game.calculateMessage(24, 42, 7));
    }

    @Test
    @DisplayName("Test game loop with correct guess")
    void testMainGameLoopWithCorrectGuess() {
        int secretNumber = 42;
        int userAnswer = 42;
        int count = 1;

        assertEquals("Correct!\nTotal Guesses: " + count, game.calculateMessage(userAnswer, secretNumber, count));
    }

    @Test
    @DisplayName("Test game loop with high guess")
    void testMainGameLoopWithHighGuess() {
        int secretNumber = 42;
        int count = 1;

        assertEquals("Your guess is too high, try again.\nYou've used " + count + " guesses.", game.calculateMessage(75, secretNumber, count));
        count++;

        assertEquals("Your guess is too high, try again.\nYou've used " + count + " guesses.", game.calculateMessage(50, secretNumber, count));
        count++;

        assertEquals("Correct!\nTotal Guesses: " + count, game.calculateMessage(42, secretNumber, count));
    }

    @Test
    @DisplayName("Test game loop with low guess")
    void testMainGameLoopWithLowGuess() {
        int secretNumber = 42;
        int count = 1;

        assertEquals("Your guess is too low, try again.\nYou've used " + count + " guesses.", game.calculateMessage(15, secretNumber, count));
        count++;

        assertEquals("Your guess is too low, try again.\nYou've used " + count + " guesses.", game.calculateMessage(30, secretNumber, count));
        count++;

        assertEquals("Correct!\nTotal Guesses: " + count, game.calculateMessage(42, secretNumber, count));
    }

    @Test
    @DisplayName("Test game loop with invalid guess")
    void testMainGameLoopWithInvalidGuess() {
        int secretNumber = 42;
        int count = 1;

        assertEquals("Your guess is invalid", game.calculateMessage(0, secretNumber, count));
        count++;

        assertEquals("Your guess is invalid", game.calculateMessage(101, secretNumber, count));
        count++;

        assertEquals("Correct!\nTotal Guesses: " + count, game.calculateMessage(42, secretNumber, count));
    }
}