package com.developerleetaehee.th_web_app.repository;

import com.developerleetaehee.th_web_app.domain.Board;
import com.developerleetaehee.th_web_app.domain.Counsel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CounselRepository extends JpaRepository<Counsel, Long>, JpaSpecificationExecutor<Counsel> {
    /**
     * 소프트 삭제 되지 않은 해당 데이터 조회 (optional로 하면 null 처리 쉬워짐)
     * @param long idx
     * @return Optional<Counsel>
     */
    Optional<Counsel> findByIdxAndDeleteDatetimeIsNull(Long idx);
}
