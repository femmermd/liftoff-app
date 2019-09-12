package org.launchcode.controllers;


import org.launchcode.models.Objects.Review;
import org.launchcode.models.data.ReviewDao;
import org.launchcode.models.forms.ReviewForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("review")
public class ReviewController extends AbstractController{

    @Autowired
    private ReviewDao reviewDao;


    @GetMapping("new")
    public String newReview(Model model, HttpServletRequest request){

        if (getUserFromSession(request.getSession()) == null){
            return "redirect:/login";
        }

        model.addAttribute("form", new ReviewForm());
        model.addAttribute("title", "New Review");
        return "reviewForm";
        }

    @PostMapping("new")
    public String postReview(@ModelAttribute @Valid ReviewForm form, Errors errors, HttpServletRequest request){

        if (errors.hasErrors()){
            return "new";
        }

        Review newReview = new Review();

        newReview.setUser(getUserFromSession(request.getSession()));

        // Fix this! you're not putting your username into the review form, it should already have it.

        newReview.setCoffeeName(form.getCoffeeName());
        newReview.setDrinkType(form.getDrinkType());
        newReview.setOrigin(form.getOrigin());
        newReview.setRating(form.getRating());
        newReview.setBrewDevice(form.getBrewDevice());
        newReview.setRoaster(form.getRoaster());
        newReview.setCafe(form.getCafe());
        newReview.setFlavors(form.getFlavors());
        newReview.setRoast(form.getRoast());
        reviewDao.save(newReview);

        return "redirect:/review/" +newReview.getId();
    }



    @GetMapping("/{reviewId}")
    public String viewReview(@PathVariable int reviewId, Model model){

        model.addAttribute("review", reviewDao.findById(reviewId));
        return "viewReview";
    }











}