package com.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.annotations.DynamicInsert;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@IdClass(OrgPK.class)
public class Org {
	@Id
	private int id;
	
	@Column
	private String type;
	
	@Column
	private String name;
	
	@Id
	private String code;
	
	@Column
	private boolean manager;
	
	@Column(name = "parent_code")
	private String parentCode;
}
