package board.cell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell cell = new NumberedCell(10, 11, 8);

    @Test
    void getXCoord() {
        assertEquals(10, cell.getXCoord());
    }

    @Test
    void getYCoord() {
        assertEquals(11, cell.getYCoord());
    }
}