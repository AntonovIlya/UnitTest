package patterns.structural.adapter;

public class IntsCalculator implements Ints {

    private final Calculator calculator;

    public IntsCalculator() {
        calculator = new Calculator();
    }

    @Override
    public int sum(int arg0, int arg1) {
        return calculator
                .newFormula()
                .addOperand(arg0)
                .addOperand(arg1)
                .calculate(Calculator.Operation.SUM)
                .result.intValue();
    }

    @Override
    public int mult(int arg0, int arg1) {
        return calculator
                .newFormula()
                .addOperand(arg0)
                .addOperand(arg1)
                .calculate(Calculator.Operation.MULT)
                .result.intValue();
    }

    @Override
    public int pow(int a, int b) {
        return calculator
                .newFormula()
                .addOperand(a)
                .addOperand(b)
                .calculate(Calculator.Operation.POW)
                .result.intValue();
    }
}
