package oop.ex6;
import java.util.regex.Matcher
import java.util.regex.Pattern;
/**
 * Created by amit on 01/06/2016.
 */
public class varsFactory {
    enum SavedWord {
        FINAL("final"), INT("int"), STRING("String"), CHAR("char"), DOUBLE("double"), BOOLEAN("boolean"),
        VOID("void");

        private final String VALUE;

        SavedWord(String value) {
            this.VALUE = value;
        }

        String getVALUE(){
            return this.VALUE;
        }

    }

     vars createVars(String line) throws SyntaxException {
        //notice that a line that starts with space will pass the test
        Pattern firstWordRegex = Pattern.compile("\\w++ ");
        Matcher firstWordMatcher = firstWordRegex.matcher(line);
        if (firstWordMatcher.find()){
            String firstWord = line.substring(firstWordMatcher.start(),firstWordMatcher.end());
            String restOfTheLine = line.substring(firstWordMatcher.end());
            switch (firstWord){
                case "final":
                    vars finalVar = createVars(restOfTheLine);
                    //add here to change var to FINAL
                    return finalVar;
                case "int":
                    return createInt(restOfTheLine);
                case "String":
                    return createString(restOfTheLine);
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
        //int firstSpace =
        String firstWord = line.substring(line.indexOf(" "))


    }

    vars createInt(String values){
        return null;
    }

    vars createString(String values){
        return null;
    }
}
