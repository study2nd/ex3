package com.study.server.ex3.domain;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Board getBoard() {
        return board;
    }

    // Comment를 등록할 때, 어느 게시판에 속할 것인지 지정
    public void setBoard(Board board) {
        // 예) 2번 댓글, 2번 댓글이 속하는 게시판을 지정
        this.board = board;
        // 예) 속하는 게시판이 3번 게시판이면,
        // 3번 게시판이 갖고 있는 댓글 목록
        // 이 댓글 목록에 나(2번 댓글)이 이미 포함되어 있으면
        // 댓글 목록에 추가 하지 않음
        // 없으면, 댓글 목록에 추가
        if (!board.getComments().contains(this)) {
            board.getComments().add(this);
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
