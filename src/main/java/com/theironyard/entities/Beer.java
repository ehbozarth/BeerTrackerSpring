package com.theironyard.entities;

import javax.persistence.*;


@Entity
public class Beer {
    @Id
    @GeneratedValue
    Integer id;

    public String name;
    public String type;
    public Integer calories;

    @ManyToOne
    public User user;



}
