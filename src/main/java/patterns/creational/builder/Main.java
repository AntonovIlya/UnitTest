package patterns.creational.builder;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Person dad = new PersonBuilder()
                .setName("Ilya")
                .setSurname("Ivanov")
                .setAge(99)
                .setCity("Moscow")
                .build();
        Person child = dad.newChildBuilder()
                .setName("Anna")
                .build();
        System.out.println("У " + dad + " есть дочь, " + child);

        try {
            new PersonBuilder().build();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            new PersonBuilder().setAge(-100).build();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
