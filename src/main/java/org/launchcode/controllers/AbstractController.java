package org.launchcode.controllers;

import org.apache.commons.io.IOUtils;
import org.launchcode.models.Objects.Photo;
import org.launchcode.models.Objects.User;
import org.launchcode.models.data.FileDao;
import org.launchcode.models.data.ReviewDao;
import org.launchcode.models.data.UserDao;
import org.launchcode.models.forms.PhotoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public abstract class AbstractController {

    @Autowired
    UserDao userDao;

    @Autowired
    ReviewDao reviewDao;

    @Autowired
    FileDao photoDao;

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

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
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





    @ModelAttribute("user")
    public User getUserForModel(HttpServletRequest request){
        return getUserFromSession(request.getSession());
    }

}

