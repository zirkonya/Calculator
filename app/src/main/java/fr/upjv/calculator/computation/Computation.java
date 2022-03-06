package fr.upjv.calculator.computation;


import fr.upjv.calculator.tools.Difficulty;

public class Computation {
    private String expression;
    private long[] values;
    private Operator[] operators;

    /**
     * values' size = operators' size +1
     * @param values
     * @param operators
     */
    public Computation(long[] values, Operator[] operators) {
        this.values = values;
        this.operators = operators;

        expression = values[0] + " ";
        for (int i = 0; i < operators.length; i++) {
            expression += String.format(" %c %d", operators[i].getSymbol(), values[i+1]);
        }
    }

    @Override
    public String toString() {
        return expression;
    }

    public long result() {
        Computation simplified = simplified();

        long result = simplified.values[0];
        if (simplified.operators.length == 0)
            return result;

        for (int i = 0; i < simplified.operators.length; i++) {
            result = compute(result, simplified.values[i+1], simplified.operators[i]);
        }

        return result;
    }

    private long compute(long v1, long v2, Operator operator) {
        switch (operator) {
            case ADD:
                return v1 + v2;
            case SUBTRACT:
                return v1 - v2;
            case MULTIPLY:
                return v1 * v2;
            case DIVIDE:
                return v1 / v2;
            case AND:
                return v1 & v2;
            case OR:
                return v1 | v2;
            default:
                return 0;
        }
    }

    private int countPriorityOperator() {
        int total = 0;
        for (Operator operator : operators) {
            if (operator.isPriority())
                total++;
        }
        return total;
    }

    private Computation simplified() {
        int priorityOperatorNumber = countPriorityOperator(); // 1

        if (priorityOperatorNumber == 0)
            return this;

        long[] newValues = new long[values.length - priorityOperatorNumber]; // long[2]
        Operator[] newOperators = new Operator[operators.length - priorityOperatorNumber]; // Operator[1]

        int next = 0;
        int inARow = 0;

        for (int i = 0; i < operators.length; i++) {
            if (operators[i].isPriority()) {
                if (inARow == 0) {
                    newValues[next] = compute(values[i], values[i+1], operators[i]);
                } else {
                    newValues[next] = compute(newValues[next], values[i+1], operators[i]);
                }
                inARow++;
            } else {
                newOperators[next] = operators[i];

                if (inARow != 0) {
                    newValues[next+1] = values[i+1];
                } else {
                    newValues[next] = values[i];
                }
                inARow = 0;
                next++;
            }
        }
        System.out.println(String.format("normal     : %s", this));
        Computation computation = new Computation(newValues, newOperators);
        System.out.println(String.format("simplified : %s", computation));
        return computation;
    }

    public static Computation generateComputation(Difficulty difficulty) {
        long[] values = new long[difficulty.getTerm()];
        Operator[] operator = new Operator[difficulty.getTerm()-1];

        values[0] = difficulty.getRandomTerm();
        for (int i = 0; i < operator.length; i++) {
            values[i+1] = difficulty.getRandomTerm();
            operator[i] = difficulty.getRandomOperator();
        }

        return new Computation(values, operator);
    }

}
