import java.sql.*;

public class conn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException {
        conn = null;

        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:development.sqlite3"); //соединяемся с БД
        System.out.println("База Подключена!");
    }


    // -------- Вывод таблицы--------
    public String ReadDB(String input) throws ClassNotFoundException, SQLException { //функция для чтения БД
        statmt = conn.createStatement(); //создаем действие для скл
        resSet = statmt.executeQuery("SELECT * FROM words"); //выбираем все слова из таблица words
        StringBuilder returnOutput = new StringBuilder("Слов с окончанием в 3 буквы не найдено"); //вывод и заодно слово из 3ех букв
        StringBuilder doubleWords = new StringBuilder("Слов с окончанием в 2 буквы не найдено..."); //вывод слов из двух букв
        int exCounter = 0;

        while (resSet.next()) {
            String value = resSet.getString("value"); //значение, получаемое из БД
            try {
                Boolean tripleOkonch = false;
                if (value.substring(value.length() - 3, value.length()).equals(input.substring(input.length() - 3, input.length()))) { //если нашли окончание из трех
                    if (returnOutput.toString().equals("Слов с окончанием в 3 буквы не найдено")) { //удаляем стандартный текст
                        returnOutput.delete(0, returnOutput.length());
                        returnOutput.append("\n-----Рифма в три буквы----\n");
                    }
                    tripleOkonch = true;
                    returnOutput.append(value); //выводим найденное слово
                    returnOutput.append(", ");
                }
                if ((!tripleOkonch) && value.substring(value.length() - 2, value.length()).equals(input.substring(input.length() - 2, input.length()))) { //если нашли окончание из двух
                    if (returnOutput.toString().equals("Слов с окончанием в 3 буквы не найдено")) { //очистим из 3ех(т.к. используется как аутпут)
                        returnOutput.delete(0, returnOutput.length());
                    }
                    if (doubleWords.toString().equals("Слов с окончанием в 2 буквы не найдено...")) { //очистим из 2ух(т.к. нашли слово)
                        doubleWords.delete(0, doubleWords.length());
                    }
                    doubleWords.append(value); //выводим
                    doubleWords.append(", ");
                }
            } catch (java.lang.StringIndexOutOfBoundsException StringOutOfBounds) {
                System.out.println("Попытка обработать исключение, value.length()-2...");
                exCounter++;
                StringOutOfBounds.printStackTrace();
            }
        }

        System.out.println("ExceptionCounter: " + exCounter);
        if (returnOutput.toString().equals("Слов с окончанием в 3 буквы не найдено")) {
            return returnOutput.toString();
        }
        if (doubleWords.toString().equals("Слов с окончанием в 2 буквы не найдено...")) {
            //inventing a wheel
        } else {
            returnOutput.delete(returnOutput.length() - 2, returnOutput.length());
            returnOutput.append(' ');
            returnOutput.append("\n-----Рифма в две буквы----\n");
            returnOutput.append(doubleWords);
        }
        returnOutput.insert(0, "-----Рифма в три буквы-----\n");
        returnOutput.delete(returnOutput.length() - 2, returnOutput.length());
        returnOutput.append('.');
        return returnOutput.toString();
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

}
