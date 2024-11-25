import java.util.ArrayList;
import java.util.HashMap;

public class CodeErzeugung
{
    ArrayList<String> Code  = new ArrayList<>();
    ArrayList<String> translatedCode = new ArrayList<>();
    HashMap<String, Integer> opcodeMap;

    public CodeErzeugung() {
        // Initialisiere die Opcode-Mapping-Tabelle
        opcodeMap = new HashMap<>();
        opcodeMap.put("bipush", 0x10);
        opcodeMap.put("sipush", 0x11);
        opcodeMap.put("iload", 0x15);
        opcodeMap.put("istore", 0x36);
        opcodeMap.put("iadd", 0x60);
        opcodeMap.put("isub", 0x64);
        opcodeMap.put("imul", 0x68);
        opcodeMap.put("idiv", 0x6c);
    }
    public void add (String befehl){
          Code.add(befehl);
    }


    public void translateToOpcodes() {

        for (String line : Code) {
            String[] parts = line.trim().split("\\s+");
            String mnemonic = parts[0];
            if (opcodeMap.containsKey(mnemonic)) {
                translatedCode.add(String.format("%02x", opcodeMap.get(mnemonic)));
                for (int i = 1; i < parts.length; i++) {
                    try {
                        int value = Integer.parseInt(parts[i]);
                        translatedCode.add(String.format("%02x", value & 0xFF));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Ungültiges Argument: " + parts[i]);
                    }
                }
            } else {
                throw new IllegalArgumentException("Ungültiger Befehl: " + mnemonic);
            }
        }
        translatedCode.add("b8 00 17");
          translatedCode.add("b1");

    }
    public void printByteCode() {
        for(int i = 0; i < translatedCode.size(); i++) {
            System.out.print(translatedCode.get(i)+" ");
        }
    }
     public void print (){
        for (int i = 0; i < Code.size(); i++){
            System.out.println(Code.get(i));
        }
     }

}
