package com.theironyard;

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
    Integer id;

    String name;
    String password;
    //Going to store the hash password


    @OneToMany(mappedBy = "user")//This is the name of the field in the Beer Class
    List<Beer> beers;

}//End of User Class
