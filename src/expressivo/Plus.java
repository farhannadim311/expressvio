package expressivo;
import java.util.Objects;

/** Immutable addition node. */
public final class Plus implements Expression {
    // RI: left != null, right != null
    // AF: represents (left + right) with structural equality (no reordering/flattening)
    private final Expression left, right;

    public Plus(Expression left, Expression right) {
        this.left = Objects.requireNonNull(left, "left");
        this.right = Objects.requireNonNull(right, "right");
        checkRep();
    }

    public Expression getLeft() { return left; }
    public Expression getRight() { return right; }

    private void checkRep() {
        assert left != null;
        assert right != null;
    }

    @Override public String toString() {
        // Emit infix with '+'; callers (e.g., parser/simplifier) can parenthesize as needed
        return left.toString() + " + " + right.toString();
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof Plus)) return false;
        Plus p = (Plus) o;
        return left.equals(p.left) && right.equals(p.right);
    }

    @Override public int hashCode() { return Objects.hash("+", left, right); }
}
