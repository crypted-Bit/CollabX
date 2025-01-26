package com.example.SCM.small.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/index")
    public String index() {
        return "user_dashboard";
    }
    @GetMapping("/index1")
    public String index1() {
        return "user_newboard";
    }
}
