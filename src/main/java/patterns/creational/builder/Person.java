package patterns.creational.builder;

import java.util.Random;

public class Person {

    private final String name;
    private final String surname;
    private int age;
    private String city;
    private final boolean probabilityAge;
    private final boolean probabilityCity;

    public Person(String name, String surname, int age) {
        Random random = new Random();
        probabilityAge = random.nextInt(100) > 30;
        probabilityCity = random.nextInt(100) > 50;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public boolean hasAge() {
        return probabilityAge;
    }

    public boolean hasAddress() {
        return probabilityCity;
    }

    public void happyBirthday() {
        if (probabilityAge) age++;
    }

    public String getCity() {
        return city;
    }

    public void setAddress(String city) {
        if (probabilityCity) this.city = city;
    }

    public PersonBuilder newChildBuilder() throws IllegalAccessException {
        PersonBuilder childBuilder = new PersonBuilder()
                .setSurname(surname)
                .setCity(city)
                .setAge(age);
        return childBuilder;
    }

    @Override
    public String toString() {
        return name + " " + surname + " возрастом " + age + " из города " + (probabilityCity ? city : "*город неизвестен*");
    }
}
