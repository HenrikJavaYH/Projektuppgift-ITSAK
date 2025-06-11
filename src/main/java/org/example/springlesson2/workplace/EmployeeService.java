package org.example.springlesson2.workplace;

import org.example.springlesson2.dto.EmployeeDTO;
import org.example.springlesson2.util.LoggingComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggingComponent loggingComponent;


    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, LoggingComponent loggingComponent) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggingComponent = loggingComponent;
    }

    public void register(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setUsername(dto.getUsername());
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employee.setRole(dto.getRole());
        employee.setConsentGiven(dto.isConsentGiven());

        employeeRepository.save(employee);

        logger.info("Registrerad användare: {}", employee.getUsername());
        loggingComponent.logRegistration(employee.getUsername());
    }
    public void delete(String username) {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Användare hittades inte: " + username));

        employeeRepository.delete(employee);
        loggingComponent.logDeletion(username);
    }
}
