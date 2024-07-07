package com.example.trelloapp.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "owner")
    private Set<Board> boards = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_boards",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id")
    )
    private Set<Board> accessibleBoards = new HashSet<>();

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    @JsonIgnore
    public boolean isUser() {
        return Role.USER.equals(this.role);
    }

    @JsonIgnore
    public boolean isManager() {
        return Role.MANAGER.equals(this.role);
    }

    @JsonIgnore
    public boolean isAdmin() {
        return Role.ADMIN.equals(this.role);
    }

}
