package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.Role;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.UserDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.RoleService;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public String userDetails(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> fetchedUser = userService.getUserById(id);
        if (fetchedUser.isEmpty()) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/users";
        }
        User user = fetchedUser.get();
        Role role = user.getRoles().stream().findFirst().get();
        model.addAttribute("user", user);
        model.addAttribute("role", role.getAuthority());
        return "users/user-details";
    }

    @GetMapping()
    public String getAllUsers(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
            ){
        Pageable pagination = PageRequest.of(page,size);
        Page<User> pages = userService.getAllUsers(pagination);
        List<User> users = pages.stream().toList();

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i < pages.getTotalPages(); i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("users", users);
        model.addAttribute("pagination", pagination);
        model.addAttribute("currentPage", page);
        System.out.println("total page = " + pages.getTotalPages());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("lastPage", pages.getTotalPages() - 1);
        model.addAttribute("size",size);
        return "users/all-users-page";
    }

    @GetMapping("/add-user")
    public String addUserPage(Model model) {
        UserDto user = new UserDto();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "users/add-user-form";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") UserDto user) {
        int roleId = Integer.parseInt(user.getRoles());
        Role role = roleService.getRoleById(roleId).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User newUser = user.toUser(passwordEncoder,roles);
        userService.addUser(newUser);
        System.out.println(user);
        return "redirect:/users";
    }


    @GetMapping("/edit-user/{id}")
    public String editUserPage(@PathVariable long id, Model model, RedirectAttributes redirectAttributes){
        Optional<User> fetchedUser = userService.getUserById(id);
        if (fetchedUser.isEmpty()) {
            redirectAttributes.addAttribute("error",true);
            return "redirect:/users";
        }
        User user = fetchedUser.get();
        Role usrRole = user.getRoles().stream().findFirst().get();
        log.info(usrRole.toString());
        model.addAttribute("user",user);
        List<Role> allRoles = roleService.getAllRoles();
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("userRoleId", usrRole.getRoleId());
        //return "redirect:/users";
        return "users/edit-user-page";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        User fetchedUser = userService.getUserById(user.getId()).get();

        fetchedUser.setUsername(user.getUsername());
        fetchedUser.setName(user.getName());
        fetchedUser.setEmail(user.getEmail());
        fetchedUser.setRoles(user.getRoles());

        userService.updateUser(fetchedUser);

        System.out.println(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id, RedirectAttributes redirectAttributes){
        try {
            userService.deleteUser(id);
            redirectAttributes.addAttribute("deleted", true);
        }
        catch (Exception exception){
            exception.printStackTrace();
            redirectAttributes.addAttribute("error",true);
        }
        return "redirect:/users";
    }

}
