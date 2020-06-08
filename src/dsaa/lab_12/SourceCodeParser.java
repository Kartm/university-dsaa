package dsaa.lab_12;

import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.javadoc.*;

import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

class SourceCodeParser {
    public static void main(String[] args) throws IOException {
        // Set file path
        Path path = Paths.get("/home/arach/Repositories/dsaa/src/dsaa/lab_12/KMP.java");

        // Set configuration
        ParserConfiguration parseConfig = new ParserConfiguration();
        parseConfig.setCharacterEncoding(Charset.forName("UTF-8"));
        parseConfig.setTabSize(4);
        parseConfig.setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_8);

        // Get the parser
        JavaParser jvParser = new JavaParser(parseConfig);

        // Parse the result
        ParseResult<CompilationUnit> parseResult = jvParser.parse(path);

        // Check for problem
        if (!parseResult.isSuccessful()) {
            System.out.print("Parsing java code fail with the following problems:");
            List<Problem> problems = parseResult.getProblems();
            for (Problem problem : problems) {
                System.out.println(problem.getMessage());
            }
            return;
        }

        // Get the compilationUnit
        // No optional checking for Optional<CompilationUnit> due to already check above.
        CompilationUnit compilationUnit = parseResult.getResult().get();

        // Get Classes
        List<ClassOrInterfaceDeclaration> classes = compilationUnit.findAll(ClassOrInterfaceDeclaration.class).stream()
                .filter(c -> !c.isInterface())
                .collect(Collectors.toList());

        // Traverse through each class to get method
        for (ClassOrInterfaceDeclaration c : classes) {
            // Get methods
            List<MethodDeclaration> methods = c.getMethods();
            for (MethodDeclaration method : methods) {
                // Get the body statement
                Optional<BlockStmt> body = method.getBody();
                // if no body continue
                if (!body.isPresent()) continue;
                // After getting the body of the method code
                // Search for the throw statements.
                List<ThrowStmt> throwStatements = body.get().findAll(ThrowStmt.class);
                // No throw statements, skip
                if (throwStatements.size() == 0) continue;

                // Storing name of exceptions thrown into this list.
                List<String> exceptionsThrown = new ArrayList<String>();

                for (ThrowStmt stmt : throwStatements) {
                    // Convert the throw expression to object creation expression and get the type.
                    String exceptionName = stmt.getExpression().asObjectCreationExpr().getType().toString();
                    if (!exceptionsThrown.contains(exceptionName)) exceptionsThrown.add(exceptionName);
                }

                /*
                 * Debug block for up to this point
                System.out.println(method.getName());
                System.out.println(exceptionsThrown);
                System.out.println();
                *
                **/

                // Get The Javadoc
                Optional<Javadoc> javadoc = method.getJavadoc();
                // To store the throws Tags
                List<JavadocBlockTag> throwTags;
                // A list of thrown exception that been documented.
                List<String> exceptionsDocumented = new ArrayList<String>();

                if (javadoc.isPresent()) {
                    throwTags = javadoc.get()
                            .getBlockTags()
                            .stream()
                            .filter(t -> t.getType() == JavadocBlockTag.Type.THROWS)
                            .collect(Collectors.toList());
                    for (JavadocBlockTag tag : throwTags) {
                        /*
                         * This may be buggy as
                         * the code assumed @throw exception
                         * to be on its own line. Therefore
                         * it will just take the first line as the exception name.
                         */
                        String exceptionName = tag.getContent().toText()
                                .split("\n")[0];  // Use system line separator or change
                        // line accordingly.

                        if (!exceptionsDocumented.contains(exceptionName))
                            exceptionsDocumented.add(exceptionName);
                    }
                }

                // getBegin can extract the line out. But evaluating the optional would take some more code
                // and is just for example so this was done like this without any checking.
                System.out.println("Method: " + method.getName() + " at line " + method.getBegin());
                System.out.println("Throws Exceptions: ");
                System.out.println(exceptionsThrown);
                System.out.println("Documented Exceptions:");
                System.out.println(exceptionsDocumented);

                System.out.println(System.lineSeparator() + System.lineSeparator());
            }
        }
    }
}