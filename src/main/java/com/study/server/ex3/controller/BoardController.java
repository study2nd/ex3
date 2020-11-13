package com.study.server.ex3.controller;

import com.study.server.ex3.domain.Board;
import com.study.server.ex3.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping({"", "/", "/board"})
    public ModelAndView getIndex() {
        List<Board> boardList = boardService.readAllBoard();

        ModelAndView response = new ModelAndView("board/index");
        response.addObject(boardList);

        return response;
    }

    @PostMapping("/board")
    public String postBoard(@ModelAttribute Board inputtedBoard) {
        Board savedBoard = boardService.writeBoard(inputtedBoard);

        return "redirect:/board/" + savedBoard.getBoardId();
    }

    @GetMapping("/board/{id}")
    public ModelAndView getPost(@PathVariable("id") Long boardId) {
        Board board = boardService.readBoard(boardId);

        System.out.println(board.getComments().toString());
        ModelAndView response = new ModelAndView("board/post");
        response.addObject(board);

        return response;
    }
}
