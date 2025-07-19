$.ajax({
  url: '/api/map/shop_location',
  method: 'GET',
  dataType: 'json',
  success: function(data) {

    const LAT = data['lat'];
    const LNG = data['lng'];

    const mapOptions = {
        center: new naver.maps.LatLng(LAT, LNG),
        zoom: 14,
        minZoom: 7, //지도의 최소 줌 레벨
        zoomControl: true, //줌 컨트롤의 표시 여부
        zoomControlOptions: { //줌 컨트롤의 옵션
            position: naver.maps.Position.TOP_RIGHT
        },
        mapTypeControl: true,
        mapTypeControlOptions: {
          style: naver.maps.MapTypeControlStyle.DROPDOWN
       }
    };

    const map = new naver.maps.Map('map', mapOptions);

    const marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(LAT, LNG),
        map: map,
        zoom: 9
    });
  },
  error: function(xhr, status, err) {
  }
});