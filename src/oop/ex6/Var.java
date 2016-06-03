package oop.ex6;

/**
 * Created by amit on 01/06/2016.
 */

/**
 * this class of Variable element.
 */
class Var {

    /*
     * the name of the Variable element.
     */
    private String name;

    /*
     * the type of the Variable element.
     */
    private String type;

    /*
     *Variable element have value or not.
     */
    private boolean value;

    /*
     *Variable element is final or not.
     */
    private boolean isFinal;

    /**
     * A constructor that creates Var with name, type, value and final.
     */
    Var(String varName, String varType, boolean varValue, boolean isAFinal) {
        name = varName;
        type = varType;
        value = varValue;
        isFinal = isAFinal;
    }

    /*
     * A function that return the Var name.
     */
    public String getName() {
        return this.name;
    }

    /*
     * A function that return the Var type.
     */
    public String getType() {
        return this.type;
    }

    /*
     * A function that change the value of the Var to true.
     */
    public void setValue() {
        this.value = true;
    }

    /*
     * A function that return if the Var have value.
     */
    public boolean getValue() {
        return value;
    }

    /*
     * A function that change the Var to final.
     */
    void changeToFinal() throws SyntaxException{
        if(!value){
            throw new SyntaxException();
        } else {
            isFinal = true;
        }
    }

    /*
     * A function that check if Var is final.
     */
    public boolean getFinal() {
        if (this.isFinal) {
            return true;
        }
        return false;
    }


}
