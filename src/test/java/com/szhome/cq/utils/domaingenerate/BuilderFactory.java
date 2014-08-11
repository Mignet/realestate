package com.szhome.cq.utils.domaingenerate;

/**
 * ����Builder���󹤳���.
 * 
 * @author Mignet
 */

public class BuilderFactory {

	private TableColumnBean columnBean = null;

	public BuilderFactory(TableColumnBean columnBean) {
		this.columnBean = columnBean;
	}

	// ���ݱ��е��ֶ����ͣ����첻ͬ��Builder.
	public Builder getBuilder() {
		if (columnBean.getData_type().equals("varchar2")) {
			return new Builder("String", columnBean);
		} else if (columnBean.getData_type().equals("date")) {
			return new Builder("Date", columnBean);
		} else if (columnBean.getData_type().equals("int")
				|| columnBean.getData_type().equals("integer")
				|| columnBean.getData_type().equals("long")) {
			return new Builder("int", columnBean);
		} else if (columnBean.getData_type().equals("char")) {
			return new Builder("String", columnBean);
		} else if (columnBean.getData_type().equals("float")) {
			return new Builder("float", columnBean);
		} else if (columnBean.getData_type().equals("blob")) {
			return new Builder("Blob", columnBean);
		} else if (columnBean.getData_type().equals("clob")) {
			return new Builder("Clob", columnBean);
		} else if (columnBean.getData_type().equals("number")) {
			return new Builder("double", columnBean);
		} else {
			return new Builder("String", columnBean);
		}
	}
}

