package timer;

import gameframe.TimerPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerPanelTest {

    @Test
    void tick() throws InterruptedException {
        TimerPanel panel = new TimerPanel(0);
        assertEquals("00:00", panel.convertToTimeFormat(panel.getCurrentTime()));

        panel = new TimerPanel(0);
        Thread.sleep(1000);
        assertEquals("00:01", panel.convertToTimeFormat(panel.getCurrentTime()));

        panel = new TimerPanel(0);
        Thread.sleep(5000);
        assertEquals("00:05", panel.convertToTimeFormat(panel.getCurrentTime()));
    }

    @Test
    void convertToTimeFormat(){
        TimerPanel panel = new TimerPanel(0);
        assertEquals("00:00", panel.convertToTimeFormat(panel.getCurrentTime()));
        panel = new TimerPanel(100);
        assertEquals("01:40", panel.convertToTimeFormat(panel.getCurrentTime()));
        panel = new TimerPanel(200);
        assertEquals("03:20", panel.convertToTimeFormat(panel.getCurrentTime()));
        panel = new TimerPanel(1000);
        assertEquals("16:40", panel.convertToTimeFormat(panel.getCurrentTime()));
    }
}