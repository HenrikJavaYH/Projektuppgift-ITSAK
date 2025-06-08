package org.example.springlesson2.workplace;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsservice implements UserDetailsService {

    public EmployeeRepository employeeRepository;

    public MyUserDetailsservice(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found");
        }
        return new org.springframework.security.core.userdetails.User(
                employee.getUsername(),
                employee.getPassword(),
                true, true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_" + employee.getRole()))
        );


    }
}
