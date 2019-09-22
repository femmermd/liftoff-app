package org.launchcode.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.launchcode.models.Objects.User;
import org.launchcode.models.data.CommentDao;
import org.launchcode.models.data.ReviewDao;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public abstract class AbstractController {

    @Autowired
    UserDao userDao;

    @Autowired
    ReviewDao reviewDao;

    @Autowired
    CommentDao commentDao;

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dzpvkjnfe",
            "api_key", "466638955192594",
            "api_secret", "1JlouVrBRX0ZjJ2TfBBGK80kfb8"));

    public static final String userSessionKey = "user_id";

    public User getUserFromSession (HttpSession session){
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : userDao.findById(userId).orElse(null);
    }

    public void setUserInSession(HttpSession session, User user){
        session.setAttribute(userSessionKey, user.getId());
    }

    public void removeUserFromSession (HttpSession session){
        session.removeAttribute("user_id");
    }

    @ModelAttribute("user")
    public User getUserForModel(HttpServletRequest request){
        return getUserFromSession(request.getSession());
    }

}

