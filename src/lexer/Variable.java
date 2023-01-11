package lexer;

public class Variable extends Token{
    public final String name;

    protected Variable(String name,int tag){
        super(tag);
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Variable{");
        sb.append("name='").append(name).append('\'');
        sb.append(", tag=").append(tag);
        sb.append('}');
        return sb.toString();
    }
}
