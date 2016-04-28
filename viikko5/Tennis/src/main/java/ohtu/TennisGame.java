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
        if (playerName == player1Name)
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1==m_score2) return tie();
        else if (m_score1>=4 || m_score2>=4) return over4();
        else    return under4();
    }
    
    public String tie(){
        if(m_score1<4)  return result(m_score1) + "-All";
        else return "Deuce";
    }
    
    public String over4(){
            int difference = m_score1-m_score2;
            if (difference==1) return "Advantage player1";
            else if (difference ==-1) return "Advantage player2";
            else if (difference>=2) return "Win for player1";
            else return "Win for player2";
    }
    
    public String under4(){
        return result(m_score1) + "-" + result(m_score2);
    }
    
    public String result(int score){
        switch(score){
            case 0: return "Love";
            case 1: return "Fifteen";
            case 2: return "Thirty";
            default: return "Forty";
                }
    }
}
