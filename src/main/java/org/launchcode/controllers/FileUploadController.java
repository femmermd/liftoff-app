package org.launchcode.controllers;

import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController extends AbstractController{

    public static java.lang.String uploadDirectory = "C:\\MAMP\\db\\mysql\\liftoff@002dapp\\images"; // this can be any directory I choose to specify, if it doesn't work

    @RequestMapping("/upload")
    public java.lang.String uploadPage(Model model){

        return "viewUpload";
    }

    @PostMapping("/upload")
    public java.lang.String upload(Model model, @RequestParam("files")MultipartFile[] files) throws IOException {

        StringBuilder fileNames = new StringBuilder();

        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());

        }



        model.addAttribute("message", "Successfully uploaded files" + fileNames.toString());
        return "redirect:";


    }











}
