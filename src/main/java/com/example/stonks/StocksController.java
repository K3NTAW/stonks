package com.example.stonks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class StocksController {

    private StockDao stockDao;

    private static Logger log = Logger.getLogger(StockDao.class.getSimpleName());
    private static final long serialVersionUID = 1L;

    @GetMapping("/")
    public String stockForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "login";
    }

    @PostMapping("/dashboard")
    public String subbmit(Model model, @ModelAttribute UserModel profile) {
        profile.connection();
        try {
            stockDao = new StockDao();
            stockDao.getCustomer(1);
        } catch (Exception ex){
            System.out.println("sss");
        }


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
    @PostMapping("/portfolio")
    public String portfolio(Model model, @ModelAttribute UserModel profile) {
        return "portfolio";
    }
    @GetMapping("/portfolio")
    public String portfolioForm(Model model, @ModelAttribute UserModel profile) throws SQLException {
        model.addAttribute("userModel", profile);
        List<UserModel> customers = new ArrayList<>();
        if (stockDao != null) {
            customers = (List<UserModel>) stockDao.getCustomer(1);
            log.info("getCustomers");
        }
        model.addAttribute("customers", stockDao.getCustomer(1));
        model.addAttribute("customers", customers);
        return "portfolio";
    }

}
