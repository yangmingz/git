package Jdbc;

public class Student {
    private String Id;
    private String user_name;
    private String password;
    private String Age;

    Student(String user_name, String password, String Age) {
        this.Id = null; //default
        this.user_name = user_name;
        this.password = password;
        this.Age = Age;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getuser_name() {
        return user_name;
    }

    public void setuser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return Age;
    }

    public void setage(String Age) {
        this.Age = Age;
    }
}
