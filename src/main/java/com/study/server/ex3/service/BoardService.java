package com.study.server.ex3.service;

import com.study.server.ex3.domain.Board;
import com.study.server.ex3.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board writeBoard(Board inputtedBoard) {
        Board savedBoard = boardRepository.save(inputtedBoard);

        return savedBoard;
    }

    public Board readBoard(Long boardId) {
        return boardRepository.getOne(boardId);
    }

    public List<Board> readAllBoard() {
        return boardRepository.findAll();
    }
}
