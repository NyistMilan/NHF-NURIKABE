package reader;

import board.Board;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class GameReader {
    private Board board;
    private Map<Integer, String> puzzles = new HashMap<>();
    private String currentGame;

    public GameReader(){
        setPuzzles();
        startGame(puzzles.get(ThreadLocalRandom.current().nextInt(1, puzzles.size()+1)));
    }

    private void startGame(String game){
        String[] strings = game.split(",");
        int cols = Integer.parseInt(strings[0].replaceAll("\\s", ""));
        int rows = Integer.parseInt(strings[1].replaceAll("\\s",""));
        currentGame = strings[2];

        board = new Board(cols, rows);
        board.setCurrentGame(game);
        board.fillBoard(currentGame);
    }

    public Board getBoard(){return this.board;}

    private void setPuzzles(){
        puzzles.put(1, "6,8,....-.--5--.-1-2-.---.-45.----.-2-1-.-.--3.---..");
        puzzles.put(2, "6,8,----8.-1-3-.3--.-...-.-.-----.-3.-..2-.---.---2.");
        puzzles.put(3, "7,10,.6--2-5.-1-.-..-----...-.-..---.----.-4.-.-.4---2-.-.-2----2-.-..3----");
        puzzles.put(4, "6,8,---.7.-.---.-2-1-.--4--.-...-..-----.-.-.43-2-..");
        puzzles.put(5, "6,8,..--7..4-2-.---.-..3---..-.-..--2----1--.----3.-");
        puzzles.put(6, "7,10,....---5---3.--2-.-.--.-3------.-.-.-2-..-.-.--4-.---1--.-.---.5-....7");
        puzzles.put(7, "8,11,.-.-...4.-2-----3---.-.---.-2-2--4..---.1---2.-.--2.---3-4---.2--...---------..-8.....--");
    }
}
