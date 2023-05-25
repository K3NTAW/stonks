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
        model.addAttribute("stocksModel", new StocksModel());
        return "login";
    }

    @PostMapping("/dashboard")
    public String subbmit(Model model, @ModelAttribute StocksModel profile) {
        profile.connection();
        if(profile.valid())
        {
            return "dashboard";
        } else {
            return "login";
        }
    }
    @GetMapping("/dashboard")
    public String dashboardForm(Model model, @ModelAttribute StocksModel profile) {
        model.addAttribute("stocksModel", profile);
        return "dashboard";
    }
}
