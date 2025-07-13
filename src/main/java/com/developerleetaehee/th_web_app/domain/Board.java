package com.developerleetaehee.th_web_app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "th_board")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, length = 30)
    private String writer;

    @Column(nullable = false, length = 100)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(name = "write_date", nullable = false, updatable = false, length = 8)
    private String writeDate;

    @Column(name = "register_datetime", nullable = false, updatable = false)
    private LocalDateTime registerDatetime;

    @Column(name = "update_datetime")
    private LocalDateTime updateDatetime;

    @Column(name = "update_user", length = 30)
    private String updateUser;

    @Column(name = "delete_datetime")
    private LocalDateTime deleteDatetime;

    @Column(name = "delete_user", length = 30)
    private String deleteUser;

    public void update(Board board) {
        this.writer = board.getWriter();
        this.subject = board.getSubject();
        this.content = board.getContent();
        this.updateUser = board.getUpdateUser();
        this.updateDatetime = LocalDateTime.now();
    }

    @Builder
    public Board(String writer, String subject, String content) {
        this.writer = writer;
        this.subject = subject;
        this.content = content;
    }

    @PrePersist
    protected void onCreate() {
        LocalDate nowDate = LocalDate.now();
        String writeDate = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.registerDatetime = LocalDateTime.now();
        this.writeDate = writeDate;
    }
}
