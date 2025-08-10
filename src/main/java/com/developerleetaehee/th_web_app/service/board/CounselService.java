package com.developerleetaehee.th_web_app.service.board;

import com.developerleetaehee.th_web_app.domain.Counsel;
import com.developerleetaehee.th_web_app.dto.counsel.AddCounselRequest;
import com.developerleetaehee.th_web_app.dto.counsel.CounselSearchRequest;
import com.developerleetaehee.th_web_app.repository.CounselRepository;
import com.developerleetaehee.th_web_app.specification.CounselSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CounselService {

    private final CounselRepository counselRepository;

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

    public Counsel save(AddCounselRequest request) {
        return counselRepository.save(request.toEntity());
    }
}
