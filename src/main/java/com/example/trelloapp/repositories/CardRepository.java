package com.example.trelloapp.repositories;

import com.example.trelloapp.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}