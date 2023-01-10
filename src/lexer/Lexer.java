package lexer;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Lexer {
    public Map<String, Token> usedTokens = new LinkedHashMap<>();
    public char peek = ' ';
    public int peek_byte = 0;
    public int line = 1;

    public void read() throws IOException {
        peek_byte = System.in.read();
        peek = (char) peek_byte;
    }

    public boolean read(char c) throws IOException {
        read();
        return c == peek;
    }

    //Tomorrow we will evaluate double chars and complete the lexer
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

            //Check if peek is a special character
            switch (peek) {
                case '&':
                    if (read('&')) {
                        usedTokens.put("AND", new SpecialCharachter(Tag.AND, "&&"));
                        System.out.println("&&");
                    } else {
                        usedTokens.put("AMPERSAND", new Token('&'));
                        System.out.println("&");
                        continue;
                    }
                    break;
                case '|':
                    if (read('|')) {
                        usedTokens.put("OR", new SpecialCharachter(Tag.OR, "||"));
                        System.out.println("||");
                    } else {
                        usedTokens.put("PIPE", new Token('|'));
                        System.out.println("|");
                        continue;
                    }
                    break;
                case '+':
                    if (read('+')) {
                        usedTokens.put("INCREMENT", new SpecialCharachter(Tag.INCREMENT, "++"));
                        System.out.println("++");
                    } else {
                        System.out.println("+");
                        usedTokens.put("PLUS", new Token('+'));
                        continue;
                    }
                    break;
                case '-':
                    if (read('-')) {
                        usedTokens.put("DECREMENT", new SpecialCharachter(Tag.DECREMENT, "--"));
                        System.out.println("--");
                    } else {
                        usedTokens.put("MINUS", new Token('-'));
                        System.out.println("-");
                        continue;
                    }
                    break;
                case '/':
                    usedTokens.put("DIVIDE", new Token('/'));
                    System.out.println("/");
                    break;
                case '*':
                    usedTokens.put("MULTIPLE", new Token('*'));
                    System.out.println("*");
                    break;
                case '=':
                    usedTokens.put("ASSIGNMENT", new Token('='));
                    System.out.println("=");
                    break;
                case '(':
                    usedTokens.put("BR_OP", new Token('('));
                    System.out.println("(");
                    break;
                case ')':
                    usedTokens.put("BR_CL", new Token(')'));
                    System.out.println(")");
                    break;
                case ';':
                    usedTokens.put("SEMICOLON", new Token(';'));
                    System.out.println(';');
                    break;
            }

            if (Character.isDigit(peek)) {
                int whole = 0;

                //Check if peek is a number
                do {
                    whole = (10 * whole) + Character.digit(peek, 10);
                    read();
                } while (Character.isDigit(peek));

                if (peek != '.') {
                    usedTokens.put("CONSTANT: ", new RealNumber(Tag.CONSTANT, whole));
                    continue;
                }
                float real = whole;

                read();

                while (Character.isDigit(peek)) {
                    real = real + (Character.digit(peek, 10) / 10f);
                    read();
                }
                usedTokens.put("CONSTANT", new FloatNumber(Tag.CONSTANT, real));
            }

            //Check if peek is an alphabet
            if (Character.isAlphabetic(peek)) {
                System.out.println(peek);
            }
            read();
        }
        System.out.println("Tokens");
        usedTokens.forEach((x, y) -> System.out.println(x + " : " + y));
    }
}
