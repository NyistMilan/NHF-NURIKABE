package board.cell;

import board.Board;
import java.awt.*;
import java.io.Serializable;

public class BasicCell extends Cell implements Serializable {
    public enum State {
        WHITE,
        BLACK,
        DOT
    }
    private static String UNICODE = "\u25CF";
    private State state = State.WHITE;
    private Board board;

    public BasicCell(int x, int y, Board board) {
        super(x, y);
        this.board = board;
        this.addActionListener(new BasicCellActionListener());
    }

    public Board getBoard(){
        return board;
    }
    public State getState(){
        return state;
    }
    public void setState(State state){
        this.state = state;
    }

    public void NextState(){
        switch (state){
            case WHITE:
                state = State.BLACK;
                this.setBackground(Color.BLACK);
                break;
            case BLACK:
                state = State.DOT;
                this.setBackground(Color.WHITE);
                this.setText(UNICODE);
                break;
            case DOT:
                state = State.WHITE;
                this.setText("");
                break;
        }
    }
}
