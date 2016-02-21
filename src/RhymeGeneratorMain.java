import java.util.Scanner;

public class RhymeGeneratorMain {
    public static void main(String[] args) {
        RhymeGenerator rgen = new RhymeGenerator();
        RhymeGUI gui = new RhymeGUI();
        gui.go();
        System.out.println("Введите строку, для которой нужно создать рифму\n");
        String input = gui.field.toString();
        rgen.rhymeWord(input);


    }
}
