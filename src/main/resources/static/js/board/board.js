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

    $("#btnReset").on("click", function() {
        startPicker.setDate(FLATPICKR_ONE_MONTH, true);
        endPicker.setDate(FLATPICKR_COMMON_TODAY, true);
        $("#search_subject").val("");
        $("#search_writer").val("");
    });

    $("#btnModify").on("click", function() {
        const boardType = $(this).closest("#btnLinkGroup").data("board_type");

        if (confirm("정보를 수정하시겠습니까?") === true) {
             $(location).attr("href", `/boards/${boardType}/posts/edit/1`);
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