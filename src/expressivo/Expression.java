package expressivo;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 *
 * =========================
 * Datatype Definition (REP)
 * =========================
 * Expression =
 *     Num(value: double >= 0.0)                              // numeric literal (int or float, stored as double)
 *   | Var(name: String, where name matches [A-Za-z]+)        // variable identifier
 *   | Plus(left: Expression, right: Expression)              // addition node
 *   | Times(left: Expression, right: Expression)             // multiplication node
 *
 * Structural Equality:
 *   - Num: equal iff values are exactly equal per Double.compare (PS3 allows parseable toString; no epsilon here)
 *   - Var: equal iff names are identical (case-sensitive)
 *   - Plus/Times: equal iff corresponding subtrees are pairwise equal AND in the same shape/order
 *                 (i.e., no algebraic simplification/commutativity/associativity baked into equals)
 *
 * String Form (parsable):
 *   - Num: canonical decimal or integer without leading '+'; use minimal form from Java formatting
 *   - Var: the variable name as-is
 *   - Plus: left + right
 *   - Times: left*right
 *   - Parentheses are included by producers (e.g., parser/simplifier) when needed; raw toString here
 *     emits fully parsable infix ensuring Expression.parse(e.toString()).equals(e)
 *
 * Immutability & RI:
 *   - All variants are immutable; fields are private and final
 *   - Var name matches [A-Za-z]+
 *   - Num value is finite and >= 0
 *   - Plus/Times children are non-null
 */
public interface Expression {

    public static Expression parse(String input) {
        throw new RuntimeException("unimplemented");
    }
    @Override String toString();
    @Override boolean equals(Object thatObject);
    @Override int hashCode();
}
