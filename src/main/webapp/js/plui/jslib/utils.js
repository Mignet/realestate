
//������ϲ����ظ��Ĳ��ֻ���ȥ���ϲ�������Ԫ�ػ��졣
Array.prototype.combine = function(obj){
	if (obj instanceof Array)
		obj.forEach(function(e, index, array){
			this.combine(e);
		});
	else
		if(this.indexOf(obj)<0)
			this.push(obj);
}

//�Ӷ�����������ȥ���������е��ظ�Ԫ�ء�
Array.prototype.remove = function(obj){
	if (obj instanceof Array)
		obj.forEach(function(e, index, array){
			this.remove(e);
		});
	else
		if(this.indexOf(obj)>-1)
			this.splice(this.indexOf(obj),1);
}
