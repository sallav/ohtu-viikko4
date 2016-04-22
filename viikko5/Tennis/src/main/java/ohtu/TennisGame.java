package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        int tempScore=0;
        if (m_score1==m_score2) return tie();
        else if (m_score1>=4 || m_score2>=4) return over4();
        else    return under4();
    }
    
    public String tie(){
        switch (m_score1){
                case 0: return "Love-All";
                case 1: return "Fifteen-All";
                case 2: return "Thirty-All";
                case 3: return "Forty-All";
                default: return "Deuce";
            }
    }
    
    public String over4(){
            int minusResult = m_score1-m_score2;
            if (minusResult==1) return "Advantage player1";
            else if (minusResult ==-1) return "Advantage player2";
            else if (minusResult>=2) return "Win for player1";
            else return "Win for player2";
    }
    
    public String under4(){
        String score;
        for (int i=1; i<3; i++){
                if (i==1) tempScore = m_score1;
                else { 
                    score+="-"; 
                    tempScore = m_score2;}
                switch(tempScore){
                    case 0:
                        score+="Love";
                        break;
                    case 1:
                        score+="Fifteen";
                        break;
                    case 2:
                        score+="Thirty";
                        break;
                    case 3:
                        score+="Forty";
                        break;
                }
            }
    }
}
