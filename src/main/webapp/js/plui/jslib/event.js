var Event = {
	
	/**
     * ΪԪ�ذ��¼�.
     * @param {Object} elem		:	Ԫ��DOM����.
     * @param {String} type		:	�¼�����,����'on'.
     * @param {Function} func	:	�¼��߼�.
     */
    addEvent : function(elem, type, func){
        if (document.addEventListener) {
            elem.addEventListener(type, func, false);
        }
        else {
            elem.attachEvent('on' + type, func);
        }
    },
	
	/**
	 * ע��������ڽ������¼�.
	 * @param {Function} onResizend	:	�޲λص�����.
	 */
	onResizend : function(onResizend){
		
		/**
		 * <<<�㷨˵��>>>
		 * --------------------------------------------------------------------------------- 
		 * 1. Ĭ�ϴ���״̬ normal.
		 * 2. �������ڴ�Сʱ״̬ resizing.
		 * 3. �������ڴ�Сʱ���ö���״̬Ϊ resizing, ��������ʱ����. ���Ѵ�����ʱ����,����������.
		 * 4. ��500�����û�ж���������С,����Ϊ��������,ִ��resizend�¼�.
		 */
			
		var actionState = 'normal',
			taskPtr = null,
			timeOutTask = function(){
				taskPtr && clearTimeout(taskPtr);
				taskPtr = setTimeout(function(){
					onResizend && onResizend();
					actionState = 'normal';
				},500)
			};	
							
		this.addEvent(
			window, 
			'resize', 
			function(){
				actionState = 'resizing';			
				timeOutTask();
			}
		);
	},
	
	/**
	 * ע�Ὺʼ��������ʱ�¼�.
	 * @param {Function} onResizestart	:	�޲λص�����.
	 */
	onResizestart : function(onResizestart){		
		var isExecuted = false;	
		this.onResizend(function(){isExecuted = false;});				
		this.addEvent(
			window, 
			'resize', 
			function(){				
				if(!isExecuted){
					onResizestart && onResizestart();
					isExecuted = true;
				}
			}
		);
	}	
}
