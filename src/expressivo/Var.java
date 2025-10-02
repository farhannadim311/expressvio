package expressivo;
import java.util.Objects;
import java.util.regex.Pattern;

/** Immutable variable identifier. */
public final class Var implements Expression {
    // RI: name != null, matches [A-Za-z]+
    // AF: represents the variable with this name
    private static final Pattern NAME = Pattern.compile("[A-Za-z]+");

    private final String name;

    public Var(String name) {
        if (name == null || !NAME.matcher(name).matches()) {
            throw new IllegalArgumentException("Var name must match [A-Za-z]+: " + name);
        }
        this.name = name;
        checkRep();
    }

    public String getName() { return name; }

    private void checkRep() {
        assert name != null;
        assert NAME.matcher(name).matches();
    }

    @Override public String toString() { return name; }

    @Override public boolean equals(Object o) {
        return (o instanceof Var) && ((Var) o).name.equals(this.name);
    }

    @Override public int hashCode() { return Objects.hash(name); }
}
