package models;

public abstract class Person {

    protected final String name;
    protected final int age;
    protected final String email;
    
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void displayInfo() {
        System.out.println(
        "Name: " + name +
        "\nEmail: " + email +
        "\nAge: " + age);  
    }
}
