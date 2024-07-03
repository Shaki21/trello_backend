package com.example.trelloapp.models;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;  // U stvarnoj aplikaciji, lozinke treba da budu heširane.

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


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Set<Board> getBoards() {
        return boards;
    }

    public Set<Board> getAccessibleBoards() {
        return accessibleBoards;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setBoards(Set<Board> boards) {
        this.boards = boards;
    }

    public void setAccessibleBoards(Set<Board> accessibleBoards) {
        this.accessibleBoards = accessibleBoards;
    }
}
