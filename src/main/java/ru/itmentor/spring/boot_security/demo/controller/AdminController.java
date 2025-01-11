package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/new")
    public String createUserForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "users/create";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, Model model) {
        try {
            userService.save(user);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("name", "error name", "Попробуйте другое имя");
            model.addAttribute("listRole", roleService.findAll());
            return "users/create";
        }
        return "redirect:/admin/";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        Optional<User> userById = userService.findById(id);

        if (userById.isPresent()) {
            model.addAttribute("user", userById.get());
            model.addAttribute("listRole", roleService.findAll());
            return "users/edit";
        } else {
            return "redirect:/admin/";
        }
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult, Model model) {
        try {
            userService.updateUser(user);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("name", "error name", "Попробуйте другое имя");
            model.addAttribute("listRole", roleService.findAll());
            user.setRoles(user.getRoles());
            return "users/edit";
        }
        return "redirect:/admin/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/";
    }
}