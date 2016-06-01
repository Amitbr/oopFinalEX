package oop.ex6;

/**
 * Created by amit on 01/06/2016.
 */
class Var {
    private String name;
    private String type;
    private boolean value;
    private boolean isFinal;

    Var(String varName, String varType, boolean varValue, boolean isAFinal) {
        name = varName;
        type = varType;
        value = varValue;
        isFinal = isAFinal;
    }

    void changeToFinal() throws SyntaxException{
        if(!value){
            throw new SyntaxException();
        } else {
            isFinal = true;
        }
    }

}
