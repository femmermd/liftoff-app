package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="")
public class IndexController {

    @RequestMapping(value="")
    public String index(){
       return "Hello World";
    }
}