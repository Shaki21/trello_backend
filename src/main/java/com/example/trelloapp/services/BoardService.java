package com.example.trelloapp.services;

import com.example.trelloapp.models.Board;
import com.example.trelloapp.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }



    public Board updateBoard(Long id, Board boardDetails) {
        Board board = boardRepository.findById(id).orElse(null);

        if (board != null) {
            board.setName(boardDetails.getName());
            // Update other properties if necessary
            return boardRepository.save(board);
        }

        return null;
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
