package multithreading.snhrnzd.task1;

import java.util.ArrayList;
import java.util.List;

public class Showroom {

    public static final int QUANTITY = 10;

    private final List<Car> cars = new ArrayList<Car>(QUANTITY);

    public void add() {
        synchronized (cars) {
            cars.add(new Car("Toyota"));
            System.out.println(Thread.currentThread().getName() + " поставляет 1 новый автомобиль");
            cars.notify();
        }
    }

    public void get() {
        System.out.println(Thread.currentThread().getName() + " зашёл в автосалон");
        synchronized (cars) {
            if (cars.size() < 1) {
                System.out.println(Thread.currentThread().getName() + " придётся подождать, автомобилей нет");
                try {
                    cars.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cars.remove(0);
        }
        System.out.println(Thread.currentThread().getName() + " покупает новый автомобиль!");
    }
}
