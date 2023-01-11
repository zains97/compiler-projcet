package lexer;

public class Token {
    public final int tag;

    protected Token(int tag){
        this.tag = tag;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Token{");
        sb.append("tag=").append(tag);
        sb.append("character: ").append((char) tag);
        sb.append('}');
        return sb.toString();
    }
}
