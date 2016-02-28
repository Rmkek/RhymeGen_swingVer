import java.io.*;

public class RhymeGeneratorMain { //создаем класс рифмогенератор
    public static void main(String[] args) { //точка входа приложения
        RhymeGUI gui = new RhymeGUI(); //создаем класс пользовательского интерфейса
        gui.go(); //запускаем интерфейс
    }


    static File file; //создаем файл

    public static void write(String filename, String text) { //функция дли записи в файл
        file = new File(filename);

        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //Записываем текст у файл
                out.print(text);
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void exists(String fileName) throws FileNotFoundException { //функция для проверки на существование файла
        File file = new File(fileName); //пытаемся создать файл, если не удается - выкидываем исключение
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    public static String read(String fileName) throws FileNotFoundException { //функция для чтения файла
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();
        file = new File(fileName);
        exists(fileName);

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    public static void update(String nameFile, String newText) throws FileNotFoundException { //функция для обновления файла
        exists(nameFile); //если существует файл -- записываем в него новый текст
        StringBuilder sb = new StringBuilder();
        String oldFile = read(nameFile);
        sb.append(oldFile);
        sb.append(newText);
        write(nameFile, sb.toString());
    }


}
