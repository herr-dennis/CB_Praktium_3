package exceptions;

public class SymbolNotDeclaredException extends Exception{

    public SymbolNotDeclaredException(){
        super("SymbolNotDeclaredException");
    }
    public SymbolNotDeclaredException(String message){
        super(message);
    }



}
