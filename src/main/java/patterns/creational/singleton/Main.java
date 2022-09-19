package patterns.creational.singleton;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static List<String> list;
    public static final Logger logger = Logger.getInstance();
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random r = new Random();

    public static void main(String[] args) {
        logger.log("Запускаем программу");
        logger.log("Просим пользователя ввести входные данные для списка");
        System.out.print("Введите размер списка: ");
        int N = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите верхнюю границу для значений: ");
        int M = Integer.parseInt(scanner.nextLine());
        logger.log("Создаём и наполняем список");
        List<Integer> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            list.add(r.nextInt(M));
        }
        System.out.print("Вот случайный список: ");
        System.out.println(list);
        logger.log("Просим пользователя ввести входные данные для фильтрации");
        System.out.print("Введите порог для фильтра: ");
        int f = Integer.parseInt(scanner.nextLine());
        Filter filter = new Filter(f);
        List<Integer> list1 = filter.filterOut(list);
        System.out.print("Отфильтрованный список: ");
        System.out.println(list1);
        logger.log("Завершаем программу");

    }
}
