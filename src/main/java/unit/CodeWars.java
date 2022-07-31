package unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CodeWars {

    /*XO("ooxx") => true
    XO("xooxx") => false
    XO("ooxXm") => true
    XO("zpzpzpp") => true // when no 'x' and 'o' is present should return true
    XO("zzoo") => false*/
    public static boolean exesAndOhs(String string) {
        String[] array = string.toLowerCase().split("");
        int countX = 0;
        int countO = 0;
        for (String s : array) {
            if (s.equals("x")) countX++;
            if (s.equals("o")) countO++;
        }
        return countO == countX;
    }

    public static List filterList(List list) {
        return (List) list.stream()
                .filter(o -> o.getClass().getSimpleName().equals("Integer"))
                .collect(Collectors.toList());
    }

    /*strange_math(11, 2) == 4
    strange_math(15, 5) == 11
    strange_math(15, 15) == 7*/
    public static int mathematics(int n, int k){
        List<String> list = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            list.add(String.valueOf(i));
        }
        Collections.sort(list);
        return list.indexOf(String.valueOf(k)) + 1;
    }

    public static String plant(char seed, int water, int fert, int temp){
        StringBuilder sb = new StringBuilder();
        boolean temperature = temp >= 20 && temp <= 30 ;
        for (int i = 0; i < water; i++) {
            for (int j = 0; j < water; j++) {
                sb.append("-");
            }
            if (temperature) {
                for (int j = 0; j < fert; j++) {
                    sb.append(seed);
                }
            }
        }
        if (!temperature) sb.append(seed);
        return sb.toString();
    }

    public static int rowSumOddNumbers(int n) {
        int a = (n * n - n + 2) / 2;
        int sum = 0;
        int a1 = 2 * a - 1;
        int a2 = 2 * (a + n - 1) - 1;
        for (int i = a1; i <= a2; i = i + 2) {
            sum += i;
        }
        return sum;
    }


}
