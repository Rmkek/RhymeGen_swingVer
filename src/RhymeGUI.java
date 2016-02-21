/**
 * Created by RMK 1 on 21.02.2016.
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RhymeGUI {
    RhymeGenerator generator = new RhymeGenerator();
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("RhymeGenerator");
    JButton button = new JButton("Рифмовать");
    JTextField field = new JTextField("Введите слово для рифмы...");
    JTextArea area = new JTextArea("Здесь будет выведена рифма...");
    JScrollPane scroller = new JScrollPane(area);
    String input = field.getText();

    public void go() {

        button.addActionListener(new buttonListener());

        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        area.setEditable(false);
        panel.add(scroller);
        panel.add(area, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.setSize(200,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



}
    class buttonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){

            area.setText(generator.rhymeWord(input, true));
        }
    }

}
