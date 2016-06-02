package oop.ex6;

import java.io.*;
import java.util.*;

/**
 * Created by Elon on 30/05/2016.
 */
public class Scanning {

    String checking;





    public Scanning(String[] var1) {
        checking = var1[0];
    }

    public void run() throws FileNotFoundException  {
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

    public LinkedList<String> firstChecking (Scanner scan) {
        int difference = 0;
        LinkedList<String> functionsList = new LinkedList<String>();
        boolean inFunction = false;
        String allFunction = "";
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
                    return null;
                }
                if ((difference == 0) && (!allFunction.equals(""))) {
                    inFunction = false;
                    functionsList.add(allFunction);
                    allFunction = "";
                }
                if (inFunction) {
                    allFunction = (allFunction + " " + line);
                }
                if (line.matches("\\s*v\\s?o\\s?i\\s?d\\s+.+")) {
                    inFunction = true;
                }
            }
        }
        if (difference > 0) {
            return null;
        }
        return functionsList;
    }

//    public boolean runInBarckets (Scanner scan){
//        String line = scan.next();
//        while (scan.hasNext()) {
//            if (line.startsWith("\\")) {
//                line = scan.next();
//            }
//           /*checkLine (line);*/
//            line = scan.next();
//            if (line.endsWith("{ *")) {
//                runInBarckets(scan);
//            }
//            else if (line.equals(" *} *")) {
//                return true;
//            }
//        }
//        return false;
//    }
}