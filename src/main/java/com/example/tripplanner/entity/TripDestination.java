package com.example.tripplanner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trip_destination")
public class TripDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_destination_seq")
    @SequenceGenerator(
            name = "trip_destination_seq",
            sequenceName = "trip_destination_sequence",
            allocationSize = 50
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false, insertable = false, updatable = false)
    private Destination destination;

    @Column(nullable = false)
    private int dayIndex;

    public TripDestination() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }
}
