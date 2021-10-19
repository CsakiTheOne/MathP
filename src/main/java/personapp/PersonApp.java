package personapp;

import personapp.type.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PersonApp {
    private static final String OUTPUT_FILENAME = "output.txt";

    public static void main(String[] args) {
        new PersonApp().start();
    }

    void start() {
        Person person1 = new Person("John", 40, false);

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            input.close();
            int a = Integer.parseInt(input.readLine());
            System.out.println("a = " + a);
        }
        catch (IOException ex) {
        }
    }
}
