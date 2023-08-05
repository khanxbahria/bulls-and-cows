package bullscows;

public class Grade {

    private final int bulls;
    private final int cows;

    Grade(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }

    @Override
    public String toString() {
        if (bulls == 0 && cows == 0) {
            return "Grade: None.";
        } else {
            return "Grade: %d bull(s) and %d cow(s).".formatted(bulls, cows);
        }
    }
}
