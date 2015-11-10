package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Beer {
    @Id
    @GeneratedValue
    Integer id;

    String name;
    String type;


}
