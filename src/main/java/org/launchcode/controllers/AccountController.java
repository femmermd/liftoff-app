package org.launchcode.controllers;


import org.launchcode.models.User;
import org.launchcode.models.data.UserDao;
import org.launchcode.models.forms.LoginForm;
import org.launchcode.models.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("")
public class AccountController extends AbstractController {

    @Autowired
    private UserDao userDao;



    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title","Index");
        return "index";
    }

    @RequestMapping(value="register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("form", new RegisterForm());
        model.addAttribute("title", "Register a new user");
        return "register";
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public String regVerify(@ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request){

        if (errors.hasErrors()){
            return "register";
        }

        User existingUser= userDao.findByUsername(form.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            return "register";
        }

        User newUser = new User();
        newUser.setHometown(form.getHometown());
        newUser.setUsername(form.getUsername());
        newUser.setPassword(form.getPassword());
        newUser.setEmail(form.getEmail());
        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);
        return "redirect:";
    }



    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("form", new LoginForm());
        model.addAttribute("title","Login");


        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute @Valid LoginForm form, Errors errors, HttpServletRequest request){

        if (errors.hasErrors()){
            return "login";
        }



        User theUser = userDao.findByUsername(form.getUsername());
        String password = form.getPassword();
        if (theUser == null){
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            return "login";
        }

        if (!theUser.getPassword().contentEquals(form.getPassword())){
            errors.rejectValue("password","password.invalid", "Invalid password");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);
        return "redirect:";


/*
        List<User> userList = new ArrayList<>(); //initialize ArrayList for users
        Iterable<User> userIterable = userDao.findAll(); // user iterable filled with every user from the DAO

        for (User user : userIterable){
            userList.add(user);
        }
        System.out.println(userList); // add each user in the iterable to the userList
        for (User user : userList){
            if (user.getUsername().contentEquals(form.getUsername())){
                if (user.getPassword().contentEquals(form.getPassword())){

                    return "redirect:";
                }
            }
        }*/

        // search through the userList for the given username
        // if the username doesn't exist, return "wrong username or password" error
        // if it does exist, check if the password equals the user's password
        // if the passwords don't match, return "wrong username or password" error
        // if the passwords do match, add the user to the session and redirect to the index


       /* return "redirect:";*/
    }


}