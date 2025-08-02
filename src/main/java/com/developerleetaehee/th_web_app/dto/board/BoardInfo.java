package com.developerleetaehee.th_web_app.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardInfo {
    private String pageTitle;
    private String pageDescription;
    private String pageSubTitle;
    private String prefixFile;
    private boolean pageAccessFlag;
}
