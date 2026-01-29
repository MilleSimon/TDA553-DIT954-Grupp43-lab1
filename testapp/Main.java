import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tester");
            AppPanel panel = new AppPanel();
            frame.add(panel);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
