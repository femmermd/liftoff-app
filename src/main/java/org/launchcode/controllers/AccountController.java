package org.launchcode.controllers;


import org.launchcode.models.User;
import org.launchcode.models.data.UserDao;
import org.launchcode.models.forms.LoginForm;
import org.launchcode.models.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="")
public class AccountController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("form", new LoginForm());
        model.addAttribute("title","Login");


        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute @Valid LoginForm form, Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute(form);
            model.addAttribute("title","Login");
            return "login";
        }

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
        }

        // search through the userList for the given username
        // if the username doesn't exist, return "wrong username or password" error
        // if it does exist, check if the password equals the user's password
        // if the passwords don't match, return "wrong username or password" error
        // if the passwords do match, add the user to the session and redirect to the index


        return "redirect:";
    }



    @RequestMapping(value="register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("form", new RegisterForm());
        model.addAttribute("title", "Register a new user");
        return "register";
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public String regVerify(@ModelAttribute @Valid RegisterForm form, Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute(form);
            model.addAttribute("title","Register a new user");
            return "register";
        }

        User user = new User();
        user.setHometown(form.getHometown());
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        userDao.save(user);
        return "redirect:";
    }
}