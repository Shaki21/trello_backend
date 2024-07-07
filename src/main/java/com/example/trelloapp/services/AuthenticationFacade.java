package com.example.trelloapp.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;


public interface AuthenticationFacade {
    Authentication getAuthentication();
    UserDetails getCurrentUser();
}
