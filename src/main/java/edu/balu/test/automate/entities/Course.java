package edu.balu.test.automate.entities;

import edu.balu.test.automate.model.txa.TransactionAccount;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    String course;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "txa_id", nullable = false)
    private Transaction transaction;

    public Course() {
    }

    public Course(String course,Transaction transaction) {
        this.transaction = transaction;
        this.course =course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
