package oop.ex6;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by amit on 01/06/2016.
 */
public class varsFactory {
    private final Pattern LEGAL_LINE = Pattern.compile( " *\\w+ *.*;");
    private final Pattern LEGAL_VAR_NAME = Pattern.compile("([_]{1}[\\w]+)|([a-zA-Z]+[\\w]*)");
    private final Pattern LEGAL_ASSIGNMENT_SYNTAX = Pattern.compile("^= *");
    private final Pattern LEGAL_INT_VALUES = Pattern.compile(" *\\d+ *$");
    private final Pattern STARTING_SPACES = Pattern.compile("^ *");


//    enum SavedWord {
//        FINAL("final"), INT("int"), STRING("String"), CHAR("char"), DOUBLE("double"), BOOLEAN("boolean"),
//        VOID("void");
//
//        private final String VALUE;
//
//        SavedWord(String value) {
//            this.VALUE = value;
//        }
//
//        String getVALUE(){
//            return this.VALUE;
//        }
//
//    }

     LinkedList<vars> createVars(String line) throws SyntaxException {
         Matcher islegalLineMatcher = LEGAL_LINE.matcher(line);
         if(!islegalLineMatcher.matches() || (line.indexOf(';')!=line.lastIndexOf(";"))){
             throw new SyntaxException();
         }
        //notice that a line that starts with space will pass the test
        Pattern firstWordRegex = Pattern.compile("\\w++ ");
        Matcher firstWordMatcher = firstWordRegex.matcher(line);
        if (firstWordMatcher.find()){
            String firstWord = line.substring(firstWordMatcher.start(),firstWordMatcher.end());
            String restOfTheLine = line.substring(firstWordMatcher.end());
            LinkedList<vars> newVars = new LinkedList<>();
            restOfTheLine = restOfTheLine.substring(0,restOfTheLine.lastIndexOf(";")); //removing ';'
            String[] varsValues = restOfTheLine.split(",");
            switch (firstWord){
                case "final":
                    LinkedList<vars> finalVars = createVars(restOfTheLine);
                    //add here to change var to FINAL
                    return finalVars;
                case "int":
                    for (String value: varsValues) {
                        newVars.add(createInt(value));
                    }
                    return newVars;
                case "String":
                    for (String value: varsValues) {
                        newVars.add(createString(value));
                    }
                    return newVars;
                case "char":
                    break;
                case "double":
                    break;
                case "boolean":
                    break;
                default:
                    throw new SyntaxException();

            }
        } else {
            throw new SyntaxException();
        }
         return null;
    }

    vars createInt (String value) throws SyntaxException{
        Matcher removeStartingSpacesMatcher = STARTING_SPACES.matcher(value);
        value = value.substring(removeStartingSpacesMatcher.end());
        Matcher nameMacher = LEGAL_VAR_NAME.matcher(value);
        if(!nameMacher.find()){
            throw new SyntaxException();
        }
        String name = value.substring(nameMacher.start(),nameMacher.end());
        boolean isAssignedWithValue = false;
        String restOfTheValue = value.substring(nameMacher.end());
        removeStartingSpacesMatcher = STARTING_SPACES.matcher(restOfTheValue);
        restOfTheValue = restOfTheValue.substring(removeStartingSpacesMatcher.end());
        if(restOfTheValue.equals("")){

        }else {
            Matcher checkForAssignmentMatcher = LEGAL_ASSIGNMENT_SYNTAX.matcher(restOfTheValue);
            if(!checkForAssignmentMatcher.find()){
                throw new SyntaxException();
            }else {
                restOfTheValue = restOfTheValue.substring(checkForAssignmentMatcher.end());
                Matcher checkAssignmentValueMatcher = LEGAL_INT_VALUES.matcher(restOfTheValue);
                if(!checkAssignmentValueMatcher.matches()){
                    throw new SyntaxException();
                }
                isAssignedWithValue = true;
            }
        }

        return null;
    }

    vars createString (String values){
        return null;
    }
}
