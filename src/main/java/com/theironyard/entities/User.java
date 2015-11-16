package com.theironyard.entities;

import com.theironyard.entities.Beer;

import javax.persistence.*;
import java.util.List;

/**
 * Created by earlbozarth on 11/11/15.
 */

//Cannot user USER as table Name

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(nullable=false)
    Integer id;

    @Column(nullable=false)
    public String name;
    @Column(nullable=false)
    public String password;
    //Going to store the hash password


    @OneToMany(mappedBy = "user")//This is the name of the field in the Beer Class
    public List<Beer> beers;

}//End of User Class
