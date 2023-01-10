package lexer;

public class Keyword extends Token{
    public final String lexeme;

    protected Keyword(int tag, String lexeme) {
        super(tag);
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Keyword{");
        sb.append("lexeme='").append(lexeme).append('\'');
        sb.append(", tag=").append(tag);
        sb.append('}');
        return sb.toString();
    }
}
