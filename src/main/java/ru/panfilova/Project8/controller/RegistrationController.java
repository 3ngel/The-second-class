package ru.panfilova.Project8.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.panfilova.Project8.dto.UserDto;
import ru.panfilova.Project8.service.UserService;
@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }
    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register"; // Возвращаем на форму с ошибками
        }
        userService.saveUser(user);
        model.addAttribute("success", true);
        return "redirect:/login";
    }
}