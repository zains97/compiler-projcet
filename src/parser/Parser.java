package parser;

import lexer.FloatNumber;
import lexer.RealNumber;
import lexer.Tag;
import lexer.Token;

import java.util.List;

public class Parser {
    List<Token> usedTokens;
    public Parser(List<Token> usedTokens){
        this.usedTokens = usedTokens;
    }

    public void parse(){
        System.out.println(PROG());
    }
    public boolean PROG(){
        return EXPR(0);
    }

    public boolean EXPR(int start){
        for (int i = start; i < usedTokens.size(); i++) {

            if (OPERAND(usedTokens.get(i))){
                i = i +1;
                if (i <= usedTokens.size() - 1) {
                    if (OPERATOR(usedTokens.get(i))){
                        i = i +1;
                        if (i <= usedTokens.size() - 1){
                            if (OPERAND(usedTokens.get(i))){
                                i = i +1;
                                if (i <= usedTokens.size() - 1){
                                    if (usedTokens.get(i).tag == Tag.SEMICOLON){
                                        return true;
                                    }
                                    else {
                                        return EXPR(i + 1);
                                    }
                                }
                                else {
                                    throw new IllegalStateException("Incomplete statement");
                                }
                            }
                        }
                        else {
                            throw new IllegalStateException("Incomplete statement");
                        }
                    }

                }
                else {
                    throw new IllegalStateException("Incomplete statement");
                }




            }
        }
        return false;
    }

    public boolean OPERAND(Token token){
        if (token.tag == Tag.CONSTANT || token.tag == Tag.ID){
            return true;
        }
        throw new IllegalStateException("Expected operand");
    }

    public boolean OPERATOR(Token token){
        if (
                token.tag == Tag.PLUS
                || token.tag == Tag.MINUS
                || token.tag == Tag.MULTIPLY
                || token.tag == Tag.DIVIDE
        ){
            return true;
        }
        else {
            throw new IllegalStateException("Expected operator");
        }
    }
}
