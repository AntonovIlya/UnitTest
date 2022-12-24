package tdd;

import tdd.Calculator;
import org.junit.jupiter.api.*;

import java.util.Random;

public class TestCalculator {

    private static final Random random = new Random();

    @Test
    void monthlyPayment() {
        double interestRate = 12;
        double creditPeriod = 3;
        double creditAmount = 600_000;
        Calculator calculator = new Calculator(interestRate, creditPeriod, creditAmount);
        Assertions.assertEquals(19929, calculator.monthlyPayment());
    }

    @Test
    void totalAmount() {
        double interestRate = 10;
        double creditPeriod = 1;
        double creditAmount = 200_000;
        Calculator calculator = new Calculator(interestRate, creditPeriod, creditAmount);
        Assertions.assertEquals(210998, calculator.totalAmount());
    }

    @Test
    void totalOverpayment() {
        double interestRate = 7.2;
        double creditPeriod = 5;
        double creditAmount = 1_000_000;
        Calculator calculator = new Calculator(interestRate, creditPeriod, creditAmount);
        Assertions.assertEquals(193742, calculator.totalOverpayment());

    }

}
