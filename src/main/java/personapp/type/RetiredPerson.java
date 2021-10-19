package personapp.type;

public class RetiredPerson extends Person {
    int pension;

    public int getPension() {
        return pension;
    }

    public void setPension(int pension) {
        this.pension = pension;
    }

    public RetiredPerson(String name, int age, boolean female) {
        super(name, age, female);
    }
}
