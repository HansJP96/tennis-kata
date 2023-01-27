
public class TennisGame2 implements TennisGame {
    public int P1point = 0;
    public int P2point = 0;

    public StringBuilder P1res = new StringBuilder();
    public StringBuilder P2res = new StringBuilder();
    private String player1Name;
    private String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        StringBuilder score = new StringBuilder();
        if (scoreValidationByPoints(P1point, P2point, true)) {
            score = new StringBuilder(updateScore(P1point, score));
            score.append("-All");
        }
        if (isDeuceScore(P1point, P2point)) {
            score = new StringBuilder("Deuce");
        }
        if (playerScoreNoPointsAgainst(P1point, P2point)) {
            updateScoreForPlayer(P1point, P1res);
            P2res = new StringBuilder("Love");
            P1res = new StringBuilder("Love");
            score = new StringBuilder(P1res).append("-").append(P2res);
        }
        if (isRegularScoreBeforeLimitPoints(P1point, P2point)) {
            updateScoreForPlayers(P1point, P2point);
            score = new StringBuilder(P1res).append("-").append(P2res);
        }
        if (isGameOverDeuceScore(P1point, P2point)) {
            score.append(playerWonAdvantage());
        }
        score = new StringBuilder(playerWonGame(String.valueOf(score)));
        return String.valueOf(score);
    }

    private boolean isDeuceScore(int player1Points, int player2Points) {
        return player1Points == player2Points && player1Points >= 3;
    }

    private boolean scoreValidationByPoints(int player1Score, int player2Score, boolean hasEqualPoints) {
        return hasEqualPoints ?
                player1Score == player2Score && player1Score < 4 : player1Score > player2Score && player1Score < 4;
    }

    private boolean isRegularScoreBeforeLimitPoints(int player1Score, int player2Score) {
        return scoreValidationByPoints(player1Score, player2Score, false) ||
                scoreValidationByPoints(player2Score, player1Score, false);
    }

    private boolean playerScoreNoPointsAgainst(int player1Score, int player2Score) {
        return player1Score > 0 && player2Score == 0;
    }

    private String updateScore(int playerScore, StringBuilder actualScore) {
        String resultObtained = actualScore.toString();
        switch (playerScore) {
            case 0:
                resultObtained = "Love";
                break;
            case 1:
                resultObtained = "Fifteen";
                break;
            case 2:
                resultObtained = "Thirty";
                break;
            case 3:
                resultObtained = "Forty";
                break;
        }
        return resultObtained;
    }

    private void updateScoreForPlayers(int p1points, int p2points) {
        P1res = new StringBuilder(updateScore(p1points, P1res));
        P2res = new StringBuilder(updateScore(p2points, P2res));
    }

    private void updateScoreForPlayer(int points, StringBuilder score) {
        score.append(updateScore(points, score));
    }

    private boolean isGameOverDeuceScore(int player1Points, int player2Points) {
        return player1Points >= 3 && player2Points >= 3;
    }

    private String playerWonAdvantage() {
        String result = "";
        if (P1point > P2point) {
            result = "Advantage player1";
        } else if (P2point > P1point) {
            result = "Advantage player2";
        }
        return result;
    }

    private boolean isPlayerWin(int player1Point, int player2Point) {
        int winDifference = 2;
        return player1Point >= 4 && player2Point >= 0 && (player1Point - player2Point >= winDifference);
    }

    private String playerWonGame(String gameScore) {
        String finalScore = gameScore;
        if (isPlayerWin(P1point, P2point)) {
            finalScore = "Win for player1";
        } else if (isPlayerWin(P2point, P1point)) {
            finalScore = "Win for player2";
        }
        return finalScore;
    }

    public void SetP1Score(int number) {

        for (int i = 0; i < number; i++) {
            P1Score();
        }

    }

    public void SetP2Score(int number) {

        for (int i = 0; i < number; i++) {
            P2Score();
        }

    }

    public void P1Score() {
        P1point++;
    }

    public void P2Score() {
        P2point++;
    }

    public void wonPoint(String player) {
        if (player == "player1")
            P1Score();
        else
            P2Score();
    }
}