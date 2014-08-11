/**
 * calendarbox - plui
 * 使用授权及技术支持请联系: server@netgis.cn
 */
(function ($) {
	
	$.parser.plugins.push('calendarbox');
	
	$.fn.calendarbox = function (options, params) {
		if (typeof options == "string") {
			return $.fn.calendarbox.methods[options](this, params);
		}
		options = options || {};
		if (!WdatePicker) {
			alert("未引入calendarbox.js包！");
			return;
		}
		return this.each(function () {
			var data = $.data(this, "calendarbox");
			var newOptions;
			if (data) {
				newOptions = $.extend(data.options, options);
				data.opts = newOptions;
			} else {
				newOptions = $.extend({}, $.fn.calendarbox.defaults, $.fn.calendarbox.parseOptions(this), options);
				$.data(this, "calendarbox", {
					options : newOptions
				});
			}
			$(this).addClass('Wdate').click(function () {
				WdatePicker(newOptions);
			});
		});
	};
	$.fn.calendarbox.methods = {
		setValue : function (target, params) {
			target.val(params);
		},
		getValue : function (target) {
			return target.val();
		},
		clearValue : function (target) {
			target.val('');
		}
	};
	$.fn.calendarbox.parseOptions = function (target) {
		return $.extend({}, $.parser.parseOptions(target, ["el", "vel", "weekMethod", "lang", "skin", "dateFmt", "realDateFmt", "realTimeFmt", "realFullFmt", "minDate", "maxDate", "startDate", {
						doubleCalendar : "boolean",
						enableKeyboard : "boolean",
						enableInputMask : "boolean",
						autoUpdateOnChanged : "boolean",
						firstDayOfWeek : "number",
						isShowWeek : "boolean",
						highLineWeekDay : "boolean",
						isShowClear : "boolean",
						isShowToday : "boolean",
						isShowOthers : "boolean",
						readOnly : "boolean",
						errDealMode : "boolean",
						autoPickDate : "boolean",
						qsEnabled : "boolean",
						autoShowQS : "boolean",
						opposite : "boolean"
					}
				]));
	};
	$.fn.calendarbox.defaults = {
		dateFmt : 'yyyy-MM-dd HH:mm:ss'
	};

	$.parser.plugins.push('calendarbox');
})(jQuery);