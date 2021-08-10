package com.api.vo;

import lombok.Data;

@Data
public class SearchParamVO {
	private String deptCode;
	private boolean deptOnly;
	private String searchType;
	private String searchKeyword;
}