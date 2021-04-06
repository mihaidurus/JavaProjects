import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller extends JFrame implements Runnable {
    private ArrayList<Enemy> enemies;
    private Character character;
    private boolean collision;
    private MainWindow mainWindow;
    private int score_count;
    private int livesLeft;

    public Controller(ArrayList<Enemy> enemies, Character character, MainWindow mainWindow) {
        this.enemies = enemies;
        this.character = character;
        this.collision = false;
        this.mainWindow = mainWindow;
        this.score_count = 0;
        this.livesLeft = 3;
    }

    public void resetEnemy() {
        int distance = Utils.WIN_SIZE_WIDTH / enemies.size();
        int[] c = {0};
        enemies.forEach(e -> {
            e.setLocation(35 + c[0] * distance, 0);
            c[0] += 1;
        });
    }

    public int lowestEnemySpeed() {
        int min = 100, index = 0;
        for (int j = 0; j < enemies.size(); j++) {
            if (enemies.get(j).enemySpeed < min) {
                min = enemies.get(j).enemySpeed;
                index = j;
            }
        }
        return index;
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + " is working");
        enemies.forEach(e -> {
            Thread tenemy = new Thread(e, "enemy");
            tenemy.start();
        });
        while (collision == false) {
            enemies.forEach(e -> {
                e.setLocation(e.getX(), e.getY() + e.enemySpeed);
            });
            int ct = 100;
            for (int i = 0; i < 3; i++) {
                if (character.getX() >= 20 + i * ct && character.getX() <= 55 + i * ct && character.getY() <= (enemies.get(i).getY() + 20) && enemies.get(i).getY() <= 400) {
                    collision = true;
                    enemies.forEach(e -> {
                        e.stop();
                    });
                    livesLeft--;
                    mainWindow.setLivesLeft("LIFES: " + livesLeft);
                }
                if(livesLeft>0&&collision==true){
                    try {
                        t.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    resetEnemy();
                    collision=false;
                }
            }
            if (collision == false && enemies.get(lowestEnemySpeed()).getY() >= character.getY() + 180) {
                resetEnemy();
                score_count++;
                String score = "SCORE: " + score_count;
                mainWindow.setScore(score);
                System.out.println("SCORE: " + score_count);
            }
            try {
                t.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mainWindow.endOfGame();
    }
}

