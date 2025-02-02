package com.dzakabluk.spring_mvc_hibernate.controller;

import com.dzakabluk.spring_mvc_hibernate.entity.User;
import com.dzakabluk.spring_mvc_hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model) {

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return "all_users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "user_info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/";
    }

    @GetMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user_info";

    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
