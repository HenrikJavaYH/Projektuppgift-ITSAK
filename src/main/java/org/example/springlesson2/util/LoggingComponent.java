package org.example.springlesson2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingComponent {

    private static final Logger logger = LoggerFactory.getLogger(LoggingComponent.class);

    public void logRegistration(String username) {
        logger.info("Registrerad användare: {}", username);
    }

    public void logDeletion(String username) {
        logger.info("Borttagen användare: {}", username);
    }
}