package com.curs.admin_panel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    @GetMapping(path = "/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping(path = "/users")
    public String users(Model model) {
        return "users";
    }
    @GetMapping(path = "/roles")
    public String roles(Model model) {
        return "roles";
    }
    @GetMapping(path = "/connect")
    public String connect(Model model) {
        return "connect";
    }
    @GetMapping(path = "/config")
    public String config(Model model) {
        return "config";
    }
    @GetMapping(path = "/control")
    public String control(Model model) {
        return "control";
    }
    @GetMapping(path = "/")
    public String index(Model model) {
        return "index";
    }

}
