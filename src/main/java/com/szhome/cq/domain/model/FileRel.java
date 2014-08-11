package com.szhome.cq.domain.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "bus_file_rel")
public class FileRel extends BaseDomain<FileRel> {
	@Id
	private String id;
	private String uploadid;
	private String opinion_id;

	public FileRel(){
		super();
		this.t = FileRel.class;
	}

	public FileRel(String id){
		super();
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}

	public void setOpinion_id(String opinion_id) {
		this.opinion_id = opinion_id;
	}

	public String getId() {
		return id;
	}

	public String getUploadid() {
		return uploadid;
	}

	public String getOpinion_id() {
		return opinion_id;
	}

}

