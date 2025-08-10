package com.developerleetaehee.th_web_app.dto.counsel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CounselSearchRequest {
    private int startPage;
    private int perPage;

    public void setPageRange(int startPage, int perPage) {
        this.startPage = startPage;
        this.perPage = perPage;
    }
}
