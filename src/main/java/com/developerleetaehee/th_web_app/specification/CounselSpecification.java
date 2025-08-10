package com.developerleetaehee.th_web_app.specification;

import com.developerleetaehee.th_web_app.domain.Counsel;
import com.developerleetaehee.th_web_app.dto.counsel.CounselSearchRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CounselSpecification {

    public static Specification<Counsel> search(CounselSearchRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isNull(root.get("deleteDatetime")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
