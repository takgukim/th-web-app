package com.developerleetaehee.th_web_app.service.board;

import com.developerleetaehee.th_web_app.domain.Counsel;
import com.developerleetaehee.th_web_app.dto.counsel.AddCounselRequest;
import com.developerleetaehee.th_web_app.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CounselService {

    private final CounselRepository counselRepository;

    public Counsel save(AddCounselRequest request) {
        return counselRepository.save(request.toEntity());
    }
}
