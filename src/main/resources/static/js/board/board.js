/*
 * 게시판
 */
$(function() {
    console.log("board.js");

    $(".tr-click-row").on("click", function() {
       const $tr = $(this).closest("tr");

       const id = $tr.data("id");
       const boardType = $tr.data("board_type");

       if (!id) {
            alert("오류! 페이지 새로 고침 후 진행하세요.");
            return;
       }

       $(location).attr('href', `/board/${boardType}/posts/${id}`);
    });

    $("#btnSearch").on("click", function() {
        $("#search_form").submit();
    });

    $("#btnSearchReset").on("click", function() {
        startPicker.setDate(FLATPICKR_ONE_MONTH, true);
        endPicker.setDate(FLATPICKR_COMMON_TODAY, true);
        $("#search_subject").val("");
        $("#search_writer").val("");
    });

    $("#btnSave").on("click", function() {
        const boardIdx = $("#board_submit #save_from_board_idx").val();
        const boardType = $("#board_submit #board_type").val();

        if (confirm("적용하시겠습니까?") === true) {
            if (boardIdx !== "") {
                boardUpdate(boardIdx, boardType);
            } else {
                boardInsert(boardType);
            }
        }
    });

    $("#btnModify").on("click", function() {
        const $tr = $(this).closest("#btnLinkGroup");

        const boardType = $tr.data("board_type");
        const boardIdx = $tr.data("board_idx");

        if (confirm("정보를 수정하시겠습니까?") === true) {
             $(location).attr("href", `/board/${boardType}/posts/edit/${boardIdx}`);
        }
    });

    $("#btnDelete").on("click", function() {
        const boardType = $(this).closest("#btnLinkGroup").data("board_type");

        if (confirm("정말로 삭제하시겠습니까?") === true) {
            // ajax로 처리
            boardSoftDelete($(this));
        }
    });

    $("#btnList").on("click", function() {
        const boardType = $(this).closest("#btnLinkGroup").data("board_type");
        $(location).attr("href", `/board/${boardType}/posts`);
    });
});

/*
 * 게시글 추가
 */
function boardInsert(boardType)
{
    const data = {
        "writer" : $("#board_submit #writer").val(),
        "subject" : $("#board_submit #subject").val(),
        "content" : $("#board_submit #content").val(),
        "board_type" : boardType
    };

    let requestParams =  {
        url: "/api/boards",
        method: "POST",
        headers: "application/json",
        params: JSON.stringify(data),
        callback: callbackInsert,
        callbackParams: data
    };

    requestAjax(requestParams);
}

/*
 * 게시글 업데이트
 */
function boardUpdate(id, boardType)
{
    const data = {
        "writer" : $("#board_submit #writer").val(),
        "subject" : $("#board_submit #subject").val(),
        "content" : $("#board_submit #content").val(),
        "updateUser" : $("#board_submit #writer").val()
    };

    const params = {
        "board_type" : boardType,
    };

    let requestParams =  {
        url: `/api/boards/${id}`,
        method: "PUT",
        headers: "application/json",
        params: JSON.stringify(data),
        callback: callbackUpdate,
        callbackParams: params
    };

    requestAjax(requestParams);
}

/*
 * 게시글 삭제 (소프트삭제)
 */
function boardSoftDelete($this)
{
    const $btnLinkGroup = $this.closest("#btnLinkGroup");

    const boardType = $btnLinkGroup.data("board_type");
    const boardIdx = $btnLinkGroup.data("board_idx");
    const boardWriter = $btnLinkGroup.data("board_writer");

    const data = {
        "delete_user" : boardWriter,
    };

    const params = {
        "board_type" : boardType,
    };

    let requestParams =  {
        url: `/api/boards/${boardIdx}/soft-delete`,
        method: "PATCH",
        headers: "application/json",
        params: JSON.stringify(data),
        callback: callbackDelete,
        callbackParams: params
    };

    requestAjax(requestParams);
}

/*
 * 게시판 데이터 추가 후 콜백 함수
 */
function callbackInsert(data, params)
{
  $(location).attr("href", `/board/${params.board_type}/posts/${data.idx}`);
}

/*
 * 게시판 데이터 수정 후 콜백 함수
 */
function callbackUpdate(data, params)
{
  $(location).attr("href", `/board/${params.board_type}/posts/${data.idx}`);
}

/*
 * 게시판 데이터 삭제 후 콜백 함수
 */
function callbackDelete(data, params)
{
  $(location).attr("href", `/board/${params.board_type}/posts`);
}