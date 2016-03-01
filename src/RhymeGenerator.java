import java.util.ArrayList;
import java.util.Random;

public class RhymeGenerator {

    public static final char[] consonants =
            {
                    //б, в, г, д, ж, з, й, к, л, м, н, п, р, с, т, ф, х, ц, ч, ш, щ
                    'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м', 'н', 'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ'
            };
    public static final char[] vowels =
            {
                    'а', 'у', 'о', 'ы', 'и', 'э', 'я', 'ю', 'ё', 'е'
            };

    private static final Random random = new Random();


    String myWords[] = {
            "на", "ну", "но", "ны", "ни", "ма", "му", "мо", "мы", "ми",
            "та", "ту", "то", "ты", "ти", "ка", "ку", "ко", "ки", "ке",
            "ха", "ху", "хо", "хи", "хе", "ба", "бу", "бо", "бы", "би", "ва",
            "ву", "во", "вы", "ви"}; //35 различных слогов

    public ArrayList<Character> inputWords = new ArrayList<Character>(100); //слова, введенные пользователем
    StringBuilder word = new StringBuilder();
    StringBuffer output = new StringBuffer(100); //вывод

    public String generateWord(String ending) {
        try {

            for (int i = 0; i < ending.length() - 1; i++) {
                if (random.nextBoolean()) {
                    word.append(pullConsonant());
                } else {
                    word.append(pullVowel());
                }
            }
            ending = ending.substring(ending.length() - 3, ending.length());
            StringBuilder output = new StringBuilder(word + ending);
            word.delete(0, word.length() - 1);
            return output.toString();
        } catch (java.lang.StringIndexOutOfBoundsException ex) {
            try {
                ending = ending.substring(ending.length() - 2, ending.length());
                return word + ending;
            } catch (java.lang.StringIndexOutOfBoundsException ex2) {
                try {
                    ending = ending.substring(ending.length() - 1, ending.length());
                    return word + ending;
                } catch (java.lang.StringIndexOutOfBoundsException ex3) {
                    return "Слишком маленькое слово (ошибка создана в генерации побуквенно)";
                }
            }
        }
    }


    public static char pullVowel() {
        return vowels[random.nextInt(vowels.length)];
    }

    public static char pullConsonant() {
        return consonants[random.nextInt(consonants.length)];
    }


    String rhymeWordDany(String input) {
        StringBuilder outputx5 = new StringBuilder();
        outputx5.append("----ПОБУКВЕННО----\n");
        outputx5.append(generateWord(input));
        outputx5.append("\n");
        outputx5.append(generateWord(input));
        outputx5.append("\n");
        outputx5.append(generateWord(input));
        outputx5.append("\n");
        outputx5.append(generateWord(input));
        outputx5.append("\n");
        outputx5.append(generateWord(input));
        outputx5.append("\n");
        return (outputx5.toString());
    }


    String rhymeWord(String input, boolean flag) { //функция для генерации

        boolean firstCharIsAVowel = false; //перваяБукваГлаясная = нет
        if ((input.charAt(0) == 'а' || input.charAt(0) == 'у' || input.charAt(0) == 'о' || input.charAt(0) == 'ы' || input.charAt(0) == 'и' || input.charAt(0) == 'э' || input.charAt(0) == 'я' || input.charAt(0) == 'ю' || input.charAt(0) == 'е' || input.charAt(0) == 'ё'
                || input.charAt(0) == 'А' || input.charAt(0) == 'У' || input.charAt(0) == 'О' || input.charAt(0) == 'Ы'
                || input.charAt(0) == 'И' || input.charAt(0) == 'Э' || input.charAt(0) == 'Я' || input.charAt(0) == 'Ю'
                || input.charAt(0) == 'Е' || input.charAt(0) == 'Ё') && (input.charAt(1) == ' ')) { //Проверяем первую букву слова (Я человек), если находим то у firstCharIsVowel меняем на true
            int myRandom = (int) (Math.random() * 34);//Этот IF проверяет первую букву вводимого слова (к примеру Я)
            output.append(myWords[myRandom]); //выводим 1 слог
            output.append(" "); //пробел
            firstCharIsAVowel = true; //перваяБукваГласная = да
        }

        for (int i = 0; i < input.length(); ++i) { //перебираем весь массив
            if (input.charAt(1) == ' ' && firstCharIsAVowel) { //Я человек, смещаемся на ч
                i = 2;//Я человек, смещаемся на ч
            }

            if (input.charAt(i) != ' ') { //если буква не пробел
                inputWords.add(input.charAt(i)); //записываем в массив
                if (firstCharIsAVowel) { //если перваяБукваГласная
                    i++;
                    firstCharIsAVowel = false;
                }
            } else { //если нашли пробел
                for (int z = 0; z < inputWords.size() / 2; z++) {
                    int myRandom = (int) (Math.random() * 34); //выводим
                    output.append(myWords[myRandom]); //слога
            }
                inputWords.clear(); //очищаем массив
                output.append(" "); //добавляем пробел
            }


        }
        int counter = 0;
        for (int i = 0; i < inputWords.size(); i++) { //генерация рифмы для последнего слова
            counter++;
            if (inputWords.get(i) == 'ё' || inputWords.get(i) == 'у' || inputWords.get(i) == 'е' || inputWords.get(i) == 'ы' ||
                    inputWords.get(i) == 'а' || inputWords.get(i) == 'о' || inputWords.get(i) == 'э' ||
                    inputWords.get(i) == 'я' || inputWords.get(i) == 'и' || inputWords.get(i) == 'ю' || inputWords.get(i) == 'А' || inputWords.get(i) == 'У' || inputWords.get(i) == 'О' || inputWords.get(i) == 'Ы' || inputWords.get(i) == 'И' || inputWords.get(i) == 'Э'
                    || inputWords.get(i) == 'Я' || inputWords.get(i) == 'Ю' || inputWords.get(i) == 'Е' || inputWords.get(i) == 'Ё') { //если находим гласную
                for (int z = 0; z < counter / 3; z++) { //делим счетчик на 3
                    int myRandom = (int) (Math.random() * 34); //выводим
                    output.append(myWords[myRandom]); //записываем в ретурн
                }

            }
        }
        inputWords.clear(); //очищаем массив
        if (flag) { //если флаг = труе
            output.append(input.charAt(input.length() - 2)); //генерируем рифму
            output.append(input.charAt(input.length() - 1)); //генерируем
        }
        //output.insert(0, "----ПО СЛОГАМ----\n");
        String returning = output.toString(); //переводим в строку
        output.delete(0, output.length()); //очищаем вывод
        return returning; //выводим строку
    }


    /* Генерация из базы данных */
    String RhymeWordDB(String input) throws java.lang.ClassNotFoundException, java.sql.SQLException { //генерация рифмы с БД
        conn DBCONNECTION = new conn(); //соединяемся
        try {
            conn.Conn(); //пытаемся соединиться
        } catch (Exception ex) {//выводим ошибку, если есть
            ex.printStackTrace();
        }
        String output = DBCONNECTION.ReadDB(input);
        //DBCONNECTION.CloseDB();
        return output; //возвращаем что прочли из БД
    }
}
