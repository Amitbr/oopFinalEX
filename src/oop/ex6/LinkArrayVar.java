package oop.ex6;

import java.util.*;

/**
 * Created by Elon on 01/06/2016.
 */
public class LinkArrayVar {

    private LinkArrayVar myArray;
    private LinkedList<Var> myList;

    public void LinkArrayVar (LinkArrayVar varArray) {
        myArray = varArray;
        myList =  new LinkedList<>();
    }

    public void LinkArrayVar () {
        myArray = null;
        myList =  new LinkedList<>();
    }

    public boolean member(String varName) {
        ListIterator varListIterator = myList.listIterator();
        while (varListIterator.hasNext()){
            Var myVar = (Var) varListIterator.next();
            if (myVar.getName().equals(varName)) {
                return true;
            }
        }
        return false;
    }

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
}
