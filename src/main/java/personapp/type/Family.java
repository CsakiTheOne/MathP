package personapp.type;

public class Family {
    Person mother, father;
    Person[] children;

    public Person getMother() {
        return mother;
    }

    public Person getFather() {
        return father;
    }

    public Person[] getChildren() {
        return children;
    }

    public Family(Person mother, Person father, Person[] children) {
        this.mother = mother;
        this.father = father;
        this.children = children;
    }
}
