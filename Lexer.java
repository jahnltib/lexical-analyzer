import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Lexer {

    public static void main(String[] args) throws IOException {
        // Read file content
        BufferedReader reader = new BufferedReader(new FileReader("example.txt"));
        StringBuilder code = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            // Remove comments (# ... in Python)
            line = line.replaceAll("#.*", "");
            code.append(line).append(" ");
        }
        reader.close();

        // Define token patterns
        String keyword = "\\b(def|if|elif|else|return|print)\\b";
        String identifier = "\\b[a-zA-Z_]\\w*\\b";
        String operator = "[+\\-*/=><]+";
        String number = "\\b\\d+(\\.\\d+)?\\b";
        String separator = "[(){}:,]";
        String stringLiteral = "\".*?\"|'.*?'";

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

        while (matcher.find()) {
            if (matcher.group("KEYWORD") != null)
                System.out.println("\"" + matcher.group("KEYWORD") + "\" = keyword");
            else if (matcher.group("IDENTIFIER") != null)
                System.out.println("\"" + matcher.group("IDENTIFIER") + "\" = identifier");
            else if (matcher.group("OPERATOR") != null)
                System.out.println("\"" + matcher.group("OPERATOR") + "\" = operator");
            else if (matcher.group("NUMBER") != null)
                System.out.println("\"" + matcher.group("NUMBER") + "\" = number");
            else if (matcher.group("SEPARATOR") != null)
                System.out.println("\"" + matcher.group("SEPARATOR") + "\" = separator");
            else if (matcher.group("STRING") != null)
                System.out.println("\"" + matcher.group("STRING") + "\" = string");
        }
    }
}
