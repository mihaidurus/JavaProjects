import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    JLabel scoreLabel = new JLabel("SCORE: 0");
    JLabel livesLeft = new JLabel("LIVES: 3");
    MainWindow(ArrayList<Enemy> enemies, Character character) {

        super("App3");
        this.setLayout(null);
        setSize(Utils.WIN_SIZE_WIDTH, Utils.WIN_SIZE_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        character.setLocation(140, 340);
        this.add(character);

        int distance = Utils.WIN_SIZE_WIDTH / 3;
        int c = 0;
        for (int i = 0; i < 3; i++) {
            enemies.get(i).setLocation(35 + c * distance, 0);
            c++;
            this.add(enemies.get(i));
        }

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    character.setLocation(character.getX() - 5, character.getY());
                    if (character.getX() >= MainWindow.this.getX() - 15 && character.getX() <= MainWindow.this.getX() - 5)
                        character.setLocation(character.getX() + 10, character.getY());
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    character.setLocation(character.getX() + 5, character.getY());
                    if (character.getX() >= MainWindow.this.getX() + 470 && character.getX() <= MainWindow.this.getX() + 480)
                        character.setLocation(character.getX() - 10, character.getY());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        scoreLabel.setBounds(10, 360, 80, 20);
        this.add(scoreLabel);
        livesLeft.setBounds(10, 390, 80, 20);
        this.add(livesLeft);
        setVisible(true);
    }
    public void endOfGame(){
        JFrame frame = new JFrame();
        JLabel endGame = new JLabel("You've lost", JLabel.CENTER);
        JDialog dialog = new JDialog(frame, "End of Game");
        dialog.setLocation(50, 150);
        dialog.setSize(200, 100);
        endGame.setBounds(150, 50, 30, 20);
        dialog.add(endGame);
        dialog.setVisible(true);
    }
    public void setLivesLeft(String livesLeft){
        this.livesLeft.setText(livesLeft);
    }
    public void setScore(String score) {
        this.scoreLabel.setText(score);
    }
}
