package oop.ex6;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amit on 01/06/2016.
 */
public class VarsFactory {
    private final static Pattern LEGAL_LINE = Pattern.compile(" *\\w+ *.*;");
    private final static Pattern LEGAL_VAR_NAME = Pattern.compile("([_]{1}[\\w]+)|([a-zA-Z]+[\\w]*)");
    private final static Pattern LEGAL_ASSIGNMENT_SYNTAX = Pattern.compile("^= *+");
    private final static Pattern LEGAL_INT_VALUES = Pattern.compile(" *+-?\\d+ *+$");
    private final static Pattern STARTING_SPACES = Pattern.compile("^ *+");
    private final static Pattern FIRST_WORLD = Pattern.compile("\\w++ ");


    enum SavedWord {
        FINAL("final", ""), INT("int", " *-?\\d+ *$"), STRING("String", " *\".*?\" *$"),
        CHAR("char", " *\'.?\' *$"), DOUBLE("double", " *-?\\d+\\.\\d+|-?\\d+ *$"),
        BOOLEAN("boolean", "false|true"), VOID("void", "");

        private final String VALUE;
        private final Pattern LEGAL_ASSIGNMENT_VALUE;

        SavedWord(String value, String assignment_value_regex) {
            this.VALUE = value;
            this.LEGAL_ASSIGNMENT_VALUE = Pattern.compile(assignment_value_regex);
        }

        String getVALUE() {
            return this.VALUE;
        }

        Pattern getPattern() {
            return LEGAL_ASSIGNMENT_VALUE;
        }

    }

    static LinkedList<Var> createVars(String line) throws SyntaxException {
        Matcher isLegalLineMatcher = LEGAL_LINE.matcher(line);
        if (!isLegalLineMatcher.matches() || (line.indexOf(';') != line.lastIndexOf(";"))) {
            throw new SyntaxException();
        }
        //notice that a line that starts with space will pass the test
        Matcher firstWordMatcher = FIRST_WORLD.matcher(line);
        if (firstWordMatcher.find()) {
            String firstWord = line.substring(firstWordMatcher.start(), firstWordMatcher.end());
            String restOfTheLine = line.substring(firstWordMatcher.end());
            LinkedList<Var> newVars = new LinkedList<>();
            restOfTheLine = restOfTheLine.substring(0, restOfTheLine.lastIndexOf(";")); //removing ';'
            String[] varsValues = restOfTheLine.split(",");
            switch (firstWord) {
                case "final":
                    LinkedList<Var> finalVars = createVars(restOfTheLine);
                    for (Var var : finalVars) {
                        var.changeToFinal();
                    }
                    return finalVars;
                case "int":
                    for (String value : varsValues) {
                        newVars.add(createVar(value, SavedWord.INT));
                    }
                    return newVars;
                case "String":
                    for (String value : varsValues) {
                        newVars.add(createVar(value, SavedWord.STRING));
                    }
                    return newVars;
                case "char":
                    for (String value : varsValues) {
                        newVars.add(createVar(value, SavedWord.CHAR));
                    }
                    return newVars;
                case "double":
                    for (String value : varsValues) {
                        newVars.add(createVar(value, SavedWord.DOUBLE));
                    }
                    return newVars;
                case "boolean":
                    for (String value : varsValues) {
                        newVars.add(createVar(value, SavedWord.BOOLEAN));
                    }
                    return newVars;
                default:
                    throw new SyntaxException();

            }
        } else {
            throw new SyntaxException();
        }
    }

    static Var createVar(String value, SavedWord savedWord) throws SyntaxException {
        Matcher removeStartingSpacesMatcher = STARTING_SPACES.matcher(value);
        value = value.substring(removeStartingSpacesMatcher.end());
        Matcher nameMatcher = LEGAL_VAR_NAME.matcher(value);
        if (!nameMatcher.find()) {
            throw new SyntaxException();
        }
        String name = value.substring(nameMatcher.start(), nameMatcher.end());
        boolean isAssignedWithValue = false;
        String restOfTheValue = value.substring(nameMatcher.end());
        removeStartingSpacesMatcher = STARTING_SPACES.matcher(restOfTheValue);
        restOfTheValue = restOfTheValue.substring(removeStartingSpacesMatcher.end());
        if (!restOfTheValue.equals("")) {
            Matcher checkForAssignmentMatcher = LEGAL_ASSIGNMENT_SYNTAX.matcher(restOfTheValue);
            if (!checkForAssignmentMatcher.find()) {
                throw new SyntaxException();
            } else {
                restOfTheValue = restOfTheValue.substring(checkForAssignmentMatcher.end());
                Matcher checkAssignmentValueMatcher = savedWord.getPattern().matcher(restOfTheValue);
                if (!checkAssignmentValueMatcher.matches()) {
                    throw new SyntaxException();
                }
                isAssignedWithValue = true;
            }
        }

        return new Var(name, savedWord.getVALUE(), isAssignedWithValue, false);
    }

    static void updateVar(String line){

    }

}
