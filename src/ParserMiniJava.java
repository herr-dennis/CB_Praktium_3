import exceptions.SymbolAlreadyDefinedException;
import exceptions.SymbolDoesNotExist;
import exceptions.SymbolNotDeclaredException;

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
            System.out.println("ACHTUNG ACHTUNG IN HEX!!");
        } catch (ParseException | SymbolAlreadyDefinedException | SymbolNotDeclaredException | SymbolDoesNotExist e) {
            System.out.println("Da hat es geknallt!");
            throw new RuntimeException(e);
        }

    }

    private static String testProgramms(String choice){
        // code_1 const Variable wurde zwei mal zugewiesen
        String code_1 = "final int n = 4; int i = 2, prev = 0, curr = 1, next =0; while i<=n {next = prev + curr; prev = curr; curr = next; i = i +1; print(curr); } ";
        String code_2 = "final int c = 21; int x = 17, y = 23; print(c*x*y*2);";
        String code_3 ="final int c = 10; int x = 3; print((c+x)*(c+x)*(c+x));";

        return switch (choice) {
            case "1" -> code_1;
            case "2" -> code_2;
            case "3" -> code_3;
            default -> code_1;
        };

    }

}
