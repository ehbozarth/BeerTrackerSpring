package com.theironyard;

import javax.persistence.*;


@Entity
public class Beer {
    @Id
    @GeneratedValue
    Integer id;

    String name;
    String type;
    Integer calories;

    @ManyToOne
    User user;



}
