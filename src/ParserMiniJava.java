import java.io.StringReader;
import java.util.Scanner;

public class ParserMiniJava {

    public static void main(String[] args) {

        System.out.println("Wähle ein Programm, das überprüft werden soll..");
        MiniJava miniJava = new MiniJava(new StringReader(""));
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();
        String programm = testProgramms(choice);

        System.out.println("Es wird geprüft : " + programm);
        miniJava.ReInit(new StringReader(programm));



        try {
            miniJava.programm();
            System.out.println("Das Programm ist korrekt!");
        } catch (ParseException e) {
            System.out.println("Da hat es geknallt!");
            throw new RuntimeException(e);
        }


    }


    private static String testProgramms(String choice){

        String intactCode = "final  int nd = 4; int i = 2,prev = 0,curr = 1, next = 0; while i <= n { next = prev + curr; prev = curr; curr = next; i =i+1;}";
        String incorrectCode = "final  int nd = 4, int i = 2,prev = 0,curr = 1, next = 0; while i <= n { next = prev + curr; prev = curr; curr = next; i =i+1;}";

        return switch (choice) {
            case "1" -> intactCode;
            case "2" -> incorrectCode;
            default -> intactCode;
        };

    }

}
