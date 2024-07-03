package com.example.trelloapp.models;

import jakarta.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private BoardList boardList;


    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public BoardList getBoardList() {
        return boardList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBoardList(BoardList boardList) {
        this.boardList = boardList;
    }
}

