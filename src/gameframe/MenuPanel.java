package gameframe;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    JButton startGame;
    JButton loadGame;
    JButton exitGame;

    public MenuPanel(){
        initPanel();
        JLabel nurikabe = new JLabel("NURIKABE");
        nurikabe.setFont(new Font("Arial", Font.BOLD, 60));
        startGame = new JButton("Start Game");
        loadGame = new JButton("Load Game");
        exitGame = new JButton("Exit Game");
        JLabel author = new JLabel("Made By Nyist Milán Konor");
        author.setFont(new Font("Arial", Font.BOLD, 15));

        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        this.setLayout(grid);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(nurikabe, constraints);
        constraints.gridy = 1;
        this.add(author, constraints);
        constraints.gridy = 3;
        this.add(Box.createRigidArea(new Dimension(0,40)), constraints);
        constraints.gridy = 4;
        this.add(startGame, constraints);
        constraints.gridy = 5;
        this.add(Box.createRigidArea(new Dimension(0,20)), constraints);
        constraints.gridy = 6;
        this.add(loadGame, constraints);
        constraints.gridy = 7;
        this.add(Box.createRigidArea(new Dimension(0,20)), constraints);
        constraints.gridy = 8;
        this.add(exitGame, constraints);
    }

    public JButton getStartGame(){
        return startGame;
    }
    public JButton getLoadGame(){
        return loadGame;
    }
    public JButton getExitGame(){
        return exitGame;
    }


    private void initPanel() {
        this.setPreferredSize(new Dimension(500, 500));
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
    }
}

