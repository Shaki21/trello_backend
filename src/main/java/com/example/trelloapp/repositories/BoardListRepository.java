package com.example.trelloapp.repositories;

import com.example.trelloapp.models.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardListRepository  extends JpaRepository<BoardList, Long> {
}
