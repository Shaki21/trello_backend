package com.example.trelloapp.services;

import com.example.trelloapp.models.User;
import com.example.trelloapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.AccessDeniedException;


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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsername(currentUsername);

        if (currentUser == null) {
            return null; // ili baci izuzetak
        }

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            // Ako je trenutni korisnik admin, može mijenjati sve korisnike
            if (currentUser.isAdmin()) {
                user.setUsername(userDetails.getUsername());
                user.setPassword(userDetails.getPassword());
                user.setRole(userDetails.getRole());
                return userRepository.save(user);
            } else if (currentUser.getId().equals(user.getId())) {
                // Ako je trenutni korisnik isti kao korisnik kojeg želi ažurirati
                user.setUsername(userDetails.getUsername());
                user.setPassword(userDetails.getPassword());
                return userRepository.save(user);
            } else {
                // Odbaci pristup ako nije admin i pokušava ažurirati tuđe podatke
                throw new AccessDeniedException("You do not have permission to update this user");
            }
        }

        return null;
    }


    public void     deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
