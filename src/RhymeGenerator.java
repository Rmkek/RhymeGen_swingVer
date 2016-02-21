import java.util.ArrayList;

public class RhymeGenerator {
    String myWords[] = {"на", "ну", "но", "ны", "ни", "ма", "му", "мо", "мы", "ми",
            "та", "ту", "то", "ты", "ти", "ка", "ку", "ко", "ки", "ке",
            "ха", "ху", "хо", "хи", "хе", "ба", "бу", "бо", "бы", "би", "ва",
            "ву", "во", "вы", "ви",}; //35 различных слогов

    public ArrayList<Character> inputWords = new ArrayList<Character>();

    void rhymeWord(String input) { //TODO Переписать с return-ом
        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) != ' ') {
                inputWords.add(input.charAt(i));
            }
            else
            {
                for(int z = 0; z<inputWords.size()/2; z++){
                    int myRandom = (int) (Math.random()*34);
                    System.out.print(myWords[myRandom]);
                }
                inputWords.clear();
                System.out.print(" ");

            }

            if(input.charAt(i) == 'ё' || input.charAt(i) == 'у' || input.charAt(i) == 'е' || input.charAt(i) == 'ы' ||
                    input.charAt(i) == 'а' || input.charAt(i) == 'о' || input.charAt(i) == 'э' ||
                    input.charAt(i) == 'я' || input.charAt(i) == 'и' || input.charAt(i) == 'ю'){
                for(int z = 0; z<inputWords.size()/2; z++){
                    int myRandom = (int) (Math.random()*34);
                    System.out.print(myWords[myRandom]);
                }//закрываем цикл для рандома для последнего слова в листе
                inputWords.clear();
            } //закрываем IF(для последнего слова в листе)
        } //закрываем цикл
        System.out.print(input.charAt(input.length() - 2));
        System.out.print(input.charAt(input.length() - 1) + "\n");

    } //закрываем метод
}
