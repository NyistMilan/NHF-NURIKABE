package board;

import board.cell.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Board extends JPanel implements Serializable {
    private Cell[][] board;
    private int rows, cols;
    private String currentGame;
    private boolean isSolved = false;
    private int currentTime;
    private Timer timer;


    public Board(int cols, int rows){
        this.rows = rows;
        this.cols = cols;
        board = new Cell[this.cols][this.rows];
        this.setUI();
    }

    public void setTimer(Timer timer){this.timer = timer;}
    public int getCurrentTime(){return currentTime;}
    public void setSolved(boolean isSolved){
        this.isSolved = isSolved;
    }
    public boolean getSolved(){
        return isSolved;
    }
    public void setCurrentGame(String currentGame){this.currentGame = currentGame;}
    public String getCurrentGame(){
        return currentGame;
    }
    public int getRows(){
        return rows;
    }
    public int getCols(){
        return cols;
    }
    public void setCell(Cell cell){
        board[cell.getXCoord()][cell.getYCoord()] = cell;
    }
    public Cell getCell(int x, int y){
        return board[x][y];
    }

    public void renderCells(){
        for (int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                    this.add(this.getCell(j, i));
            }
        }
    }

    public void fillBoard(String gameWithoutDimensions){
        int indexer = 0;
        for (int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getCols(); j++){
                if(Character.isDigit(gameWithoutDimensions.charAt(j + indexer)))
                    this.setCell(new NumberedCell(j, i, Character.getNumericValue(gameWithoutDimensions.charAt(j + indexer))));
                else {
                    this.setCell(new BasicCell(j, i, this));
                }
            }
            indexer += this.getCols();
        }
        this.renderCells();
    }

    public boolean saveBoard(int currentTime){
        if(this.getSolved()){
            return false;
        }
        try {
            FileOutputStream f = new FileOutputStream("D:/Asztal/Programming/IntelliJ/NHF/Nurikabe/save.txt");
            ObjectOutputStream out = new ObjectOutputStream(f);
            this.currentTime = currentTime;
            out.writeObject(this);
            out.close();
        }
        catch(IOException ex) {System.out.println("Hiba mentés közben.");}
        return true;
    }

    public boolean checkWin(){
        if(this.getSolved())
            return true;

        String[] strings = currentGame.split(",");

        int indexer = 0;
        for (int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getCols(); j++){
                if(!Character.isDigit(strings[2].charAt(j + indexer))) {
                    BasicCell cell = (BasicCell) this.getCell(j,i);
                    if(cell.getState() == BasicCell.State.WHITE)
                        return false;
                    if(cell.getState() == BasicCell.State.BLACK && strings[2].charAt(j + indexer) != '-')
                        return false;
                    if(cell.getState() == BasicCell.State.DOT && strings[2].charAt(j + indexer) != '.')
                        return false;
                }
            }
            indexer += this.getCols();
        }
        this.setSolved(true);
        timer.stop();
        JOptionPane.showOptionDialog(this, "YOU WON!", "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        return true;
    }

    private void setUI(){
        this.setLayout(new GridLayout(getRows(), getCols(), 0, 0));
        this.setPreferredSize(new Dimension(400, 400));
        this.setAlignmentY(CENTER_ALIGNMENT);
    }
}
