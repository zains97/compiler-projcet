package lexer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Lexer {
    public List<Token> usedTokens = new LinkedList<>();
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
    public List<Token> scan() throws IOException {
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
                        usedTokens.add(new SpecialCharachter(Tag.AND, "&&"));
                    } else {
                        throw  new IllegalStateException("& is not a token");
                    }
                    break;
                case '|':
                    if (read('|')) {
                        usedTokens.add(new SpecialCharachter(Tag.OR, "||"));
                    } else {
                        throw  new IllegalStateException("| is not a valid token");

                    }
                    break;
                case '+':
                    if (read('+')) {
                        usedTokens.add( new SpecialCharachter(Tag.INCREMENT, "++"));
                    } else {
                        usedTokens.add( new SpecialCharachter(Tag.PLUS, "+"));
                        continue;
                    }
                    break;
                case '-':
                    if (read('-')) {
                        usedTokens.add(new SpecialCharachter(Tag.DECREMENT, "--"));
                    } else {
                        usedTokens.add( new SpecialCharachter(Tag.MINUS, "-"));
                        continue;
                    }
                    break;
                case '/':
                    usedTokens.add( new SpecialCharachter(Tag.DIVIDE, "/"));
                    break;
                case '*':
                    usedTokens.add( new SpecialCharachter(Tag.MULTIPLY, "*"));
                    break;
                case '=':
                    usedTokens.add( new SpecialCharachter(Tag.ASSIGNMENT, "="));
                    break;
                case '(':
                    usedTokens.add( new SpecialCharachter(Tag.BR_OP, "("));
                    break;
                case ')':
                    usedTokens.add( new SpecialCharachter(Tag.BR_CL, ")"));
                    break;
                case ';':
                    usedTokens.add( new SpecialCharachter(Tag.SEMICOLON, ";"));
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
                    usedTokens.add( new RealNumber(Tag.CONSTANT, whole));

                    continue;
                }
                float real = whole;
                float divisor = 10;
                read();

                while (Character.isDigit(peek)) {
                    real = real + (Character.digit(peek, 10) / divisor);
                    divisor = divisor * 10;
                    read();
                }
                usedTokens.add( new FloatNumber(Tag.CONSTANT, real));
                continue;
            }

            //Check if peek is an alphabet
            if (Character.isAlphabetic(peek)) {
                String variableName = "";

                do {
                    variableName = variableName + peek;
                    read();
                }while (Character.isLetterOrDigit(peek));
                usedTokens.add(new Variable(variableName, Tag.ID));
            }
            read();
        }
        System.out.println("Lexer Output:");
        System.out.println("=============");
        usedTokens.forEach(System.out::println);
        return usedTokens;
    }

}
