import java.util.List;

public class Game implements Comparable<Game> {

    private Player player1;
    private Player player2;
    private int result;
    private int gameNo;
    private  boolean alreadyPlayed=false;
    private List<Player> opponentsList;



    public Game(Player player1, Player player2,int gameNo, int result) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
        this.gameNo=gameNo;
    }
    public void removeOpponentsList() {

        player1.removeOpponentsList(player2);
        player2.removeOpponentsList(player1);
    }
    public void setOpponentsList() {

        player1.setOpponentsList(player2);
        player2.setOpponentsList(player1);
    }


    public boolean isAlreadyPlayed() {
        return alreadyPlayed;
    }

    public void setAlreadyPlayed(boolean alreadyPlayed) {
        this.alreadyPlayed = alreadyPlayed;
    }

    public void setPlayer1(Player player1) {

        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setPlayersResult() {
        if (result == 2) {
            player1.setResult(3);
            player2.setResult(0);

        } else if (result == 1) {
            player1.setResult(1);
            player2.setResult(1);


        } else if (result == 0) {
            player1.setResult(0);
            player2.setResult(3);

        }

    }
    public void undoPlayersResult() {
        if (result == 2) {
            player1.setResult(-3);

        } else if (result == 1) {
            player1.setResult(-1);
            player2.setResult(-1);


        } else if (result == 0) {
            player2.setResult(-3);
        }

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getResult() {
        return result;
    }

    public int getGameNo() {
        return gameNo;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
    }

    /*@Override
    public int compareTo(Game o) {
        int score1= this.player1.getTotalScore()+this.player2.getTotalScore();
        int score2=o.player1.getTotalScore()+o.player2.getTotalScore();

        if(score1>score2)
            return 1;
        else if(score1<score2)
            return -1;
        else
            return 0;
    }*/



    @Override
    public String toString() {
        String status="";

        if(getResult()==2) {
            status = " 1-0 ";

        }
        else if(getResult()==1)
        {
            status=" 1/2-1/2 ";

        }

        else if(getResult()==0)
        {
            status=" 0-1 ";

        }

        String str=("Game# "+ getGameNo()+" "+ getPlayer1().toString()+ status+" "
                +getPlayer2().toString()+" "+this.isAlreadyPlayed());
        return str;

    }


    @Override
    public int compareTo( Game o2) {
        Player pl1=this.getPlayer1(),pl2=this.getPlayer2(),pl3=o2.getPlayer1(),pl4=o2.getPlayer2();
        if ( ( pl1.getName()==pl3.getName()||pl1.getName()==pl4.getName())
                &&(pl2.getName()==pl3.getName()||(pl2.getName()==pl4.getName())))
            return 0;
        else
            return -1;
    }
}
