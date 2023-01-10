package lexer;

public class FloatNumber extends Token {
    private final float value;

    public FloatNumber(int tag, float value) {
        super(tag);
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FloatNumber{");
        sb.append("value=").append(value);
        sb.append(", tag=").append(tag);
        sb.append('}');
        return sb.toString();
    }
}
