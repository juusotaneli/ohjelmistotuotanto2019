package ohtu;

public class TennisGame {

    private int player1_score = 0;
    private int player2_score = 0;
    private String player1;
    private String player2;

    public TennisGame(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1)
            player1_score += 1;
        else
            player2_score += 1;
    }

    public String getScore() {

        if (player1_score == player2_score) {
            return gameIsTied(player1_score);
        } else if (player1_score >= 4 || player2_score >= 4) {
            return playerHasWonOrHasAdvantage(player1_score, player2_score);
        } else {
            return currentScore(player1_score, player2_score);
        }    
            
    }
    private String gameIsTied(int score) {

        if (score == 0) {
            return "Love-All";
        } else if (score == 1) {
            return "Fifteen-All";
        } else if (score == 2) {
            return "Thirty-All";
        } else if (score == 3) {
            return "Forty-All";
        } else if (score == 4) { 
            return "Deuce";
        }
        return "";
    }
    private String playerHasWonOrHasAdvantage(int player1_score, int player2_score) {

        if (player1_score - player2_score == 1) {
            return "Advantage player1";
        }else if (player1_score - player2_score == -1) {
            return "Advantage player2";
        }else if (player1_score - player2_score >= 2) {
            return "Win for player1";
        }else {
            return "Win for player2";
        }
    }
    private String currentScore(int player1_score, int player2_score) {
        return scoreToStringFormat(player1_score) + "-" + scoreToStringFormat(player2_score);
    }
    private String scoreToStringFormat (int score) {
        if (score == 0) {
            return "Love";
        }else if (score == 1) {
            return "Fifteen";
        }else if (score == 2) {
            return "Thirty";
        }else if (score == 3) {
            return "Forty";
        }
        return "";

    }
}