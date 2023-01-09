package lexer;

import java.io.IOException;

public class Lexer {
    private char peek = ' ';
    private int peek_byte = 0;
    private int line = 1;

    private void read() throws IOException {
        peek_byte = System.in.read();
        peek = (char) peek_byte;
    }

    private boolean read(char c) throws IOException {
        read();
        return c == peek;
    }

    //Tommorow we will evaluate double chars and complete the lexer
    public void scan() throws IOException {
        while (peek == ' ' || peek == '\t') {
            read();
        }

        while (peek == '\n') {
            line = line + 1;
        }

        while (peek_byte != -1) {
            if (peek == '\n') {
                line = line + 1;
            }

            //Check if peek is a special charahcter
            switch (peek) {
                case '&':
                    if (read('&')) {
                        System.out.println("&&");
                    } else {
                        System.out.println("&");
                    }
                    break;
                case '|':
                    if (read('|')) {
                        System.out.println("||");
                    } else {
                        System.out.println("|");
                    }
                    break;
                case '+':
                    System.out.println("+");
                    break;
                case '-':
                    System.out.println("-");
                    break;
                case '/':
                    System.out.println("/");
                    break;
                case '*':
                    System.out.println("*");
                    break;
                case '=':
                    System.out.println("=");
                    break;
                case '(':
                    System.out.println("(");
                    break;
                case ')':
                    System.out.println(")");
                    break;
                case ';':
                    System.out.println(';');
                    break;
            }

            //Check if peek is a number
            if (Character.isDigit(peek)) {
                System.out.println(peek);
            }

            //Check if peek is an alphabet
            if (Character.isAlphabetic(peek)) {
                System.out.println(peek);
            }
            read();
        }
    }
}
