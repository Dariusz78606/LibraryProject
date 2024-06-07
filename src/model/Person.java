package model;

public abstract class Person {
    protected String username;

    public Person(String username) {
        if (username.isBlank()){
            this.username = "empty";
        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}