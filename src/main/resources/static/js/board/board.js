$(function() {
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
});