(function($){
	$.fn.circleGraphic=function(options){
		$.fn.circleGraphic.defaults={
			color:'#F90',
			startAngle: 0,
			elementid: $(this).attr('id'),
			percentage: null,
			progressTxt: null
		};
//		console.log("options : " + options.toSource());
		var opts = $.extend({},$.fn.circleGraphic.defaults,options);
		//console.log("opts" + opts.toSource());
		var percentage= opts.percentage;
//		console.log("percentage" + percentage);
		var ID=opts.elementid+"_label";
//		alert(ID);

		this.append("<canvas id='"+ID+"'></canvas>");

		//var canvas= document.getElementById(ID);
		var canvas = initCanvas(document.getElementById(ID));
		
//		alert("canvas : "+ document.getElementById(ID));
		var context= canvas.getContext('2d');
//		var context= document.getElementById(ID).getContext('2d');
		
		var Width = this.width();
		this.height(Width);
		var Height = this.height();

		canvas.width = Width;
		canvas.height = Height;

		var startAngle = opts.startAngle,
			endAngle = (opts.percentage/100),
			angle = (opts.percentage/100),
			radius = Width*0.4,
			progressTxt = opts.progressTxt;
		

		function initCanvas(canvas) {
			if (window.G_vmlCanvasManager && window.attachEvent && !window.opera) {
				canvas = window.G_vmlCanvasManager.initElement(canvas);
			}
			return canvas;
		}
		
		function drawTrackArc(){
			context.beginPath();
			context.strokeStyle = '#ECECEC';
			context.lineWidth = 5;
			context.arc(Width/2,Height/2,radius,(Math.PI/180)*(startAngle*360-90),(Math.PI/180)*(endAngle*360+270),false);
			context.stroke();
			context.closePath();
		}

		function drawOuterArc(_angle,_color){
			var angle = _angle;
			var color = _color;
			context.beginPath();
			context.strokeStyle = color;
			context.lineWidth = 10;
			context.arc(Width/2,Height/2,radius,(Math.PI / 180) * (startAngle * 360 - 90), (Math.PI / 180) * (angle * 360 - 90), false);
	       	context.stroke();
	       	context.closePath();
		}	

		function numOfPercentage(_angle,_color,progressTxt){
			var angle = Math.floor(_angle*100);
			var color=_color;
			context.font = "20px fantasy";
			context.fillStyle = color;
//			var metrics = context.measureText(angle);
//			var textWidth = metrics.width;
//			var xPos = Width/2-textWidth/2,
//				yPos = Height/2+textWidth/2;
			/*context.fillText(angle+"%",xPos,yPos);*/
			
			var metrics = context.measureText(progressTxt);
			var textWidth = metrics.width;
			var xPos = Width/2-textWidth/2,
				yPos = Height/2;
			context.fillText(progressTxt,xPos,yPos);
		}
		
		function draw(){
//			var loop = setInterval(function(){
				context.clearRect(0,0,Width,Height);
				
				drawTrackArc();
				drawOuterArc(angle,opts.color);
				
				numOfPercentage(angle,opts.color,progressTxt);
//				if(angle>endAngle){
//					clearInterval(loop);
//				}

//			},1000/60);
		}
		draw();
		return this;
	};
})(jQuery);

