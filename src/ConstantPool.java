import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstantPool {

    private List<String> constantPool = new ArrayList<>(); // Konstanter Pool
    private Map<String, Integer> variableRefs = new HashMap<>(); // Referenzen für Variablen
    private Map<String, Integer> methodRefs = new HashMap<>();   // Referenzen für Methoden

    public String addGlobalVariable(String name) {
        String var = "Var " + name;
        if (!variableRefs.containsKey(name)) {
            constantPool.add(var);
            int index = constantPool.size() - 1;
            variableRefs.put(name, index);
            return Integer.toString(index);
        }
        return Integer.toString(variableRefs.get(name)) ;
    }

    public int addMethod(String name, String returnType, int parameterCount) {
        String entry = "Method " + name + " (" + parameterCount + " Parameter): " + returnType;
        if (!methodRefs.containsKey(name)) {
            constantPool.add(entry);
            int index = constantPool.size() - 1;
            methodRefs.put(name, index);
            return index;
        }
        return methodRefs.get(name);
    }

     public int getConstantIndex(String name) {
        return variableRefs.get(name);

     }

}
