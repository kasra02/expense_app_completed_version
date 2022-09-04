package com.example.demo.controller;


import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping({"/","/login"})
    public String showLoginPage()
    {
        return "login";
    }


    @GetMapping("/register")
    public String showForm(Model model)
    {
        model.addAttribute("user",new UserDTO());
        return "register";
    }

    @PostMapping ("/register")
    public String processForm(@Valid @ModelAttribute("user") UserDTO userDTO , BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "register";
        }
        userService.save(userDTO);
        model.addAttribute("successMsg",true);
        return "login";
    }
}
