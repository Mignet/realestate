package com.szhome.cq.utils.domaingenerate;

import org.springframework.util.StringUtils;

/**
 * ¹¹Ôì×Ö·û´®Àà
 * @author	Mignet
 */

public class Builder {

	private String propertyType;
	private String name;
	private String comment;
	private boolean  isPrimary;
	public Builder(String propertyType,TableColumnBean columnBean){
		this.propertyType = propertyType;
		this.name = columnBean.getColumn_name();
		this.comment = columnBean.getComment();
		this.isPrimary = columnBean.isPrimary();
	}
	public String buildProperty(){
		if(isPrimary){
			return Inform.ID_STRING + Inform.PRIVATE_STRING.replace("#{1}",propertyType).replace("#{2}",name);
		}else{
			return Inform.PRIVATE_STRING.replace("#{1}",propertyType).replace("#{2}",name);
		}
	}
	public String buildComment(){
		return Inform.COMMENT_STRING.replace("#{1}",comment);
	}

	public String buildSetFunction(){
		return Inform.SET_FUNC_STRING.replace("#{1}",propertyType).replace("#{2}",name).replace("#{4}", StringUtils.capitalize(name));
	}

	public String buildGetFunction(){
		return Inform.GET_FUNC_STRING.replace("#{1}",propertyType).replace("#{2}",name).replace("#{3}", StringUtils.capitalize(name));
	}

	public String buildConstruct(){
		return  Inform.CONSTRUCT_VARIANT_TYPE.replace("#{1}",propertyType).replace("#{2}",name);
	}

	public String buildConstructVar(){
		return  "		"+Inform.CONSTRUCT_VAR.replace("#{2}",name);
	}
}


