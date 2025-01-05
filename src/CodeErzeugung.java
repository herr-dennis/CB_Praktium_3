import java.util.ArrayList;
import java.util.HashMap;

public class CodeErzeugung
{
    ArrayList<String> Code  = new ArrayList<>();
    ArrayList<String> translatedCode = new ArrayList<>();
    HashMap<String, Integer> opcodeMap;
    private int byteCount = 0; // Zählt die generierten Bytes


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
        opcodeMap.put("if_icmpeq", 0x9f); // ==
        opcodeMap.put("if_icmpne", 0xa); // !=
        opcodeMap.put("if_icmplt", 0xa1); // <
        opcodeMap.put("if_icmpge", 0xa2); // >=
        opcodeMap.put("if_icmpgt", 0xa3); // >
        opcodeMap.put("if_icmple", 0xa4); // <=
        opcodeMap.put("goto", 0xa7); // <=
        opcodeMap.put("return", 0xb1);
        opcodeMap.put("print", 0xb8);
        opcodeMap.put("ireturn" , 0xac);
        opcodeMap.put("invokestatic" , 0xb8);
        opcodeMap.put("invokestatic" , 0xb8);
        opcodeMap.put("putstatic" , 0xb3);
        opcodeMap.put("getstatic" , 0xb2);


    }
    public void add (String befehl){
          Code.add(befehl);
          countBytes();
    }

    public void insertAddress(String befehl) {
        for (int i = 0; i < Code.size(); i++) {

            if (Code.get(i).contains("-1")) {
                String temp = Code.get(i);
                temp = temp.replace("-1", befehl);
                Code.set(i, temp);
            }
        }
    }

    public  String dezimalZuHexMitBytes(int zahl, int byteAnzahl) {

        String hex = Integer.toHexString(zahl).toUpperCase();
        int maxHexLength = byteAnzahl * 2;

        if (hex.length() > maxHexLength) {
            hex = hex.substring(hex.length() - maxHexLength);
        } else {
            while (hex.length() < maxHexLength) {
                hex = "0" + hex;
            }
        }
        return hex;
    }

    public void countBytes() {
        byteCount = 0;

        for (String line : Code) {
            String[] parts = line.trim().split("\\s+");
            String mnemonic = parts[0];

            if (opcodeMap.containsKey(mnemonic)) {

                byteCount++;

                switch (mnemonic) {
                    case "bipush": // 1 Byte-Argument
                        byteCount += 1;
                        break;

                    case "sipush": // 2 Byte-Argument
                        byteCount += 2;
                        break;

                    case "if_icmpge":
                    case "if_icmple":
                    case "if_icmpgt":
                    case "if_icmplt":
                        case "goto":
                    case"getstatic":
                      case "putstatic":

                        byteCount += 2;
                        break;

                    default:
                        // Andere Befehle (z. B. iload, istore)
                        for (int i = 1; i < parts.length; i++) {
                            try {
                                Integer.parseInt(parts[i]);
                                // Für jedes Argument fügen wir ein Byte hinzu
                                byteCount++;
                            } catch (NumberFormatException e) {
                                throw new IllegalArgumentException("Ungültiges Argument: " + parts[i]);
                            }
                        }
                        break;
                }
            } else {

                throw new IllegalArgumentException("Ungültiger Befehl: " + mnemonic);
            }
        }
    }


    public void insertMethodCode(ArrayList<String> code){

        for(int i = code.size() - 1; i >= 0; i--){
            Code.addFirst(code.get(i));
        }


    }


    public String toUpperCase(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - 'a' + 'A');
            }
            result.append(c);
        }

        return result.toString();
    }



    public void setJumpOverMethod(String befehl){
        Code.addFirst(befehl);
    }

    public void translateToOpcodes() {

        for (String line : Code) {
            String[] parts = line.trim().split("\\s+");
            String mnemonic = parts[0];
            if (opcodeMap.containsKey(mnemonic)) {

                // Behandlung des Sprungbefehls (z. B. a3 für Sprünge)
                if (mnemonic.equals("if_icmpge") || mnemonic.equals("if_icmple") || mnemonic.equals("if_icmpgt") || mnemonic.equals("if_icmplt") ||mnemonic.equals("goto") ) {
                    // Wenn der Opcode nur ein Argument hat, muss der zweite Byte-Wert hinzugefügt werden
                    if (parts.length == 2) {
                        // Hier fügen wir das fehlende Byte hinzu, um den Sprung korrekt zu machen
                        if(mnemonic.equals("goto")){
                            int value = Integer.parseInt(parts[1]);
                            if(value < 0){
                                String[] parts_ = { parts[0], "255", parts[1] }; // Füge "ff als Platzhalter hinzu
                                parts = parts_; // Teile neu zuweisen
                            }else{
                                String[] parts_ = { parts[0], "00", parts[1] }; // Füge "ff als Platzhalter hinzu
                                parts = parts_; // Teile neu zuweisen
                            }

                        }
                        else{
                            String[] parts_ = { parts[0], "00", parts[1] }; // Füge "00" als Platzhalter hinzu
                            parts = parts_; // Teile neu zuweisen
                        }


                    }
                }

                if(mnemonic.equals("putstatic")||mnemonic.equals("getstatic")){
                    translatedCode.add(String.format("%02x", opcodeMap.get(mnemonic)));
                    translatedCode.add(parts[1]);
                    continue;
                }

                translatedCode.add(String.format("%02x", opcodeMap.get(mnemonic)));
                for (int i = 1; i < parts.length; i++) {
                    try {
                        int value = Integer.parseInt(parts[i]);

                        if (value < 0) {
                            value = (value & 0xFFFF);
                        }
                        translatedCode.add(String.format("%02x", value & 0xFF));

                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Ungültiges Argument: " + parts[i]);
                    }
                }
            } else {
                throw new IllegalArgumentException("Ungültiger Befehl: " + mnemonic);
            }
        }


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

     public int getByteCount() {
        return byteCount;
     }

     public ArrayList<String> getCodeMnemonic() {
        return Code;
     }

}
