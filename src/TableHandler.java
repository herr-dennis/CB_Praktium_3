
import exceptions.SymbolAlreadyDefinedException;
import exceptions.SymbolNotDeclaredException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TableHandler {

        private Map<String, SymbolTable> tables = new HashMap<>(); // Lokale Tabellen für Methoden
        private Stack<SymbolTable> stack = new Stack<>();

        public void createTable(String methodName) throws SymbolAlreadyDefinedException {
            if (tables.containsKey(methodName)) {
                throw new SymbolAlreadyDefinedException("Methode " + methodName + " existiert bereits.");
            }
            SymbolTable localTable = new SymbolTable(methodName);
            tables.put(methodName, localTable);
            stack.push(localTable);

        }

    // Gibt die aktuelle Tabelle zurück (ohne sie zu entfernen)
    public SymbolTable currentTable() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }


    boolean localTableExists() {
       if (stack.isEmpty()) {
           return false;
       }
       return true;
    }



    // Entfernt die aktuelle Tabelle vom Stack
    public void removeCurrentTable() {
        if (!stack.isEmpty()) {
            stack.pop();
        } else {
            throw new IllegalStateException("Kein Kontext zum Entfernen vorhanden.");
        }
    }


    public SymbolTable getTable(String methodName) throws SymbolNotDeclaredException {
            if (!tables.containsKey(methodName)) {
                throw new SymbolNotDeclaredException();
            }
            return tables.get(methodName);
        }


}
