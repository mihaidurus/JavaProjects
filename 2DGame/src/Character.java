import javax.swing.*;
import java.awt.*;

public class Character extends JComponent {
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(0, 0, Utils.FIGURE_SIZE, Utils.FIGURE_SIZE);
    }
}
