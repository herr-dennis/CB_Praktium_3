import exceptions.*;

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
        } catch (WrongParametersException e) {
            throw new RuntimeException(e);
        } catch (RwertException e) {
            throw new RuntimeException(e);
        }

    }

    private static String testProgramms(String choice){
        // code_1 const Variable wurde zwei mal zugewiesen
        String code_1 = "final int n = 4; int i = 2, prev = 0, curr = 1, next =0; while i<=n {next = prev + curr; prev = curr; curr = next; i = i +1; print(curr); } ";
        String code_2 = "final int c = 21; int x = 17;  func sub(int a , int b) { int z = 0, n =2; print(z); return z;} print(2);";
        String code_3 ="final int c = 10; int x = 3;  void add (int u) { } {add(2); print((c+x)*(c+x)*(c+x));}";
        String code_4 = "final int c = 21; int x = 17;  func sub(int a , int b , int c) { int z = 0, n =2; print(z); return z;}  {x = sub(3,8,9);  print(2);}";

        return switch (choice) {
            case "1" -> code_1;
            case "2" -> code_2;
            case "3" -> code_3;
            case "4" -> code_4;
            default -> code_1;
        };

    }

}
