package ro.siit.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.siit.pms.entity.Property;
import ro.siit.pms.entity.User;
import ro.siit.pms.repository.UserRepository;
import ro.siit.pms.service.impl.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.siit.pms.service.model.CustomUserDetails;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public LoginController(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users/add")
    public String form(){
        return "users/add";
    }

    @PostMapping("/users/add")
    public String addUser(Model model,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password){
        userService.saveUser(username, password);
        return "redirect:/login/";
    }

    @GetMapping("/users/panel")
    public String list(Model model, Principal principal) {
        List<User> users = this.userRepository.findAll();
        model.addAttribute("users", users);

        return "users/list";
    }

    @GetMapping("/users/edit/{id}")
    public String editUsersForm(@PathVariable UUID id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable UUID id,
                                 @ModelAttribute("user") User user,
                                 Model model) {

        // get property from database by id
        User existingUser = userService.getUserById(id);
        existingUser.setUserName(user.getName());
        existingUser.setUserRole(user.getRole());

        // save updated property object
        userService.updateUser(existingUser);
        return "redirect:/users/panel";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return "redirect:/users/panel";
    }

    private CustomUserDetails getUserDetailsFromAuthentication(Principal principal) {
        return ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal());
    }

}

