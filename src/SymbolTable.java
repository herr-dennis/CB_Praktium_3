import exceptions.SymbolAlreadyDefinedException;
import exceptions.SymbolNotDeclaredException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String, SymbolInfo> table = new HashMap<>();
    private int nextAddress = 1;
    private Map<String, Methode> methodes = new HashMap<>();
    private String tableForMethod= "main";

    public SymbolTable() {}
    public SymbolTable(String tableForMethod) {
        this.tableForMethod = tableForMethod;
    }

    public void addConst(String name, int value) throws SymbolAlreadyDefinedException {
        if (table.containsKey(name)) {
            throw new SymbolAlreadyDefinedException();
        }
        table.put(name, new SymbolInfo("const", value));
    }

    public void addMethod(String name, String type) throws SymbolAlreadyDefinedException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Methodenname darf nicht null oder leer sein.");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Typ darf nicht null oder leer sein.");
        }

        if (methodes.containsKey(name)) {
            throw new SymbolAlreadyDefinedException("Methode " + name + " ist bereits definiert.");
        }
        Methode method = new Methode(name, type);
        methodes.put(name, method);
    }

    public void addParaToMethod(String name, int para) throws SymbolNotDeclaredException {
        Methode methode = methodes.get(name);
        if (methode == null) {
            throw new SymbolNotDeclaredException();
        }

        methode.setParameter(para);
    }

    public ArrayList<Integer> getArrayOfParaMethod(String name) {
        Methode methode = methodes.get(name);
        return methode.getParameters();
    }

    public void addMethodPara(String ide, int para){
        Methode methode = methodes.get(ide);
        methode.setAnzahlPara(para);
    }

    public int getActuallyMethodPara(String method){
        Methode methode = methodes.get(method);
        return methode.getAnzahlPara();
    }

    public int getParameterFromMethodPara(String method){
        Methode methode = methodes.get(method);
        return methode.getAnzahlPara();
    }

    public ArrayList<String> getByteCodeForMethod(String method){
        Methode methode = methodes.get(method);
        return methode.getByteCode();
    }

    public void addCodeToMethod(String methodName, String mnemonic) throws SymbolNotDeclaredException {
        Methode methode = methodes.get(methodName);
        if (methode == null) {
            throw new SymbolNotDeclaredException("Methode " + methodName + " wurde nicht gefunden.");
        }
        methode.addCodeLine(mnemonic);
    }

      public String getCurrentMethodName() {
        return tableForMethod;}

    public void addVar(String name, int address) throws SymbolAlreadyDefinedException {
        if (table.containsKey(name)) {
            throw new SymbolAlreadyDefinedException();
        }
        table.put(name, new SymbolInfo("varGlobal", address, nextAddress++));
    }

    public void addLocalVar(String name, int address) throws SymbolAlreadyDefinedException {
        if (table.containsKey(name)) {
            throw new SymbolAlreadyDefinedException();
        }
        table.put(name, new SymbolInfo("varLocal", address, nextAddress++));
    }

    public void printMnemonicCode(String methode){
        Methode methodeMethod = methodes.get(methode);
        methodeMethod.printMnemonicCode();}

    public Methode getMethod(String methodName) throws SymbolNotDeclaredException {
        return methodes.get(methodName);}

    public void setAddress(int address) {
            nextAddress = address;}

    public SymbolInfo getSymbolInfo(String name) {
        return table.get(name);}

    public String getTyp(String name) {
        SymbolInfo si = table.get(name);
        return si.type;}

    public int getAddress(String name) {
        SymbolInfo si = table.get(name);
        return si.address;}

    public int getValue(String name) {
        SymbolInfo si = table.get(name);
        return si.value;}

    public boolean contains(String name) {
        return table.containsKey(name);}

    public boolean containsMethod(String name){
        Methode methode = methodes.get(name);
        if(methode == null){
            return false;
        }
        return true;
    }

    public int getMethodBytes(String methodName) {
        Methode methode = methodes.get(methodName);
        return methode.getBytesMethod();
    }

    public void printTable() {
        for (String key : table.keySet()) {
            System.out.print(key);
            getSymbolInfo(key).print();
        }
    }


    public int getBytesAllMethods() {
        int bytesAllMethods = 0;

        // Über alle Methoden in der HashMap iterieren
        for (Methode methode : methodes.values()) {
            bytesAllMethods += methode.getBytesMethod(); // `getBytes()` sollte die Byte-Anzahl der Methode liefern
        }
        return bytesAllMethods;
    }

}




class SymbolInfo {
    String type;
    int value = 0;
    int address;

    public SymbolInfo(String type, int value, int address) {
        this.type = type;
        this.value = value;
        this.address = address;
    }

    public SymbolInfo(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public void print() {
        System.out.println(" " + type + " " + value);
    }
}