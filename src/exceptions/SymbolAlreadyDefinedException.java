package exceptions;

public class SymbolAlreadyDefinedException  extends Exception{
    public SymbolAlreadyDefinedException(){
        super("exceptions.SymbolAlreadyDefinedException");

    }

    public SymbolAlreadyDefinedException(String message){
        super(message);
    }
}
