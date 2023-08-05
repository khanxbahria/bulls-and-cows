package bullscows;

import java.util.Scanner;

class Game {
    private final Scanner scanner = new Scanner(System.in);

    private int inputNumber() {
        String input = scanner.nextLine();
        if (!input.matches("\\d+")) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", input);
            System.exit(1);
        }
        return Integer.parseInt(input);
    }


    private String generateSecretCode() {
        System.out.println("Input the length of the secret code: ");
        int requiredLength = inputNumber();
        System.out.println("Input the number of possible symbols in the code: ");
        int numAlphanumericsAllowed = inputNumber();
        SecretGenerator secretGen = new SecretGenerator(requiredLength, numAlphanumericsAllowed);
        System.out.printf("The secret is prepared: %s %s.\n", secretGen.getRedacted(), secretGen.getRange());
        return secretGen.generate();
    }

    public void play() {
        String secretCode;
        secretCode = generateSecretCode();
        System.out.println("Okay, let's start a game! ");
        Grader grader = new Grader(secretCode);
        String guess = null;
        for (int turnCount = 1; !secretCode.equals(guess); turnCount++) {
            System.out.printf("Turn %d:\n", turnCount);
            guess = scanner.next();
            Grade grade = grader.grade(guess);
            System.out.println(grade);
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
