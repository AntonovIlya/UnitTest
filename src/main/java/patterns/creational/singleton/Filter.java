package patterns.creational.singleton;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    protected int treshold;

    public Filter(int treshold) {
        this.treshold = treshold;
    }

    public List<Integer> filterOut(List<Integer> source) {
        List<Integer> result = new ArrayList<>();
        Main.logger.log("Запускаем фильтрацию");
        for (Integer i : source) {
            Main.logger.log("Элемент \"" + i + (i > treshold ? "\"" : "\" не") + " подходит");
            if (i > treshold) result.add(i);
        }
        result = source.stream()
                .filter(x -> x > treshold)
                .toList();
        return result;
    }
}
