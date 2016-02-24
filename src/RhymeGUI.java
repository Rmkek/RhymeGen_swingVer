import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RhymeGUI {

    RhymeGenerator generator = new RhymeGenerator();
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("RhymeGenerator");
    JButton button = new JButton("Рифмовать");
    JTextField field = new JTextField("Введите слово для рифмы...");
    JTextArea area = new JTextArea("Здесь будет выведена рифма", 6, 16);
    JScrollPane scroller = new JScrollPane(area);

    public void go() {


        button.addActionListener(new buttonListener());
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        area.setEditable(false);
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setSize(100, 150);
        panel.add(scroller, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        field.requestFocus();
        frame.getContentPane().add(panel);
        frame.setSize(250, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }



    class buttonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String userInput = field.getText();
            String generatedWord = generator.rhymeWord(userInput, true);
            area.setText(generatedWord);

      /*      try {
                MaryInterface marytts = new LocalMaryInterface();
                Set<String> voices = marytts.getAvailableVoices();
                marytts.setVoice(voices.iterator().next());
                AudioInputStream audio = marytts.generateAudio(generatedWord);
                AudioPlayer player = new AudioPlayer(audio);
                player.start();
                player.join();
            } catch(Exception ex){
                ex.printStackTrace();
                area.setText("Failed to set-up marytts!");
            } */
        }
    }

}
