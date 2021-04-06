import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int speed;
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            speed = rand.nextInt(3) + 1;
            Enemy enemy = new Enemy(speed);
            enemy.setSize(Utils.FIGURE_SIZE, Utils.FIGURE_SIZE);
            enemies.add(enemy);
        }
        Character character = new Character();
        character.setSize(Utils.FIGURE_SIZE, Utils.FIGURE_SIZE);
        MainWindow mainWindow = new MainWindow(enemies, character);
        Controller controller = new Controller(enemies, character, mainWindow);
        Thread controllerThread=new Thread(controller,"Controller");
        controllerThread.start();
    }
}
