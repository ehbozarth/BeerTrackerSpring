package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by earlbozarth on 11/10/15.
 */

@Controller
public class BeerTrackerController {

    @Autowired
    BeerRepository beers;
    @Autowired
    UserRepository users;


    //Creates default User and user name once SPRING has sprung
    @PostConstruct
    public void init(){
        User user = users.findOneByName("Default name");
        if(user == null){
            user = new User();
            user.name = "Default Name";
            users.save(user);
        }
    }


    @RequestMapping("/")
    public String home(
            HttpServletRequest request,
            Model model,
            String type,
            Integer calories,
            String search,
            String showMine
    ) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username == null){
            return "login";
        }
        if(showMine != null){
            model.addAttribute("beers", users.findOneByName(username).beers);
        }
        else if(search != null){
            model.addAttribute("beers", beers.searchByName(search));
        }
        else if (type != null && calories != null) {
            model.addAttribute("beers", beers.findByTypeAndCaloriesIsLessThanEqual(type, calories));
        } else if (type != null) {
            model.addAttribute("beers", beers.findByTypeOrderByNameAsc(type));
        } else {
            model.addAttribute("beers", beers.findAll());
            //beers.findAll() is a SELECT * FROM beers equivalent
        }
        return "home";
    }//End of home

    @RequestMapping("/add-beer")
    public String addBeer(String beername, String beertype, Integer beercalories, HttpServletRequest request){
        //create and use session to help join values from different tables
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user = users.findOneByName(username);
        Beer tempBeer = new Beer();
        tempBeer.name = beername;
        tempBeer.type = beertype;
        tempBeer.user = user;
        tempBeer.calories = beercalories;

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

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username){
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        User user = users.findOneByName(username);
        if(user == null){
            user = new User();
            user.name = username;
            users.save(user);
        }

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }



}//End of BeerTrackerController
