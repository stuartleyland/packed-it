package com.packedit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "http://localhost:8050")
public class LoginController {

    @RequestMapping(value = "/api/login")
    @ResponseBody
    public String login() {
        return "Login controller";
    }
}
