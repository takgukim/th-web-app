package com.developerleetaehee.th_web_app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="th_counsel")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Counsel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(name = "customer_name", length = 50, nullable = false)
    @JsonProperty("customer_name")
    private String customerName;

    @Column(name = "counsel_method", length = 15, nullable = false)
    @JsonProperty("counsel_method")
    @Schema(
            description = "상담 방법",
            allowableValues = {"call : 전화, email : 이메일"}
    )
    private String counselMethod;

    @Column(name = "counselContent", length = 100 , nullable = false)
    private String counselContent;

    @Column(name = "counsel_kind", length = 20, nullable = false)
    @JsonProperty("counsel_kind")
    @Schema(
            description = "상담 종류",
            allowableValues = {
                    "compliment : 칭찬, complaint : 불만, refund : 환불, exchange : 교환, suggestion : 건의, vendor_apply : 입점"
            },
            example = "compliment"
    )
    private String counselKind;

    @Column(name = "progress_state",  length = 20, nullable = false)
    @JsonProperty("progress_state")
    @Schema(
            description = "상담 진행 상태는 최초등록시 대기이며 이후 관리자가 변경 가능",
            allowableValues = {"pending : 대기, in_progress : 진행중, completed : 완료, on_hold : 보류"},
            example = "pending"
    )
    private String progressState;

    @Column(name = "request_memo")
    @JsonProperty("request_memo")
    @Schema(description = "고객이 요청할 때 사용하는 항목")
    private String requestMemo;

    @Column(name = "company_memo")
    @JsonProperty("company_memo")
    @Schema(description = "신청된 내용에 대해서 상담사가 입력하는 항목")
    private String companyMemo;

    @Column(name = "apply_date", nullable = false, updatable = false, length = 10)
    @JsonProperty("apply_date")
    private String applyDate;

    @Column(name = "ip_address", length = 45, nullable = false)
    @JsonProperty("ip_address")
    private String ipAddress;

    @Column(name = "register_datetime", nullable = false, updatable = false)
    private LocalDateTime registerDatetime;

    @Column(name="update_user", length = 30)
    private String updateUser;

    @Column(name = "update_datetime")
    private LocalDateTime updateDatetime;

    @Column(name = "delete_user", length = 30)
    private String deleteUser;

    @Column(name = "delete_datetime")
    private LocalDateTime deleteDatetime;

    public void update(Counsel counsel) {
        this.companyMemo = counsel.getCompanyMemo();
        this.updateUser = counsel.getUpdateUser();
        this.updateDatetime = LocalDateTime.now();
    }

    @Builder
    public Counsel(
            String customerName,
            String counselMethod,
            String counselContent,
            String counselKind,
            String progressState,
            String requestMemo,
            String ipAddress) {
        this.customerName = customerName;
        this.counselMethod = counselMethod;
        this.counselContent = counselContent;
        this.counselKind = counselKind;
        this.progressState = progressState;
        this.requestMemo = requestMemo;
        this.ipAddress = ipAddress;
    }

    @PrePersist
    protected void onCreate() {
        LocalDate nowDate = LocalDate.now();
        String applyDate = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.registerDatetime = LocalDateTime.now();
        this.applyDate = applyDate;
    }

}

