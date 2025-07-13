package com.developerleetaehee.th_web_app.service;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.AddBoardRequest;
import com.developerleetaehee.th_web_app.dto.PageRequest;
import com.developerleetaehee.th_web_app.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestMapper boardMapper;

    public List<Board> findAll(int startPage, int perPage) {
        int offset = startPage * perPage;

        PageRequest pageRequest = new PageRequest(offset, perPage);

        List<Board> boards = boardMapper.findAll(pageRequest);

        return boards;
    }

    public Board findById(long id) {
        return boardMapper.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));
    }

    public Board save(Board board) {
        LocalDate nowDate = LocalDate.now();
        board.setWriteDate(nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        boardMapper.insert(board);

        return board;
    }

    public Board update(long id, Board updateBoard) {
        Board board = this.findById(id);

        board.setWriter(updateBoard.getWriter());
        board.setSubject(updateBoard.getSubject());
        board.setContent(updateBoard.getContent());
        board.setUpdateUser(updateBoard.getUpdateUser());

        board.update(updateBoard);

        boardMapper.update(board);

        return board;
    }

    public void softDelete(long id, Board deleteBoard) {
        Board board = this.findById(id);

        board.setDeleteUser(deleteBoard.getDeleteUser());
        board.setDeleteDatetime(LocalDateTime.now());

        boardMapper.softDelete(board);
    }

    public void delete(Long id) {
        Board board = this.findById(id);

        boardMapper.delete(id);
    }

}
