package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by earlbozarth on 11/10/15.
 */

@Controller
public class BeerTrackerController {

    @Autowired
    BeerRepository beers;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("beers", beers.findAll());
        //beers.findAll() is a SELECT * FROM beers equivalent
        return "home";
    }//End of home

    @RequestMapping("/add-beer")
    public String addBeer(String beername, String beertype){
        Beer tempBeer = new Beer();
        tempBeer.name = beername;
        tempBeer.type = beertype;
        beers.save(tempBeer);
        return "redirect:/";
    }//End of add-beer

    @RequestMapping("/edit-beer")
    public String editBeer(Integer id, String name, String type){
        Beer beer = beers.findOne(id);
        beer.name = name;
        beer.type = type;
        beers.save(beer);
        return "redirect:/";

    }
}//End of BeerTrackerController
