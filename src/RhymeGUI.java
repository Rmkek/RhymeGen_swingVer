import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class RhymeGUI { //класс для генерации гуи(основной класс в программе)

    RhymeGeneratorMain rhymeGeneratorMain = new RhymeGeneratorMain(); //экземпляр класса Main
    RhymeGenerator generator = new RhymeGenerator();//экземпляр класса рифмогенератора
    JPanel panel = new JPanel(); //создаем панель
    JFrame frame = new JFrame("RhymeGenerator"); //фрейм (окно программы)
    JButton button = new JButton("Рифмовать"); //кнопка
    JTextField field = new JTextField("Введите слово для рифмы..."); //поле ввода
    JTextArea area = new JTextArea("Здесь будет выведена рифма", 6, 16); //поле вывода
    JScrollPane scroller = new JScrollPane(area); //добавляем прокрутку на поле вывода
    JMenuBar menuBar = new JMenuBar(); //меню
    JMenu menu = new JMenu("Меню"); //подменю
    JMenu fileMenu = new JMenu("Файл"); //подменю файл
    JMenuItem closeMenu = new JMenuItem("Закрыть"); //подменю закрыть
    JMenuItem clearMenu = new JMenuItem("Очистить"); //подменю очистить
    JMenuItem saveFileItem = new JMenuItem("Сохранить..."); //подменю сохранить
    File file = new File("/home/rmk/Rhymes.txt"); //место создания файла(линукс)

    public void go() { //создаем гуи
        fileMenu.add(saveFileItem); //добавляем в меню
        fileMenu.add(clearMenu); //компоненты
        menu.add(fileMenu); //отрисовка меню
        menu.add(closeMenu); //отрисовка
        menuBar.add(menu);// меню
        closeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }); //добавляем код для закрытия
        clearMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file.delete();
            }
        }); //добавляем код для удаления
        saveFileItem.addActionListener(new saveItemListener()); //добавляем слушателя для сохранения
        button.addActionListener(new buttonListener()); //слушатель для кнопки
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


    class buttonListener implements ActionListener { //слушатель кнопки
        String userInput;
        String generatedWord;

        public void actionPerformed(ActionEvent event) { //по нажатию кнопки
            try {
                area.setText(generator.RhymeWordDB(field.getText())); //устанавливаем текст на сгенерированный из БД
            } catch (java.lang.ClassNotFoundException ClassNotFound) {//отлавливаем ошибку
                area.setText("ClassNotFoundException"); //выводим пользователю
                ClassNotFound.printStackTrace(); //выводим в консоль
            } catch (java.sql.SQLException sqlex) { //отлавливаем ошибку с БД
                area.setText("SQLException"); //выводим пользователю
                sqlex.printStackTrace(); //выводим в консоль
            }

            //код ниже это сохранненый мною код для дальнейшей доработки(генерация рифмы + чтение вслух)

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

    class saveItemListener implements ActionListener { //сохранение файла
        @Override
        public void actionPerformed(ActionEvent e) { //по нажатию на кнопку сохранения
            try {
                if (file.exists()) { //проверяем существует ли файл
                    try {
                        RhymeGeneratorMain.update("/home/rmk/Rhymes.txt", area.getText() + "\n"); //пишем в него текст
                    } catch (java.io.FileNotFoundException ex) { //отлавливаем исключение
                        System.out.println("FILE NOT FOUND"); //выводим ошибку в консоль
                        ex.printStackTrace();
                    }
                } else { //файл не существует
                    file.createNewFile(); //создаем файл
                    RhymeGeneratorMain.write("/home/rmk/Rhymes.txt", area.getText() + "\n"); //пишем в него текст
                }
            } catch (java.io.IOException ex) { //отлавливаем ошибку
                System.out.println("Exception happened while creating file..."); //ошибка при создании файла
                ex.printStackTrace(); //выводим в консоль
            }
        }

    }
}
