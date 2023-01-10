package lexer;

public class SpecialCharachter extends Token {
    public final String characeter;

    public SpecialCharachter(int tag, String character) {
        super(tag);
        this.characeter = character;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SpecialCharachter{");
        sb.append("characeter='").append(characeter).append('\'');
        sb.append(", tag=").append(tag);
        sb.append('}');
        return sb.toString();
    }
}
