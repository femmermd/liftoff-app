package org.launchcode.controllers;

import org.launchcode.models.Objects.User;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    @Autowired
    UserDao userDao;

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

