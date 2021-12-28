package board;

import board.cell.BasicCell;
import board.cell.Cell;
import board.cell.NumberedCell;
import gameframe.MainFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;
    String game;

    @BeforeEach
     void setup(){
        board = new Board(6, 8);
        game = "....-.--5--.-1-2-.---.-45.----.-2-1-.-.--3.---..";
    }

    @Test
    void addCell() {
        Cell cell = new NumberedCell(0, 0, 1);
        assertNull(board.getCell(0,0));

        board.setCell(cell);
        assertEquals(cell, board.getCell(0,0));
    }

    @Test
    void renderCells(){
        board.fillBoard(game); //renderCells is inside this method
        NumberedCell cell = (NumberedCell) board.getCell(2,1); //String "7" cell

        assertEquals("5", cell.getText());
    }

    @Test
    void checkWin() {
        MainFrame frame = new MainFrame("Nurikabe", 500, 500);
        frame.startGame();
        board = frame.solve();
        assertEquals(true, board.checkWin());
    }

    @Test
    void fillBoard(){
        board.fillBoard(game);
        assertEquals(BasicCell.class, board.getCell(0,0).getClass());  //Should be a Basic Cell since the String represents a "."
        assertEquals(NumberedCell.class, board.getCell(2,1).getClass()); //2nd row 3rd column should be a "5"
    }

    @Test
    void saveBoard(){
        assertEquals(true, board.saveBoard(10));
        MainFrame frame = new MainFrame("Nurikabe", 500, 500);
        frame.startGame();
        board = frame.solve();
        assertEquals(false, board.saveBoard(10));
    }
}