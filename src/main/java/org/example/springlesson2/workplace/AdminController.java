package org.example.springlesson2.workplace;

import org.example.springlesson2.exception.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {


    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        EmployeeService employeeService = null;
        employeeService.deleteById(id);
        return "redirect:/users";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(UserNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error-user-not-found"; // Du behöver skapa denna vy
    }


}
