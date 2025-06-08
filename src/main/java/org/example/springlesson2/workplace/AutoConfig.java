package org.example.springlesson2.workplace;


import jakarta.annotation.PostConstruct;
import org.example.springlesson2.dto.EmployeeDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
            employeeRepository.save(employee);
        }
    }

    @Service
    public static class EmployeeService {

        private final EmployeeRepository employeeRepository;
        private final PasswordEncoder passwordEncoder;

        public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
            this.employeeRepository = employeeRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public void register(EmployeeDTO dto) {
            Employee employee = new Employee();
            employee.setUsername(dto.getUsername());
            employee.setPassword(passwordEncoder.encode(dto.getPassword()));
            employee.setRole(dto.getRole());
            employee.setConsentGiven(dto.isConsentGiven());

            employeeRepository.save(employee);
        }
    }
}
