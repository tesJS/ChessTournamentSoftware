import java.util.ArrayList;
import java.util.List;

public class Player  implements Comparable<Player> {
    private List<Player> opponentsList=new ArrayList<>();;
    private String name;
    private double result;
    private int totalScore=0;
    private  boolean alreadyPlayed=false;

    public Player(String name) {
        this.name = name;
    }
    public void removeOpponentsList(Player e) {


        opponentsList.remove(e);
        /*List<Player> tx=opponentsList;
        for(Player pl:tx)
        {
            if(pl.getName().equals(e.getName()))
                tx.remove(pl);
        }
        opponentsList=tx;*/


    }
    public void setOpponentsList(Player e) {

        opponentsList.add(e);

    }

    public List<Player> getOpponentsList() {
        return opponentsList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlreadyPlayed() {
        return alreadyPlayed;
    }

    public void setAlreadyPlayed(boolean alreadyPlayed) {
        this.alreadyPlayed = alreadyPlayed;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore += totalScore;
    }

    public double getResult() {

        return result;
    }

    public int getTotalScore() {
        return totalScore;
    }


    public String getName() {
        return name;
    }

    public void setResult(double result) {
        this.result = result;
        totalScore += result;

    }
    @Override
    public int compareTo(Player o) {

        /*if (this.getTotalScore() > o.getTotalScore())
            return 1;
        else if (o.getTotalScore() > this.getTotalScore())
            return -1;
        else return 0;*/
        return this.getTotalScore()-o.getTotalScore();

    }
    @Override
    public String toString() {
        String str = (name + "("+ getTotalScore())+")";
        return str;
    }


}
