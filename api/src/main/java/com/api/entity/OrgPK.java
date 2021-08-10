package com.api.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrgPK implements Serializable {
	private int id;
	private String code;
}
