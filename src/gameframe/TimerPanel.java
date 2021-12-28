package gameframe;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class TimerPanel extends JPanel implements Serializable {
    private int currentTime;
    private JLabel timeLabel;
    private Timer timer;

    public TimerPanel(int currentTime) {
        this.currentTime = currentTime;
        initLabel();
        timer = new Timer(1000, e -> {tick();});
        timer.setInitialDelay(0);
        timer.start();
    }

    public Timer getTimer(){
        return timer;
    }
    public int getCurrentTime(){
        return this.currentTime;
    }
    public void stopTimer(){
        this.timer.stop();
    }

    public void tick(){
        timeLabel.setText(convertToTimeFormat(currentTime++));
        this.add(timeLabel);
    }

    // Convert to a string with a format of: MM:SS
    public String convertToTimeFormat(int currentTime){
        String time;
        int minutes = currentTime / 60;
        int seconds = currentTime % 60;

        time = (minutes < 10) ? "0" + minutes + ":" : minutes + ":";
        time += (seconds < 10) ? "0" + seconds : String.valueOf(seconds);
        return time;
    }

    private void initLabel(){
        timeLabel = new JLabel(convertToTimeFormat(this.currentTime));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        this.add(timeLabel);
    }
}
