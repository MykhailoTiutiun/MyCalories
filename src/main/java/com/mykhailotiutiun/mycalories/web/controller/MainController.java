package com.mykhailotiutiun.mycalories.web.controller;

import com.mykhailotiutiun.mycalories.persistence.entities.User;
import com.mykhailotiutiun.mycalories.services.DietService;
import com.mykhailotiutiun.mycalories.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.transform.sax.SAXResult;

@Controller
@RequestMapping("/")
public class MainController {

    private final DietService dietService;

    public MainController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("diet", dietService.getDietByUser(user).get());
        return "main.html";
    }

}
