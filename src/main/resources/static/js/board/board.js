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

       $(location).attr('href', `/boards/${boardType}/posts/${id}`);
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
                boardUpdate(boardIdx);
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
             $(location).attr("href", `/boards/${boardType}/posts/edit/${boardIdx}`);
        }
    });

    $("#btnDelete").on("click", function() {
        const boardType = $(this).closest("#btnLinkGroup").data("board_type");

        if (confirm("정말로 삭제하시겠습니까?") === true) {
            // ajax로 처리
        }
    });

    $("#btnList").on("click", function() {
        const boardType = $(this).closest("#btnLinkGroup").data("board_type");
        $(location).attr("href", `/boards/${boardType}/posts`);
    });
});

/*
 * 게시글 추가
 */
function boardInsert(boardType)
{
    $.ajax({
      url: "/api/boards",
      contentType: "application/json",
      method: "POST",
      data: JSON.stringify({
        "writer" : $("#board_submit #writer").val(),
        "subject" : $("#board_submit #subject").val(),
        "content" : $("#board_submit #content").val(),
        "board_type" : boardType,
      }),
      dataType: "json",
      success: function(data, status, xhr) {

        console.log("상태코드" + xhr.status);

        if (xhr.status === 201) {
            // 조회는 200, 삭제 204, 생성 되면 201, 수정 200
            alert("적용되었습니다.");

            // 상세보기로 이동
            $(location).attr("href", `/boards/adults_only/posts/${data.idx}`);
        }
      },
      error: function(xhr, status, err) {
        alert("error : " + xhr.status);
      }
    });
}

/*
 * 게시글 업데이트
 */
function boardUpdate(id)
{
    $.ajax({
      url: `/api/boards/${id}`,
      contentType: "application/json",
      method: "PUT",
      dataType: "json",
      data: JSON.stringify({
        "writer" : $("#board_submit #writer").val(),
        "subject" : $("#board_submit #subject").val(),
        "content" : $("#board_submit #content").val(),
        "updateUser" : $("#board_submit #writer").val()
      }),
      success: function(data, status, xhr) {

         console.log("상태코드" + xhr.status);

         if (xhr.status === 200) {
            // 조회는 200, 삭제 204, 생성 되면 201, 수정 200
            alert("수정되었습니다.");

            // 상세보기로 이동
            $(location).attr("href", `/boards/adults_only/posts/${id}`);
         }
      },
      error: function(xhr, status, err) {
        alert("error : " + xhr.status);
      }
    });
}