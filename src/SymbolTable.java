import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String, SymbolInfo> table = new HashMap<>();
    private int nextAddress = 0;

    public void addConst(String name, int value) {
        if(table.containsKey(name)) {
            try {
                throw new SymbolAlreadyDefinedException();
            } catch (SymbolAlreadyDefinedException e) {
                throw new RuntimeException(e);
            }
        }
        table.put(name, new SymbolInfo("const", value, nextAddress++));
    }

    public void addVar(String name, int address) {
        if(table.containsKey(name)) {
            try {
                throw new SymbolAlreadyDefinedException();
            } catch (SymbolAlreadyDefinedException e) {
                throw new RuntimeException(e);
            }
        }
        table.put(name, new SymbolInfo("var", address, nextAddress++));
    }

    public SymbolInfo getSymbolInfo(String name) {
        return table.get(name);
    }

    public String getTyp(String name) {
        SymbolInfo si = table.get(name);
        return si.type;
    }
    public int getAddress(String name) {
        SymbolInfo si = table.get(name);
        return si.address;
    }
    public  int getValue(String name) {
        SymbolInfo si = table.get(name);
        return si.value;
    }

  public void printTable() {
        for (String key : table.keySet()) {
               System.out.print(key);
               getSymbolInfo(key).print();
        }
  }


}

class SymbolInfo {
    String type;
    int value;
    int address;

    public SymbolInfo(String type, int value , int address) {
        this.type = type;
        this.value = value;
        this.address = address;
    }

    public void print() {
        System.out.println(" "+ type + " "+ value);

    }

}