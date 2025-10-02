package expressivo;
import java.util.Objects;

/** Immutable numeric literal. */
public final class Num implements Expression {
    // RI: value is finite and >= 0.0
    // AF: represents the numeric literal 'value'
    private final double value;

    public Num(double value) {
        if (!Double.isFinite(value) || value < 0.0) {
            throw new IllegalArgumentException("Num must be finite and nonnegative: " + value);
        }
        this.value = value;
        checkRep();
    }

    public double getValue() { return value; }

    private void checkRep() {
        assert Double.isFinite(value);
        assert value >= 0.0;
    }

    @Override public String toString() {
        // minimal parsable form; strip trailing ".0" for integers
        if (value == Math.rint(value)) {
            return Long.toString((long) value);
        }
        return Double.toString(value);
    }

    @Override public boolean equals(Object o) {
        return (o instanceof Num) && Double.compare(((Num) o).value, value) == 0;
    }

    @Override public int hashCode() {
        return Objects.hash(Double.valueOf(value));
    }
}
