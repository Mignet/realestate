$(document).ready(function(){
	   /*var aj = $.ajax( {  
	       url:'house!getAllHouse.action',// ��ת�� action  
	       data:{  
	    	   projectid : $('#projectid').val() 
	       },  
	       type:'post',  
	       cache:false,  
	       dataType:'json',  
	       success:function(data) {  
	    	   var list = data;
	    	   var paper = Raphael("holder",screen.availWidth,screen.availHeight);
	    	   paper.setStart();
	    	   var CELL_H = 25;var tall = 50;
	    	   //���ն�����ʾ
	    	   var len = list.length;
	    	   for(var i=0;i<len;i++){
	    		   var sinfo = list[i].at_floor.split('.');
	    		   if(sinfo[0].indexOf('��Ԫ')!=-1){
	    			   //var cell = paper.rect(100,j*CELL_H,cell_w,CELL_H);
	    		   }
	    	   }
	        },  
	        error : function() {  
	             alert("�쳣��");  
	        }  
	   });*/
		var offset = 120;
		var w = 8;
		var h = 22;
		var cell_w = 40*1.5;
		var cell_h = 25*1.5;
  	var paper = Raphael("holder", offset+w*cell_w+2*cell_w, h*cell_h+90);
  	paper.setStart();
  	for(i=0;i<w;i++){
  		for(j=1+h;j>0;j--){
  			if(i==w-1){
		  		var t = paper.text(offset+w*cell_w+25, j*cell_h+cell_h/2, "-- �� "+((h-j)>=0?(h+1-j):(h-j))+" ��");
  			}
  			var cell = paper.rect(offset+i*cell_w,j*cell_h,cell_w,cell_h);
  			//����
  			var txt = paper.text(offset+i*cell_w+cell_w/2,j*cell_h+cell_h/2,(h+1-j)+"0"+i);
  			 txt.click(function(){
  				var returnval = window.showModalDialog("house-trace.jsp","","dialogWidth=600px;dialogHeight=500px");
  			}) 
  			cell.id = "["+i+","+j+"]";
  			cell.click(function(){
//  				var returnval = window.showModalDialog("house-trace.jsp","","dialogWidth=600px;dialogHeight=500px");
  				//alert("open a house!"+this.id);
  				/* if($chk(returnval)&&returnval.trim()!=''){
  					flag = false;
  				} */
  				/*this.animate({
  				    //r: 5 * (Math.random() * 5 + 1),
  				    //stroke: Raphael.hsb(Math.random(), 1, 1),
  				    fill: Raphael.hsb(0.5, 1, 1) ,
  				    'fill-opacity': Math.random() 
  				  }, 200); */
  			});
  			if(Math.random(0,1)<0.03){
  				cell.attr({
  					stroke: "black",
  					"stroke-width": 2,
  					fill: 'blue',
  					'fill-opacity': 0.5
  				});
  			}else if(Math.random(0,1)>0.03 && Math.random(0,1)<0.06){
  				cell.attr({
  					stroke: "black",
  					"stroke-width": 2,
  					fill: Raphael.hsb(0.5, 1, 1),
  					'fill-opacity': 0.5
  				});
  			}else if(Math.random(0,1)>0.06 && Math.random(0,1)<0.09){
  				cell.attr({
  					stroke: "black",
  					"stroke-width": 2,
  					fill: 'red',
  					'fill-opacity': 0.5
  				});
  			}else{
  				cell.attr({
  					stroke: "black",
  					"stroke-width": 2,
  					fill: '#fff',
  					'fill-opacity': 0.5
  				});
  			}
  		}
  	}
  	var ground = paper.rect(offset,(h+1)*cell_h,w*cell_w,cell_h);
  	ground.attr({
  		stroke: "black",
		  "stroke-width": 2,
		  fill: 'gray',
		  'fill-opacity': 1
  	})
		var st = paper.setFinish();
		/* st.attr({
		  stroke: "black",
		  "stroke-width": 2,
		  fill: 'gray',
		  'fill-opacity': 0.5
		}); */
});