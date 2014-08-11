
//两数组合并，重复的部分会消去。合并后数组元素互异。
Array.prototype.combine = function(obj){
	if (obj instanceof Array)
		obj.forEach(function(e, index, array){
			this.combine(e);
		});
	else
		if(this.indexOf(obj)<0)
			this.push(obj);
}

//从对象数组中移去参数数组中的重复元素。
Array.prototype.remove = function(obj){
	if (obj instanceof Array)
		obj.forEach(function(e, index, array){
			this.remove(e);
		});
	else
		if(this.indexOf(obj)>-1)
			this.splice(this.indexOf(obj),1);
}
