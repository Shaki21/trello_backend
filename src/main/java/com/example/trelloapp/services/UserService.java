package com.example.trelloapp.services;

import com.example.trelloapp.models.User;
import com.example.trelloapp.repositories.UserRepository;
import com.example.trelloapp.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }

        return null;
    }

    public void deleteUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsername(currentUsername);

        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("You do not have permission to delete this user");
        }

        userRepository.deleteById(id);
    }
}
