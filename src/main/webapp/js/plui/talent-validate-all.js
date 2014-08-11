var tt = {};
tt.Util = {};
tt.f={};

tt.i18Req = "����д{0}!";
tt.i18Req1 = "��ѡ��{0}!";
tt.i18Email = "{0}���������������!";
tt.i18Int = "����������!";
tt.i18Datetime = "��ȷ�ĸ�ʽΪ{1}��Ʃ�磺{2}";

//  num range start
tt.i18NumRange = "{0}������{1}��{2}֮��!";
tt.i18NumRangeMin = "{0}����С�ڵ���{1}!";
tt.i18NumRangeMax = "{0}������ڵ���{1}!";
tt.i18NumRangeExp = "{0}�Ϸ���Χ��{1}!";
//  num range end

tt.i18LenMin = "{0}���ֻ������{1}���ַ������ѳ���{2}���ַ�(һ������ռ�����ַ�)!";
tt.i18Len = "{0}����Ҫ����{1}���ַ����㻹��Ҫ����{2}���ַ�(һ������ռ�����ַ�)!";
tt.i18LenExp = "{0}���ȺϷ���Χ��{1}(һ������ռ�����ַ�)!";
tt.i18LenTip = "��������{0}���ַ�������������{1}���ַ�(һ������ռ�����ַ�)!";

tt.i18Num = "����������!";
tt.i18Regex = "{0}{1}";
tt.i18Ip = "{0}�����������IP��ַ!";
tt.i18Postcode = "{0}�������������������!";
tt.i18Tel = "{0}������������绰����!<br>�Ϸ��ĸ�ʽ���磺0734-1234567�� 021-12345678";
tt.i18Idcard = "{0}�������������֤����!";

tt.i18SelectCountMin = "{0}���ֻ��ѡ��{1}����Ѷ�ѡ{2}��!";
tt.i18SelectCount = "{0}����Ҫѡ��{1}��㻹��Ҫѡ��{2}��!";
tt.i18SelectCount_1 = "{0}����Ҫѡ��{1}��!";
tt.i18SelectCountExp = "{0}ѡ�и����ĺϷ���Χ��{1}!";

tt.ajaxError1="�ӷ������˻�ȡ����ʧ��!";
tt.ajaxError2="�������쳣!";

tt.i18DftOk = null;//'OK!';

//-----  compare validator
tt.i18Compare = "{0}����ȷ!";
tt.i18StrCompare = "{0}Ӧ��{1}{2}{3}!";
tt.i18NumCompare = "{0}Ӧ��{1}{2}{3}!";
tt.i18StrValueCompare = "{0}����{1}{2}!";
tt.i18NumValueCompare = "{0}����{1}{2}!";
tt.operMap = [];
tt.operMap["<"] = "С��";
tt.operMap["<="] = "С�ڻ����";
tt.operMap["=="] = "����";
tt.operMap["!="] = "������";
tt.operMap[">"] = "����";
tt.operMap[">="] = "���ڻ����";

tt.close = "�ر�";

/**
 * mignet@dxtx.com
 */
(function() {
	var initializing = false, fnTest = /xyz/.test(function() {
				//xyz;
			}) ? /\b_super\b/ : /.*/;
	this.tt.C = function() {
	};
	tt.C.ext = function(prop) {
		var _super = this.prototype;
		initializing = true;
		var prototype = new this();
		initializing = false;
		for (var name in prop) {
			prototype[name] = typeof prop[name] == "function"
					&& typeof _super[name] == "function"
					&& fnTest.test(prop[name]) ? (function(name, fn) {
				return function() {
					var tmp = this._super;
					this._super = _super[name];
					var ret = fn.apply(this, arguments);
					this._super = tmp;
					return ret;
				};
			})(name, prop[name]) : prop[name];
		}
		function _c() {
			// �����ʵ����ʱ������ԭ�ͷ���init
			if (!initializing && this.init){this.init.apply(this, arguments);}
				
		}
		_c.prototype = prototype;
		_c.constructor = _c;
		_c.ext = arguments.callee;
		return _c;
	};
})();


tt.Conf = {
	ver: '3.0.0',
	
	errorStyle : 'alert',     //������ʾ�ķ��Ĭ���ṩ��['text', 'alert']
	tipStyle : 'tip',        //tip��ʾ�ķ��Ĭ���ṩ��['tip']
	
	clearOtherError : true, // ����֤ĳһԪ��ʱ���Ƿ����������ֶεĴ�����ʾ��true ���������ֶεĴ�����ʾ
	validateOn : ['change'], // ������֤���¼����͡�  ['keyup', 'focus', 'change']
	
	clrSpace : true,  //��֤ʱ���Ƿ��������ֵ���˵Ŀո�
	
	animate: true,      //�Ƿ�ʹ�ö���(��������ֻ�����ҳ����������jquery.jsʱ����Ч)
	
	highlight: true,    //����꾭��inputԪ��ʱ���Ƿ�Ҫ����Ϣͻ����ʾ
	
	
	//---------  ���������������޸�  ----------------
	pro4MsgId : 'ttTalentMsgId',
	pro4Star : 'ttTalentReqStarId',
	
	eventId : 'talentEventId',
	
	errCls: "talentErrMsg",         //������ʾ��Ϣ����ʽ
	tipCls: "talentTipMsg",         //��֤ͨ��ʱ����Ϣ����ʽ
	errInputCls: "talentErrInput",  //��֤��ͨ��ʱ����������ʽ
	reqStarCls: "talentReqStar"     // ������������������һ���Ǻŵ���ʽ
};
/**
 * 
 * @param {}
 *            target
 * @param {}
 *            type such as "click"
 * @param {}
 *            handler
 */
tt.addEH = function(o, type, handler) {
	if (arguments.length == 4) {
		$(o).unbind(type, handler);
	} else {
		$(o).bind(type, handler);
	}
};

/**
 * ��htmlElement���뵽srcElementԪ�غ���
 * 
 * @param srcElement
 * @param htmlElement
 */
tt.insertAfter = function(src, e) {
	$(e).insertAfter(src);
};

/**
 * Ϊelement���className��ʽ
 * 
 * @param element
 *            ��������Ԫ��
 * @param cls
 *            ��ʽ��
 * @return
 */
tt.addCls = function(element, cls) {
	$(element).addClass(cls);
};
/**
 * Ϊelementɾ��className��ʽ
 * 
 * @param element
 *            ��������Ԫ��
 * @param cls
 *            ��ʽ��
 * @return
 */
tt.rmCls = function(element, cls) {
	$(element).removeClass(cls);
};

/**
 * ɾ��ĳһ��Ԫ��
 * 
 * @param element
 * @return
 */
tt.rmEle = function(e) {
	if (e && e.parentNode) {
		e.parentNode.removeChild(e);
	}
};

/**
 * �൱��string��trim
 * 
 * @param str
 * @return
 */
tt.trim = function(s) {
	return $.trim(s);
};

/**
 * ��������ʵ����js����
 * 
 * @param {}
 *            clazz
 * @return {}
 */
tt.instanceByClass = function(c) {
	eval("var r = new " + c + "();");
	return r;
};

/**
 * 
 * @param {}
 *            v comparedValue
 * @param {}
 *            exp expression
 * @return {}
 */
tt.parRngExp = function(v, exp) {
	var map = {
		'(' : '>',
		'[' : '>='
	};
	var expArr = [];
	var m1 = {
		"{" : "(",
		"}" : ")",
		"|" : "||",
		"&" : "&&"
	};
	for (i = 0; i < exp.length; i++) {
		c = exp.charAt(i);

		if (c == '(' || c == '[') {
			compareOper1 = map[c];

			index1 = exp.indexOf(')');
			index2 = exp.indexOf(']');
			_index = index1;
			compareOper2 = '<';
			if (index1 == -1 && index2 == -1) {
				alert('expression is invalid, not found ] or )!');
				return null;
			} else if (index1 == -1 || (index1 > index2 && index2 != -1)) {
				_index = index2;
				compareOper2 = '<=';
			}
			var singleExp = exp.substring(i + 1, _index);

			var numArr = singleExp.split(',');
			numArr[0] = tt.trim(numArr[0]);

			if (numArr.length == 1) {
				numArr[1] = tt.trim(numArr[0]);
			} else if (numArr.length == 2) {
				numArr[1] = tt.trim(numArr[1]);
			} else {
				alert(singleExp + ' is error!');
				return null;
			}

			expArr.push("(");
			if (numArr[0] != '') {
				expArr.push(v);
				expArr.push(compareOper1);
				expArr.push(numArr[0]);
			}
			if (numArr[0] != '' && numArr[1] != '') {
				expArr.push(' && ');
			}
			if (numArr[1] != '') {
				expArr.push(v);
				expArr.push(compareOper2);
				expArr.push(numArr[1]);
			}

			expArr.push(")");

			exp = exp.substring(_index + 1, exp.length);
			i = 0;
			continue;
		} else if (m1[c]) {
			expArr.push(m1[c]);
		}
	}
	return expArr.join('');
};
/**
 * tt.getI18S("my name is {0}, your name is {1}",["kebo","smis"], 0);
 * tt.getI18S("my name is {1}, your name is {2}",["kebo","smis"], 1);
 */
tt.getI18S = function() {
	var ret = arguments[0];
	if (arguments.length > 1) {
		si = 0; // startIndex
		if (arguments.length == 3) {
			si = arguments[2];
		}
		tt.each(arguments[1], function(item) {
					ret = ret.replace("{" + si + "}", item);
					si++;
				}, this);
	}
	return ret;
};
Array.prototype.ttCons = function(e) {
	i = 0;
	for (; i < this.length && this[i] != e; i++);
	return !(i == this.length);
};

Array.prototype.ttIndexOf = function(obj) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == obj) {
			return i;
		}
	}
	return -1;
};
/**
 * 
 * @param {}
 *            e
 * @return {} true:����
 */

/**
 * ɾ��ָ����ŵ�Ԫ��
 * 
 * @param {}
 *            index
 */
Array.prototype.ttRmAt = function(index) {
	this.splice(index, 1);
	return this;
};
/**
 * ��������ɾ��ָ��Ԫ��
 * 
 * @param {}
 *            obj
 */
Array.prototype.ttRm = function(obj) {
	var index = this.ttIndexOf(obj);
	if (index >= 0) {
		this.ttRmAt(index);
	}
	tt.rmNull(this);
	return this;
};

tt.getStrLen = function(s) {
	var len = 0;
	var c = -1;
	for (var i = 0; i < s.length; i++) {
		c = s.charCodeAt(i);
		if (c >= 0 && c <= 128)
			len += 1;
		else
			len += 2;
	}
	return len;
};
tt.$ = function(id) {
	if (id) {
		return document.getElementById(id);
	}
	return null;

};

/**
 * 
 * @param {}
 *            arr Ҫ����������
 * @param {}
 *            call
 * @param {}
 *            thisObj
 * @param {}
 *            eachType 1:���鷽ʽ��2:������ʽ Ĭ��Ϊ1
 */
tt.each = function(arr, callback, thisObj, eachType) {
	if (arr) {
		if (!eachType || eachType != 1) {
			for (var i = 0; i < arr.length; i++) { // ���鷽ʽ
				callback.call(thisObj, arr[i]);
			}
		} else {
			for (var name in arr) {
				callback.call(thisObj, arr[name]);
			}
		}
	}
};

/**
 * ��ȡԪ�ص�λ����Ϣ
 * 
 * @param {}
 *            e
 * @return {} {"t":t,'l':l,"b":b,'r':r};
 */
tt.getPos = function(e) {
	var p = $(e).position();
	var rect = e.getBoundingClientRect();
	var width = rect.right - rect.left;
	var height = rect.bottom - rect.top;
	return {
		"t" : p.top,
		'l' : p.left,
		"b" : p.top + height,
		'r' : p.left + width
	};
};

/**
 * ��srcE�Ƶ�targetE����
 * 
 * @param {}
 *            srcE
 * @param {}
 *            targetE
 */
tt.moveToPos = function(srcE, targetE) {
	var targetpostion = tt.getPos(targetE);
	srcE.style.zIndex = 99999;
	srcE.style.position = "absolute";

	var top = targetpostion.t - 2;
	var left = targetpostion.r + 8;

	try {
		if (tt.Conf.animate) {
			srcE.style.display = "none";
			srcE.style.top = top;
			srcE.style.left = left;
			$(srcE).fadeIn("slow");
		} else {
			throw e1;
		}
	} catch (e1) {
		srcE.style.top = top;
		srcE.style.left = left;
	}
};

tt.getSelectedCount = function(j, es) {
	if (!es) {
		return 0;
	}

	var types = tt.inputType(es[j]);
	var c = 0;

	if (types.isSelect) {
		for (var i = 0; i < es[j].options.length; i++) {
			if (es[j].options[i].selected) {
				c++;
			}
		}
	} else {
		for (var i = 0; i < es.length; i++) {
			if (es[i].checked) {
				c++;
			}
		}
	}
	return c;
};

/**
 * 
 * @param {}
 *            e element
 * @return {}
 */
tt.inputType = function(e) {
	return {
		'isSelect' : e.tagName == "SELECT",
		'isCheckbox' : e.tagName == "INPUT" && e.type == 'checkbox',
		'isRadio' : e.tagName == "INPUT" && e.type == 'radio'
	};
};

/**
 * ��ȡ���ʽ��ֵ
 * 
 * @param {}
 *            exp ���磺(#{myid} / 34) * {myname} //
 * @param {}
 *            index used for "getElementsByName()[index]"
 * @return (44 / 34) * 22
 */
tt.getExp = function(exp, index) {
	var idRex = /\#{1}\{{1}([^}]+)\}{1}/gi;
	var nameRex = /\${1}\{{1}([^}]+)\}{1}/gi;

	var ret = exp;
	while ((result = idRex.exec(exp)) != null) {
		ret = ret.replace(result[0], tt.$(result[1]).value);
	}
	while ((result = nameRex.exec(exp)) != null) {
		var es = document.getElementsByName((result[1]));
		var v = (es[index]) ? es[index].value : es[es.length - 1].value;
		ret = ret.replace(result[0], v);
	}

	return ret;
};

tt.setVfP = function(clrSpace) {
	tt.vf.clrSpace = clrSpace;
};

tt.rmNull = function(arr) {
	var temp = [];
	for (var i = 0; i < arr.length; i++) {
		if (arr[i]) {
			temp.push(arr[i]);
		}
	}
	return temp;
};
var global_msg = "";
/**
 * validate all
 */
tt.validate = function() {
	tt.setVfP(true);
	tt.vf.vFilter = "a";
	//return tt.vf.vBf();
	return {result:tt.vf.vBf(),message:global_msg};
};

/**
 * validate form eg:tt.validateForm('formname1', 'formname2', ... );
 */
tt.validateForm = function () {
	tt.setVfP(true);
	tt.vf.vFilter = "f";
	return tt.vf.vBf(new tt.f.Form(arguments));
};
/**
 * validate element eg:tt.validateElement(element1, element2, ... );
 */
tt.validateElement = function() {
	tt.setVfP(false);
	tt.vf.vFilter = "e";
	return tt.vf.vBf(new tt.f.Ele(arguments));
};
/**
 * validate id eg:tt.validateId('id1', 'id2', ... );
 */
tt.validateId = function() {
	tt.setVfP(true);
	tt.vf.vFilter = "i";
	return tt.vf.vBf(new tt.f.Id(arguments));
};
/**
 * validate name eg:tt.validateName('name1', 'name2', ... );
 */
tt.validateName = function() {
	tt.setVfP(true);
	tt.vf.vFilter = "n";
	return tt.vf.vBf(new tt.f.Name(arguments));
};
/**
 * remove all validator form validatorFactory
 */
tt.removeAll = function() {
	tt.vf.rmAll();
};
tt.clearMsg = function(){
	tt.vf.clrAllMsg();
};
/**
 * ��Ϣ��ʾ��Ĺرհ�ť����������
 * 
 * @param {}
 *            obj
 * @param {}
 *            closeObj
 */
tt.closeHandler = function(obj, closeObj, e, eCls, closeCls) {
	var _closeCls = closeCls + "_hover";
	this.click = function() {
		tt.rmEle(obj);
		tt.rmCls(e, eCls + '_1');
	};
	this.mouseover = function() {
		tt.addCls(closeObj, _closeCls);
	};
	this.mouseout = function() {
		tt.rmCls(closeObj, _closeCls);
	};
};
tt.msgHandler = function(e, cls, msgCls, target) {
	var _cls = cls + "_1";
	var _msgcls = msgCls + "_hover";
	this.mouseover = function() {
		tt.addCls(e, _cls);
		tt.addCls(target, _msgcls);
	};
	this.mouseout = function() {
		tt.rmCls(e, _cls);
		tt.rmCls(target, _msgcls);
	};
	this.click = function() {
		try {
			e.focus(true);
		} catch (e1) {}
	};
};

/**
 * baseHandler
 */
tt.bh = tt.C.ext({
	setV:function(v)
	{
		this.v = v;
		return this;
	},
	setE:function(e)
	{
		this.e = e;
		return this;
	},
	setF:function(f)
	{
		this.f = f;
		return this;
	},
	setVal:function(val)
	{
		this.val = val;
		return this;
	},
	setIndex:function(index)
	{
		this.index = index;
		return this;
	},
	needHandle:function()
	{
		return !(this.e.style.display == 'none' || this.e.disabled)//���ڲ��ɼ���Ԫ��,������
	},
	render:function(cls, msg, closeCls, inputCls) {
			var e = this.e;
			
			var _inputE = this.f.getInputId();
			_inputE = _inputE ? _inputE : tt.$(this.v.getInputId());
			var ee = _inputE ? _inputE :  e;
			
			var types = tt.inputType(e);
			var swrap = null;
			
			if (tt.Conf.msgId) {
				swrap = document.createElement("li");
				
			} else {
				swrap = document.createElement("span");
			}
			
			
			var s = document.createElement("span");  //�����ʾ��Ϣ
			swrap.appendChild(s);
			s.style.width = "auto";
			
			var msgId = this.f.getMsgId(e);
			msgId = msgId ? msgId : this.v.getMsgId();
			msgId = msgId ? msgId : tt.Conf.msgId;
			if (msgId) {
				tt.$(msgId).appendChild(swrap);
			} else {
				if (types.isCheckbox || types.isRadio) {
					tt.moveToPos(swrap, _inputE ? _inputE : this.f.es[this.f.es.length - 1]);
				} else {
					tt.moveToPos(swrap, ee);
				}
				ee.parentNode.insertBefore(swrap, ee);
				
				tt.vf.msgs.push({"msg":swrap,"ele":ee});
			}
			
			swrap.id = e[tt.Conf.pro4MsgId];
			swrap.className = cls;
			s.innerHTML = msg;
			swrap.title = msg;
			s.style.display = "inline";
			
			var close = document.createElement("span");
			swrap.appendChild(close);
			
			if (msgId) {
				try {
					if (tt.vf.vFilter != "e" && tt.Conf.animate) {
						var $swrap = $(swrap);
						//$swrap.fadeOut("fast");
						$swrap.fadeIn("slow");
					} else{}
				}catch(e1) {
				}
			}
			
			close.className = closeCls;
			close.innerHTML = "X";
			close.title = tt.close;
			
			tt.addCls(ee, inputCls);
			
			var _closeHandler = new tt.closeHandler(swrap, close, ee, inputCls, closeCls);
			tt.addEH(close, "click", _closeHandler["click"]);
			
			
			var _msgHandler =  new tt.msgHandler(ee, inputCls, cls, swrap);
			tt.addEH(s, "click", new tt.msgHandler(ee, inputCls, cls, s)["click"]);
			
			if (tt.Conf.highlight) {
//				tt.addEH(close, "mouseover", _closeHandler["mouseover"]);
//				tt.addEH(close, "mouseout", _closeHandler["mouseout"]);
				
				tt.addEH(swrap, "mouseover", _msgHandler["mouseover"]);
				tt.addEH(swrap, "mouseout", _msgHandler["mouseout"]);
			}
			
			
	}
});
tt.text = tt.bh.ext({
	h:function()
	{
		var i18n = this.v.getI18(this.f.label, this.e, this.f, this.index, this.val);
		if (this.needHandle() && i18n) {
			this.render(tt.Conf.errCls, i18n, "talentClose", tt.Conf.errInputCls);
		}
	}
});
tt.alert = tt.bh.ext({
	h:function()
	{
		var i18n = this.v.getI18(this.f.label);
		if (this.needHandle() && i18n) {
			tt.addCls(this.e, tt.Conf.errInputCls);
	        //alert(i18n);
	        //��������Ϣ���ص������
	        global_msg = i18n;
		}
	}
});
tt.tip = tt.bh.ext({
	h:function()
	{
		var tipMsg = this.v.getTip(this.e,this.f,this.v,this.val,this.index);
		if (this.needHandle() && tipMsg) {
			this.render(tt.Conf.tipCls, tipMsg, "talentClose talentCloseTip", "talentSucInput");
		}
	}
});
tt.vHandler = function(e, f) {
	var c = "talentErrMsg_hover";
	this.h = function() {
		var es = f.errorStyle ? f.errorStyle : tt.Conf.errorStyle;
		if (es == 'alert') {
			return;
		}
		tt.validateElement(e);
		if (tt.Conf.highlight) {
			tt.addCls(tt.$(e[tt.Conf.pro4MsgId]), c);
		}
	}, this.rmCls = function() {
		tt.rmCls(tt.$(e[tt.Conf.pro4MsgId]), c);
	}, this.addCls = function() {
		tt.addCls(tt.$(e[tt.Conf.pro4MsgId]), c);
	}
};

/**
 * @author Tanyaowu
 * @version 2.0.0
 * @date 2011-8-13 BaseValidator
 */
tt.BV = tt.C.ext({
	init : function() {
		this.fs = [];
		this.i18ps = [];
		this.isInFactory = false;// ����֤���Ƿ��Ѿ�����֤����������.false:���ڹ�����;true:���ڹ�����.
		this.clrSpace = tt.Conf.clrSpace;
		this.vType = "l";
		this.setI18n = this.setI18;
	},
	/**
	 * ��ʾ��Ϣ��ʾ�ĵط�
	 * 
	 * @param {}
	 *            msgId
	 */
	setMsgId : function(msgId) {
		this.msgId = msgId;
		return this;
	},
	getMsgId : function() {
		return this.msgId;
	},
	/**
	 * ���ڴ���input������id
	 * 
	 * @param {}
	 *            inputId
	 */
	setInputId : function(inputId) {
		this.inputId = inputId;
		return this;
	},
	getInputId : function() {
		return this.inputId;
	},
	getVType : function() {
		return this.vType;
	},
	setVType : function(vType) {
		this.vType = vType;
		return this;
	},
	initI18n : function(m) {
		!this.i18n ? this.i18n = m : null;
		return this;
	},
	setI18 : function(m) {
		this.i18n = m;
		return this;
	},
	setClrSpace : function(c) {
		this.clrSpace = c;
	},

	/**
	 * 
	 * @param {}
	 *            s ��Ҫ����֤�Ĵ����������ô˴��п�����������ߵĿո�
	 * @param {}
	 *            i index ��ǰԪ����ţ���0��ʼ
	 * @param {}
	 *            es elements
	 * @param {}
	 *            f Field
	 * @return {Boolean} true:��֤ͨ��
	 */
	v : function(s, i, es, f) {
		return true;
	},
	/**
	 * 
	 * @param {}
	 *            f Field
	 */
	doAfterAdd : function(f) {
		for (var i = 0; i < f.es.length; i++) {
			seq = tt.vf.seq++;
			!f.es[i][tt.Conf.pro4MsgId]
					? f.es[i][tt.Conf.pro4MsgId] = tt.Conf.pro4MsgId + seq
					: null;
		}
	},
	/**
	 * ���Ƴ�����Щ����,���������ʵ�ָú���,��Required��֤��,��Ҫȥ������ĺ��Ǻ�
	 */
	doBeforeRm : function(f) {
		this.clrFErr(f);
	},
	_onValid : function(sv, j, es, f) {
		this.handTip(es[j], f, sv, j);
	},
	_onInvalid : function(sv, j, es, f) {
		tt.vf.invalidEs.push(es[j]);
		this.handErr(es[j], f, sv, j);
	},
	_beforeV : function(sv, j, es, f) {
	},
	_afterV : function(sv, j, es, f) {
	},

	onValid : function(sv, j, es, f) {
	},
	onInvalid : function(sv, j, es, f) {
	},
	beforeV : function(sv, j, es, f) {
	},
	afterV : function(sv, j, es, f) {
	},
	/**
	 * 
	 * @param {}
	 *            fl filter
	 * @return {Boolean}
	 */
	vBf : function(fl) {
		var ret = true;
		if (this.vType == 'r') {
			if (['a', 'f'].ttCons(tt.vf.vFilter)) {
				this.v();
			}
			return;
		}

		for (var i = 0; i < this.fs.length; i++) {
			if(!ret){//У������г��ִ��󣬼�����ֹ
				return false;
			}
			var es = this.fs[i].es;
			if (es) {
				for (j = 0; j < es.length; j++) {
					if (tt.vf.invalidEs.ttCons(es[j])) { // û��ͨ��ǰ�����֤��
						continue;
					}

					/** ����Ҫ��֤������֤ͨ���������һ��Ԫ�صĴ��� */
					if (fl && !fl.f(es[j])) { // �������ˣ�����Ҫ��֤
						if (tt.Conf.clearOtherError) {
							this.clrEleErr(es[j], this.fs[i]);
						}
						continue;
					}

					var types = tt.inputType(es[j]);

					var sv = es[j].value;
					sv = sv ? sv : "";

					if (this.clrSpace
							&& (!types['isSelect'] && !types['isCheckbox'] && !types['isRadio'])) {
						sv = tt.trim(sv);
						if (!["e"].ttCons(tt.vf.vFilter)) {
							try{
								es[j].value = sv;
							} catch(e) {
								
							}
							
							
						}
					}

					if (types['isRadio'] || types['isCheckbox']) {
						this.clrFErr(this.fs[i]);
					} else {
						this.clrEleErr(es[j], this.fs[i]);
					}

					this._beforeV(sv, j, es, this.fs[i]);
					this.beforeV(sv, j, es, this.fs[i]);
					if (this.v(sv, j, es, this.fs[i])) { // ��֤ͨ��
						this._onValid(sv, j, es, this.fs[i]);
						this.onValid(sv, j, es, this.fs[i]);
						continue;
					} else {
						this._onInvalid(sv, j, es, this.fs[i]);
						this.onInvalid(sv, j, es, this.fs[i]);
						ret = false;
					}
					this._afterV(sv, j, es, this.fs[i]);
					this.afterV(sv, j, es, this.fs[i]);
				}
			}
		}
		return ret;
	},

	/**
	 * �Ƴ��ֶ� �÷�:xx.rm("name1", "name2", "name3"...);
	 */
	rm : function() {
		return this._rm("name", arguments);
	},
	/**
	 * �Ƴ��ֶ� �÷�:xx.rmId("id1", "id2", "id3"...);
	 */
	rmId : function() {
		return this._rm("id", arguments);
	},
	_rm : function(type, args) {
		for (var i = 0; i < args.length; i++) {
			for (var j = 0; j < this.fs.length; j++) {
				var f = false;
				if (typeof args[i] != "string") {
					f = (this.fs[j] && this.fs[j] == args[i]);
				} else {
					f = (this.fs[j] && this.fs[j][type] == args[i]);
				}

				if (f) {
					this.doBeforeRm(this.fs[j]);
					this.fs[j].doAfterRm(this);
					this.fs[j] = null;
				}
			}
		}
		this.fs = tt.rmNull(this.fs);
		return this;
	},

	/**
	 * �Ƴ������ֶ� �÷�:xxValidator.rmAll();
	 */
	rmAll : function() {
		for (var i = 0; i < this.fs.length; i++) {
			this.rm(this.fs[i]);
		}
		this.fs = [];
	},
	/**
	 * ��Ҫ��֤���ֶμӵ���֤���� �÷�:xx.add("name1", "name2", "name3"...);
	 */
	add : function() {
		return this._addF('name', arguments);
	},
	/**
	 * ��Ҫ��֤���ֶμӵ���֤���� �÷�:xx.addId("id1", "id2", "id3"...);
	 */
	addId : function() {
		return this._addF('id', arguments);
	},

	/**
	 * ��Ҫ��֤���ֶμӵ���֤���� �÷�:xx.addObj(obj1, obj2, obj3...);
	 */
	addObj : function() {
		return this._addF('obj', arguments);
	},

	_addF : function(type, arg) {
		for (var i = 0; i < arg.length; i++) {
			var f = null;
			if (type == 'id') {
				var obj = tt.$(arg[i]);
				// alert(obj.id +" "+ arg[i]);
				if (obj && (obj.id == arg[i]) && this._c('name', obj.name)
						&& this._c('id', arg[i])) {
					f = new tt.Field("", null, arg[i]);
				}
			} else if (type == 'obj') {
				f = new tt.Field("", null, false, arg[i]);
			} else {
				isStr = (typeof arg[i] == 'string');
				var fg = false;
				if (isStr) {
					fg = this._c('name', arg[i]);
				} else {
					fg = (this._c('name', arg[i].name) && this._c('id',
							arg[i].id));
				}

				if (fg) {
					if (isStr) {
						f = new tt.Field("", arg[i]);
					} else {
						f = arg[i];
					}
				}
			}

			if (f != null) {
				f.doAfterAdd(f.label, f.name, f.id, f.e, this);

				this.fs[this.fs.length] = f;
				for (var j = 0; j < f.es.length; j++) {
					if (f.es[j].tt_addedEvent) {
						continue;
					}
					f.es[j].tt_addedEvent = true;
					this.attachE(f.es[j], f);
				}

				if (!this.isInFactory)// ��Ҫʱ�����֤����������
				{
					tt.vf.add(this);
					this.isInFactory = true;
				}
				this.doAfterAdd(f);
			}
		}
		return this;
	},
	/**
	 * 
	 * @param {}
	 *            proName: id, name and so on
	 * @param {}
	 *            value
	 * @return {Boolean}
	 */
	_c : function(proName, value) {
		if (!value) {
			return true;
		}

		for (var i = 0; i < this.fs.length; i++) {
			if (this.fs[i][proName] == value) {
				return false;
			}
		}
		return true;
	},
	/**
	 * ����û����֤ͨ���Ķ���,���������������ѡ��,������ת���ö���,�޸ĸö������ʽ��
	 */
	handErr : function(e, f, val, j) {
		var es = f.errorStyle ? f.errorStyle : tt.Conf.errorStyle;
		var h = tt.instanceByClass("tt." + es);
		h.setV(this).setE(e).setF(f).setVal(val).setIndex(j).h();
	},
	handTip : function(e, f, val, j) {
		var h = tt.instanceByClass("tt." + tt.Conf.tipStyle);
		h.setV(this).setE(e).setF(f).setVal(val).setIndex(j).h();
	},
	/**
	 * ��֤��ͨ��ʱ����ȡ��ʾ���û�����Ϣ
	 * 
	 * @param label
	 */
	getI18 : function(label, e, f, index, val) {
		if (this.i18n) {
			ret = tt.getI18S(this.i18n, [label], 0);
			return tt.getI18S(ret, this.i18ps, 1);
		}
		return null;
	},
	setTip : function(tip) {
		this.tip = tip;
		return this;
	},

	/**
	 * 
	 * @param {}
	 *            e element
	 * @param {}
	 *            f field
	 * @param {}
	 *            v validator
	 * @param {}
	 *            val
	 * @param {}
	 *            index
	 * @return {}
	 */
	getTip : function(e, f, v, val, index) {
		return this.tip;
	},
	/**
	 * ���field�Ĵ���
	 */
	clrFErr : function(f) {
		var es = f.es;
		for (i = 0; i < es.length; i++) {
			this.clrEleErr(es[i], f);
		}
	},
	clrAllErr : function() {
		if (this.fs) {
			for (var i = 0; i < this.fs.length; i++) {
				this.clrFErr(this.fs[i]);
			}
		}
	},
	clrEleErr : function(e, f) {
		if (e) {
			tt.rmCls(e, tt.Conf.errInputCls);
			tt.rmCls(e, "talentSucInput");
			tt.rmCls(e, tt.Conf.errInputCls + "_1");
			tt.rmCls(e, "talentSucInput_1");

			var inputForMsg = f.getInputId();
			if (inputForMsg) {
				tt.rmCls(inputForMsg, tt.Conf.errInputCls);
				tt.rmCls(inputForMsg, "talentSucInput");
			}
			tt.rmEle(tt.$(e[tt.Conf.pro4MsgId]));
		}

	},
	/**
	 * ��ȡ����֤������֤������element
	 */
	getEs : function() {
		es = [];
		for (i = 0; i < this.fs.length; i++) {
			for (var j = 0; j < this.fs[i].es.length; j++) {
				es = es.concat(this.fs[i].es[j]);
			}
		}
		return es;
	},
	getVWhen : function() {
		return this.vWhen;
	},
	setVWhen : function(vWhen) {
		this.vWhen = vWhen;
	},
	/**
	 * ��html element��Щ����Ĵ��������֤�¼�
	 * 
	 * @param {}
	 *            e html element
	 */
	attachE : function(e, f) {
		var es = f.errorStyle ? f.errorStyle : tt.Conf.errorStyle;
		if (es == 'alert') {
			return;
		}

		var types = tt.inputType(e);

		var handler = new tt.vHandler(e, f);
		var hd = handler.h;
		var validateOn = f.validateOn ? f.validateOn :  tt.Conf.validateOn;
		for (var x = 0; x < validateOn.length; x++) {
			var evt = validateOn[x];
			if (types.isCheckbox || types.isRadio) {
				if (evt == 'change') {
					tt.addEH(e, evt, hd);
					break;
				}
				continue;
			}

			if (types.isSelect && e.multiple != true) {
				if (evt == 'change') {
					tt.addEH(e, 'blur', hd);
					break;
				}
				continue;
			}

			tt.addEH(e, evt, hd);
		}

		var ee = f.getInputId();
		if (!ee) {
			ee = e;
		}

		if (tt.Conf.highlight) {
			tt.addEH(ee, "mouseout", handler.rmCls);
			tt.addEH(ee, "mouseover", handler.addCls);
			tt.addEH(ee, "focus", handler.addCls);
			tt.addEH(ee, "blur", handler.rmCls);
		}

	}
});
tt.Field = tt.C.ext({
	init : function(label, name, id, e) {
		this.label = label ? label : "";
		this.name = name ? name : null;
		this.id = id ? id : null;
		this.e = e ? e : null;
		this.vArr = [];
	},
	initEs: function(label, name, id, e){
		es = [];
		
		if(e){
			es.push(e);
		} 
		if (name) {
			es = document.getElementsByName(name);
		}
		if (id) {
			es = [];
			this.id = id;
			e = tt.$(id);
			if (e) {
				es.push(e);
			}
		}
		this.es = es;
	},
	
	/**
	 * ����ʲô�¼��ܴ�����֤
	 * @param {} validateOn ���磺['keyup', 'focus', 'change']
	 */
	setValidateOn : function(validateOn) {
		this.validateOn = validateOn ? validateOn : [];
		return this;
	},
	/**
	 * 
	 * @param {} errorStyle 'text'��'alert'
	 * @return {}
	 */
	setErrorStyle : function(errorStyle) {
		this.errorStyle = errorStyle;
		return this;
	},
	
	doAfterAdd : function(label, name, id, e, validator){
		this.vArr.push(validator);
		this.initEs(label, name, id, e);
		return this;
	},
	doAfterRm: function(validator) {
		this.vArr.ttRm(validator);
	},
	setMsgId:function(id) {
		this.msgId = id;
		return this;
	},
	getMsgId:function(e) {
		return this.msgId;
	},
	setInputId: function(id){
		this.inputForErr = tt.$(id);
		return this;
	},
	getInputId:function(e) {
		return this.inputForErr;
	},
	
	add: function()
	{
		tt.each (arguments, function(item) {
			item.add(this);
		}, this);
		return this;
	},
	
	rm: function()
	{
		tt.each (arguments, function(item) {
			item.rm(this);
		}, this);
		return this;
	},
	
	rmAll: function() {
		for (var i = this.vArr.length - 1; i >=0; i--) {
			this.vArr[i].rm(this);
		}
		return this;
	}
});
tt.f.Dft = tt.C.ext({
	init:function()
	{
		this.p = [];
		var a = arguments;
		if(a.length != 0){
			for (var i =0; i < a[0].length; i++){
				this.p.push(a[0][i]);
			}
		}
	},
	/**
	 * @return true:��ʾ��Ҫ��֤,false:��ʾ��������,����Ҫ��֤.
	 */
	f:function(e)
	{
		return true;//Ĭ�϶���Ҫ��֤
	}
});
tt.f.Form = tt.f.Dft.ext({
	f:function(e)
	{
		return e.form && this.p.ttCons(e.form.getAttribute('name'));
	}
});
tt.f.Ele = tt.f.Dft.ext({
	f:function(e)
	{
		return this.p.ttCons(e);
	}
});
tt.f.Id = tt.f.Dft.ext({
	f:function(e)
	{
		return this.p.ttCons(e.id);
	}
});
tt.f.Name = tt.f.Dft.ext({
	f:function(e)
	{
		return this.p.ttCons(e.name);
	}
});
tt.RV = tt.BV.ext({
set : function(regex, i18n) {
	this.regex = regex;
	this.i18ps[0] = i18n;
	return this;
},

v : function(s) {
	this.initI18n(tt.i18Regex);
	return (!s) || this.regex.test(s);
}
});
tt.ReqV = tt.BV.ext({
v : function(s, i, es, f) {
	this.setI18(tt.i18Req);
	if (es[i].tagName && es[i].tagName == "SELECT") {
		this.setI18(tt.i18Req1);
	}
	return (s);
},
/**
 * ���ֶκ�������Ǻ�
 */
doAfterAdd : function(f) {
	this._super(f);
	//this.addStar(f);���ü��Ǻ�
},
/**
 * ��ע������Щ����,���������ʵ�ָú���,��Required��֤��,��Ҫȥ������ĺ��Ǻ�
 */
doBeforeRm : function(f) {
	this._super(f);
	this.clearStar(f); // ����Ǻ�
},
/**
 * ����Ǻ�
 */
addStar : function(f) {
	for (var i = 0; i < f.es.length; i++) {
		seq = tt.vf.seq++;
		!f.es[i][tt.Conf.pro4Star] ? f.es[i][tt.Conf.pro4Star] = tt.Conf.pro4Star + seq : null;
		tt.insertAfter(f.es[i], tt.getI18S("<span id='{0}' class='{1}'>*</span>", [f.es[i][tt.Conf.pro4Star], tt.Conf.reqStarCls]));
	}
},
/**
 * ����Ǻ�
 */
clearStar : function(f) {
	for (i = 0; i< f.es.length; i++) {
		tt.rmEle(tt.$(f.es[i][tt.Conf.pro4Star]));
	}
}
});
tt.DV = tt.BV.ext({
    set:function(pattern)
    {
    	this.pattern = pattern;
    	return this;
    },
    /**
     * ˵��:���ڵ���֤������ֱ��ժ��validation-framework.js,�������޸�
     */
    v:function(s)
    {
        if (!s)
        {
           return true;//����֤Ϊ�յĴ�
        }
        this.initI18n(tt.i18Datetime);
        
        var dateP = this.pattern;//params[0];
        dateP = dateP ? dateP : "yyyy-mm-dd";
        this.i18ps[0] = dateP;
        this.i18ps[1] = dateP.replace("yyyy","2011").replace("mm","08").replace("dd","08");
        
        var MONTH = "mm";
        var DAY = "dd";
        var YEAR = "yyyy";
        var orderMonth = dateP.indexOf(MONTH);
        var orderDay = dateP.indexOf(DAY);
        var orderYear = dateP.indexOf(YEAR);
        var f = true;
        var dateReg = null;
        
        if ((orderDay < orderYear && orderDay > orderMonth)) {
            var iDelim1 = orderMonth + MONTH.length;
               var iDelim2 = orderDay + DAY.length;
               var delim1 = dateP.substring(iDelim1, iDelim1 + 1);
               var delim2 = dateP.substring(iDelim2, iDelim2 + 1);
               if (iDelim1 == orderDay && iDelim2 == orderYear) {
                dateReg = /^(\\d{2})(\\d{2})(\\d{4})$/;
               } else if (iDelim1 == orderDay) {
                dateReg = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
               } else if (iDelim2 == orderYear) {
                dateReg = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
               } else {
                dateReg = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
               }
        
               var matched = dateReg.exec(s);
               if(matched != null) {
                if (!this._myc(matched[2], matched[1], matched[3])) {
                       f =  false;
                }
               } else {
                   f =  false;
               }
           } else if ((orderMonth < orderYear && orderMonth > orderDay)) { 
            var iDelim1 = orderDay + DAY.length;
               var iDelim2 = orderMonth + MONTH.length;
               var delim1 = dateP.substring(iDelim1, iDelim1 + 1);
               var delim2 = dateP.substring(iDelim2, iDelim2 + 1);
               if (iDelim1 == orderMonth && iDelim2 == orderYear) {
                dateReg = /^(\\d{2})(\\d{2})(\\d{4})$/;
               } else if (iDelim1 == orderMonth) {
                dateReg = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
               } else if (iDelim2 == orderYear) {
                dateReg = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
               } else {
                dateReg = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
               }
               var matched = dateReg.exec(s);
            if(matched != null) {
                if (!this._myc(matched[1], matched[2], matched[3])) {
                    f = false;
                   }
               } else {
                f = false;
               }
           } else if ((orderMonth > orderYear && orderMonth < orderDay)) {
            var iDelim1 = orderYear + YEAR.length;
               var iDelim2 = orderMonth + MONTH.length;
               var delim1 = dateP.substring(iDelim1, iDelim1 + 1);
        
               var delim2 = dateP.substring(iDelim2, iDelim2 + 1);
               if (iDelim1 == orderMonth && iDelim2 == orderDay) {
                dateReg = /^(\\d{4})(\\d{2})(\\d{2})$/;
               } else if (iDelim1 == orderMonth) {
                dateReg = new RegExp("^(\\d{4})(\\d{2})[" + delim2 + "](\\d{2})$");
               } else if (iDelim2 == orderDay) {
                dateReg = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})(\\d{2})$");
               } else {
                dateReg = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{2})$");
               }
            var matched = dateReg.exec(s);
               if(matched != null) {
                if (!this._myc(matched[3], matched[2], matched[1])) {
                       f =  false;
                   }
               } else {
                   f =  false;
               }
           } else {
               f =  false;
           }
        return f;
    },
    _myc:function(d, m, y)
    {
		if (d < 1 || d > 31)
		{
			return false;
		}
		if (m < 1 || m > 12)
		{
			return false;
		}
		
		if ([4,6,9,11].ttCons(m) && (d > 30))
		{
			return false;
		}
		if (m == 2)
		{
			var leap = (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
			if (d>29 || (d == 29 && !leap))
			{
				return false;
			} 
	    }
		return true;
	}
});
tt.NV = tt.BV.ext({
	v:function(s)
	{
		this.initI18n(tt.i18Num);
		return this.isNum(s);
	},
	isNum:function(s)
	{
		return (!s) || (!isNaN(s) && (s.indexOf('.') != (s.length - 1)) );
	}
});
tt.IV = tt.NV.ext({
	v:function(s)
	{
		this.initI18n(tt.i18Int);
		return this.isInt(s);
	},
	isInt:function(s)
	{
		if (this.isNum(s))
		{
			var t = (s % 10) + "";
			return (!s) || t.indexOf(".") == -1;
		}
		return false;
	}
});
tt.NRV = tt.NV.ext({
	init:function(){
		this._super();
		this.needAddNumV = true;
	},
	/**
	 * ������Сֵ,"--"��ʾ����С
	 */
	setMin:function(min)
	{
		this.min = min;
		if (min == '--')//����С
		{
			this.i18n = tt.i18NumRangeMin;
			this.i18ps[0] = this.max;
			
		}else
		{
			this.i18ps[0] = min;
		}
	},
	/**
	 * �������ֵ,"++"��ʾ�����
	 */
	setMax:function(max)
	{
		this.max = max;
		if (max == '++')
		{
			this.i18n = tt.i18NumRangeMax;
			this.i18ps[0] = this.min;
		}
		else
		{
			if (this.min == "--")
			{
				this.i18ps[0] = max;
			}
			else
			{
				this.i18ps[1] = max;
			}
		}
	},
	set:function(min, max)
	{
		if(arguments.length == 2) {
			this.setMin(min);
			this.setMax(max);
		} else {
			this.exp = min;
			this.i18n = tt.i18NumRangeExp;
			this.i18ps[0] = this.exp;
		}
		return this;
	},
	_rm:function(type,args){
		this.numV ? this.numV._rm(type,args) : null;
		this.numV = null;
		return this._super(type,args);
	},
	v:function(s)
	{
		this.initI18n(tt.i18NumRange);
		if (!this.numV && this.needAddNumV){
			this.numV = new tt.NV();
			for (i=0; i < this.fs.length; i++) {
				this.numV.add(this.fs[i]);
			}
		}
		if (!s || !this.isNum(s))//����������֣�����������֤������֤
		{
			return true;
		}
		
		if (s != 0) {
			s = s.replace(/(^0*)/g, "");  //ȥ��ǰ���0�������п��ܱ�����8����
		}
		
		if (this.exp) {
			return eval(tt.parRngExp(s, this.exp));
		}
		
		try 
		{
			if (this.max == "++" && this.min == "--")
			{
				return true;
			}
			
			if (this.max == "++")
			{
				if (eval(s) < eval(this.min))
				{
					return false;
				}else 
				{
					return true;
				}
			}
			
			if (this.min == "--")
			{
				if (eval(s) > eval(this.max))
				{
					return false;
				}else 
				{
					return true;
				}
			}
			
			if (eval(s) > eval(this.max) || eval(s) < eval(this.min))
			{
				return false;
			}
			return true;
		}
		catch (e)
		{
			return false;
		}
	}
});
tt.LV = tt.NRV.ext({
	init:function(){
		this._super();
		this.needAddNumV = false;
	},
	getTip:function(e,f,v,val,index) {
		var len = tt.getStrLen(val);
		if(this.max != '++' && !this.exp && !['f','a'].ttCons(tt.vf.vFilter)) {
			return tt.getI18S(tt.i18LenTip, [len, this.max - len]);
		} else {
			return this._super(e,f,v,val,index);
		}
	},
	v:function(s)
	{
		var len = tt.getStrLen(s);
		var r = this._super(len+"");
		if(!this.exp){
			v = len - this.max;
			if (v > 0) {  //������
				this.i18n = tt.i18LenMin;
				this.i18ps[0] = this.max;
				this.i18ps[1] = v;
			} else if ((v = this.min - len) > 0){ //���Ȳ���
				this.i18n = tt.i18Len;
				this.i18ps[0] = this.min;
				this.i18ps[1] = v;
			}
		} else {
			this.i18n = tt.i18LenExp;
			this.i18ps[0] = this.exp;
		}
		return r;
	}
});
tt.SCV = tt.NRV.ext({
	init:function(){
		this._super();
		this.needAddNumV = false;
	},
	v:function(s, j, es, f)
	{
		var types = tt.inputType(es[j]);
		
		if (es.length > j + 1 && !['e','i'].ttCons(tt.vf.vFilter) && (types.isCheckbox || types.isRadio)) {
			return true; //ֻ�ж�һ��
		}
		
		var count = tt.getSelectedCount(j, es);
		var r = this._super(count+"");
		if(!this.exp){
			v = count - this.max;
			if (v > 0) {  //ѡ ����
				this.i18n = tt.i18SelectCountMin;
				this.i18ps[0] = this.max;
				this.i18ps[1] = v;
			} else if ((v = this.min - count) > 0){ //��ѡ��
				this.i18n = tt.i18SelectCount;
				if (this.min == 1){
					this.i18n = tt.i18SelectCount_1;
				}
				
				this.i18ps[0] = this.min;
				this.i18ps[1] = v;
			}
		} else {
			this.i18n = tt.i18SelectCountExp;
			this.i18ps[0] = this.exp;
		}
		return r;
	}
});
tt.OnlyShow = tt.BV.ext({
	v : function(){
		return this.result;
	},
	getTip : function() {
		return this.result ? this.msg : null;
	},
	getI18 : function() {
		return !this.result ? this.msg : null;
	}
});
tt.RemoteV = tt.BV.ext({
	init:function(){
		this._super();
		this.vType = 'r';
		this.rmId = this.rm;
	},
	v:function()
	{
		var thisObj = this;
		
		var ajaxConf = this.ajaxConf;
		
		var postData = [];
		//[{name:conf.keyConfig.pageIndex, value: pageIndex},{name:conf.keyConfig.pageSize, value:pageSize}];
		if (ajaxConf.form) {
			postData.push($(ajaxConf.form).serialize());
		}
		if (ajaxConf.formId) {
			postData.push($("#" + ajaxConf.formId).serialize());
		}
		if (ajaxConf.postData) {
			var data;
			for (var i = 0; i < ajaxConf.postData.length; i++) {
				data = ajaxConf.postData[i];
				postData.push(data.name + "=" + encodeURIComponent(data.value));
			}
		}
		var postDataStr = postData.join("&");
		
		ajaxConf.data = postDataStr;
		
		$.ajax(ajaxConf);
		
		return true;
	},
	rm:function() {
		tt.vf.rm([this]);
		tt.vf.rm(this.vs);
	},
	add:function() {
		tt.vf.add(this);
		return this;
	},
	/**
	 * 
	 * @param {} ajaxConf ���磺
	 * {
  		url:"xx.do",       //�����url
  		form: formElement, //Ҫ�ύ��form
  		type:"post",    //post/get��Ĭ��Ϊpost
  		async:false,    //true/false��Ĭ��Ϊtrue
  		postData:[{name:"e", value:"ee"},{name:"w", value:"ww"}]
	  }
	 */
	set:function(ajaxConf)
	{
		ajaxConf.async = false;
		ajaxConf.type = ajaxConf.type ? ajaxConf.type : "POST";
	    ajaxConf.dataType = "json";
		ajaxConf.thisObj = this;
		ajaxConf.complete =  function(_data) {
	        //tt_complete();       //�رս�����
			tt.vf.rm(this.thisObj.vs);
			var responseObj;
			if (!_data.responseText){
				this.thisObj.setI18(tt.ajaxError1); //"�ӷ������˻�ȡ����ʧ��!";
				return;
			}
			try {
				responseObj = eval("(" + _data.responseText + ")");
			} catch(e){
				this.thisObj.setI18(tt.ajaxError2); //"�������쳣!";
				return;
			}
			
			/**
			 * {
			 * "name1":{'result':true, 'msg':'��֤�ɹ�'},
			 * "name2":{'result':false, 'msg':'��֤ʧ��'}
			 * }
			 */
			this.thisObj.vs = [];
			for (var item in responseObj) {
				var onlyShow = new tt.OnlyShow().setVType("ext");
				this.thisObj.vs.push(onlyShow);
				onlyShow.result = responseObj[item].result;
				onlyShow.msg = responseObj[item].msg;
				var msgId = responseObj[item].msgId;
				
				var f1;
				if (item.indexOf("id:") != -1) {
					f1 = new tt.Field("", "", item.substring(3)).setMsgId(msgId);
				}
				else {
					f1 = new tt.Field("", item).setMsgId(msgId);
				}
				onlyShow.add(f1);
			}
	    };
		ajaxConf.beforeSend =  function(){};
		
		
		this.ajaxConf = ajaxConf;
		return this;
	}
});
tt.CV = tt.BV.ext({
	/**
	 * @param cmpType
	 *            �Ƚ����� 'n':���ֱȽ�; 'v':�ַ����Ƚϣ�
	 * @param oper
	 *            �ȽϷ�,����Ϊ'<','<=','==','!=','>','>='
	 * @param fOrV
	 *            ���Ƚϵ��ֶλ�ֵ����ʽ
	 * @param showCmpVal
	 *            �Ƿ���ʾ���Ƚϵ�ֵ ����: var field1 = new tt.Field("�û���", "loginName");
	 *            new tt.CV().set('n','>',field1);//Ҫ����Ӵ���֤�����ֶα������field1��ֵ
	 */
	set : function(cmpType, oper, fOrV, showCmpVal) {
		this.cmpType = cmpType;
		this.oper = oper;
		this.cmpF = null; // comparedField
		this.cmpV = null; // comparedValue
		this.i18ps[0] = tt.operMap[this.oper];
		this.showCmpVal = true; // Ĭ��Ϊtrue
		if (arguments.length == 4) {
			this.showCmpVal = showCmpVal;
		}
		if (["string", 'number'].ttCons(typeof fOrV)) {
			this.cmpV = fOrV;
		} else {
			this.cmpF = fOrV;
			this.cmpF.initEs(fOrV.label, fOrV.name, fOrV.id, fOrV.e);
			this.i18ps[1] = fOrV.label;
		}

//		if (!this.showCmpVal) {
//			this.i18n = tt.i18Compare;
//		} else 
		
		if (cmpType == 'n' && this.cmpF) {
			this.i18n = tt.i18NumCompare;
		} else if (cmpType == 'v' && this.cmpF) {
			this.i18n = tt.i18StrCompare;
		} else if (cmpType == 'n' && this.cmpV) {
			this.i18n = tt.i18NumValueCompare;
		} else if (cmpType == 'v' && this.cmpV) {
			this.i18n = tt.i18StrValueCompare;
		} else {
			alert("error cmp type:" + cmpType);
		}
		return this;
	},
	v : function(str, index, es, f) {
		if (!str) {
			return true;
		}

		var _cmpV;
		if (this.cmpV) {
			try {
				if (this.cmpType == 'n') {
					_cmpV = eval(tt.getExp(this.cmpV, index));
				} else if (this.cmpType == 'v') {
					_cmpV = tt.getExp(this.cmpV, index);
				}
			} catch (e) {
				return false;
			}

			if (this.showCmpVal) {
				this.i18ps[1] = "<span class='talentComparedValue'>" + _cmpV
						+ "</span>";
			} 
			else {
				this.i18ps[1] = "";
			}
		} else {
			var es = this.cmpF.es;
			if (es.length == 0) {
				return true;
			}

			_cmpV = (es[index]) ? es[index].value : es[es.length - 1].value;
			if (_cmpV == null || _cmpV == "") {
				return true;
			}

			if (this.showCmpVal) {
				this.i18ps[2] = "<span class='talentComparedValue'>" + _cmpV
						+ "</span>";
			} 
			else {
				this.i18ps[2] = "";
			}
		}

		var s;
		if (this.cmpType == "n")// �����ֱȽ�
		{
			var numV = tt.vf.num;
			if ((!numV.v(str)) || (!numV.v(_cmpV + "")))// ��������
			{
				return true; // ��������������������֤��ȥ��֤
			}

			s = str + this.oper + _cmpV;
		} else if (this.cmpType == "v")// ���ַ����Ƚ�
		{
			s = "\"" + str + "\"" + this.oper + "\"" + _cmpV + "\"";
		}
		return eval(s) == true;
	}
});
tt.GV = tt.BV.ext({
	/**
	 * 
	 * @param {} exp ���磺"tt.vf.req{} && (tt.vf.email{} || tt.vf.idcard{})"
	 */
	set: function(exp){
		
		this.exp = exp;
		return this;
	},
	v : function(s, i, es, f)
	{
		return eval("(" + this.exp.replace(/\{\}{1}/g, ".v(s, i, es, f)") + ")");
	}
});
tt.ExpV = tt.BV.ext({
			set : function(exp) {
				this.exp = exp;
				return this;
			},
			v : function(str, index, es, f) {
				try {
					var _exp = tt.getExp(this.exp, index);
					this.i18ps[0] = _exp;
					return eval(_exp);
				} catch (e) {
					return false;
				}
			}
		});
tt.VF = tt.C.ext({
	init : function() {
		this.localVArr = [];
		this.remoteVArr = [];
		this.extVArr = [];
		
		var c = "(\\d{1,2}|0{2}\\d{1}|1\\d\\d|2[0-4]\\d|25[0-5])";
		var cs = c + "\\.";
		var ipexp = "^" + cs + cs + cs + c + "$";
		this.ip = new tt.RV().set(new RegExp(ipexp)).setI18(tt.i18Ip);
		this.ip.onValid = function (sv, j, es, f) {
			var types = tt.inputType(es[j]);
			if ((types.isSelect || types.isCheckbox || types.isRadio) || !sv) {
				return;
			}
			
			if (tt.vf.vFilter != "e") {
				var arr = sv.split(".");
				var newArr = [];
				for (var i = 0; i < arr.length; i++) {
					var trimV = arr[i].replace(/(^0*)/g, "");
					newArr.push(trimV == "" ? "0" : trimV);
				}
				es[j].value = newArr.join(".");
			}
		};
		
		this.email = new tt.RV().set(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).setI18(tt.i18Email);
		this.postcode = new tt.RV().set(/^[1-9]\d{5}(?!\d)$/).setI18(tt.i18Postcode);
		this.tel = new tt.RV().set(/^\d{3}-\d{8}$|^\d{4}-\d{7}$/).setI18(tt.i18Tel);
		this.idcard = new tt.RV().set(/^\d{15}$|^\d{17}[\d,x,X]{1}$/).setI18(tt.i18Idcard);
		this.req = new tt.ReqV();
		this['int'] = new tt.IV();  
		this.num = new tt.NV();
		
		this.msgs = [];
		this.seq = 0;            //���к�
	},
	rm: function(validatorArr){
		if(validatorArr) {
			var varr;
			for (var j = 0; j < validatorArr.length; j++){
				if (validatorArr[j].getVType() == "l") {
					varr = this.localVArr;
				} else if (validatorArr[j].getVType() == "r"){
					varr = this.remoteVArr;
				} else {
					varr = this.extVArr;
				}
				for (var i = 0; i < varr.length; i++){
					if(validatorArr[j] == varr[i]){
						varr[i].rmAll();
						varr[i] = null;
					}
				}
			}
		}
		this.localVArr = tt.rmNull(this.localVArr);
		this.remoteVArr = tt.rmNull(this.remoteVArr);
		this.extVArr = tt.rmNull(this.extVArr);
	},
	/**
	 * �Ƴ�������֤��
	 */
	rmAll : function() {
		this.rm(this.remoteVArr);
		this.rm(this.localVArr);
		this.rm(this.extVArr);
	},
	clrAllMsg: function(){
		this.clrMsg(this.remoteVArr);
		this.clrMsg(this.localVArr);
		this.clrMsg(this.extVArr);
	},
	clrMsg: function(validatorArr){
		if(validatorArr) {
			var varr;
			for (var j = 0; j < validatorArr.length; j++){
				if (validatorArr[j].getVType() == "l") {
					varr = this.localVArr;
				} else if (validatorArr[j].getVType() == "r"){
					varr = this.remoteVArr;
				} else {
					varr = this.extVArr;
				}
				for (var i = 0; i < varr.length; i++){
					if(validatorArr[j] == varr[i]){
						varr[i].clrAllErr();
					}
				}
			}
		}
	},
	/**
	 * �÷�:tt.vf.add(validator1, validator2, validator3... ...
	 * validatorx);
	 */
	add : function() {
		for ( var i = 0; i < arguments.length; i++) {
			if(arguments[i].getVType() == "l") {
				this.localVArr.push(arguments[i]); 
			} else if(arguments[i].getVType() == "r"){
				this.remoteVArr.push(arguments[i]); 
			} else {
				this.extVArr.push(arguments[i]); 
			}
		}
	},
	vBf : function(f) {
		this.invalidEs = [];
		this.msgs = [];
		
		this.rm(this.extVArr);
		
		var ret = this._vBf(f, this.localVArr);

		if (ret) {
			ret = this._vBf(f, this.remoteVArr);
			ret = this._vBf(f, this.extVArr);
		}
		return ret;
	},
	_vBf: function(f, vArr) {
		var ret = true;
		for (var i = 0; i < vArr.length; i++) {
			var vwhen = vArr[i].getVWhen();
			if (vwhen && !vwhen.ttCons(tt.vf.vFilter)){
				continue;
			}
			if (!vArr[i].vBf(f)) {
				ret = false;
			}
		}
		return ret;
	},
	resizeWindow : function() {
		for (var i = 0; i < tt.vf.msgs.length; i++) {
			tt.moveToPos(tt.vf.msgs[i].msg, tt.vf.msgs[i].ele);
		}
	}
});


tt.vf = new tt.VF();
tt.addEH(window, "resize", tt.vf.resizeWindow);

