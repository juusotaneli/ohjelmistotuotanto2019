package statistics;

import statistics.matcher.*;

public class QueryBuilder extends Stack {

    Stack stack;

    public QueryBuilder() {
        this.stack = new Stack();

    }
    public Stack stack() {
        return stack;
    }
    public QueryBuilder playsIn(String team) {
        stack.push(new PlaysIn(team));
        return this;
    }
    public QueryBuilder hasAtLeast(int value, String fieldName) {
        stack.push(new HasAtLeast(value, fieldName));
        return this;
    }
    public QueryBuilder hasFewerThan(int value, String fieldName) {
        stack.push(new HasFewerThan(value, fieldName));
        return this;
    }
    public QueryBuilder hasOneOrMore(Matcher...matchers) {
        stack.push(new Or(matchers));
        return this;
    }
    public Matcher build() {
        return new And(stack.getAll());
    }
}
