package com.example.stonks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StocksController {
    @GetMapping("/")
    public String stockForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "login";
    }

    @PostMapping("/dashboard")
    public String subbmit(Model model, @ModelAttribute UserModel profile) {
        profile.connection();
        if(profile.valid())
        {
            return "dashboard";
        } else {
            return "login";
        }
    }
    @PostMapping("/Dashboard")
    public String createaccount(Model model, @ModelAttribute UserModel profile) {
        profile.connection();
        profile.create();
        return "dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboardForm(Model model, @ModelAttribute UserModel profile) {
        model.addAttribute("userModel", profile);
        return "dashboard";
    }
    @PostMapping("/register")
    public String create(Model model, @ModelAttribute UserModel profile) {
        return "register";
    }
    @GetMapping("/register")
    public String createForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "register";
    }
}
