package compiler;

import lexer.Lexer;
import lexer.Token;
import parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {
        File code = new File("src/compiler/code.txt");
        System.setIn(new FileInputStream(code));

        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.scan();
        Parser parser = new Parser(tokens);
        System.out.println("\n\n");
        System.out.println("Parser output");
        System.out.println("==============");
        parser.parse();
    }
}
