package fr.upjv.calculator.calcul;


public class Calcul {
    private String expression;
    private int[] values;
    private Operator[] operators;

    /**
     * values' size = operators' size +1
     * @param values
     * @param operators
     */
    public Calcul(int[] values, Operator[] operators) {
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

    public int result() {
        Calcul simplified = simplified();
        int result = simplified.values[0];
        if (simplified.operators.length == 0)
            return result;
        for (int i = 0; i < simplified.operators.length; i++) {
            result = calcul(result, simplified.values[i+1], simplified.operators[i]);
        }
        return result;
    }

    private int calcul(int v1, int v2, Operator operator) {
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

    private Calcul simplified() {
        int priorityOperatorNumber = countPriorityOperator();
        if (priorityOperatorNumber == 0)
            return this;
        int[] newValues = new int[values.length - priorityOperatorNumber];
        Operator[] newOperators = new Operator[operators.length - priorityOperatorNumber];

        int next = 0;
        int inARow = 0;

        for (int i = 0; i < operators.length; i++) {
            if (operators[i].isPriority()) {
                if (inARow == 0) {
                    newValues[next] = calcul(values[i], values[i+1], operators[i]);
                } else {
                    newValues[next] = calcul(newValues[next], values[i+1], operators[i]);
                }
                inARow++;
            } else {
                inARow = 0;
                newOperators[next] = operators[i];
                newValues[++next] = values[i+1];
            }
        }

        return new Calcul(newValues, newOperators);
    }


}
