import java.util.ArrayList;

// Für die Symboltabelle des Hauptprogramms
public class Methode {

    private String name;
    private String type;
    private CodeErzeugung codeErzeugung;
    private  ArrayList<Integer> parameter;
    private int valueLokalVar;
    private int anzahlPara;
    private int bytesMethod;

    public Methode(String name, String type) {
        this.name = name;
        this.type = type;
        codeErzeugung = new CodeErzeugung();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CodeErzeugung getCodeErzeugung() {
        return codeErzeugung;
    }

    public void addCodeLine(String newCode) {
        codeErzeugung.add(newCode);
    }


    public ArrayList<Integer> getParameters(){
        return parameter;
    }


    public int getBytesMethod(){
                codeErzeugung.countBytes();
                return codeErzeugung.getByteCount();
    }

    public ArrayList<String> getByteCode() {
        return codeErzeugung.getCodeMnemonic();
    }

      public ArrayList<Integer> getParameter() {
        return parameter;
      }
      public void setParameter(int paramter) {
        this.parameter.add(paramter);
      }

    public int getValueLokalVar() {
        return valueLokalVar;
    }

    public void setValueLokalVar(int valueLokalVar) {
        this.valueLokalVar = valueLokalVar;
    }

    public int getAnzahlPara() {
        return anzahlPara;
    }

    public void setAnzahlPara(int anzahlPara) {
        this.anzahlPara = anzahlPara;
    }

    public void printMnemonicCode(){
        codeErzeugung.print();
    }

}
