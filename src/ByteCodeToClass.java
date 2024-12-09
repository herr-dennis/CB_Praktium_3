import java.io.IOException;

public class ByteCodeToClass {


    public static void main(String[] args) {

        String outputFileName = "p51";
        //b8 00 17 aufruf von Print
        // Erstelle einen MethodObject für den "main"-Einstiegspunkt
        String mainMethodBytecode = "10 02 36 00 10 00 36 01 10 01 36 02 10 00 36 03 15 00 10 04 a3 00 19 15 01 15 02 60 36 03 15 02 36 01 15 03 36 02 15 00 10 01 60 36 00 a7 ff e1 b1";
        MethodObject mainMethod = new MethodObject("main", 0, mainMethodBytecode);


        // Parameter: (Output-Dateiname, Generate Class File?, Generate Hex File?, Generate Readable Hex with Comments?)
        JavaClassFileGenerator generator = new JavaClassFileGenerator(outputFileName, true, true, true);

        generator.generateClassFile(mainMethod);
        System.out.println("Class file successfully generated: " + outputFileName + ".class");
    }


}
