package oop.ex6;

import java.io.*;
import java.util.*;

/**
 * Created by Elon on 30/05/2016.
 */
public class Scanning {

    String checking;
    private LinkArrayVar globalArrayVar;
    private LinkedList<FunctionParameter> myFunctions;

    public Scanning(String[] var1) {
        globalArrayVar = new LinkArrayVar();
        checking = var1[0];
    }

    public void run() throws FileNotFoundException, SyntaxException {
        try {
            File file = new File(checking);
            InputStream input = new FileInputStream(file);
            Scanner s = new Scanner(input).useDelimiter("(\\r\\n)");
            firstChecking(s);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void firstChecking(Scanner scan) throws SyntaxException {
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
                    String[] myArray = {parametersFunction, allFunction};
                    functionsList.add(myArray);
                    allFunction = "";
                    parametersFunction = "";
                }
                if ((difference == 0) && (allFunction.equals(""))) {
                    tempVarList = varFactory.createVars(line);
                    for (Var var : tempVarList) {
                        varList.add(var);
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
            FunctionParameter runFunction = new FunctionParameter(myStrings[0], varList, myStrings[1]);
        }
    }

    private String split (String  line, char c) {
        return line.substring(0, line.indexOf(c)-1);
    }
}