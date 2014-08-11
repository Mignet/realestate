/**
 * jQuery alert plugin
 * Version 1.0.3  (11/27/2009)
 * @requires jQuery v1.2.6+
 *
 */
;(function($) {
/**
* @name alert
* @type jQuery
* @cat Plugins/alerttip
* @return jQuery
* @author   li xiangyang <lxy19791111@163.com>
* @param    options ��ѡ����
*/

/**
 * 
 * �˲��ּ�����javascriptԭ����alert��������,ʹ�����ڱ�У��ʱ��ʾ����ʾ��Ϣ���û���˵���Ѻ�. 
 * 
 * 
 * @example $('#tip).alert('hello world.');
 * @desc ����һ���������ʹ�÷���,��������idΪtip��Ԫ�������ʾһ��DIV������.
 *
 *
 * @example $('#tip').alert({
 *  position: 'right',
 *  focus: true,
 *  alertzIndex: 999,
 *  alertClass: 'corner'
 * });
 * @desc ���ǿ���ͨ������ṩ��options����,ʹDIV��������ʾ��������Ҫ�ĵط�,�����Ƿ���ͬʱʹ���ý���,��������ʾ����ʽ(��ʽ���������jquery.alert.css)
 * 
 */

$.alert = {version: '1.0.3'};
var $alertContainer,$alertMsg,$alertTable;
$.fn.alert = function(msg, options) {
	return this.each(function(){
		var opts = $.extend(true, {}, $.fn.alert.defaults, options || {});
		var alertzIndex = +opts.alertzIndex;
		var position = opts.position ? opts.position : 'right'; 
		if(!$('#alertContainer').length){
			$(['<div id="alertContainer">',
					'<table cellspacing="0" cellpadding="0" border="0">',
							'<tbody>',
								'<tr>',
									'<td class="alert_tl" />',
									'<td class="alert_tc">',
										'<div />',
									'</td>',
									'<td class="alert_tr" />',
								'</tr>',
								'<tr>',
									'<td class="alert_ml" />',
									'<td class="alert_mc" id="alertMsg"></td>',
									'<td class="alert_mr" />',
								'</tr>',
								'<tr>',
									'<td class="alert_bl" />',
									'<td class="alert_bc">',
										'<div />',
									'</td>',
									'<td class="alert_br" />',
								'</tr>',
							'</tbody>',
				    '</table>',
			   '</div>'].join('')).appendTo('body');
			$alertContainer = $('#alertContainer').css({position:'absolute',overflow:'hidden'}).hide();
			$alertTable = $alertContainer.find('table');
			$alertMsg = $('#alertMsg');
			if($.fn.bgiframe){
				$alertContainer.bgiframe();
			}
		}// end if
		var $obj = $(this);
		// ��ʾ����
	    $alertMsg.html(msg);
	    // ��ʾtuna_alert Ϊ��ʾ��Ϣ������
	    $alertTable.removeClass().addClass('alert-' + opts.alertClass);
	    // ����zIndex����ʾ��ʾDIV
	    $alertContainer.css({zIndex:alertzIndex}).show();
	    // ����λ��
	    var offset = $obj.offset();
	    var alertTop = offset.top;
	    var alertLeft = offset.left;
	    var oHeight = $obj.height();
	    var oWidth = $obj.width();
	    var offsetTop = ($obj.outerHeight() - oHeight)/2;
	    var offsetLeft = ($obj.outerWidth() - oWidth)/2;
	    if(position == 'right'){
		    alertTop = alertTop - ($alertContainer.height()/2 - $obj.height()/2) + offsetTop;
		    alertLeft = alertLeft + oWidth + offsetLeft;
	    }else if(position == 'top'){
	    	alertTop = alertTop - $alertContainer.height();
	    }else if(position == 'bottom'){
	    	alertTop = alertTop + $obj.height() + (offsetTop * 2);
	    }
	    $alertContainer.css('left', alertLeft + 'px').css('top', alertTop + 'px');
	    // ������������ʽ
	    //$obj.addClass('invalid');	
	    /** У��ͨ�����ô˺��� */
	    function hideMsg(event) {
	    	// ����������ɾ����ʽ
	        //$obj.removeClass('invalid');
	        // ����У����ʾ��
	        $alertContainer.hide();
	        // ɾ��������blur�¼���m�����İ�
	        $obj.unbind('blur', hideMsg);
	        // ɾ��body��blur�¼���hideMsg�����İ�
	        $('body').unbind('mousedown', hideMsg);
	    }
	
	    var flag = 1;
	    var events = $obj.data("events");
	    // ���Ԫ�ز����� flag=0
	    if ($obj[0].disabled) {
	        flag = 0;
	    } else {
	    	// �������������blur�¼�,��flag��Ϊ0,ʹ���ڶ����Ͻ�hideMsg����ע���blur�¼�
	    	// ���ⴥ�������ϵ�blur�¼�ʱͬʱִ��hideMsg��������������ʾ����ʾDIV����
	    	if(events && events['blur']){
	    		flag = 0;
	    	}
	    	// ���blur�¼�����У��ʱ��ѭ������
	    	if(opts.focus){
		    	// �������������ִ��У������ϵĽ����¼�
		        setTimeout(function () {
		        	try {
		        		$obj.trigger('focus');
		        	} catch (e) {
		        		flag = 0;
		        	}}, 0);
	    	}
	    }
	    if (flag) {
	    	// �����������������ע��hideMsg������blur�¼�
	        $obj.bind('blur', hideMsg);
	    } else {
	    	// �������������body��ע��hideMsg������mousedown�¼�
	        $('body').bind('mousedown', hideMsg);
	    }
	});//end each
};
// Ĭ�ϲ���
$.fn.alert.defaults = {
	position: 			'right', 	// λ���ַ���(��ѡ)Ĭ��Ϊright,��ѡΪtop,bottom
	focus: 				true,  		// �Ƿ���У������ý���(���blur�¼�����У��ʱ��ѭ������)Ĭ��Ϊtrue�ö����ý���
	alertzIndex: 		999,
	alertClass:			'default'	// 'alert-' + alertClass.
};
})(jQuery);
