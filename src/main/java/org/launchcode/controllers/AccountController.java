package org.launchcode.controllers;

import com.cloudinary.utils.ObjectUtils;
import org.hibernate.collection.internal.PersistentBag;
import org.launchcode.models.Objects.Comment;
import org.launchcode.models.Objects.Review;
import org.launchcode.models.Objects.User;
import org.launchcode.models.forms.CommentForm;
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
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("")
public class AccountController extends AbstractController {




    @RequestMapping(value="")
    public String index(Model model, HttpServletRequest request){
        model.addAttribute("title","Index");
        if (! (getUserFromSession(request.getSession()) == null)){
            model.addAttribute(getUserFromSession(request.getSession()));
        }

        ArrayList<Review> reviewList= new ArrayList<Review>();
        reviewDao.findAll().forEach(reviewList::add);


        ArrayList<Comment> commentList = new ArrayList<Comment>();
        commentDao.findAll().forEach(commentList::add);


        model.addAttribute("reviewList",reviewList);
        model.addAttribute("commentList",commentList);
        model.addAttribute("commentForm", new CommentForm());

        return "index";
    }

    @PostMapping(value="")
    public String index(@ModelAttribute @Valid CommentForm form, @RequestParam("reviewId") int reviewId, Errors errors, HttpServletRequest request){

        if (errors.hasErrors()){
            return "index";
        }

        if (getUserFromSession(request.getSession()) == null){
            return "redirect:login";
        }

        Comment comment = new Comment();
        comment.setReview(reviewDao.findById(reviewId));
        comment.setText(form.getText());
        comment.setUser(getUserFromSession(request.getSession()));
        commentDao.save(comment);
        Review review = reviewDao.findById(reviewId);
        ArrayList<Comment> commentList = new ArrayList<Comment>();

        if (! review.getComments().isEmpty()){
            review.getComments().addAll(commentList);}

        commentList.add(comment);
        Set<Comment> set = new HashSet(commentList);
        review.setComments(set);
        reviewDao.save(review);

        return "redirect:";
    }



    @RequestMapping(value="register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("form", new RegisterForm());
        model.addAttribute("title", "Register a new user");
        return "register";
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public String regVerify(@ModelAttribute @Valid RegisterForm form, @RequestParam("photo") MultipartFile photo, Errors errors, HttpServletRequest request) throws IOException {

        if (errors.hasErrors()){
            return "register";
        }

        User existingUser= userDao.findByUsername(form.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            return "register";
        }

        Map upload = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
        String photoId = upload.get("public_id").toString();

        User newUser = new User();
        newUser.setBio(form.getBio());
        newUser.setHometown(form.getHometown());
        newUser.setUsername(form.getUsername());
        newUser.setPassword(form.getPassword());
        newUser.setEmail(form.getEmail());
        newUser.setPhotoId(photoId);
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

    @GetMapping("/user/{username}")
    public String viewProfile(@PathVariable String username, Model model) throws IOException {

        User user = userDao.findByUsername(username);

        model.addAttribute("user", user);
        model.addAttribute("photoId",user.getPhotoId());

        ArrayList<Review> reviewList= new ArrayList<Review>();
        reviewDao.findAllByUserId(user.getId()).forEach(reviewList::add);

        model.addAttribute("reviewList",reviewList);



        return "profile";
    }

    @GetMapping("/profilephoto")
    public String profilePhoto(Model model){
        model.addAttribute("form", new PhotoForm());
        model.addAttribute("title", "Upload a profile picture");
        return "photo";
    }

/*    @PostMapping("/profilephoto")
    public String processProfilePhoto(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        User user = getUserFromSession(request.getSession());
        String id = upload.get("public_id").toString();
        user.setPhotoId(id);
        userDao.save(user);
        return "redirect:";
    }*/
}