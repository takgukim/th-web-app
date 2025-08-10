package com.developerleetaehee.th_web_app.repository;

import com.developerleetaehee.th_web_app.domain.Counsel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CounselRepository extends JpaRepository<Counsel, Long>, JpaSpecificationExecutor<Counsel> {
    /**
     * 소프트 삭제 되지 않은 해당 데이터 조회 (optional로 하면 null 처리 쉬워짐)
     * @param long idx
     * @return Optional<Counsel>
     */
    Optional<Counsel> findByIdxAndDeleteDatetimeIsNull(Long idx);

    /**
     * 진행 상태 특정 조건 검색 (optional로 하면 null 처리 쉬워짐)
     * @param long idx
     * @param string progressState
     * @return Optional<Counsel>
     */
    Optional<Counsel> findByIdxAndProgressState(Long idx, String progressState);

    /**
     * 진행 상태 특정 조건 검색 (optional로 하면 null 처리 쉬워짐)
     * @param long idx
     * @param string progressState
     * @return Optional<Counsel>
     * 자바 17버전 이상 허용
     * jpql은 테이블 컬럼명기준이 아니라 엔티로 이루어짐
     * 전체 컬럼은 *로 하면안됨
     * 조건이 길어지면 함수명도 어느정도 줄이면서 아래처럼 하면 편하다고함
     */
    @Query("""
        SELECT c
        FROM Counsel AS c
        WHERE c.idx = :idx
        AND c.progressState = :progressState
        AND c.deleteDatetime IS NULL
    """)
    Optional<Counsel> findValidByIdxAndProgressState(
            @Param("idx") Long idx,
            @Param("progressState") String progressState
    );
}
