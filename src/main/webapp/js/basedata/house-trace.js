/*********************************************************************************
*文  件  名  称: house-trace.js
*功  能  描  述: 房屋跟踪
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Mignet
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

//////////////////////////////////////////////////////////////////
		Raphael.fn.connection = function (obj1, obj2, line, bg) {
		    if (obj1.line && obj1.from && obj1.to) {
		        line = obj1;
		        obj1 = line.from;
		        obj2 = line.to;
		    }
		    var bb1 = obj1.getBBox(),
		        bb2 = obj2.getBBox(),
		        p = [{x: bb1.x + bb1.width / 2, y: bb1.y - 1},
		        {x: bb1.x + bb1.width / 2, y: bb1.y + bb1.height + 1},
		        {x: bb1.x - 1, y: bb1.y + bb1.height / 2},
		        {x: bb1.x + bb1.width + 1, y: bb1.y + bb1.height / 2},
		        {x: bb2.x + bb2.width / 2, y: bb2.y - 1},
		        {x: bb2.x + bb2.width / 2, y: bb2.y + bb2.height + 1},
		        {x: bb2.x - 1, y: bb2.y + bb2.height / 2},
		        {x: bb2.x + bb2.width + 1, y: bb2.y + bb2.height / 2}],
		        d = {}, dis = [];
		    for (var i = 0; i < 4; i++) {
		        for (var j = 4; j < 8; j++) {
		            var dx = Math.abs(p[i].x - p[j].x),
		                dy = Math.abs(p[i].y - p[j].y);
		            if ((i == j - 4) || (((i != 3 && j != 6) || p[i].x < p[j].x) && ((i != 2 && j != 7) || p[i].x > p[j].x) && ((i != 0 && j != 5) || p[i].y > p[j].y) && ((i != 1 && j != 4) || p[i].y < p[j].y))) {
		                dis.push(dx + dy);
		                d[dis[dis.length - 1]] = [i, j];
		            }
		        }
		    }
		    if (dis.length == 0) {
		        var res = [0, 4];
		    } else {
		        res = d[Math.min.apply(Math, dis)];
		    }
		    var x1 = p[res[0]].x,
		        y1 = p[res[0]].y,
		        x4 = p[res[1]].x,
		        y4 = p[res[1]].y;
		    dx = Math.max(Math.abs(x1 - x4) / 2, 10);
		    dy = Math.max(Math.abs(y1 - y4) / 2, 10);
		    var x2 = [x1, x1, x1 - dx, x1 + dx][res[0]].toFixed(3),
		        y2 = [y1 - dy, y1 + dy, y1, y1][res[0]].toFixed(3),
		        x3 = [0, 0, 0, 0, x4, x4, x4 - dx, x4 + dx][res[1]].toFixed(3),
		        y3 = [0, 0, 0, 0, y1 + dy, y1 - dy, y4, y4][res[1]].toFixed(3);
		    var path = ["M", x1.toFixed(3), y1.toFixed(3), "C", x2, y2, x3, y3, x4.toFixed(3), y4.toFixed(3)].join(",");
		    if (line && line.line) {
		        line.bg && line.bg.attr({path: path});
		        line.line.attr({path: path});
		    } else {
		        var color = typeof line == "string" ? line : "#000";
		        return {
		            bg: bg && bg.split && this.path(path).attr({stroke: bg.split("|")[0], fill: "none", "stroke-width": bg.split("|")[1] || 3}),
		            line: this.path(path).attr({stroke: color, fill: "none"}),
		            from: obj1,
		            to: obj2
		        };
		    }
		};

		var el;
		//window.onload = function () {
		    var dragger = function () {
		        this.ox = this.type == "rect" ? this.attr("x") : this.attr("cx");
		        this.oy = this.type == "rect" ? this.attr("y") : this.attr("cy");
		        this.animate({"fill-opacity": .2}, 500);
		    };
		    move = function (dx, dy) {
		        var att = this.type == "rect" ? {x: this.ox + dx, y: this.oy + dy} : {cx: this.ox + dx, cy: this.oy + dy};
		        this.attr(att);
		        for (var i = connections.length; i--;) {
		            r.connection(connections[i]);
		        }
		        r.safari();
		    };
		    up = function () {
		        this.animate({"fill-opacity": 0}, 500);
		    }
		//};
//////////////////////////////////////////////////////////////////
		$(function(){
			if(Math.random(0,1)>0.5){
				var color = '#bf200f';
				r = Raphael("holder", screen.availWidth/2, screen.availHeight),
				connections = [],
				shapes = [];
				for(var i=0; i<2; i++){
					var o = r.rect(100+100*i, 180, 80, 40,10);
					shapes.push(o);
					var t = r.text(140+100*i, 200,'房号:201').attr({fill: "#000", "font-size": 16});
				};
				var o = r.rect(300, 100, 80, 40, 10);
				r.text(340, 120,'房号:202').attr({fill: "#000", "font-size": 16});
				shapes.push(o);
				o = r.rect(400, 100, 80, 40, 10);
				r.text(440, 120,'房号:202').attr({fill: "#000", "font-size": 16});
				shapes.push(o);
				o = r.rect(300, 260, 80, 40, 10);
				r.text(340, 280,'房号:203').attr({fill: "#000", "font-size": 16});
				shapes.push(o);
				o = r.rect(400, 260, 80, 40, 10);
				r.text(440, 280,'房号:203').attr({fill: "#000", "font-size": 16});
				shapes.push(o);
				
				for (var i = 0, ii = shapes.length; i < ii; i++) {
					shapes[i].attr({fill: '#000', stroke: '#000', "fill-opacity": 0, "stroke-width": 2, cursor: "move"});
//		        shapes[i].drag(move, dragger, up);
				}
				
				connections.push(r.connection(shapes[0], shapes[1], color, color));
				connections.push(r.connection(shapes[1], shapes[2], color, color));
				connections.push(r.connection(shapes[1], shapes[4], color, color));
				connections.push(r.connection(shapes[2], shapes[3], color, color));
				connections.push(r.connection(shapes[4], shapes[5], color, color));
			}else{
				var color = '#bf200f';
				r = Raphael("holder", screen.availWidth/2, screen.availHeight),
				connections = [],
				shapes = [];
				for(var i=0; i<2; i++){
					var o = r.rect(300+100*i, 180, 80, 40,10);
					shapes.push(o);
					var t = r.text(340+100*i, 200,'房号:201').attr({fill: "#000", "font-size": 16});
				};
				var o = r.rect(100, 100, 80, 40, 10);
				r.text(140, 120,'房号:202').attr({fill: "#000", "font-size": 16});
				shapes.push(o);
				o = r.rect(200, 100, 80, 40, 10);
				r.text(240, 120,'房号:202').attr({fill: "#000", "font-size": 16});
				shapes.push(o);
				o = r.rect(100, 260, 80, 40, 10);
				r.text(140, 280,'房号:203').attr({fill: "#000", "font-size": 16});
				shapes.push(o);
				o = r.rect(200, 260, 80, 40, 10);
				r.text(240, 280,'房号:203').attr({fill: "#000", "font-size": 16});
				shapes.push(o);
				
				for (var i = 0, ii = shapes.length; i < ii; i++) {
					shapes[i].attr({fill: '#000', stroke: '#000', "fill-opacity": 0, "stroke-width": 2, cursor: "move"});
//		        shapes[i].drag(move, dragger, up);
				}
				
				connections.push(r.connection(shapes[0], shapes[1], color, color));
				connections.push(r.connection(shapes[0], shapes[3], color, color));
				connections.push(r.connection(shapes[0], shapes[5], color, color));
				connections.push(r.connection(shapes[2], shapes[3], color, color));
				connections.push(r.connection(shapes[4], shapes[5], color, color));
			}
		});
