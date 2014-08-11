package com.szhome.cq.domain.model;

import java.sql.Blob;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "bus_image")
public class Image extends BaseDomain<Image> {
	@Id
	private String img_id;
	private Object image_data;

	public Image(){
		super();
		this.t = Image.class;
	}

	public Image(String img_id){
		super();
		this.img_id = img_id;
	}

	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}

	public void setImage_data(Object image_data) {
		this.image_data = image_data;
	}

	public String getImg_id() {
		return img_id;
	}

	public Object getImage_data() {
		return image_data;
	}

}

