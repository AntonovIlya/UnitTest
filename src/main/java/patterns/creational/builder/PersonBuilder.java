package patterns.creational.builder;

public class PersonBuilder {

    private String name;
    private String surname;
    private int age;
    private String city;

    public PersonBuilder() {
    }

    public PersonBuilder setName(String name) {
        if (this.name == null) this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        if (this.surname == null) this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) throws IllegalAccessException {
        if (age <= 0) throw new IllegalAccessException("Указан неверный возраст");
        if (this.age == 0) this.age = age;
        return this;
    }

    public PersonBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public Person build() throws IllegalAccessException {
        if (name == null || surname == null || age == 0)
            throw new IllegalAccessException("Не хватает обязательных полей");
        Person person = new Person(name, surname, age);
        person.setAddress(city);
        return person;
    }

}
