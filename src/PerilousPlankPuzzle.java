import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.layout.Border;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PipedWriter;

/**
 * Created by Thoma on 21/05/2017.
 */
public class PerilousPlankPuzzle implements ActionListener {

    //Import each image to allow me to add them to the buttons

    ImageIcon water = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//water1.jpg");
    ImageIcon bank2 = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//bank2.jpg");
    ImageIcon bank1 = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//bank1.jpg");
    ImageIcon stump1_man = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//stump1_man.jpg");
    ImageIcon stump2_man = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//stump2_man.jpg");
    ImageIcon stump3_man = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//stump3_man.jpg");
    ImageIcon stump1 = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//stump1.jpg");
    ImageIcon stump2 = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//stump2.jpg");
    ImageIcon stump3 = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//stump3.jpg");
    ImageIcon plankEast = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//plank1.jpg");
    ImageIcon plankNorth = new ImageIcon("C://Users//mccabet1//IdeaProjects// Perilous Plank Puzzle//src//plank2.jpg");

    //Boolean variables which all me to detect which stub I am at.
    private boolean makingMove = false;
    private boolean hasBigPlank = false;
    private boolean hasSmallPlank = false;
    private boolean plankPlacedAtMiddle = false;
    private boolean plankPlacedAtTop = false;
    private boolean bigPlankAtStart = true;
    private boolean smallPlankAtStart = true;
    private boolean at86 = true;
    private boolean at59 = false;
    private boolean at44 = false;
    private boolean at48 = false;
    private boolean at33 = false;
    private boolean at06 = false;

    //creating a 2d array of buttons
    private JButton[][] buttons = new JButton[9][13];


    public void init() {

        //Creating my frame and panel in which my game is inside.
        JFrame mainFrame = new JFrame("Perilous Plank Game");
        JPanel gamePanel = new JPanel(new GridLayout(13,9));
        JPanel UIPanel = new JPanel(new BorderLayout());
        mainFrame.setSize(310,460);

        //Creates all buttons and adds action listener to them.
        for(int x =0; x<9; x++){
            for(int i = 0; i<13; i++){
                buttons[x][i] = new JButton(water);
                buttons[x][i].addActionListener(this);
                gamePanel.add(buttons[x][i]);
            }
        }

        //Sets bank image to the first row
        for(int x =0; x<1; x++) {
            for (int i = 0; i < 9; i++) {
                buttons[x][i].setIcon(bank2);
            }
        }

        //Sets bank image to the last row
        for(int x =8; x<9; x++) {
            for (int i = 4; i < 13; i++) {
                buttons[x][i].setIcon(bank1);
            }
        }

        //Removes borders from the buttons for improved visuals
        for(int x =0; x<9; x++){
            for(int i = 0; i<13; i++){
                buttons[x][i].setBorderPainted(false);
                buttons[x][i].setFocusPainted(false);
            }
        }
        LevelOne();
        UIPanel.add(gamePanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(UIPanel);
        mainFrame.setVisible(true);
    }

    @Override
    //Controls all the movement and button clicks based on users position and planks.
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttons[8][6]){
            makingMove = true;
        }

        if(e.getSource()==buttons[5][9] && makingMove && bigPlankAtStart){
            buttons[5][9].setIcon(stump1_man);
            buttons[8][6].setIcon(stump2);
            buttons[4][4].setIcon(stump1);
            buttons[4][8].setIcon(stump1);
            at86 = false;
            at59 = true;
            makingMove = false;
        }
        if(e.getSource()==buttons[4][4] && makingMove){
            buttons[4][4].setIcon(stump1_man);
            buttons[8][6].setIcon(stump2);
            buttons[5][9].setIcon(stump1);
            buttons[4][8].setIcon(stump1);
            at44 = true;
            at59 = false;
            at86 = false;
            at06 = false;
            at33 = false;
            at48 = false;
            makingMove = false;
        }

        if(e.getSource()==buttons[6][5] && !hasBigPlank && !at86 && at59){
            buttons[7][1].setIcon(water);
            buttons[6][5].setIcon(water);
            buttons[7][10].setIcon(water);
            hasBigPlank = true;
            bigPlankAtStart = false;
        }

        if(e.getSource()==buttons[5][0] && hasSmallPlank && at59){
            buttons[5][0].setIcon(plankNorth);
            hasSmallPlank = false;
        }

        if(e.getSource()==buttons[5][0] && !hasSmallPlank && at44){
            buttons[5][0].setIcon(water);
            hasSmallPlank = true;
            smallPlankAtStart = false;
        }

        if(e.getSource()==buttons[5][9]){
            makingMove = true;
        }
        if(e.getSource()==buttons[4][4]){
            makingMove = true;
        }

        if(e.getSource()==buttons[4][5] && hasBigPlank && at44){
            buttons[4][5].setIcon(plankEast);
            buttons[4][6].setIcon(plankEast);
            buttons[4][7].setIcon(plankEast);
            buttons[4][8].setIcon(stump1);
            plankPlacedAtMiddle = true;
            hasBigPlank = false;
        }

        if(e.getSource()==buttons[4][8] && makingMove && plankPlacedAtMiddle){
            buttons[4][4].setIcon(stump1);
            buttons[5][9].setIcon(stump1);
            buttons[3][3].setIcon(stump1);
            buttons[4][8].setIcon(stump1_man);
            at59 = false;
            at44 = false;
            at86 = false;
            at06 = false;
            at33 = false;
            at48 = true;
        }

        if(e.getSource()==buttons[3][12]&& hasSmallPlank && at48){
            buttons[3][12].setIcon(plankNorth);
            System.out.println("HAS PLANK");
            hasSmallPlank = false;
            plankPlacedAtTop = true;
        }

        if(e.getSource()==buttons[4][7] && !hasBigPlank && at48){
            buttons[4][5].setIcon(water);
            buttons[4][6].setIcon(water);
            buttons[4][7].setIcon(water);
            hasBigPlank = true;
        }

        if(e.getSource()==buttons[4][8]){
            makingMove = true;
        }

        if(e.getSource()==buttons[3][3] && makingMove && plankPlacedAtTop){
            buttons[4][8].setIcon(stump1);
            buttons[3][3].setIcon(stump1_man);
            buttons[4][4].setIcon(stump1);
            buttons[5][9].setIcon(stump1);
            at48 = false;
        }

        if(e.getSource()==buttons[2][7] && hasBigPlank){
            buttons[2][7].setIcon(plankNorth);
            buttons[1][11].setIcon(plankNorth);
            buttons[1][2].setIcon(plankNorth);
        }

        if(e.getSource()==buttons[0][6] && makingMove && plankPlacedAtTop){
            System.out.println("WINNER");
            buttons[3][3].setIcon(stump1);
            buttons[0][6].setIcon(stump3_man);
            buttons[4][4].setIcon(stump1);
            buttons[4][8].setIcon(stump1);
            buttons[5][9].setIcon(stump1);
            buttons[3][3].setIcon(stump1);
            at48 = false;
            for(int x =0; x<9; x++){
                for(int i = 0; i<13; i++){
                    buttons[x][i].setEnabled(false);
                }
            }
        }
    }

    //Puts all the images in the correct place for the first level.
    public void LevelOne(){
        buttons[8][6].setIcon(stump2_man);
        buttons[5][9].setIcon(stump1);
        buttons[4][4].setIcon(stump1);
        buttons[4][8].setIcon(stump1);
        buttons[3][3].setIcon(stump1);
        buttons[0][6].setIcon(stump3);
        buttons[7][1].setIcon(plankNorth);
        buttons[6][5].setIcon(plankNorth);
        buttons[7][10].setIcon(plankNorth);
        buttons[5][0].setIcon(plankNorth);
    }
}
