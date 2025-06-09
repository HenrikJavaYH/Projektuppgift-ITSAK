package org.example.springlesson2.workplace;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AutoConfig {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public AutoConfig(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (employeeRepository.findByUsername("employee") == null) {
            Employee employee = new Employee();
            employee.setUsername("employee");
            employee.setPassword(passwordEncoder.encode("password"));
            employee.setRole("ADMIN");
            employee.setConsentGiven(true);
            employeeRepository.save(employee);
        }
    }
}
