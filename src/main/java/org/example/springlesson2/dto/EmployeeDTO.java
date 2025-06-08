package org.example.springlesson2.dto;

import jakarta.validation.constraints.*;

public class EmployeeDTO {

    @NotBlank(message = "Användarnamn får inte vara tomt")
    private String username;

    @NotBlank(message = "Roll får inte vara tom")
    private String role;

    @NotBlank(message = "Lösenord krävs")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=(?:.*\\d){2,})(?=(?:.*[!@#$%&*]){2,}).{8,}$",
            message = "Lösenordet måste ha minst 8 tecken, 1 versal, 2 siffror och 2 specialtecken (!@#$%&*)"
    )
    private String password;

    private boolean consentGiven;

    // Getters & Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isConsentGiven() { return consentGiven; }
    public void setConsentGiven(boolean consentGiven) { this.consentGiven = consentGiven; }
}

