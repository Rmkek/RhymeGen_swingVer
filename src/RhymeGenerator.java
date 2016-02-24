import java.util.ArrayList;

public class RhymeGenerator {
    String myWords[] = {
            "на", "ну", "но", "ны", "ни", "ма", "му", "мо", "мы", "ми",
            "та", "ту", "то", "ты", "ти", "ка", "ку", "ко", "ки", "ке",
            "ха", "ху", "хо", "хи", "хе", "ба", "бу", "бо", "бы", "би", "ва",
            "ву", "во", "вы", "ви"}; //35 различных слогов

    public ArrayList<Character> inputWords = new ArrayList<Character>(100);

    StringBuffer output = new StringBuffer(100);


    String rhymeWord(String input, boolean flag) {

        boolean firstCharIsAVowel = false;
        if (input.charAt(0) == 'а' || input.charAt(0) == 'у' || input.charAt(0) == 'о' || input.charAt(0) == 'ы' || input.charAt(0) == 'и' || input.charAt(0) == 'э' || input.charAt(0) == 'я' || input.charAt(0) == 'ю' || input.charAt(0) == 'е' || input.charAt(0) == 'ё'
                || input.charAt(0) == 'А' || input.charAt(0) == 'У' || input.charAt(0) == 'О' || input.charAt(0) == 'Ы' || input.charAt(0) == 'И' || input.charAt(0) == 'Э' || input.charAt(0) == 'Я' || input.charAt(0) == 'Ю' || input.charAt(0) == 'Е' || input.charAt(0) == 'Ё') { //Проверяем первую букву слова (Я человек), если находим то у firstCharIsVowel меняем на true
            int myRandom = (int) (Math.random() * 34);//Этот IF проверяет первую букву вводимого слова (к примеру Я)
            output.append(myWords[myRandom]);
            output.append(" ");
            firstCharIsAVowel = true;
        }

        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(1) == ' ' && firstCharIsAVowel) { //Я человек, смещаемся на ч
                i = 2;
            }

            if (input.charAt(i) != ' ') {
                inputWords.add(input.charAt(i));
            } else {
                for (int z = 0; z < inputWords.size() / 2; z++) {
                    int myRandom = (int) (Math.random() * 34);
                    output.append(myWords[myRandom]);
            }
                inputWords.clear();
                output.append(" ");
            }


        }
        int counter = 0;
        for (int i = 0; i < inputWords.size(); i++) {
            counter++;
            if (inputWords.get(i) == 'ё' || inputWords.get(i) == 'у' || inputWords.get(i) == 'е' || inputWords.get(i) == 'ы' ||
                    inputWords.get(i) == 'а' || inputWords.get(i) == 'о' || inputWords.get(i) == 'э' ||
                    inputWords.get(i) == 'я' || inputWords.get(i) == 'и' || inputWords.get(i) == 'ю' || inputWords.get(i) == 'А' || inputWords.get(i) == 'У' || inputWords.get(i) == 'О' || inputWords.get(i) == 'Ы' || inputWords.get(i) == 'И' || inputWords.get(i) == 'Э'
                    || inputWords.get(i) == 'Я' || inputWords.get(i) == 'Ю' || inputWords.get(i) == 'Е' || inputWords.get(i) == 'Ё') {
                for (int z = 0; z < counter / 3; z++) {
                    int myRandom = (int) (Math.random() * 34);
                    output.append(myWords[myRandom]);
                }

            }
        }
        inputWords.clear();
        if (flag) {
            output.append(input.charAt(input.length() - 2));
            output.append(input.charAt(input.length() - 1));
        }
        String returning = output.toString();
        output.delete(0, output.length());
        return returning;
    }
}
