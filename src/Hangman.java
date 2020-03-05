
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class Hangman {

    private static final Scanner input = new Scanner(System.in);
    private static final int MAX_GUESSES = 7;

    public static void main(String[] args) {
        ArrayList<String> words = getWords();
        ArrayList<Character> previousGuesses = new ArrayList<>();
        String randomWord = getRandomWord(words);
        char[] guesses = initGuesses(randomWord);
        int attempts = 0;

        while (attempts < MAX_GUESSES && !isFinished(guesses, randomWord)) {
            System.out.println(Arrays.toString(guesses));

            char guess = getGuess();

            if (!checkGuess(guess, guesses, randomWord, previousGuesses)) {
                attempts++;
                System.out.println("Your guess is incorrect. Guesses left: " + (MAX_GUESSES - attempts));
            }
        }

        if (attempts >= MAX_GUESSES) {
            System.out.println("The word was " + randomWord + ".");
        } else {
            System.out.println("You win! The word was " + randomWord + ".");
        }
    }

    public static ArrayList<String> getWords() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("words.txt"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        ArrayList<String> words = new ArrayList<>();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return words;
    }

    public static String getRandomWord(ArrayList<String> list) {
        return list.get((int) (Math.random() * list.size()));
    }

    public static char[] initGuesses(String word) {
        char[] guesses = new char[word.length()];
        for (int i = 0; i < guesses.length; i++) {
            guesses[i] = '_';
        }
        return guesses;
    }

    public static boolean checkGuess(char guess, char[] guesses, String word, ArrayList<Character> previousGuesses) {
        boolean updated = false;

        if (previousGuesses.contains(guess)) {
            System.out.println("You have already guessed " + guess + ".");
            return updated;
        }

        for (int i = 0; i < guesses.length; i++) {
            if (guess == word.charAt(i)) {
                updated = true;
                guesses[i] = guess;
            }
        }

        return updated;
    }

    public static boolean isFinished(char[] guesses, String word) {
        boolean finished = true;
        for (int i = 0; i < guesses.length; i++) {
            if (guesses[i] != word.charAt(i)) {
                finished = false;
                break;
            }
        }
        return finished;
    }

    public static char getGuess() {

        return input.nextLine().charAt(0);
    }
}
