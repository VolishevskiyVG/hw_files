package tests;

import java.util.List;

public class PersonJsonParse {
    public String firstName;
    public String lastName;
    public String gender;
    public int age;
    public List<phoneNumbers> phoneNumbers;
    public static class  phoneNumbers {
        public String type;
        public String number;

    }
}