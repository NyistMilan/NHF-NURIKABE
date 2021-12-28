package board.cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class BasicCellActionListener implements ActionListener, Serializable {
    @Override
    public void actionPerformed(ActionEvent e) {
        BasicCell button = (BasicCell) e.getSource();
        button.NextState();
        button.getBoard().checkWin();
    }
}
