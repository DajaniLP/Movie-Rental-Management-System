package models;

public abstract class Person {

    protected final String id;
    protected final String name;
    protected final int age;
    protected final String email;
    
    public Person(String id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getId() {
        return id;
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
        "ID: " + id +
        "\nName: " + name +
        "\nEmail: " + email +
        "\nAge: " + age);  
    }
}
