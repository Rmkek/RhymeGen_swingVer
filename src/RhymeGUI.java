import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class RhymeGUI {
    RhymeGeneratorMain rhymeGeneratorMain = new RhymeGeneratorMain();
    RhymeGenerator generator = new RhymeGenerator();
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("RhymeGenerator");
    JButton button = new JButton("Рифмовать");
    JTextField field = new JTextField("Введите слово для рифмы...");
    JTextArea area = new JTextArea("Здесь будет выведена рифма", 6, 16);
    JScrollPane scroller = new JScrollPane(area);
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Меню");
    JMenu fileMenu = new JMenu("Файл");
    JMenuItem closeMenu = new JMenuItem("Закрыть");
    JMenuItem clearMenu = new JMenuItem("Очистить");
    JMenuItem saveFileItem = new JMenuItem("Сохранить...");
    File file = new File("/home/rmk/Rhymes.txt");

    public void go() {
        fileMenu.add(saveFileItem);
        fileMenu.add(clearMenu);
        menu.add(fileMenu);
        menu.add(closeMenu);
        menuBar.add(menu);
        closeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        clearMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file.delete();
            }
        });
        saveFileItem.addActionListener(new saveItemListener());
        button.addActionListener(new buttonListener());
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        area.setEditable(false);
        area.setWrapStyleWord(false);
        area.setLineWrap(true);
        area.setSize(100, 150);
        panel.add(scroller, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        field.requestFocus();
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(panel);
        frame.setSize(250, 210);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }


    class buttonListener implements ActionListener {
        String userInput;
        String generatedWord;

        public void actionPerformed(ActionEvent event) {
            try {
                area.setText(generator.RhymeWordDB(field.getText()));
            } catch (java.lang.ClassNotFoundException ClassNotFound) {
                area.setText("ClassNotFoundException");
                ClassNotFound.printStackTrace();
            } catch (java.sql.SQLException sqlex) {
                area.setText("SQLException");
                sqlex.printStackTrace();
            }

            /*if(area.getText().equals("Здесь будет выведена рифма") || area.getText().equals("Введено слишком маленькое слово!")){
                area.setText(null);
            }
            try {
                userInput = field.getText();
                generatedWord = generator.rhymeWord(userInput, true);
                area.append(generatedWord + "\n");
            } catch (java.lang.StringIndexOutOfBoundsException ex){
                    area.setText("Введено слишком маленькое слово!");
                    ex.printStackTrace();
            } /*

            /*try {
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

    class saveItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (file.exists()) {
                    try {
                        RhymeGeneratorMain.update("/home/rmk/Rhymes.txt", area.getText() + "\n");
                    } catch (java.io.FileNotFoundException ex) {
                        System.out.println("FILE NOT FOUND");
                        ex.printStackTrace();
                    }
                } else {
                    file.createNewFile();
                    RhymeGeneratorMain.write("/home/rmk/Rhymes.txt", area.getText() + "\n");
                }
            } catch (java.io.IOException ex) {
                System.out.println("Exception happened while creating file...");
                ex.printStackTrace();
            }
        }

    }
}
