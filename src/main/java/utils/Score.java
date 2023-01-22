package utils;

public enum Score {
    LOVE("Love"),
    FIFTEEN("Fifteen"),
    THIRTY("Thirty"),
    FORTY("Forty");

    private final String score;
    Score(String score) {
        this.score = score;
    }
    public String getScore() {
        return score;
    }
}
