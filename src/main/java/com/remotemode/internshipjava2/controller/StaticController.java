package com.remotemode.internshipjava2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {

    @RequestMapping("/")
    public String getLoginPage() {
        return "start";
    }

    @RequestMapping("/main")
    public String getMainPage() {
        return "main";
    }
}