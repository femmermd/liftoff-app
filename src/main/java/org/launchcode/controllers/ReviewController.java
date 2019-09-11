package org.launchcode.controllers;


import org.launchcode.models.data.ReviewDao;
import org.launchcode.models.forms.ReviewForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("review")
public class ReviewController extends AbstractController{

    @Autowired
    private ReviewDao reviewDao;


    @GetMapping("new")
    public String newReview(Model model){
        model.addAttribute("form", new ReviewForm());
        model.addAttribute("title", "New Review");
        return "reviewForm";
        }

}
