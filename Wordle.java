import java.util.Scanner;

public class Wordle {
    private String secretWord;
    private String[] guesses;
    private int numGuesses;

    public Wordle(String secretWord) {
        this.secretWord = secretWord;
        this.guesses = new String[6];
        this.numGuesses = 0;
    }

    public boolean makeGuess(String guess) {
        if (numGuesses >= 6) {
            return false;
        }
        guesses[numGuesses] = guess;
        numGuesses++;
        return true;
    }

    public String getFeedback(int guessNum) {
        String guess = guesses[guessNum];
        StringBuilder feedback = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char c = guess.charAt(i);
            if (c == secretWord.charAt(i)) {
                feedback.append("\u001b[32m" + c + "\u001b[0m ");
            } else if (secretWord.indexOf(c) != -1) {
                feedback.append("\u001b[33m" + c + "\u001b[0m ");
            } else {
                feedback.append("\u001b[37m" + c + "\u001b[0m ");
            }
        }
        return feedback.toString();
    }


    public boolean isWon() {
        return numGuesses > 0 && getFeedback(numGuesses - 1).equals("[green][green][green][green][green]");
    }

    public boolean isLost() {
        return numGuesses >= 6 && !isWon();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Wordle game = new Wordle("WORLD");

        while (!game.isWon() && !game.isLost()) {
            System.out.println("Enter your guess: ");
            String guess = scanner.nextLine().toUpperCase();
            game.makeGuess(guess);
            System.out.println(game.getFeedback(game.numGuesses - 1));
        }

        if (game.isWon()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }
}
