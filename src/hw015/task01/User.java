package hw015.task01;

import java.util.Objects;

public class User {
    private String login;
    private String pwd;
    private Role role;
    private int age;

    public User(String login, String pwd, Role role, int age) {
        this.login = login;
        this.pwd = pwd;
        this.role = role;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(login, user.login) && Objects.equals(pwd, user.pwd) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, pwd, role, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                ", role=" + role +
                ", age=" + age +
                '}';
    }

    public enum Role {
        USER, ADMIN
    }

}
