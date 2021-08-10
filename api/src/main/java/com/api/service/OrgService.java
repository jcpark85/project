package com.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.dao.OrgRepository;
import com.api.entity.Org;

@Service
public class OrgService {
	private OrgRepository repository;

	public OrgService(OrgRepository repository) {
		super();
		this.repository = repository;
	}
	
	// 상위부서 조회 및 세팅
	public void getParentOrg(List<Org> orgList, Org org) {
		Org newOrg = new Org();
		
		if(org.getParentCode() == null || "".equals(org.getParentCode())) {
			return;
		}
		
		newOrg = repository.findByCodeAndTypeNot(org.getParentCode(), "Member");
		orgList.add(newOrg);
		
		getParentOrg(orgList, newOrg);
	}
	
	// 트리구조 세팅
	public void setOrgTree(List<Org> orgList) {
		List<Org> compList = new ArrayList<Org>();
		List<Org> divList = new ArrayList<Org>();
		List<Org> deptList = new ArrayList<Org>();
		List<Org> memList = new ArrayList<Org>();
		
		for(Org org : orgList) {
			if("Company".equals(org.getType())) {
				compList.add(org);
			} else if("Division".equals(org.getType())) {
				divList.add(org);
			} else if("Department".equals(org.getType())) {
				deptList.add(org);
			} else if("Member".equals(org.getType())) {
				memList.add(org);
			}
		}
		
		//........
	}
}
