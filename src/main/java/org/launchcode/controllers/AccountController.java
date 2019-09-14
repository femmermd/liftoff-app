package org.launchcode.controllers;


import org.apache.commons.io.IOUtils;
import org.launchcode.models.Objects.Photo;
import org.launchcode.models.Objects.User;
import org.launchcode.models.forms.LoginForm;
import org.launchcode.models.forms.PhotoForm;
import org.launchcode.models.forms.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("")
public class AccountController extends AbstractController {




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
    public String regVerify(@ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request) throws IOException {

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

    @GetMapping("/photo")
    public String photo(Model model){
        model.addAttribute("form", new PhotoForm());
        model.addAttribute("title", "Upload a profile picture");
        return "photo";
    }

    @PostMapping("/photo")
    public String processPhoto (@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        Photo newPhoto = new Photo();

        FileInputStream targetStream = new FileInputStream(convert(file)) {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

        byte[] byteArray = IOUtils.toByteArray(targetStream);
        newPhoto.setPhoto(byteArray);
        newPhoto.setUser(getUserFromSession(request.getSession()));
        photoDao.save(newPhoto);
        return "redirect:/";
    }


}