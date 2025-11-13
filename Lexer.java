import java.io.*;   // File Reading
import java.util.*;  // For utilities like StringBuilder
import java.util.regex.*; // Regular expressions which is patter and matcher 

public class Lexer {

    public static void main(String[] args) throws IOException {
        // Read file content
        BufferedReader reader = new BufferedReader(new FileReader("example.txt"));           // Opening the example.txt
        StringBuilder code = new StringBuilder();               //Store entire file content as a single string 
        String line;
        while ((line = reader.readLine()) != null) {         // Reading file
            // Remove comments (# ... in Python)  
            line = line.replaceAll("#.*", "");       // Appends the line and a space to separate tokens 
            code.append(line).append(" ");
        }
        reader.close();      // Close file 

        // Define token patterns
        String keyword = "\\b(def|if|elif|else|return|print)\\b";   // Keywords
        String identifier = "\\b[a-zA-Z_]\\w*\\b";      // Identifiers 
        String operator = "[+\\-*/=><]+";       // Operators 
        String number = "\\b\\d+(\\.\\d+)?\\b";     // Numbers 
        String separator = "[(){}:,]";              // Punctiation and Separators 
        String stringLiteral = "\".*?\"|'.*?'";     // String literals 

        // Combine patterns
        Pattern tokenPatterns = Pattern.compile(
            String.join("|",
                "(?<KEYWORD>" + keyword + ")",
                "(?<IDENTIFIER>" + identifier + ")",
                "(?<OPERATOR>" + operator + ")",
                "(?<NUMBER>" + number + ")",
                "(?<SEPARATOR>" + separator + ")",
                "(?<STRING>" + stringLiteral + ")"
            )
        );

        Matcher matcher = tokenPatterns.matcher(code);
        System.out.println("Set of lexemes and tokens (<lexeme> = <token>)\n");
        // Loop through all the matches and classifying each lexeme 
        while (matcher.find()) {
            if (matcher.group("KEYWORD") != null)       // Named groups  
                System.out.println("\"" + matcher.group("KEYWORD") + "\" = keyword");       // Found a keyword 
            else if (matcher.group("IDENTIFIER") != null)
                System.out.println("\"" + matcher.group("IDENTIFIER") + "\" = identifier");             // Found an identifier 
            else if (matcher.group("OPERATOR") != null)
                System.out.println("\"" + matcher.group("OPERATOR") + "\" = operator");             // Found an operator 
            else if (matcher.group("NUMBER") != null)
                System.out.println("\"" + matcher.group("NUMBER") + "\" = number");                 // Found a number 
            else if (matcher.group("SEPARATOR") != null)
                System.out.println("\"" + matcher.group("SEPARATOR") + "\" = separator");           // Found a separator
            else if (matcher.group("STRING") != null)
                System.out.println("\"" + matcher.group("STRING") + "\" = string");             // Found a string literal 
        }
    }
}
