package org.launchcode.controllers;


import org.launchcode.models.Objects.User;
import org.launchcode.models.data.UserDao;
import org.launchcode.models.forms.LoginForm;
import org.launchcode.models.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


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

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        if (!getUserFromSession(session).equals(null)) {
            removeUserFromSession(session);
        }

        session.removeAttribute("user_id");
        return "redirect:";
    }



}