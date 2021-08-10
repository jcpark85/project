package com.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.dao.OrgRepository;
import com.api.entity.Org;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {
	
	@Autowired
	private OrgRepository orgRepository;
	
	private int id;
	private String type;
	private String name;
	private String code;
	
	@Test
	public void insert() {
		Org org = new Org();
		
		id = 1;
		type = "Company";
		name = "ABC회사";
		code = "ROOT";
		
		org.setId(id);
		org.setType(type);
		org.setName(name);
		org.setCode(code);
		org.setParentCode(null);
		org.setManager(false);
		
		Org saveOrg = orgRepository.save(org);
		
		assertEquals(id, saveOrg.getId());
		assertEquals(type, saveOrg.getType());
		assertEquals(name, saveOrg.getName());
		assertEquals(code, saveOrg.getCode());
	}
	
	@Test
	public void select() {
		Org org = new Org();
		
		id = 1;
		type = "Company";
		name = "ABC회사";
		code = "ROOT";
		
		org.setId(id);
		org.setType(type);
		org.setName(name);
		org.setCode(code);
		org.setParentCode(null);
		org.setManager(false);
		
		orgRepository.save(org);
		
		List<Org> orgList = orgRepository.findAll();
		Org orgs = orgList.get(0);
		
        assertThat(orgs.getId()).isEqualTo(id);
        assertThat(orgs.getType()).isEqualTo(type);
        assertThat(orgs.getName()).isEqualTo(name);
        assertThat(orgs.getCode()).isEqualTo(code);
	}
}
