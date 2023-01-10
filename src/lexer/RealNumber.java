package lexer;

public class RealNumber extends Token{
    private final int value;

    protected RealNumber(int tag, int value) {
        super(tag);
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RealNumber{");
        sb.append("value=").append(value);
        sb.append(", tag=").append(tag);
        sb.append('}');
        return sb.toString();
    }
}
