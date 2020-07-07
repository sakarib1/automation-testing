package edu.balu.test.automate.model.student;

import java.util.Arrays;

public class Student {
    Long id;
    String firstName;
    String lastName;
    String email;
    String programme;
    String[] courses;

    public Student(){

    }

    public Student(Long id, String firstName, String lastName, String email, String programme, String[] courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.programme = programme;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                "; Name=" + firstName +
                ", " + lastName +
                '}';
    }
}
