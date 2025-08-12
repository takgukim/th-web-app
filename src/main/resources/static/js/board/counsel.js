/*
 * 게시판
 */
$(function() {
    console.log("counsel.js");

    const contractMethodInit = $("input[name='contract_method']:checked").val();
    $(`.div-${contractMethodInit}-section`).show();

    $("input[name='contract_method']").on("click", function() {
        $(".div-call-section, .div-email-section").hide();
        $(`.div-${$(this).val()}-section`).show();
    });

    $("#btnSave").on("click", function() {
        counselInsert();
    });

    $("#btnReset").on("click", function() {
        alert("reset");
    });
});

/*
 * 상담글 추가
 */
function counselInsert()
{
    const customerName = $("#counsel_submit #customer_name").val();
    const contractMethod = $("#counsel_submit input[name='contract_method']:checked").val()
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
        alert("선택하신 연락방법에 따른 정보를 입력해주세요.");
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
  $(location).attr("href", `/counsel/write`);
}