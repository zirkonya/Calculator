package fr.upjv.calculator.tools;

import fr.upjv.calculator.calcul.Operator;

public enum Difficulty {
    SIMPLE("51MPL3", 2, 0, 10, Operator.ADD, Operator.SUBTRACT),
    NORMAL("N0RM4L", 3, 1, 10, Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY),
    COMPLEX("C0MPL3X", 4, 1, 100, Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY),
    HARD("H4RD", 4, 1, 100, Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY, Operator.DIVIDE),
    HELL("H3LL", 4, 1, 500, Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY, Operator.DIVIDE),
    BINARY("B1N4RY", 2, 0, 256,  Operator.AND, Operator.OR);

    private final String name;
    private final int term;
    private final int from;
    private final int to;
    private final Operator[] operators;

    Difficulty(String name, int term, int from, int to, Operator...operators) {
        this.name = name;
        this.term = term;
        this.operators = operators;
        this.from = from;
        this.to = to;
    }

    public int getTerm() {
        return term;
    }

    public Operator getRandomOperator() {
        return operators[(int) (Math.random() * operators.length)];
    }

    public int getRandomTerm() {
        return (int) (Math.random() * Math.abs(to - from)) + from;
    }

    public String getName() {
        return name;
    }

}
