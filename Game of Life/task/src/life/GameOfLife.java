package life;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import static java.lang.Thread.sleep;

public class GameOfLife extends JFrame {
    JPanel panel;
    JLabel generationLabel;
    JLabel aliveLabel;
    JToggleButton playToggleButton;
    JButton pauseButton;
    JButton resetButton;

    int generation;
    volatile boolean run = false;
    GameOfLifeData gameOfLifeData;
    public GameOfLife() {
        this.generation = 0;
        inti();
        gameOfLifeData = new GameOfLifeData(100, new Random());
        showPanel();

        play();
    }
    private void play() {
        System.out.println("start Run");
        while (true) {
            //System.out.println("Running");
            while (run) {
                showPanel();
                gameOfLifeData.next();
                generation++;
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private void inti() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        playToggleButton = new JToggleButton("play");
        pauseButton = new JButton("pause");
        resetButton = new JButton("reset");


        generationLabel = new JLabel("Generation #0");
        generationLabel.setName("GenerationLabel");
        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        JPanel left = new JPanel();
        left.add(playToggleButton);
        left.add(pauseButton);
        left.add(resetButton);
        left.add(generationLabel);
        left.add(aliveLabel);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        add(left, BorderLayout.WEST);
        aliveLabel.setText("alive: ");
        generationLabel.setText("Generation #");


        playToggleButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    run = true;
                    System.out.println("PLAY");
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    run = false;
                    System.out.println("PLAY PAUSE");

                }
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run = false;
                playToggleButton.setSelected(false);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOfLifeData = new GameOfLifeData(100, new Random());
                generation = 0;

            }
        });

    }
    public void showPanel(){
        int n = gameOfLifeData.n;

        panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.setVisible(false);
        panel.setBackground(Color.black);
        generationLabel.setText("Generation #" + generation);
        aliveLabel.setText("Alive: " + gameOfLifeData.live);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                JLabel cell = new JLabel();
                if (gameOfLifeData.matrix[i][j]) {
                    cell.setBackground(Color.black);
                } else {
                    cell.setBackground(Color.white);
                }
                cell.setOpaque(true);
                panel.add(cell);
            }
        }




        panel.setLayout(new GridLayout(n, n, 2, 2));
        panel.setVisible(true);

        setVisible(true);
    }
    public static void main(final String[] args) {
        GameOfLife g = new GameOfLife();

    }
}
