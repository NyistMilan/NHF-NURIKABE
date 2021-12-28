package gameframe;

import board.Board;
import board.cell.BasicCell;
import board.cell.NumberedCell;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainFrame extends JFrame {
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private Board currentBoard;
    private JMenuBar menu;
    private JMenu options;

    public MainFrame(String name, int width, int height){
        this.initFrame(name, width, height);
        menuPanel = new MenuPanel();
        this.addButtonListeners();
        this.add(menuPanel, BorderLayout.CENTER);
        this.pack();
    }

    public Board getBoard(){
        return currentBoard;
    }
    public void setBoard(Board board){currentBoard = board;}

    public void startGame() {
        this.remove(menuPanel);
        gamePanel = new GamePanel();
        currentBoard = gamePanel.getBoard();
        this.add(gamePanel, BorderLayout.CENTER);
        this.pack();
    }

    public void startLoadedGame(){
        this.remove(menuPanel);
        gamePanel = new GamePanel();
        gamePanel.newTimerPanel(currentBoard);
        gamePanel.newBoard(currentBoard);
        this.add(gamePanel, BorderLayout.CENTER);
        this.pack();
    }

    public boolean loadGame(String filename){
        try {
            FileInputStream f = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(f);
            currentBoard = (Board) in.readObject();
            if(currentBoard == null)
                return false;

            startLoadedGame();
            in.close();
        } catch(IOException ex) { return false;} catch(ClassNotFoundException ex) { return false;}
        return true;
    }

    public Board solve(){
        String solvedBoard = this.currentBoard.getCurrentGame();

        String[] strings = solvedBoard.split(",");
        int cols = Integer.parseInt(strings[0].replaceAll("\\s", ""));
        int rows = Integer.parseInt(strings[1].replaceAll("\\s",""));
        currentBoard = new Board(cols, rows);
        currentBoard.setCurrentGame(solvedBoard);

        int indexer = 0;
        for (int i = 0; i < currentBoard.getRows(); i++){
            for(int j = 0; j < currentBoard.getCols(); j++){
                if(Character.isDigit(strings[2].charAt(j + indexer)))
                    currentBoard.setCell(new NumberedCell(j, i, Character.getNumericValue(strings[2].charAt(j + indexer))));
                else if(strings[2].charAt(j + indexer) == '.'){
                    BasicCell cell = (new BasicCell(j, i, currentBoard));
                    cell.setState(BasicCell.State.DOT);
                    cell.setText("\u25CF");
                    currentBoard.setCell(cell);
                }
                else if(strings[2].charAt(j + indexer) == '-'){
                    BasicCell cell = (new BasicCell(j, i, currentBoard));
                    cell.setState(BasicCell.State.BLACK);
                    cell.setBackground(Color.BLACK);
                    currentBoard.setCell(cell);
                }
            }
            indexer += currentBoard.getCols();
        }
        currentBoard.setSolved(true);
        gamePanel.newBoard(currentBoard);
        this.add(gamePanel, BorderLayout.CENTER);
        gamePanel.getTimerPanel().stopTimer();
        this.pack();
        JOptionPane.showOptionDialog(this, "YOU WON!", "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        return currentBoard;
    }

    private void returnMenu(){
            this.remove(gamePanel);
            menuPanel = new MenuPanel();
            this.addButtonListeners();
            this.add(menuPanel, BorderLayout.CENTER);
            this.pack();
    }

    private void addButtonListeners(){
        JButton startGame = menuPanel.getStartGame();
        startGame.addActionListener(e -> {
            addMenuListeners();
            startGame();
        });

        JButton loadGame = menuPanel.getLoadGame();
        loadGame.addActionListener(e -> {
            if(!loadGame("D:/Asztal/Programming/IntelliJ/NHF/Nurikabe/save.txt"))
                return;
            addMenuListeners();
        });
        JButton exitGame = menuPanel.getExitGame();
        exitGame.addActionListener(e -> {System.exit(0);});
    }

    private void addMenuListeners(){
        JMenuItem solveItem = new JMenuItem("solve");
        JMenuItem returnItem = new JMenuItem("return");
        solveItem.addActionListener(a -> {
            solve();
            options.remove(solveItem);
        });
        returnItem.addActionListener(a -> {
            returnMenu();
            this.gamePanel.getTimerPanel().stopTimer();
            options.remove(solveItem);
            options.remove(returnItem);
        });
        options.add(returnItem);
        options.add(solveItem);
    }

    private void initFrame(String name, int wdth, int hght) {
        menu = new JMenuBar();
        options = new JMenu("options");
        menu.add(options);
        this.setJMenuBar(menu);
        this.setTitle(name);
        this.setMinimumSize(new Dimension(wdth, hght));
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if(gamePanel != null) {
                    Board board = gamePanel.getBoard();
                    board.saveBoard(gamePanel.getTimerPanel().getCurrentTime());
                }
            }
        });
    }
}
