import java.util.*;

public class Round {

    private List<Game> soFarPlayedGames=new ArrayList<>();
    private List<Game> roundGamesList;
    private List<Player> playersList;
    private  int gamesPerRound;
    private int roundNo;
    private Player player=null;
    Random randomno = new Random();

    public Round(List<Game> gamesList, List<Player> playersList,int roundNo) {
        soFarPlayedGames=gamesList;
        this.roundGamesList = new ArrayList<>();
        setPlayersList(playersList);
        clearPlayers();
        this.gamesPerRound = playersList.size()/2;
        this.roundNo = roundNo;

    }


     public void setPlayersList(List<Player> playersList) {
            Player player=null;
            int index;

            if((playersList.size()%2)==0)
            playersList= playersList;
            else if((playersList.size()%2)!=0) {
                player= Collections.min(playersList);
                player.setTotalScore(3);
                this.player=player;
                System.out.println("Player deleted is :"+ player.getName());
                playersList.remove(player);
            }

            this.playersList=playersList;

        }
    public Player getByePlayer(){
        return player;
    }
    public List<Game> getRoundGamesList() {
        return roundGamesList;
    }


    public int getGamesPerRound() {
        return gamesPerRound;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void clearPlayers(){

        for(Player pl:playersList)
            pl.setAlreadyPlayed(false);
    }
    // In orde to set pairing mechanism so that populate method recognizes whether a  player is yet paired or not
    public void setPlayStaus(Game game){

        game.setAlreadyPlayed(true);
        game.getPlayer1().setAlreadyPlayed(true);
        game.getPlayer2().setAlreadyPlayed(true);
        //game.getPlayer1().setOpponentsList(game.getPlayer2());
    }
    // If populate method does not generate required no pf round games, this method enables the
    // generateRoundGames to undo setPlayStatus and make populate method try again by clearing players play status
    public void undoPlayStaus(Game game){

        game.setAlreadyPlayed(false);
        game.getPlayer1().setAlreadyPlayed(false);
        game.getPlayer2().setAlreadyPlayed(false);
        //game.getPlayer1().setOpponentsList(game.getPlayer2());
    }



// it sets all the round players result, game number and etc
    public void setGamesStatus(){

        int k=1;

        for(Game game:roundGamesList)
        {
            game.setOpponentsList();
            game.setPlayersResult();
            game.setGameNo((getRoundNo()*10)+k++);
        }
        if(getByePlayer()!=null)
            playersList.add(getByePlayer());
    }

    public List<Game> generateRoundGames(){


        boolean fullyPaired=false;
        int k=1,j=0,a=0,b=0,c=0,l=0;
        int noPlayers=playersList.size();

        Collections.sort(playersList,Collections.reverseOrder());
        populateRoundGames();

                // checks if populateRoundGames method generated games equal number of round games.
                // If not tries again by trying a maximaum of 3 pairng techniques by manipulating the players list.
                if ((roundGamesList.size() < getGamesPerRound())) {

                    // by shifting a player by one step and calling popukate method again
                    while(a<noPlayers-1)
                    {

                        System.out.println("(a)Round games short of number of roundGames");
                        Collections.swap(playersList,a,a+1);
                        a++;
                        for(Game g:roundGamesList)
                        undoPlayStaus(g);
                        roundGamesList.clear();
                        populateRoundGames();
                        if(roundGamesList.size()==getGamesPerRound()) {
                            fullyPaired=true;
                            break;
                        }
                    }
                    // by shifting a player by two steps and calling popukate method again
                    while((b<noPlayers-2)&&!fullyPaired)
                    {

                        System.out.println("(b)Round games short of number of roundGames");
                        Collections.swap(playersList,b,b+2);
                        b++;
                        for(Game g:roundGamesList)
                            undoPlayStaus(g);
                        roundGamesList.clear();
                        populateRoundGames();
                        if(roundGamesList.size()==getGamesPerRound()) {
                            fullyPaired=true;
                            break;
                        }
                    }
                    // last resort by shuffling  all the playersList and calling popukate method again
                    while((c<noPlayers-2)&&!fullyPaired)
                    {

                        System.out.println("(c)Round games short of number of roundGames");
                        Collections.swap(playersList,c,c+2);
                        c++;
                        for(Game g:roundGamesList)
                            undoPlayStaus(g);
                        roundGamesList.clear();
                        populateRoundGames();
                        if(roundGamesList.size()==getGamesPerRound()) {
                            fullyPaired=true;
                            break;
                        }
                    }
                    //if still not enough number of round games, it displays all the games generated by populate method
                    setGamesStatus();
                }
                // if populate method  successfully generated required number of games per round then it calss
                // setGameStatus method to set playes result and  games
               else  if (roundGamesList.size() == getGamesPerRound())
                {
                    setGamesStatus();
                }



        return  roundGamesList;
    }

    // Pairing Method
    public void populateRoundGames(){

        Player pl1,pl2;
        Game game;
        int k=1,res;
        boolean repeatGame=false;



        for (int i = 0; i <= playersList.size()-2; i++) {
            if(roundGamesList.size()>=getGamesPerRound())
                break;
            for (int j = i+1; j <= playersList.size()-1; j++) {

                    repeatGame=false;
                    pl1=playersList.get(i);
                    pl2=playersList.get(j);


                        Comparator<Player> comp=new Comparator<Player>() {
                        @Override
                        public int compare(Player o1, Player o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                        };

                        for(Player p:pl1.getOpponentsList())
                        {

                            if((p.getName().equals(pl2.getName())))
                            {
                                repeatGame = true;
                                break;
                            }
                        }

                    if (!(pl1.isAlreadyPlayed()) &&
                                        !(pl2.isAlreadyPlayed())&&!repeatGame)
                        {
                            res=randomno.nextInt(3);
                            game=new Game(pl1,pl2,k++,res);

                            roundGamesList.add(game);

                            //soFarPlayedGames.add(game);
                            setPlayStaus(game);

                        }

                     }
        }
    }

    public List<Game> getGamesList() {
        return soFarPlayedGames;
    }

    public String toString() {
        String str="";
        str+="\n The list of games in Round "+getRoundNo()+ " \n ";

        for(Game g:roundGamesList){
            str+="\n"+g.toString()+"\n";
            str+="*****************************************";
        }
        return str;

    }
}


