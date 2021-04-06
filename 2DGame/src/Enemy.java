import javax.swing.*;
import java.awt.*;

public class Enemy extends JComponent implements Runnable {
    public int enemySpeed;
    private boolean collision;

    public Enemy(int enemySpeed) {
        this.enemySpeed = enemySpeed;
        this.collision = false;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, Utils.FIGURE_SIZE, Utils.FIGURE_SIZE);
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();

        while (this.collision == false) {
            System.out.println(t.getName() + " is working");
        }
        System.out.println(t.getName() + " has stopped");
    }

    public void stop() {
        this.collision = true;
    }
}