   	  		
        setTimeout(function(){
        	var lon=$('.circle-info');
            $.each(lon,function (index,domEle){
            if(index==0){
            	$('.circle-info').eq(0).html('<i>开通</i>41/<i>班级人</i>50');
            	$('.circle-info').eq(0).css('border-top','2px solid #2FD0FA')
            }
            if(index==1){
            	$('.circle-info').eq(1).html('<i>班排名</i>4/<i>年级数</i>10');
            	$('.circle-info').eq(1).css('border-top','2px solid #46CA5A')
            }
            if(index==2){
            	$('.circle-info').eq(2).html('<i>实交</i>41/<i>应交</i>50');
            	$('.circle-info').eq(2).css('border-top','2px solid #FCC62E')
            }
        });
        $('.tu .head .circle-text').css('line-height','20px!important')
        },100)  
           
//当前作业切换
var arr=[];
var arr1=[];
$('.dianji-main li').each(function(){
	var lon=$('.dianji-main li ').index(this);
	var txt=$('.dianji-main li .ji-si span').eq(lon).text();
	var txt1=$('.dianji-main li .ji-wu').eq(lon).text();
	arr[lon]=txt;
	arr1[lon]=txt1;
	if(arr[lon]=='未提交'){		
		$('.dianji-main li .ji-si span').eq(lon).attr('class','yansheh');
	}
	if(arr1[lon]==0){		
		$('.dianji-main li .ji-wu').eq(lon).addClass('yansheh');
	}
})

//判断进度条颜色
var sum;
var lon=$('.votebox');
var arrfen=[];
setTimeout(function(){
  for(var i=0;i<lon.length;i++){
	rennum=$('.last i').eq(i).text();    
	sum=rennum/100;	
	var num1=$('.votebox').eq(i).children().children('.barline').width();	
	var num2=sum*num1;
	var num=$('.votebox').eq(i).children(   ).children('.barline').children().width(num2);
	if(rennum>75+'%'){
		$('.votebox').eq(i).children().children('.barline').children().addClass('color1');
	}else if(rennum<59+'%'){
		$('.votebox').eq(i).children().children('.barline').children().addClass('color2');
	}else if(rennum=>59+'%'&&rennum<=75+'%'){
		$('.votebox').eq(i).children().children('.barline').children().addClass('color3');
	}
 } 
},100)
//选择题型
$('.header-search').on('touchstart',function(){
	$('#tixing').css('display','block');
})
$("#tixing ul li").eq(0).addClass("titit");
$("#tixing ul li").on('touchstart',function(){
	var index=$("#tixing ul li").index(this)
    $("#tixing ul li").eq(index).addClass("titit").siblings().removeClass("titit");
    var txt=$("#tixing ul li").eq(index).text();
    $('.header-search span').text(txt);
    setTimeout(function(){
    	$('#tixing').css('display','none');
    },200)    
});
