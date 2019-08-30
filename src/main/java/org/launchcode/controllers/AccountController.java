package org.launchcode.controllers;


import org.launchcode.models.User;
import org.launchcode.models.data.UserDao;
import org.launchcode.models.forms.LoginForm;
import org.launchcode.models.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping(value="")
public class AccountController {

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "/login")
    public String login(Model model){

        model.addAttribute("form", new LoginForm());
        model.addAttribute("title","Login");


        return "login";
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