/*
 * 공통 사용 변수 및 기능
 */

 const COMMON_TODAY = new Date();
 const COMMON_ONE_MONTH = getOneMonthAgoSameDay(COMMON_TODAY);
 const FLATPICKR_COMMON_TODAY = flatpickr.formatDate(new Date(COMMON_TODAY), 'Y-m-d');
 const FLATPICKR_ONE_MONTH = flatpickr.formatDate(new Date(COMMON_ONE_MONTH), 'Y-m-d');

 $(window).on('load', function () {
    $('#loading-spinner').fadeOut('slow');
 });

 $(function() {
    console.log("common.js");
 });

 function getOneMonthAgoSameDay(date)
 {
   // 오늘 날짜에서 한 달 전 날짜를 그대로 뺌 (월 말 처리 자동)
   const year = date.getFullYear();
   const month = date.getMonth();
   const day = date.getDate();

   let prevMonth = month - 1;
   let prevYear = year;

   if (prevMonth < 0) {
     prevMonth = 11;
     prevYear = year - 1;
   }

   // 이전 달의 마지막 날짜 구하기
   const lastDayPrevMonth = new Date(prevYear, prevMonth + 1, 0).getDate();

   // 오늘 날짜의 일(day)이 이전 달에 없으면 이전 달의 마지막 날로 조정
   const newDay = Math.min(day, lastDayPrevMonth);

   return new Date(prevYear, prevMonth, newDay);
 }

 /*
  * ajax 비동기 처리
  */
  function requestAjax(requestParams)
  {
     if (requestParams === undefined || requestParams === null) {
  		return;
  	}

  	const url = requestParams.url;
  	const httpMethod = requestParams.method;
  	const httpHeader = requestParams.headers || "application/json";
  	const params = requestParams.params;
  	const callback = requestParams.callback;
  	const callbackParams = requestParams.callbackParams || null;

  	const csrfHeader = $("meta[name='_csrf_header']").attr("content");
  	const csrfToken = $("meta[name='_csrf']").attr("content");

  	$('#loading-spinner').show(); // 로딩 스피너 표시

     $.ajax({
       url: url,
       contentType: httpHeader,
       headers: {
        [csrfHeader] : csrfToken
       },
       method: httpMethod,
       data: params,
       dataType: "json",
       success: function(data, status, xhr) {

         console.log("상태코드" + xhr.status);

         if (xhr === null || xhr === undefined) {
         	alert("관리자에게 문의해주세요.");
         	return;
         }

         if (callback instanceof Function) {
             // 콜백함수 실행
         	callback(data, callbackParams);
         }

         switch (xhr.status) {
             case 200:
             case 201:
             case 204:
                 // 정상
                 break;
             default:
                 // 상태값이 제대로 안 온 경우 확인을 위해 필요
                 alert(`정상적인 처리가 되지 않았습니다.\n관리자에게 문의하세요`);
                 break;
         }

       },
       complete: function(xhr, status, err) {
          $('#loading-spinner').hide(); // 완료되면 로딩 스피너 숨김
       },
       error: function(xhr, status, err) {
         alert("error : " + xhr.status);
       }
     });
  }