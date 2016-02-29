import java.sql.*;

public class conn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;
    public String returning = "noSuchWord";

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException {
        conn = null;

        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:development.sqlite3"); //соединяемся с БД

        System.out.println("База Подключена!");
    }


    // -------- Вывод таблицы--------
    public String ReadDB(String input) throws ClassNotFoundException, SQLException { //функция для чтения БД
        //-------- ОСТОРОЖНО, ФУНКЦИЯ НЕ ДОРАБОТАНА! ---------
        statmt = conn.createStatement(); //создаем действие для скл
        resSet = statmt.executeQuery("SELECT * FROM words"); //выбираем все слова из таблица words
        StringBuilder returnOutput = new StringBuilder("No words found"); //вывод
        int exCounter = 0;

        while (resSet.next()) {
            String value = resSet.getString("value");
            try {
                if (value.substring(value.length() - 3, value.length()).equals(input.substring(input.length() - 3, input.length()))) {
                    if (returnOutput.toString().equals("No words found")) {
                        returnOutput.delete(0, returnOutput.length());
                    }
                    returnOutput.append(value);
                    returnOutput.append(", ");
                }
            } catch (java.lang.StringIndexOutOfBoundsException StringOutOfBounds) {
                System.out.println("Попытка обработать исключение, value.length()-2...");
                exCounter++;
                if (value.substring(value.length() - 2, value.length()).equals(input.substring(input.length() - 2, input.length()))) {
                    if (returnOutput.toString().equals("No words found")) {
                        returnOutput.delete(0, returnOutput.length());
                    } else {
                        System.out.println("Неудачно...");
                    }
                    returnOutput.append(value);
                    returnOutput.append(", ");
                }
            }
        }
        System.out.println("ExceptionCounter: " + exCounter);
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
