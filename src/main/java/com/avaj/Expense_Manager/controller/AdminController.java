package com.avaj.Expense_Manager.controller;

import com.avaj.Expense_Manager.entity.FeedBack;
import com.avaj.Expense_Manager.entity.Role;
import com.avaj.Expense_Manager.entity.User;
import com.avaj.Expense_Manager.service.FeedBackService;
import com.avaj.Expense_Manager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private FeedBackService feedBackService;

    public AdminController(UserService userService, FeedBackService feedBackService) {
        this.userService = userService;
        this.feedBackService = feedBackService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model theModel){
        List<User> users = userService.getAllUsers();
        List<FeedBack> feedBacks = feedBackService.getFeedBack();
        theModel.addAttribute("users",users);
        theModel.addAttribute("feedBacks",feedBacks);
        return "dashboard";
    }
    @GetMapping("/updateRole")
    public String showUpdateRoleForm(@RequestParam("userId") Long userId,Model theModel){
        Role role = new Role();
        User user = userService.getUserById(userId);
        theModel.addAttribute("user",user);
        theModel.addAttribute("role",role);
        return "updateRole";
    }
    @PostMapping("/updateRole")
    public String processUpdateRole(@ModelAttribute("user") User user,@ModelAttribute("role") Role role){
        userService.updateRole(user,role);
        return "redirect:/dashboard";
    }
}
