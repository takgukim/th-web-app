package com.developerleetaehee.th_web_app.specification;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.dto.board.BoardSearchRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BoardSpecification {

    public static Specification<Board> search(BoardSearchRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isNull(root.get("deleteDatetime")));
            predicates.add(cb.equal(root.get("boardType"), request.getBoardType()));
            predicates.add(cb.greaterThanOrEqualTo(root.get("writeDate"), request.getSearchStartDate()));
            predicates.add(cb.lessThanOrEqualTo(root.get("writeDate"), request.getSearchEndDate()));

            if (request.getWriter() != null && !request.getWriter().isEmpty()) {
                predicates.add(cb.like(root.get("writer"), "%" + request.getWriter() + "%"));
            }

            if (request.getSubject() != null && !request.getSubject().isEmpty()) {
                predicates.add(cb.like(root.get("subject"), "%" + request.getSubject()+ "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
