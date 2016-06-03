package oop.ex6;

import java.io.*;
import java.util.*;

/**
 * Created by Elon on 30/05/2016.
 */

/**
 * this class scan on the file and check his correctness.
 */
public class Scanning {

    /*
     * the String to check.
     */
    private String checking;

    /*
     * A LinkArrayVar of all the global Vars.
     */
    private LinkArrayVar globalArrayVar;

    /*
     * A LinkedList of functionParameters.
     */
    private LinkedList<FunctionParameter> myFunctions;

    /**
     * A constructor that get file and creates new scanning.
     */
    public Scanning(String[] var1) {
        globalArrayVar = new LinkArrayVar();
        checking = var1[0];
    }

    /**
     * A function that run the main.
     */
    public void run() throws FileNotFoundException, SyntaxException {
        try {
            File file = new File(checking);
            InputStream input = new FileInputStream(file);
            Scanner s = new Scanner(input).useDelimiter("(\\r\\n)");
            readTheFile(s);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A function that read the file and check him, use another functions.
     */
    public void readTheFile(Scanner scan) throws SyntaxException {
        int difference = 0;
        LinkedList<Var> varList = new LinkedList<Var>();
        LinkedList<Var> tempVarList = new LinkedList<Var>();
        LinkedList<String[]> functionsList = new LinkedList<String[]>();
        boolean inFunction = false;
        String parametersFunction = "";
        String allFunction = "";
        VarsFactory varFactory = new VarsFactory();
        while (scan.hasNext()) {
            String line = scan.next();
            if (!line.matches("\\{2}.*")) {
                if (line.matches("\\s*}")) {
                    difference--;
                }
                if (line.matches(".*\\{\\s*")) {
                    difference++;
                }
                if (difference < 0) {
                    throw new SyntaxException();
                }
                if ((difference == 0) && (!allFunction.equals(""))) {
                    inFunction = false;
                    //האיבר הראשון הוא השורה ללא ה} והאיבר השני הוא סטרינג של כל הפונקציה עם אנטרים בין שורה לשורה
                    String[] myArray = {parametersFunction, allFunction};
                    functionsList.add(myArray);
                    allFunction = "";
                    parametersFunction = "";
                }
                if ((difference == 0) && (allFunction.equals(""))) {
                    // יצרתי רשימה של המשתנים בעזרת הvarfactory ואז בדקתי שאין שניים באותו שם, כי אנחנו בגלובל עמוד 5 הערה רביעית
                    tempVarList = varFactory.createVars(line);
                    for (Var var : tempVarList) {
                        if (newVar(var, varList)) {
                            varList.add(var);
                        }
                        else {
                            throw new SyntaxException();
                        }
                    }
                }
                if (inFunction) {
                    allFunction = (allFunction + "\n" + line);
                }
                if (line.matches("void .+ \\{")) {
                    parametersFunction = split(line, '}');
                    inFunction = true;
                }
            }
        }
        if (difference > 0) {
            throw new SyntaxException();
        }
        for (String[] myStrings: functionsList) {
            //כאן עוברים על כל הפונקציות, אחרי שיש רשימה כבר של כל הגלובלים, בהתאם לclass של functionparamaters
            FunctionParameter runFunction = new FunctionParameter(myStrings[0], varList, myStrings[1]);
        }
    }

    /*
     * A function that end line before the char {.
     */
    private String split (String  line, char c) {
        return line.substring(0, line.indexOf(c)-1);
    }

    /*
     * A function that check there is no another variable with this name.
     */
    private boolean newVar(Var varElement, LinkedList<Var> varlist) {
        for (Var var: varlist) {
            if (var.getName().equals(varElement.getName())) {
                return false;
            }
        }
        return true;
    }
}