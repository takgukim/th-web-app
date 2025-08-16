/*
 * 게시판
 */
$(function() {
    console.log("counsel.js");

    $("#btnSearch").on("click", function() {
        $("#search_form").submit();
    });

    $("#btnSearchReset").on("click", function() {
        startPicker.setDate(FLATPICKR_ONE_MONTH, true);
        endPicker.setDate(FLATPICKR_COMMON_TODAY, true);
        $("#search_subject").val("");
        $("#search_writer").val("");
        $("#search_customer_name").val("");
    });

    $("input[name='contract_method']").on("click", function() {
        $(".div-call-section, .div-email-section").hide();
        $(`.div-${$(this).val()}-section`).show();
    });

    $("#btnSave").on("click", function() {
        counselInsert();
    });

    $("#btnReset").on("click", function() {
        if (confirm("입력하신 내용을 모두 초기화하겠습니까?") === true) {
            $("#counsel_submit #customer_name").val("");
            $("#counsel_submit input[name='contract_method']").prop("checked", false);
            $("#contract-call").trigger("click");
            $("#counsel_submit #select-counsel-kind").val("");
            $("#counsel_submit #request_memo").val("");
        }
    });

    $("#contract-call").trigger("click");
});

/*
 * 상담글 추가
 */
function counselInsert()
{
    const customerName = $("#counsel_submit #customer_name").val();
    const contractMethod = $("#counsel_submit input[name='contract_method']:checked").val();
    const counselKind = $("#counsel_submit #select-counsel-kind").val();
    const requestMemo = $("#counsel_submit #request_memo").val();

    if (customerName === "") {
        alert("고객명을 입력해주세요.");
        return;
    }

    if (contractMethod == undefined) {
        alert("연락방법을 선택하세요.");
        return;
    }

    const counselContent = $(`#${contractMethod}`).val();
    if (counselContent === "") {
        alert("선택하신 연락 방법에 따른 정보를 입력해주세요.");
        return;
    }

    if (counselKind === "") {
        alert("상담종류를 선택하세요.");
        return;
    }

    if (confirm("상담을 정말 등록하시겠습니까?") === false) {
        return;
    }

    const data = {
        "customer_name" : customerName,
        "counsel_method" : contractMethod,
        "counsel_content" : counselContent,
        "counsel_kind" : counselKind,
        "request_memo" : requestMemo
    };

    let requestParams =  {
        url: "/api/counsels",
        method: "POST",
        headers: "application/json",
        params: JSON.stringify(data),
        callback: callbackInsert,
        callbackParams: data
    };

    requestAjax(requestParams);
}

/*
 * 사용자 상담 데이터 추가 후 콜백 함수
 */
function callbackInsert(data, params)
{
    alert(`상담이 등록되었습니다.\n1-2일이내에 직원이 안내 예정입니다.`);

    $(location).attr("href", `/counsel/write`);
}