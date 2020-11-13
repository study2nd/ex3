package com.study.server.ex3.controller;

import com.study.server.ex3.domain.Board;
import com.study.server.ex3.domain.Comment;
import com.study.server.ex3.service.BoardService;
import com.study.server.ex3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/board/{id}/comment")
    public String postComment(@PathVariable("id") Long boardId,
                              @ModelAttribute Comment inputtedComment,
                              HttpServletRequest request) {
        System.out.println(inputtedComment.toString());
        commentService.writeComment(inputtedComment, boardId);

        // 방법 1) 요청이 전달된 페이지의 주소를 활용하는 법
        return "redirect:" + request.getHeader("referer");
        // 방법 2) 데이터를 전달받아 사용하는 법
        // return "redirect:/board/" + boardId;
    }

    @PostMapping("/board/{board_id}/comment/{comment_id}/delete")
    public String deleteComment(/*@PathVariable("board_id") int boardId,*/
                                @PathVariable("comment_id") Long commentId,
                                HttpServletRequest request) {
        commentService.removeComment(commentId);

        return "redirect:" + request.getHeader("referer");
    }
}
