package board.cell;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.Serializable;

public abstract class Cell extends JButton implements Serializable {
    private int x, y;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;

        this.setUI();
    }

    private void setUI(){
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(Color.BLACK, 1));
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setPreferredSize(new Dimension(30,30));

        this.setFont(new Font("Arial", Font.PLAIN, 35));
    }

    public int getXCoord(){
        return this.x;
    }
    public int getYCoord(){
        return this.y;
    }
}
