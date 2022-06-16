import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame window= new Window();
                window.setSize(500, 500);
                window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                window.setVisible(true);
                window.setLocationRelativeTo(null);
                window.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/notes.png")));

            }
        });

    }
}
