package com.example.SCM.small.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SCM.helper.Message;
import com.example.SCM.small.Dao.UserRepository;
import com.example.SCM.small.entities.User;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class HomeController {
	
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;
   
 
    
    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "HomePage-SmartContactManager");
        return "home";
    }
    
    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "AboutPage-SmartContactManager");
        return "about";
    }
    
    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register-SmartContactManager");
        model.addAttribute("user", new User());
        return "signup";
    }
    
   

    @PostMapping("/do_register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result1, Model model, HttpSession session, @RequestParam(value="agreement", defaultValue ="false") boolean agreement) {
        try {
            if (!agreement) {
                throw new Exception("You have not agreed to the terms and conditions");
            }
            if (result1.hasErrors()) {
                model.addAttribute("user", user);
                return "signup";
            }
            
            // Check if email is already registered
            if (userRepo.findByEmail(user.getEmail()) != null) {
                model.addAttribute("user", user);
                model.addAttribute("message", new Message("Email already registered", "alert-danger"));
                return "signup";
            }

            // Check if passwords match
            if (!user.getPassword().equals(user.getRepeatPassword())) {
                model.addAttribute("user", user);
                model.addAttribute("message", new Message("Passwords do not match", "alert-danger"));
                return "signup";
            }

            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("calling.jpg");
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User result = this.userRepo.save(user);
            model.addAttribute("user", new User());
            model.addAttribute("message", new Message("Successfully Registered!!", "alert-success"));

           
            return "signup";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            model.addAttribute("message", new Message("Something went wrong " + e.getMessage(), "alert-danger"));
            return "signup";
        }
    }

    
    


    @GetMapping("/signin")
    public String login(Model model) {
        model.addAttribute("title", "Login Page");
        return "login";
    }
}
