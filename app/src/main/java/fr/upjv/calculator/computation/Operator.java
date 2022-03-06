package fr.upjv.calculator.computation;

public enum Operator {
    ADD('+', false),
    SUBTRACT('-', false),
    MULTIPLY('*', true),
    DIVIDE('/', true),
    AND('&', false),
    OR('|', false);

    private char symbol;
    private boolean priority;
    Operator(char symbol, boolean priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isPriority() {
        return priority;
    }
}
