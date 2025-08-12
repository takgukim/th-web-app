package com.developerleetaehee.th_web_app.service.board;

import com.developerleetaehee.th_web_app.domain.Counsel;
import com.developerleetaehee.th_web_app.dto.counsel.*;
import com.developerleetaehee.th_web_app.repository.CounselRepository;
import com.developerleetaehee.th_web_app.specification.CounselSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CounselService {

    private final CounselRepository counselRepository;

    @Autowired
    private CounselCustomCode counselCustomCode;

    public Page<Counsel> getCounselPage(CounselSearchRequest counselSearchRequest) {
        Pageable pageable = PageRequest.of(
                counselSearchRequest.getStartPage(),
                counselSearchRequest.getPerPage()
        );

        return counselRepository.findAll(CounselSpecification.search(counselSearchRequest), pageable);
    }

    public Page<Counsel> findAll(CounselSearchRequest counselSearchRequest) {
        Pageable pageable = PageRequest.of(
                counselSearchRequest.getStartPage(),
                counselSearchRequest.getPerPage(),
                Sort.by(Sort.Direction.DESC, "idx")
        );

        return counselRepository.findAll(CounselSpecification.search(counselSearchRequest), pageable);
    }

    public Counsel findById(long id) {
        return counselRepository.findByIdxAndDeleteDatetimeIsNull(id)
                .orElseThrow(() -> new RuntimeException("해당 상담글이 없습니다."));
    }

    public Counsel findByIdAndProgressState(long id, String progressState) {
        return counselRepository.findValidByIdxAndProgressState(id, progressState)
                .orElseThrow(() -> new RuntimeException("해당 상담글 상태를 확인하세요."));
    }

    public Counsel save(AddCounselRequest request) {
        return counselRepository.save(request.toEntity());
    }

    @Transactional
    public Counsel update(long id, UpdateCounselRequest request) {
        Counsel counsel = this.findById(id);

        counsel.setCompanyMemo(request.getCompanyMemo());
        counsel.setUpdateUser(request.getUpdateUser());
        counsel.update(counsel);

        return counsel;
    }

    @Transactional
    public Counsel updateState(long id, UpdateCounselState request) {

        // 진행상태가 대기인것만 삭제
        Counsel counsel = this.findByIdAndProgressState(id, "pending");

        counsel.setProgressState(request.getProgressState());
        counsel.setUpdateUser(request.getUpdateUser());
        counsel.setUpdateDatetime(LocalDateTime.now());

        return counsel;
    }

    @Transactional
    public void softDelete(long id, DeleteCounselRequest request) {

        // 진행상태가 대기인것만 삭제
        Counsel counsel = this.findByIdAndProgressState(id, "pending");

        counsel.setDeleteUser(request.getDeleteUser());
        counsel.setDeleteDatetime(LocalDateTime.now());
    }

    public void deleteById(long id) {

        // 진행상태가 대기인것만 삭제
        this.findByIdAndProgressState(id, "pending");

        counselRepository.deleteById(id);
    }

    public String checkCounselCode(String code, String value) {
        String result = null;

        switch (code) {
            case "counsel_method" :
                result = counselCustomCode.getCounselMethodMap().get(value);
                break;
            case "counsel_kind" :
                result = counselCustomCode.getCounselKindMap().get(value);
                break;
            case "progress_state" :
                result = counselCustomCode.getCounselStateMap().get(value);
                break;
        }

        return result;
    }
}
