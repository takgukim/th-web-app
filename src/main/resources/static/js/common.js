/*
 * 공통 사용 변수 및 기능
 */

 const COMMON_TODAY = new Date();
 const COMMON_ONE_MONTH = getOneMonthAgoSameDay(COMMON_TODAY);
 const FLATPICKR_COMMON_TODAY = flatpickr.formatDate(new Date(COMMON_TODAY), 'Y-m-d');
 const FLATPICKR_ONE_MONTH = flatpickr.formatDate(new Date(COMMON_ONE_MONTH), 'Y-m-d');

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