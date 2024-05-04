package com.bella.bouche.controllers;

import com.bella.bouche.models.Account;
import com.bella.bouche.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class AuthController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Account account) {
        account.setCreatedAt(LocalDateTime.now());
        accountService.saveAccount(account);
        return "redirect:/";
    }
}
