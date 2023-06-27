package ru.Filatov.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name ="user")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    @NotEmpty(message = "Name shouldn't be empty!")
    @Size(min = 3, message = "Minimal name size is 3")
    String name;
    @Column
    @NotEmpty(message = "Surname shouldn't be empty!")
    String surname;
    @Column
    @NotEmpty(message = "Email shouldn't be empty!")
    @Email(message = "Email should be valid!")
    String email;
    @Column
    @NotEmpty(message = "Sex shouldn't be empty!")
    String sex;

    public void setId(int id) {
        this.id = id;
    }

    public User(String name, String surname, String email, String sex) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + email +
                ", sex=" + sex +
                '}';
    }
}
