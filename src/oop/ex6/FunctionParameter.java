package oop.ex6;

import java.util.LinkedList;

/**
 * Created by amit on 02/06/2016.
 */
public class FunctionParameter {
    private String Name;
    private LinkedList<Var> varList;
    private String functionList;

    FunctionParameter(String name, LinkedList<Var> varList, String functionList){
        this.Name = name;
        this.varList = varList;
        this.functionList = functionList;
    }


}
