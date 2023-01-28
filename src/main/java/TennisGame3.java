import utils.Score;

public class TennisGame3 implements TennisGame {

    private int p2;
    private int p1;
    private String p1N;
    private String p2N;

    public TennisGame3(String p1N, String p2N) {
        this.p1N = p1N;
        this.p2N = p2N;
    }

    public String getScore() {
        if (isGameBelowFortyPoints(p1, p2) && noDeuceWinningScore(p1, p2)) {
            return belowFortyPointsCalculationScore(p1, p2);
        }
        return isDeuceResult(p1, p2) ? "Deuce" : overDeuceResult(p1, p2);
    }

    private boolean isGameBelowFortyPoints(int player1Score, int player2Score) {
        return player1Score < 4 && player2Score < 4;
    }

    private boolean noDeuceWinningScore(int player1Score, int player2Score) {
        return player1Score + player2Score != 6;
    }

    private boolean isAdvantagePoint(int player1Score, int player2Score) {
        return Math.abs(player1Score - player2Score) == 1;
    }

    private boolean isDeuceResult(int player1Score, int player2Score) {
        return player1Score == player2Score;
    }

    private String overDeuceResult(int player1Score, int player2Score) {
        String playerWinning = player1Score > player2Score ? p1N : p2N;
        return isAdvantagePoint(player1Score, player2Score) ?
                "Advantage " + playerWinning : "Win for " + playerWinning;
    }

    private String regularGameScoreResult(int tempScore) {
        return Score.values()[tempScore].getScore();
    }

    private String belowFortyPointsCalculationScore(int player1Score, int player2Score) {
        return (player1Score == player2Score) ?
                regularGameScoreResult(p1) + "-All" : regularGameScoreResult(p1) + "-" + regularGameScoreResult(p2);
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName))
            this.p1 += 1;
        else
            this.p2 += 1;
    }
}