package com.theironyard;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BeerRepository extends CrudRepository<Beer, Integer>{

    List<Beer> findByType(String type);

    List<Beer> findByTypeAndCalories(String type, Integer calories);

    List<Beer> findByTypeAndCaloriesIsLessThanEqual(String type, Integer calories);

    Beer findFirstByType(String type);
    //Find first beer type

    int countByType(String type);
    //Count the number of each beer type

    List<Beer> findByTypeOrderByNameAsc(String type);
    //Order by Ascending order

    @Query("SELECT b FROM Beer b WHERE LOWER(name) LIKE '%' || LOWER(?) || '%'")
    List<Beer> searchByName(String name);
    //Creating our own SQL statement because (searchBy does not exist)
    //Make sure to have table name Capitalized










}//End of BeerRepository
