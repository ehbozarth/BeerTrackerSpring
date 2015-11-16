package com.theironyard.entities;

import javax.persistence.*;


@Entity
public class Beer {
    @Id
    @GeneratedValue
    @Column(nullable=false)
    Integer id;

    @Column(nullable=false)
    public String name;
    @Column(nullable=false)
    public String type;
    @Column(nullable=false)
    public Integer calories;

    @ManyToOne
    public User user;



}
