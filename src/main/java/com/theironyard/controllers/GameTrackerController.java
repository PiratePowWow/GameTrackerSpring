package com.theironyard.controllers;

import com.theironyard.entities.Game;
import com.theironyard.services.GameRepository;
import com.theironyard.entities.User;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by PiratePowWow on 3/8/16.
 */
@Controller
public class GameTrackerController {
    @Autowired
    GameRepository games;
    @Autowired
    UserRepository users;
    @PostConstruct
    public void init(){
        System.out.println("Started Up!");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String genre, Integer releaseYear, String platform, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", (User) session.getAttribute("user"));
        if(platform !=null){
            model.addAttribute("games", games.findByApproximatePlatform(platform));
        }
        else if (genre != null && releaseYear != null){
            model.addAttribute("games", games.findByUserAndGenreAndReleaseYear(genre, releaseYear, user));
        }
        else if(genre != null){
            model.addAttribute("games", games.findByUserAndGenre(genre, user));
        }
        else{
            model.addAttribute("games", games.findByUser(user));
        }
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, String gamePlatform, String gameGenre, int gameYear){
        Game game = new Game(gameName, gamePlatform, gameGenre, gameYear, (User) session.getAttribute("user"));
        games.save(game);
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        if(users.findUserByName(userName)==null){
            users.save(new User(userName, PasswordStorage.createHash(password)));
            session.setAttribute("user", users.findUserByName(userName));
        }
        else if(!PasswordStorage.verifyPassword(password, users.findUserByName(userName).getPasswordHash())){
            throw new Exception("Incorrect Password");
        }
        session.setAttribute("user", users.findUserByName(userName));
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
