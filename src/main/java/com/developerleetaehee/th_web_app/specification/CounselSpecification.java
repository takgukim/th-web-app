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
            predicates.add(cb.greaterThanOrEqualTo(root.get("applyDate"), request.getSearchStartDate()));
            predicates.add(cb.lessThanOrEqualTo(root.get("applyDate"), request.getSearchEndDate()));

            if (request.getCustomerName() != null && !request.getCustomerName().isEmpty()) {
                predicates.add(cb.like(root.get("customerName"), "%" + request.getCustomerName() + "%"));
            }

            if (request.getCounselMethod() != null && !request.getCounselMethod().isEmpty()) {
                predicates.add(cb.equal(root.get("counselMethod"), request.getCounselMethod()));
            }

            if (request.getCounselKind() != null && !request.getCounselKind().isEmpty()) {
                predicates.add(cb.equal(root.get("counselKind"), request.getCounselKind()));
            }

            if (request.getProgressState() != null && !request.getProgressState().isEmpty()) {
                predicates.add(cb.equal(root.get("progressState"), request.getProgressState()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
