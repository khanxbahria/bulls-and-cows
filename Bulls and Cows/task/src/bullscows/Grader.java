package bullscows;

public class Grader {
    private final String secretCode;

    public Grader(String secretCode) {

        this.secretCode = secretCode;
    }

    public String getSecretCode() {

        return secretCode;
    }

    public Grade grade(String guessCode) {
        char[] guessChar = guessCode.toCharArray();
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < guessChar.length; i++) {
            if (guessChar[i] == secretCode.charAt(i)) {
                bulls++;
            } else if (secretCode.indexOf(guessChar[i]) != -1) {
                cows++;
            }
        }
        return new Grade(bulls, cows);
    }
}
