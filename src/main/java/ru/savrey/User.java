package ru.savrey;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
