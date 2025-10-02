package expressivo;
import java.util.Objects;

/** Immutable multiplication node. */
public final class Times implements Expression {
    // RI: left != null, right != null
    // AF: represents (left * right) with structural equality (no reordering/flattening)
    private final Expression left, right;

    public Times(Expression left, Expression right) {
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
        // Emit infix with '*'; callers can add parentheses where required
        return left.toString() + "*" + right.toString();
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof Times)) return false;
        Times t = (Times) o;
        return left.equals(t.left) && right.equals(t.right);
    }

    @Override public int hashCode() { return Objects.hash("*", left, right); }
}
