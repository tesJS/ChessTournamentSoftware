import java.io.*;
import java.util.*;

public class TournamentTest {


    public static void main(String[] args) {

        List<Player> playersList = new ArrayList<>();
        List<Game> soFarPlayedGames = new ArrayList<>();
        List<Game> currentRoundGames = new ArrayList<>();
        Random randomNo = new Random();
        Round round;
        Random rand = new Random();
        int random;

        int noOfRounds = 14;
        int noOfPlayers = 15;
        try{
            File filename= new File("D:\\Financial\\Tournament.txt");

            PrintWriter mywriter = new PrintWriter(filename);






        Player[] player = new Player[noOfPlayers];

        player[0] = new Player("Carlsen");
        player[1] = new Player("Nakamura");
        player[2] = new Player("MVL");
        player[3] = new Player("Caruana");
        player[4] = new Player("Mamedyarov");
        player[5] = new Player("Anand");
        player[6] = new Player("Wesley So");
        player[7] = new Player("Aronian");
        player[8] = new Player("Karjakin");
        player[9] = new Player("Kasparov");
        player[10] = new Player("Tesfaye");
        player[11] = new Player("Elleni");
        player[12] = new Player("Tinsae");
        player[13] = new Player("Tewelde");
        player[14] = new Player("Ermias");


        for (int i = 0; i <= player.length - 1; i++) {
            playersList.add(player[i]);
            //player[i].setTotalScore(random=rand.nextInt(20));
        }

        for (int i = 0; i <= noOfRounds - 1; i++) {
            System.out.println();
            System.out.println("Players and their scores: /n");

            Collections.sort(playersList, Collections.reverseOrder());
            for (Player pl : playersList) {
                System.out.println(pl.toString());
            }

            round = new Round(soFarPlayedGames, playersList, i + 1);
            currentRoundGames = round.generateRoundGames();
            soFarPlayedGames.addAll(currentRoundGames);
            System.out.println(round.toString());
            System.out.println("=======================================================================================\n");

        }
        System.out.println("Final List of Players and their Scores: ");
        Collections.sort(playersList, Collections.reverseOrder());
        for (Player pl : playersList) {
            System.out.println();
            System.out.print(pl.toString());
            System.out.println();

        }

            mywriter.close();

        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        finally {
            System.out.println("We did it");
        }

    }
}


















