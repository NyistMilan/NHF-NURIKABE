package board.cell;

import board.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicCellTest {
    BasicCell cell = new BasicCell(0,0, new Board(6,8));

    @Test
    void nextState() {
        assertEquals(BasicCell.State.WHITE, cell.getState());
        cell.NextState();
        assertEquals(BasicCell.State.BLACK, cell.getState());
        cell.NextState();
        assertEquals(BasicCell.State.DOT, cell.getState());
        cell.NextState();
        assertEquals(BasicCell.State.WHITE, cell.getState());
    }
}