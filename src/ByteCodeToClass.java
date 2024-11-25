import java.io.IOException;

public class ByteCodeToClass {


    public static void main(String[] args) {

        String outputFileName = "V^3";
        //b8 00 17 aufruf von Print
        // Erstelle einen MethodObject für den "main"-Einstiegspunkt
        String mainMethodBytecode = "10 0a 36 00 10 03 36 01 15 00 15 01 60 15 00 15 01 60 68 15 00 15 01 60 68 b8 00 17 b1";
        MethodObject mainMethod = new MethodObject("main", 0, mainMethodBytecode);


        // Parameter: (Output-Dateiname, Generate Class File?, Generate Hex File?, Generate Readable Hex with Comments?)
        JavaClassFileGenerator generator = new JavaClassFileGenerator(outputFileName, true, true, true);

        generator.generateClassFile(mainMethod);
        System.out.println("Class file successfully generated: " + outputFileName + ".class");
    }


}
