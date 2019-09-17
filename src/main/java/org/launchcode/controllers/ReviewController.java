package org.launchcode.controllers;


import com.cloudinary.utils.ObjectUtils;
import org.launchcode.models.Objects.Review;
import org.launchcode.models.Objects.User;
import org.launchcode.models.data.ReviewDao;
import org.launchcode.models.forms.PhotoForm;
import org.launchcode.models.forms.ReviewForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
@RequestMapping("review")
public class ReviewController extends AbstractController{


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
    public String postReview(@ModelAttribute @Valid ReviewForm form, @RequestParam("photo") MultipartFile photo, Errors errors, HttpServletRequest request) throws IOException {

        if (errors.hasErrors()){
            return "reviewForm";
        }

        Map upload = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
        String photoId = upload.get("public_id").toString();

        Review newReview = new Review();
        newReview.setUser(getUserFromSession(request.getSession()));
        newReview.setCoffeeName(form.getCoffeeName());
        newReview.setDrinkType(form.getDrinkType());
        newReview.setOrigin(form.getOrigin());
        newReview.setRating(form.getRating());
        newReview.setBrewDevice(form.getBrewDevice());
        newReview.setRoaster(form.getRoaster());
        newReview.setCafe(form.getCafe());
        newReview.setFlavors(form.getFlavors());
        newReview.setRoast(form.getRoast());
        newReview.setPhotoId(photoId);

        reviewDao.save(newReview);

        return "redirect:/review/" +newReview.getId();
    }


    @PostMapping("/reviewphoto")
    public String processReviewPhoto(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        User user = getUserFromSession(request.getSession());
        String id = upload.get("public_id").toString();
        user.setPhotoId(id);
        userDao.save(user);
        return "redirect:";
    }


    @GetMapping("/{reviewId}")
    public String viewReview(@PathVariable int reviewId, Model model){

        model.addAttribute("review", reviewDao.findById(reviewId));
        return "viewReview";
    }


}