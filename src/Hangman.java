
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hangman {

    private static final Scanner input = new Scanner(System.in);
    private static final int MAX_GUESSES = 7;

    public static void main(String[] args) {
        ArrayList<String> words = getWords();
        Set<Character> previousGuesses = new HashSet<>();
        String randomWord = getRandomWord(words);

        char[] guesses = initGuesses(randomWord);
        int attempts = 0;

        System.out.println("==> Welcome to Joshua and Vyley's Hangman Game! <==");

        // Main game loop.
        while (attempts < MAX_GUESSES &&  ! isFinished(guesses, randomWord)) {
            printMan(attempts);
            printGuesses(guesses);
            System.out.print("\n==> Letters Used: " + previousGuesses);
            System.out.print("\n==> Attempts Left: " + (MAX_GUESSES - attempts));
            System.out.print("\n==> Enter a letter for your guess: ");
            char guess = getGuess();

            if ( ! checkGuess(guess, guesses, randomWord, previousGuesses)) {
                attempts ++;
            }

            previousGuesses.add(guess);
        }

        if (attempts >= MAX_GUESSES) {
            System.out.println("\n\n~ Game Over! ~");
            System.out.println("==> The word was " + randomWord + ".");
        } else {
            System.out.println("==> You win! The word was " + randomWord + ".");
        }
    }

    /**
     * Reads in a file with random words. Should be placed in the root of the
     * project.
     *
     * @return
     */
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
            while (( line = reader.readLine() ) != null) {
                words.add(line);
            }

        } catch (IOException ex) {
            Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, null, ex);
        }

        return words;
    }

    /**
     * Randomly picks a word from the list of words.
     *
     * @param list
     * @return
     */
    public static String getRandomWord(ArrayList<String> list) {
        return list.get((int) ( Math.random() * list.size() ));
    }

    /**
     * Initializes the guesses array with underscores.
     *
     * @param word
     * @return
     */
    public static char[] initGuesses(String word) {
        char[] guesses = new char[word.length()];
        for (int i = 0; i < guesses.length; i ++) {
            guesses[i] = '_';
        }
        return guesses;
    }

    /**
     * Iterates through the guesses array and determines if the guess entered by
     * the user is a part of the string or not. Wherever it is, it replaces the
     * underscore. If any change is made, a flag is set. Also, the guess is
     * added to the set of guesses.
     *
     * @param guess
     * @param guesses
     * @param word
     * @param previousGuesses
     * @return
     */
    public static boolean checkGuess(char guess, char[] guesses, String word, Set<Character> previousGuesses) {
        boolean updated = false;
        if (previousGuesses.contains(guess)) {
            System.out.println("==> You have already guessed " + guess + ".");
            return updated;
        }

        for (int i = 0; i < guesses.length; i ++) {
            if (guess == word.charAt(i)) {
                updated = true;
                guesses[i] = guess;
            }
        }
        return updated;
    }

    /**
     * Determines if the guesses array matches the String word verbatim.
     *
     * @param guesses
     * @param word
     * @return true if all spaces are matched, false otherwise.
     */
    public static boolean isFinished(char[] guesses, String word) {
        boolean finished = true;
        for (int i = 0; i < guesses.length; i ++) {
            if (guesses[i] != word.charAt(i)) {
                finished = false;
                break;
            }
        }
        return finished;
    }

    /**
     * Returns the first character of whatever the user enters into the console.
     *
     * @return
     */
    public static char getGuess() {
        return input.nextLine().charAt(0);
    }

    /**
     * Prints out the dashes in a fancy format (with spaces).
     *
     * @param guesses
     */
    public static void printGuesses(char[] guesses) {
        System.out.print("\nWord: ");
        for (int i = 0; i < guesses.length; i ++) {
            System.out.print(guesses[i] + " ");
        }
        System.out.println("");
    }

    /**
     * Determines which piece of the hangman to draw.
     * @param attempts 
     */
    public static void printMan(int attempts) {
        switch (attempts) {
            case 0:
                drawNoose();
                break;
            case 1:
                drawWrongOne();
                break;
            case 2:
                drawWrongTwo();
                break;
            case 3:
                drawWrongThree();
                break;
            case 4:
                drawWrongFour();
                break;
            case 5:
                drawWrongFive();
                break;
            case 6:
                drawWrongSix();
                break;
        }
    }

    private static void drawNoose() {
        System.out.println("  ===========");
        System.out.println("  |         |");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("_____        ");
    }

    private static void drawWrongOne() {
        System.out.println("  ===========");
        System.out.println("  |         |");
        System.out.println("  |         O");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("_____        ");
    }

    private static void drawWrongTwo() {
        System.out.println("  ===========");
        System.out.println("  |         |");
        System.out.println("  |         O");
        System.out.println("  |         |");
        System.out.println("  |         |");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("  |          ");
        System.out.println("_____        ");
    }

    private static void drawWrongThree() {
        System.out.println("  ===========  ");
        System.out.println("  |         |  ");
        System.out.println("  |         O  ");
        System.out.println("  |         |  ");
        System.out.println("  |         |  ");
        System.out.println("  |          \\");
        System.out.println("  |            ");
        System.out.println("  |            ");
        System.out.println("_____          ");
    }

    private static void drawWrongFour() {
        System.out.println("  ===========   ");
        System.out.println("  |         |   ");
        System.out.println("  |         O   ");
        System.out.println("  |         |   ");
        System.out.println("  |         |   ");
        System.out.println("  |        / \\ ");
        System.out.println("  |             ");
        System.out.println("  |             ");
        System.out.println("_____           ");
    }

    private static void drawWrongFive() {
        System.out.println("  ===========   ");
        System.out.println("  |         |   ");
        System.out.println("  |         O   ");
        System.out.println("  |         |-\\");
        System.out.println("  |         |   ");
        System.out.println("  |        / \\ ");
        System.out.println("  |             ");
        System.out.println("  |             ");
        System.out.println("_____           ");
    }

    private static void drawWrongSix() {
        System.out.println("  ===========   ");
        System.out.println("  |         |   ");
        System.out.println("  |         O   ");
        System.out.println("  |       /-|-\\");
        System.out.println("  |         |   ");
        System.out.println("  |        / \\ ");
        System.out.println("  |             ");
        System.out.println("  |             ");
        System.out.println("_____           ");
    }
}
