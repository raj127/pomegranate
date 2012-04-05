function jumpPage(pageNo,pageSize) {
	$("#pageNo").val(pageNo);
	$("#pageSize").val(pageSize);
	$("#mainForm").submit();
}

function sort(orderBy, defaultOrder) {
	var i=$("#orderBy").val().indexOf("convert");
	//如果没有以convert函数开头则增加
	if(i!=0){
		$("#orderBy").val("convert("+$("#orderBy").val()+", 'gbk')")
	}
	var orderByValue;
	var j=orderBy.indexOf("convert");
	if(j!=0){
		orderByValue =  "convert("+orderBy+", 'gbk')";
	}
	if ($("#orderBy").val() == orderByValue) {
		if ($("#order").val() == "") {
			$("#order").val(defaultOrder);
		}
		else if ($("#order").val() == "desc") {
			$("#order").val("asc");
		}
		else if ($("#order").val() == "asc") {
			$("#order").val("desc");
		}
	}
	else {
		$("#orderBy").val(orderByValue);
		$("#order").val(defaultOrder);
	}

	$("#mainForm").submit();
}

//数字排序
function intsort(orderBy, defaultOrder) {
	if ($("#orderBy").val() == orderBy) {
		if ($("#order").val() == "") {
			$("#order").val(defaultOrder);
		}
		else if ($("#order").val() == "desc") {
			$("#order").val("asc");
		}
		else if ($("#order").val() == "asc") {
			$("#order").val("desc");
		}
	}
	else {
		$("#orderBy").val(orderBy);
		$("#order").val(defaultOrder);
	}

	$("#mainForm").submit();
}

$(document).ready(function(){
	$(".mainNav a").click(function(){
		$(".mainNav a").attr("class","");
		$("#"+this.id).attr("class","actived");
		var currentMenuNo = parseInt(this.id.substring(1));
		$(".secondNav div").each(function(){
			$(this).hide();
			$("#subNav"+currentMenuNo).show();
		});
	});
	$("#contentTable tr:odd").addClass("color1");
	$("#contentTable tr:even").addClass("color2");
	$("#contentTable tr").hover(function(){
		jQuery(this).addClass("color3")},
		function(){
		jQuery(this).removeClass("color3")
		});
});


function search() {
	$("#order").val("");
	$("#orderBy").val("");
	$("#pageNo").val("1");

	$("#mainForm").submit();
}

isNumber = function (e) {  
    if ($.browser.msie) {  
        if ( ((event.keyCode > 47) && (event.keyCode < 58)) ||  
              (event.keyCode == 8) ) {  
            return true;  
        } else {  
            return false;  
        }  
    } else {  
        if ( ((e.which > 47) && (e.which < 58)) ||  
              (e.which == 8) ) {  
            return true;  
        } else {  
            return false;  
        }  
    }
}

function linkTo(url) {
	if(url == undefined || url == ''){
		return;
	}else{
		window.location.href = url;
	}
}

//将中文转换为url中使用的unicode码
function encode_utf8(a){ 
    a = a.replace(/\r\n/g, "\n "); 
    var utftext = ""; 
    for(var n=0; n<a.length; n++){ 
	    // 
	    var c=a.charCodeAt(n); 
	    //0-127   =>   1byte 
	    if(c <128) 
	    	utftext += String.fromCharCode(c); 
	    //127-2047 => 2byte 
	    else if((c> 127) && (c<2048)){ 
	        utftext += "%"+((c>>6)|192); 
	        utftext += "%"+((c&63)|128);
	    } 
	    //2048-66536 => 3byte 
	    else{ 
	        utftext += "%"+((c>>12)|224).toString(16); 
	        utftext += "%"+(((c>>6)&63)|128).toString(16); 
	        utftext += "%"+((c&63)|128).toString(16);
	    } 
    }
    return utftext; 
} 

//对字符串进行中文处理
function dealCNChar(str) {
	if(str == '' || str == null){
		return '';
	}else{
		return encode_utf8(str);
	}
}

//去掉input对象中的特殊字符
function removeIllegalChars(obj) {
	var str = obj.value;
	var reg = /[?|\-|<|>&%@#]/g;
	obj.value = str.replace(reg, "");
}


//获得当前日期偏移值的数据
function DateAdd(interval,number,date) { 
/*--------------- DateAdd(interval,number,date) ----------------- 
 * DateAdd(interval,number,date) 
 * 参数:interval,字符串表达式，表示要添加的时间间隔. 
 * 参数:number,数值表达式，表示要添加的时间间隔的个数,可以为负数
 * 参数:date,时间对象. 
 * 返回:新的时间对象. 
 * 例子:获得5天前的日期对象:
 * var now = new Date(); 
 * var newDate = DateAdd("d",-5,now); 
 *--------------- DateAdd(interval,number,date) ----------------- 
 */ 
	switch(interval) { 
		case "y" : { //年
			date.setFullYear(date.getFullYear()+number); 
			return date; 
			break; 
		} 
		
		case "q" : { //季度
			date.setMonth(date.getMonth()+number*3); 
			return date; 
			break; 
		} 
		
		case "m" : { //月
			date.setMonth(date.getMonth()+number); 
			return date; 
			break; 
		} 
		
		case "w" : { //周
			date.setDate(date.getDate()+number*7); 
			return date; 
			break; 
		} 
		
		case "d" : { //日
			date.setDate(date.getDate()+number); 
			return date; 
			break; 
		} 
		
		case "h" : { //小时
			date.setHours(date.getHours()+number); 
			return date; 
			break; 
		} 
		
		case "m" : { //分钟
			date.setMinutes(date.getMinutes()+number); 
			return date; 
			break; 
		} 
		
		case "s" : { //秒
			date.setSeconds(date.getSeconds()+number); 
			return date; 
			break; 
		} 
		
		default : { 
			date.setDate(d.getDate()+number); 
			return date; 
			break; 
		} 
	} 
} 

/**  
 * 时间对象的格式化;  
 */  
Date.prototype.format = function(format) {   
    /*  
     * eg:format="YYYY-MM-dd hh:mm:ss";  
     */  
    var o = {   
        "M+" :this.getMonth() + 1, // month   
        "d+" :this.getDate(), // day   
        "h+" :this.getHours(), // hour   
        "m+" :this.getMinutes(), // minute   
        "s+" :this.getSeconds(), // second   
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter   
        "S" :this.getMilliseconds()   
    // millisecond   
    }   
  
    if (/(y+)/.test(format)) {   
        format = format.replace(RegExp.$1, (this.getFullYear() + "")   
                .substr(4 - RegExp.$1.length));   
    }   
  
    for ( var k in o) {   
        if (new RegExp("(" + k + ")").test(format)) {   
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]   
                    : ("00" + o[k]).substr(("" + o[k]).length));   
        }   
    }   
    return format;   
}	



