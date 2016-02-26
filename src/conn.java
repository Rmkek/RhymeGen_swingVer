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
        conn = DriverManager.getConnection("jdbc:sqlite:development.sqlite3");

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException {
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public String ReadDB(String input) throws ClassNotFoundException, SQLException {

        statmt = conn.createStatement();
        resSet = statmt.executeQuery("SELECT * FROM words");
        //Character lastThirdChar = input.charAt(input.length() - 3);
        Character lastSecondChar = input.charAt(input.length() - 2);
        Character lastChar = input.charAt(input.length() - 1);
        StringBuffer charSummed = new StringBuffer();
        //charSummed.append(lastThirdChar.toString() + lastSecondChar.toString() + lastChar.toString());
        charSummed.append(lastSecondChar.toString() + lastChar.toString());
        StringBuffer returnOutput = new StringBuffer("No words found");
        while (resSet.next()) {
            String value = resSet.getString("value");
            //Character ValueThirdChar = value.charAt(value.length() - 3);
            Character ValueSecondChar = value.charAt(value.length() - 2);
            Character ValueChar = value.charAt(value.length() - 1);
            StringBuffer valueSummed = new StringBuffer();

            //valueSummed.append(ValueThirdChar.toString() + ValueSecondChar.toString() + ValueChar.toString());
            valueSummed.append(ValueSecondChar.toString() + ValueChar.toString());
            if (charSummed.toString().equals(valueSummed.toString())) {
                if (returnOutput.toString().equals("No words found")) {
                    returnOutput.delete(0, returnOutput.length());
                }
                returnOutput.append(value + ", ");
            } else {
                valueSummed.delete(0, valueSummed.length());
            }
        }
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
