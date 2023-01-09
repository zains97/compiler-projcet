package compiler;

import lexer.Lexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        File code = new File("src/compiler/code.txt");
        System.setIn(new FileInputStream(code));

        Lexer lexer = new Lexer();
        lexer.scan();
    }
}
