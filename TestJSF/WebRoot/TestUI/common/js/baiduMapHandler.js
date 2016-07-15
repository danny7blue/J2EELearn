	var opts = {
		width : 250,     // 信息窗口宽度
		height: 80,     // 信息窗口高度
		title : "信息窗口" , // 信息窗口标题
		enableMessage:true//设置允许信息窗发送短息
		};
	var marker;
	var currentLat;
	var currentLng;
	var searchPoint;
	var sContent;
	
	//地图事件设置函数：
    function setMapEvent(){
    	console.log('set up map event!');
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //地图控件添加函数：
    function addMapControl(){
    	console.log('add map controller!');
        //向地图中添加缩放控件
		var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
		map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
		var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
		map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
		var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
		map.addControl(ctrl_sca);
		var navigationControl = new BMap.NavigationControl({
   		// 靠左上角位置
    	anchor: BMAP_ANCHOR_TOP_LEFT,
    	// LARGE类型
    	type: BMAP_NAVIGATION_CONTROL_LARGE,
    	// 启用显示定位
    	enableGeolocation: true
  		});
		map.addControl(navigationControl);
		var geolocationControl = new BMap.GeolocationControl();
  		geolocationControl.addEventListener("locationSuccess", function(e){
    	// 定位成功事件
    	var address = '';
    	address += e.addressComponent.province;
    	address += e.addressComponent.city;
    	address += e.addressComponent.district;
    	address += e.addressComponent.street;
    	address += e.addressComponent.streetNumber;
    	alert("当前定位地址为：" + address);
  		});
  		geolocationControl.addEventListener("locationError",function(e){
    	// 定位失败事件
    	alert(e.message);
  		});
  		map.addControl(geolocationControl);
    }
    
    //显示坐标位置
    function showInfo(e){
		currentLat = e.point.lat;
		currentLng = e.point.lng;
		
    	console.log('set up lat and lng value!');
		//alert(e.point.lng + ", " + e.point.lat);
	}
	
	//鼠标右击时间, 添加搜索图标,设定搜索点
	function addSerachPoint(e) {
		map.removeOverlay(locationMarker);
		map.removeOverlay(searchPoint);
		currentLat = e.point.lat;
		currentLng = e.point.lng;
		
		var pt = new BMap.Point(currentLng, currentLat);
		searchPoint = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
		map.addOverlay(searchPoint);  
		// 将标注添加到地图中
		jQuery('.currentLng').val(e.point.lng);
		jQuery('.currentLat').val(e.point.lat);
	}
	
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}
	
	// 搜索结果的定位和图标加载
	function setPlace(){
		console.log('set up map place!');
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			jQuery('.currentLng').val(local.getResults().getPoi(0).point.lng);
			jQuery('.currentLat').val(local.getResults().getPoi(0).point.lat);
			//alert('您搜索的位置经度是: ' + local.getResults().getPoi(0).point.lng + ", " + '经度是: ' + local.getResults().getPoi(0).point.lat);
			map.centerAndZoom(pp, 18);
	 		searchPoint = new BMap.Marker(pp,{icon:myIcon});
			map.addOverlay(searchPoint);    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
	
	function getValues() {
		alert(jQuery('.currentLng').val());
		alert(jQuery('.currentLng').val());
	}
	
	// 获取数据并对不同结果加以处理
	function handleResult() {
		map.clearOverlays();
		var string = jQuery('.hiddenResult').val();
		var json = eval("("+string+")");
		console.log(json);
		if(json.status == "ERROR") {
			if(json.error == "No parking spaces found") {
				alert("对不起, 附近没有合适的停车场");
			} else {
				alert("Error, something is wrong. The error message is: " + json.error + " Please check and rerun");
			}
			return false;
		}
		map.addOverlay(searchPoint);              // 将标注添加到地图中
		var resultJson = json.result;
		var returnMaxNum = 10;
		console.log("resultJson: " + resultJson);
		if(resultJson.hasOwnProperty('spaces')) {
			var spaces = resultJson.spaces;
			console.log("spaces length: " + spaces.length);
			if(spaces.length < returnMaxNum) {
				getSpaces(spaces, spaces.length);
			} else {
				getSpaces(spaces, returnMaxNum);
			}
		} else {
			alert("附近沒有合適的停車場");
		}
	}
	
	// 停车场信息的解析
	function getSpaces(spaces, returnNum) {
		var points = [];
		var contents = [];
		for(i=0; i<returnNum; i++) {
				console.log(spaces[i]);
				var rid = spaces[i].rid;
				console.log("Rid: " + rid);
				var title = spaces[i].title;
				console.log("Title: " + title);
				var lat = spaces[i].lat;
				console.log("纬度: " + lat);
				var lng = spaces[i].lng;
				console.log("经度: " + lng);
				var ggpoint = new BMap.Point(lng, lat);
				points.push(ggpoint);
				console.log(points);
				var baseCostString = "";
				var extendCostString = "";
				var costAmountString = "";
				var image = "";
				if(spaces[i].images.length>0) {
					var imgURL = spaces[i].images[0].url;
					image = "<img style='float:right;margin:4px' id='imgDemo' src='" + imgURL + "' width='139' height='104' title='" + title + "'/>";
					console.log("ImageURL: " + imgURL);
				}
				console.log(spaces[i].priceschema);
				var cost = spaces[i].priceschema.prices[0].costs;
				console.log(spaces[i].priceschema.prices);
				var base_price;
				for(j=0;j<spaces[i].dgroups.length; j++) {
					if(spaces[i].dgroups[j].name == 'Hours & Prices') {
						base_price = spaces[i].dgroups[j].items[1].val;
						baseCostString = "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>" + base_price + "</p>";
					}
					if(spaces[i].dgroups[j].items.length > 2) {
						var extend_price = spaces[i].dgroups[j].items[2].val;
						extendCostString = "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>" + extend_price + "</p>";
					}
				}
				var currency = spaces[i].priceschema.currency_code;
				if(currency == "CNY") currency = "人民币";
				var address = spaces[i].addresses;
				console.log(spaces[i].cprices);
				if(spaces[i].hasOwnProperty('cprices')) {
					console.log(spaces[i].cprices);
					var dateRange = spaces[i].cprices.items[0].date;
					var amount = spaces[i].cprices.items[0].amount;
					costAmountString = "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>停车时间:" + dateRange + " 总花费 :" + amount + currency + "</p>";
				}
				
				
				/*var marker = new BMap.Marker(new BMap.Point(lng, lat)); // 创建点
				map.addOverlay(marker);    //增加点
*/				//坐标转换完之后的回调函数
				sContent =
				"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>" + title + "</h4>" + 
				image + 
				"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>" + address + "</p>" + 
				baseCostString + 
				extendCostString + 
				costAmountString + 
				"</div>";
				contents.push(sContent);
				/*translateCallback = function (data){
			      if(data.status === 0) {
			        var marker = new BMap.Marker(data.points[0]);
					addClickHandler(sContent,marker);
			        map.addOverlay(marker);
			        map.setCenter(data.points[0]);
			      }
			    }
			
			    setTimeout(function(){
			        var convertor = new BMap.Convertor();
			        var pointArr = [];
			        pointArr.push(ggpoint);
			        convertor.translate(pointArr, 3, 5, translateCallback)
			    }, 1000);*/
			}
			//坐标转换完之后的回调函数
	    translateCallback = function (data){
	      if(data.status === 0) {
	        for (var i = 0; i < data.points.length; i++) {
	        	var marker = new BMap.Marker(data.points[i]);
	        	var content = contents[i];
	        	addClickHandler(content, marker);
	            map.addOverlay(marker);
	            map.setCenter(data.points[i]);
	        }
	      }
	    }
	    setTimeout(function(){
	        var convertor = new BMap.Convertor();
	        convertor.translate(points, 3, 5, translateCallback)
	    }, 1000);
	}
	
	//单击搜索结果的处理事件
	function addClickHandler(content,marker){
		console.log('addClickHandler');
		marker.addEventListener("click",function(e){
			openInfo(content,e)}
		);
	}
	
	// 显示信息框
	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
