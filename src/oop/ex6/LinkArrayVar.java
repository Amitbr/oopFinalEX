package oopFinalEX.src.oop.ex6;

import java.util.*t;

/**
 * Created by Elon on 01/06/2016.
 */
public class LinkArrayVar {

    private LinkArrayVar myArray;
    private LinkedList myList;

    public void LinkArrayVar (LinkArrayVar varArray, LinkedList varList) {
        myArray = varArray;
        myList = varList;
    }

    public boolean member(String varName) {
        ListIterator varListIterator = myList.listIterator();
        while (varListIterator.hasNext()){
            Var myVar = varListIterator.next();
            if (var.getName().equals(varName)) {
                return true;
            }
        }
        return false;
    }

    public boolean memberWithValue(String varName) throws SyntaxException {
        ListIterator varListIterator = myList.listIterator();
        while (varListIterator.hasNext()){
            Var myVar = varListIterator.next();
            if (var.name().equals(varName)) {
                if (var.value) {
                    return true;
                }
                return false;
            }
        }
        throw  new syntaxException();
    }
}
