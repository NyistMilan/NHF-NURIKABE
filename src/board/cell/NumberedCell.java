package board.cell;

import java.io.Serializable;

public class NumberedCell extends Cell implements Serializable {
    private int num;

    public NumberedCell(int x, int y, int num){
        super(x, y);
        this.num = num;
        this.setText(Integer.toString(this.num));
        this.setContentAreaFilled(false);
    }
}
