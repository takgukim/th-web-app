package com.developerleetaehee.th_web_app.service.board;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.board.AddBoardRequest;
import com.developerleetaehee.th_web_app.dto.board.BoardSearchRequest;
import com.developerleetaehee.th_web_app.dto.board.DeleteBoardRequest;
import com.developerleetaehee.th_web_app.dto.board.UpdateBoardRequest;
import com.developerleetaehee.th_web_app.repository.BoardRepository;
import com.developerleetaehee.th_web_app.specification.BoardSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> findAll(BoardSearchRequest boardSearchRequest) {

        int startPage = boardSearchRequest.getStartPage();
        int perPage = boardSearchRequest.getPerPage();

        Pageable pageable = PageRequest.of(startPage, perPage, Sort.by(Sort.Direction.DESC, "idx"));

        return boardRepository.findAll(BoardSpecification.search(boardSearchRequest), pageable);
    }

    public Board findById(long id) {
        return boardRepository.findByIdxAndDeleteDatetimeIsNull(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));
    }

    public Board save(AddBoardRequest request) {
        return boardRepository.save(request.toEntity());
    }

    @Transactional
    public Board update(long id, UpdateBoardRequest request) {
        Board board = this.findById(id);

        board.setWriter(request.getWriter());
        board.setSubject(request.getSubject());
        board.setContent(request.getContent());
        board.setUpdateUser(request.getUpdateUser());
        board.setReadCount(request.getReadCount());
        board.update(board);

        return board;
    }

    @Transactional
    public void softDelete(long id, DeleteBoardRequest request) {
        Board board = this.findById(id);

        board.setDeleteUser(request.getDeleteUser());
        board.setDeleteDatetime(LocalDateTime.now());
    }

    public void delete(Long idx) {
        this.findById(idx);

        boardRepository.deleteById(idx);
    }
}
