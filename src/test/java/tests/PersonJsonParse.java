package tests;

import java.util.List;

public class PersonJsonParse {
    public String firstName;
    public String lastName;
    public String gender;
    public int age;
    public List<PhoneNumbers> phoneNumbers;

    public static class PhoneNumbers {
        public String type;
        public String number;

    }
}