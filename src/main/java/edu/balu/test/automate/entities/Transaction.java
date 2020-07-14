package edu.balu.test.automate.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TransactionAccount")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String programme;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
    private List<Course> courses;

    @Column(columnDefinition = "boolean default false")
    private boolean migrationStatus;

    @Column(columnDefinition = "boolean default false")
    private boolean successfulVerificationStatus;

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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public boolean isMigrationStatus() {
        return migrationStatus;
    }

    public void setMigrationStatus(boolean migrationStatus) {
        this.migrationStatus = migrationStatus;
    }

    public boolean isSuccessfulVerificationStatus() {
        return successfulVerificationStatus;
    }

    public void setSuccessfulVerificationStatus(boolean successfulVerificationStatus) {
        this.successfulVerificationStatus = successfulVerificationStatus;
    }
}
