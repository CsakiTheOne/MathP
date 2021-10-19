package personapp.type;

public class Person {
    String name;
    int age;
    boolean female;

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void increaseAge() {
        age++;
    }

    public boolean isFemale() {
        return female;
    }

    public Person(String name, int age, boolean female) {
        this.name = name;
        this.age = age;
        this.female = female;
    }
}
