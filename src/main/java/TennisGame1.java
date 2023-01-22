import utils.Score;

public class TennisGame1 implements TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName)) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        StringBuilder score = new StringBuilder();
        if (isDrawScore()) {
            score.append(drawScoreResult());
        } else if (isGameOverDrawScore()) {
            score.append(gameOverScoreResult());
        } else {
            score = new StringBuilder(regularGameScore());
        }
        return String.valueOf(score);
    }

    private boolean isDrawScore() {
        return m_score1 == m_score2;
    }

    private String drawScoreResult() {
        switch (m_score1) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            default:
                return "Deuce";
        }
    }

    private boolean isGameOverDrawScore() {
        return m_score1 >= 4 || m_score2 >= 4;
    }

    private String gameOverScoreResult() {
        int minusResult = m_score1 - m_score2;
        switch (minusResult) {
            case 1:
                return "Advantage player1";
            case -1:
                return "Advantage player2";
            default:
                return gameOverWinner(minusResult);
        }
    }

    private String gameOverWinner(int result) {
        return result >= 2 ? "Win for player1" : "Win for player2";
    }

    private StringBuilder regularGameScore() {
        StringBuilder regularScore = new StringBuilder();
        int tempScore;
        for (int i = 1; i < 3; i++) {
            if (i == 1) tempScore = m_score1;
            else {
                regularScore.append("-");
                tempScore = m_score2;
            }
            regularScore.append(regularGameScoreResult(tempScore));
        }
        return regularScore;
    }

    private String regularGameScoreResult(int tempScore) {
        return Score.values()[tempScore].getScore();
    }
}
