package gameframe;

import javax.swing.*;
import java.awt.*;
import board.Board;
import reader.GameReader;

public class GamePanel extends JPanel {
    private GameReader game;
    private TimerPanel timerPanel;
    private Board board;

    public GamePanel() {
        timerPanel = new TimerPanel(0);
        game = new GameReader();
        board = game.getBoard();
        board.setTimer(timerPanel.getTimer());
        this.initPanel();
        this.add(timerPanel);
        this.add(board);
    }

    public Board getBoard(){
        return board;
    }
    public TimerPanel getTimerPanel(){return timerPanel;}

    public void newBoard(Board board){
        this.remove(this.board);
        this.board = board;
        board.renderCells();
        this.add(board);
    }

    public void newTimerPanel(Board board){
        this.remove(timerPanel);
        this.timerPanel = new TimerPanel(board.getCurrentTime()-1);
        this.add(timerPanel);
    }

    private void initPanel() {
        this.setPreferredSize(new Dimension(500, 500));
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
    }
}
