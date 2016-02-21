import java.util.ArrayList;

public class RhymeGenerator {
    String myWords[] = {"на", "ну", "но", "ны", "ни", "ма", "му", "мо", "мы", "ми",
            "та", "ту", "то", "ты", "ти", "ка", "ку", "ко", "ки", "ке",
            "ха", "ху", "хо", "хи", "хе", "ба", "бу", "бо", "бы", "би", "ва",
            "ву", "во", "вы", "ви",}; //35 различных слогов

    public ArrayList<Character> inputWords = new ArrayList<Character>();

    String output;
    String rhymeWord(String input, boolean flag) { //TODO Переписать с return-ом
        if (input.charAt(0) == 'а' || input.charAt(0) == 'у' || input.charAt(0) == 'о' || input.charAt(0) == 'ы' || input.charAt(0) == 'и' || input.charAt(0) == 'э' || input.charAt(0) == 'я' || input.charAt(0) == 'ю' || input.charAt(0) == 'е' || input.charAt(0) == 'ё'
                || input.charAt(0) == 'А' || input.charAt(0) == 'У' || input.charAt(0) == 'О' || input.charAt(0) == 'Ы' || input.charAt(0) == 'И' || input.charAt(0) == 'Э' || input.charAt(0) == 'Я' || input.charAt(0) == 'Ю' || input.charAt(0) == 'Е' || input.charAt(0) == 'Ё')
        {
            int myRandom = (int) (Math.random() * 34);//Этот IF проверяет первую букву вводимого слова (к примеру Я)
            output = output + myWords[myRandom]; //записываем в вывод
            output = output + " ";
        }

    for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) != ' ') {
                inputWords.add(input.charAt(i));
            }
            else
            {
                for(int z = 0; z<inputWords.size()/2; z++){
                    int myRandom = (int) (Math.random()*34);
                    output = output + myWords[myRandom];
                }
                inputWords.clear();
                output = output + " ";

            }

            if(input.charAt(i) == 'ё' || input.charAt(i) == 'у' || input.charAt(i) == 'е' || input.charAt(i) == 'ы' ||
                    input.charAt(i) == 'а' || input.charAt(i) == 'о' || input.charAt(i) == 'э' ||
                    input.charAt(i) == 'я' || input.charAt(i) == 'и' || input.charAt(i) == 'ю' || input.charAt(i) == 'А' || input.charAt(i) == 'У' || input.charAt(i) == 'О' || input.charAt(i) == 'Ы' || input.charAt(i) == 'И' || input.charAt(i) == 'Э'
                    || input.charAt(i) == 'Я' || input.charAt(i) == 'Ю' || input.charAt(i) == 'Е' || input.charAt(i) == 'Ё'){
                for(int z = 0; z<inputWords.size()/2; z++){
                    int myRandom = (int) (Math.random()*34);
                    output = output + myWords[myRandom];
                }//закрываем цикл для рандома для последнего слова в листе
                inputWords.clear();
            } //закрываем IF(для последнего слова в листе)
        } //закрываем цикл

        if(flag) {
            output = output + input.charAt(input.length() - 2);
            output = output + input.charAt(input.length() - 1);
        }
        return output;
    } //закрываем метод
}
