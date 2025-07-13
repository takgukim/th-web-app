package com.developerleetaehee.th_web_app.mapper;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TestMapper {
    List<Board> findAll(PageRequest pageRequest);
    Optional<Board> findById(Long idx);
    int insert(Board board);
    int update(Board board);
    int softDelete(Board board);
    int delete(Long idx);
}
