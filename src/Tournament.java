import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private List<Game> gamesPlayedList=new ArrayList<>();
    private List<Game> currentRoundGames=new ArrayList<>();
    private List<Round> roundsList=new ArrayList<>();
    private List<Player> playersList;
    private Round[] rounds;
    int totalNoRounds;
    private int tournamentNo;
    private Round round;
    private int roundNo;
    int gamesPerRound;
    int gamesPerTournament;
    List<Game[]> roundGames;

    public Tournament(List<Player> playersList, int totalNoRounds) {
        this.playersList = playersList;
        this.totalNoRounds = totalNoRounds;

    }

    public int getTotalNoRounds() {
        return totalNoRounds;
    }

    public void populateRounds(){

        Round round=null;

        for(int i=0;i<getTotalNoRounds();i++) {

            round = new Round(gamesPlayedList, playersList, i + 1);
            currentRoundGames = round.generateRoundGames();
            gamesPlayedList.addAll(currentRoundGames);
            round.toString();
            roundsList.add(round);

        }
    }


    @Override
    public String toString() {
        Round round=null;
        String str="Helsingin Shakki Klubbi Kesä Turnaus: /n";
        for(int i=0;i<getTotalNoRounds();i++) {
            round=roundsList.get(i);
//            System.out.printf("The list of games in Round %S",round.getRoundNo());
            round.toString();

        }


        return str;

    }
}
