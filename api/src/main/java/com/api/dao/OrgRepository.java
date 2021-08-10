package com.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.entity.Org;

@Repository
public interface OrgRepository extends JpaRepository<Org, Integer> {
	List<Org> findByTypeNot(String type);
	
	@Query(value="select * from Org where (code = ?1 or parent_code = ?2) and type != ?3", nativeQuery=true)
	List<Org> findByCodeOrParentCodeAndTypeNot(String code, String parentCode, String type);
	
	@Query(value="select * from Org where (code = ?1 or parent_code = ?2)", nativeQuery=true)
	List<Org> findByCodeOrParentCode(String code, String parentCode);
	
	List<Org> findByTypeNotAndNameLike(String type, String name);
	
	List<Org> findByTypeAndNameLike(String type, String name);
	
	Org findByCodeAndTypeNot(String code, String type);
}
