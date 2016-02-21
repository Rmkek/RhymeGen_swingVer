/**
 * Created by RMK 1 on 21.02.2016.
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RhymeGUI {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("RhymeGenerator");
    JButton button = new JButton("Рифмовать");
    JTextField field = new JTextField("Введите слово для рифмы...");
    JTextArea area = new JTextArea("Здесь будет выведена рифма...");
    JScrollBar bar = new JScrollBar();
    public void go() {
        area.setEditable(false);
        area.add(bar);
        panel.add(area, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.setSize(200,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}

}
