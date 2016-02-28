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
        int INPUT_LENGTH = input.length(); //длина слова
        StringBuffer valueSummed = new StringBuffer(); //окончание из words
        StringBuffer charSummed = new StringBuffer(); //окончание из введенного слова
        StringBuffer returnOutput = new StringBuffer("No words found"); //вывод
        int myCase = 0; //нужно для выборки
        Character lastFourthChar = null; //окончания слов
        Character lastThirdChar = null;
        Character lastSecondChar;
        Character lastChar;

        switch (INPUT_LENGTH) { //по длине слова выбираем
            case 1: //если длина слова == 1 (2 буквы)
                return "Слишком маленькое слово!";
            case 2: //если 3 буквы, то
                myCase = 2; //меняем myCase = 2
                lastSecondChar = input.charAt(input.length() - 2); //предпоследняя буква слова
                lastChar = input.charAt(input.length() - 1); //последняя буква слова
                break;
            case 3:
                myCase = 3;
                lastThirdChar = input.charAt(input.length() - 3); //аналогично с case 2 (пред-предпоследняя буква слова)
                lastSecondChar = input.charAt(input.length() - 2);//предпоследняя буква слова
                lastChar = input.charAt(input.length() - 1);//последняя буква слова
                break;
            case 4:
                myCase = 4;
                lastFourthChar = input.charAt(input.length() - 4); //case 3
                lastThirdChar = input.charAt(input.length() - 3);
                lastSecondChar = input.charAt(input.length() - 2);
                lastChar = input.charAt(input.length() - 1);
                break;
            default:
                lastFourthChar = input.charAt(input.length() - 4); //если не попадает под предыдущие проверки
                lastThirdChar = input.charAt(input.length() - 3);
                lastSecondChar = input.charAt(input.length() - 2);
                lastChar = input.charAt(input.length() - 1);
                break;
        }


        switch (myCase) {
            case 1:
                return "Too small";
            case 2:
                charSummed.append(lastSecondChar + lastChar);
                break;
            case 3:
                charSummed.append(lastThirdChar + lastSecondChar + lastChar);
                break;
            case 4:
                charSummed.append(lastFourthChar + lastThirdChar + lastSecondChar + lastChar);
                break;
            default:
                charSummed.append(lastFourthChar + lastThirdChar + lastSecondChar + lastChar);
                break;
        }

        while (resSet.next()) {
            String value = resSet.getString("value");
            int VALUE_LENGTH = value.length();
            Character ValueFourthChar;
            Character ValueThirdChar;
            Character ValueSecondChar;
            Character ValueChar;

            switch (VALUE_LENGTH) {
                case 1:
                    break;
                case 2:
                    ValueSecondChar = value.charAt(value.length() - 2);
                    ValueChar = value.charAt(value.length() - 1);
                    valueSummed.append(ValueSecondChar + ValueChar);
                    break;
                case 3:
                    ValueThirdChar = value.charAt(value.length() - 3);
                    ValueSecondChar = value.charAt(value.length() - 2);
                    ValueChar = value.charAt(value.length() - 1);
                    valueSummed.append(ValueThirdChar + ValueSecondChar + ValueChar);
                    break;
                case 4:
                    ValueFourthChar = value.charAt(value.length() - 4);
                    ValueThirdChar = value.charAt(value.length() - 3);
                    ValueSecondChar = value.charAt(value.length() - 2);
                    ValueChar = value.charAt(value.length() - 1);
                    valueSummed.append(ValueFourthChar + ValueThirdChar + ValueSecondChar + ValueChar);
                    break;
                default:
                    ValueFourthChar = value.charAt(value.length() - 4);
                    ValueThirdChar = value.charAt(value.length() - 3);
                    ValueSecondChar = value.charAt(value.length() - 2);
                    ValueChar = value.charAt(value.length() - 1);
                    valueSummed.append(ValueFourthChar + ValueThirdChar + ValueSecondChar + ValueChar);
                    break;
            }
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
