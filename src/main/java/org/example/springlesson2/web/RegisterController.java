package org.example.springlesson2.web;

import jakarta.validation.Valid;
import org.example.springlesson2.dto.EmployeeDTO;
import org.example.springlesson2.workplace.Employee;
import org.example.springlesson2.workplace.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RegisterController {

    private final EmployeeService employeeService;

    public RegisterController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        System.out.println("Registrerar användare: " + employeeDTO.getUsername());  // Debug

        employeeService.register(employeeDTO);
        return "redirect:/login";
    }

    // TEST: Visa alla registrerade användare
    @GetMapping("/users")
    @ResponseBody
    public List<Employee> getAllUsers() {
        return employeeService.getAll();
    }
}
