package bullscows;

import java.util.Random;

class SecretGenerator {
    private final int requiredLength;
    private final int numAlphanumericsAllowed;
    private final String[] allowedChars;

    public SecretGenerator(int requiredLength, int numAlphanumericsAllowed) {
        if (requiredLength == 0 || numAlphanumericsAllowed == 0) {
            System.out.print("""
                    Error: required length and number of possible symbols cannot be 0.
                    """);
            System.exit(1);
        } else if (requiredLength > numAlphanumericsAllowed) {
            System.out.printf("""
                    Error: it's not possible to generate a code with a length of %d with %d unique symbols.
                    """
                    .formatted(requiredLength, numAlphanumericsAllowed));
            System.exit(1);
        }
        else if (numAlphanumericsAllowed > 36) {
            System.out.print("""
                    Error: maximum number of possible symbols in the code is 36 (0-9, a-z).
                    """);
            System.exit(1);
        }

        this.requiredLength = requiredLength;
        this.numAlphanumericsAllowed = numAlphanumericsAllowed;
        this.allowedChars = makeAllowedCharsArray();
    }

    private String[] makeAllowedCharsArray() {
        String[] allowedChars = new String[numAlphanumericsAllowed];
        for (int i = 0; i < numAlphanumericsAllowed; i++) {
            if (i < 10) {
                allowedChars[i] = Integer.toString(i);
            } else {
                char alphabet = (char) ('a' + (i - 10));
                allowedChars[i] = Character.toString(alphabet);
            }
        }
        return allowedChars;
    }

    public String generate() {
//        generate random index
        Random random = new Random();
        StringBuilder sb = new StringBuilder(requiredLength);
        while (sb.length() < requiredLength) {
            int randomIndex = random.nextInt(numAlphanumericsAllowed);
            String randomChar = allowedChars[randomIndex];
            if (!sb.toString().contains(randomChar)) {
                sb.append(randomChar);
            }
        }
        return sb.toString();

    }

    public String getRedacted() {
        return "*".repeat(requiredLength);
    }

    public String getRange() {
        if (numAlphanumericsAllowed == 1) {
            return "0";
        } else if (numAlphanumericsAllowed <= 10) {
            int lastDigit = numAlphanumericsAllowed - 1;
            return "(0-" + lastDigit + ")";
        } else if (numAlphanumericsAllowed == 11) {
            return "(0-9, a)";
        } else {
            char lastChar = (char) ('a' + (numAlphanumericsAllowed - 10 - 1));
            return "(0-9, a-" + lastChar + ")";
        }
    }
}
