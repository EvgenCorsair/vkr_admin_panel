package ru.evgen.adminpanel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class MainController {
    @GetMapping(path = "/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping(path = "/users")
    public String users(Model model, Principal principal) {
        return "users";
    }
    @GetMapping(path = "/roles")
    public String roles(Model model, Principal principal) {
        return "roles";
    }
    @GetMapping(path = "/connect")
    public String connect(Model model, Principal principal) {
        return "connect";
    }
    @GetMapping(path = "/config")
    public String config(Model model, Principal principal) {
        return "config";
    }
    @GetMapping(path = "/control")
    public String control(Model modelб, Principal principal) {
        return "control";
    }
    @GetMapping(path = "/")
    public String index(Model model, Principal principal) {
        return "index";
    }

}
