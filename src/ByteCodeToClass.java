import java.io.IOException;

public class ByteCodeToClass {


    public static void main(String[] args) {

        String test ="10 02 36 01 10 00 36 02 10 01 36 03 10 00 36 04 15 01 10 04 a3 00 21 15 02 15 03 60 36 04 15 03 36 02 15 04 36 03 15 01 10 01 60 36 01 15 03 b8 00 17 a7 ff de b1";
        String outputFileName = "p5_test";
        //b8 00 17 aufruf von Print
        // Erstelle einen MethodObject für den "main"-Einstiegspunkt
        String mainMethodBytecode = "10 02 36 01 10 00 36 02 10 01 36 03 10 00 36 04 15 01 10 04 a3 00 1c 15 02 15 03 60 36 04 15 03 36 02 15 04 36 03 15 01 10 01 60 36 01 a7 ff e3 15 03 b8 00 17 b1  ";
        MethodObject mainMethod = new MethodObject("main", 0, test);
        // a 00 19
        // a7 ff e3

        // Parameter: (Output-Dateiname, Generate Class File?, Generate Hex File?, Generate Readable Hex with Comments?)
        JavaClassFileGenerator generator = new JavaClassFileGenerator(outputFileName, true, true, true);

        generator.generateClassFile(mainMethod);
        System.out.println("Class file successfully generated: " + outputFileName + ".class");
    }


}
