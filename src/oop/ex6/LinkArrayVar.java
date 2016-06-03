package oop.ex6;

import java.util.*;

/**
 * Created by Elon on 01/06/2016.
 */

/**
 * this class have all the Vars sorted by their location in file.
 */
public class LinkArrayVar {

    /*
     * A son of the class.
     */
    private LinkArrayVar myArray;
    /*
     * the Vars in the current location;
     */
    private LinkedList<Var> myList;

    /**
     * A constructor that creates LinkArrayVar with the specific Vars only.
     */
    public LinkArrayVar(LinkedList<Var> varList) {
        myList = varList;
        myArray = null;
    }

    /**
     * A constructor that creates LinkArrayVar recursive with else LinkArrayVar.
     */
    public void LinkArrayVar (LinkArrayVar varArray) {
        myArray = varArray;
        myList =  new LinkedList<>();
    }

    /**
     * A constructor that creates LinkArrayVar.
     */
    public LinkArrayVar() {
        myArray = null;
        myList =  new LinkedList<>();
    }

    /**
     * Check if Var exist in the VarArray by name and return the Var.
     */
    public Var member(String varName) {
        ListIterator varListIterator = myList.listIterator();
        while (varListIterator.hasNext()){
            Var myVar = (Var) varListIterator.next();
            if (myVar.getName().equals(varName)) {
                return myVar;
            }
        }
        return null;
    }

    /*
     * Check if Var exist and with value in the VarArray by name and return true\false.
     */
    public boolean memberWithValue(String varName) throws SyntaxException {
        ListIterator varListIterator = myList.listIterator();
        while (varListIterator.hasNext()){
            Var myVar = (Var) varListIterator.next();
            if (myVar.getName().equals(varName)) {
                if (myVar.getValue()) {
                    return true;
                }
                return false;
            }
        }
        throw new SyntaxException();
    }
    /*
    * Add elements to the LinkArrayVar.
    */
    public void add(Var var) {
        myList.add(var);
    }
}
