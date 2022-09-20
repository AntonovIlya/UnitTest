package patterns.structural.facade;

public class BinOps {
    public String sum(String a, String b) {
        int sum = Integer.parseInt(a,2) + Integer.parseInt(b,2);
        return String.valueOf(sum);
    }

    public String mult(String a, String b) {
        int sum = Integer.parseInt(a,2) * Integer.parseInt(b,2);
        return String.valueOf(sum);
    }
}
