package com.study.server.ex3.service;

import com.study.server.ex3.domain.Board;
import com.study.server.ex3.domain.Comment;
import com.study.server.ex3.repository.BoardRepository;
import com.study.server.ex3.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;

    public Comment writeComment(Comment inputtedComment, Long boardId) {
        Board board = boardRepository.getOne(boardId);
        inputtedComment.setBoard(board);

        Comment savedComment = commentRepository.save(inputtedComment);

        return savedComment;
    }

    public void removeComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
