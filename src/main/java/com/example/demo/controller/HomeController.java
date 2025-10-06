package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OidcUser user, Model model) {
        if (user != null) {
            String accessToken = user.getIdToken().getTokenValue();
            System.out.println("ID Token: " + accessToken);
            model.addAttribute("username", user.getPreferredUsername());
            model.addAttribute("email", user.getEmail());
        }
        return "home";
    }
}
