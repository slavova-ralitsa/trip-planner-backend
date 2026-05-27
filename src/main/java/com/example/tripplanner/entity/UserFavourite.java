package com.example.tripplanner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_favourites",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "destination_id"})})

public class UserFavourite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_favourite_seq")
    @SequenceGenerator(
            name = "user_favourite_seq",
            sequenceName = "user_favourite_sequence",
            allocationSize = 50
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    public UserFavourite() {}

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

}
