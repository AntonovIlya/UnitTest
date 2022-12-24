package tdd;

public class Calculator {

    private final double creditPeriod;
    private final double creditAmount;
    private final double MIR; // MonthlyInterestRate
    private double payment;
    private double amount;
    private double overpayment;

    public Calculator(double interestRate, double creditPeriod, double creditAmount) {
        this.creditPeriod = creditPeriod;
        this.creditAmount = creditAmount;
        MIR = (interestRate / 12) / 100;
    }

    public double monthlyPayment() {
        calculateParams();
        return Math.round(payment);
    }

    public double totalAmount() {
        calculateParams();
        return Math.round(amount);
    }

    public double totalOverpayment() {
        calculateParams();
        return Math.round(overpayment);
    }

    private void  calculateParams() {
        payment = creditAmount * (MIR * Math.pow((1 + MIR), creditPeriod * 12)) / (Math.pow((1 + MIR), creditPeriod * 12) - 1);
        amount = payment * (creditPeriod * 12);
        overpayment = amount - creditAmount;
    }
}
