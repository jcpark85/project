package com.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.dao.OrgRepository;
import com.api.entity.Org;
import com.api.error.ErrorCode;
import com.api.error.ErrorException;
import com.api.error.ErrorResult;
import com.api.service.OrgService;
import com.api.vo.SearchParamVO;

@RestController
public class OrgController {
	@Autowired
	private OrgService orgService;
	
	private OrgRepository repository;

	public OrgController(OrgRepository repository) {
		super();
		this.repository = repository;
	}

	// 부서 추가
	@PostMapping("/org/dept")
	public Org postDept(Org org) {
		return repository.save(org);
	}
	
	// 부서 수정
	@PutMapping("/org/dept/{deptId}")
	public Org putDept(@PathVariable int deptId, @RequestBody Map<String, String> map) {
		Org org = repository.findById(deptId).orElse(null);
		org.setType(map.get("type"));
		org.setName(map.get("name"));
		org.setCode(map.get("code"));
		
		return repository.save(org);
	}
	
	// 부서 삭제
	@DeleteMapping("/org/dept/{deptId}")
	public void deleteDept(@PathVariable int deptId) {
		repository.deleteById(deptId);
	}
	
	// 부서원 추가
	@PostMapping("/org/member")
	public Org postMember(Org org) {
		return repository.save(org);
	}
	
	// 부서원 수정
	@PutMapping("/org/member/{memberId}")
	public Org putMember(@PathVariable int memberId, @RequestBody Map<String, Object> map) {
		Org org = repository.findById(memberId).orElse(null);
		org.setType((String) map.get("type"));
		org.setName((String) map.get("name"));
		org.setCode((String) map.get("code"));
		org.setManager((Boolean) map.get("manager"));
		
		return repository.save(org);
	}
	
	// 부서원 삭제
	@DeleteMapping("/org/member/{memberId}")
	public void deleteMember(@PathVariable int memberId) {
		repository.deleteById(memberId);
	}
	
	// 조직도 조회
	@GetMapping("/org/organizations")
	public List<Org> getOrgList(SearchParamVO srchVo) {
		List<Org> result = null;
		
		// 1. 조직도 조회 API에서는 부서만(deptOnly) 파라미터에 따라 부서 정보만 응답
		// 2. 부서코드(deptCode) 파라미터를 추가했을 때 해당 부서를 포함하여 하위 부서를 응답
		if(srchVo.isDeptOnly()) {
			if(srchVo.getDeptCode() == null) {
				result = repository.findByTypeNot("Member");
			} else {
				result = repository.findByCodeOrParentCodeAndTypeNot(srchVo.getDeptCode(), srchVo.getDeptCode(), "Member");
			}
		}else {
			if(srchVo.getDeptCode() == null) {
				result = repository.findAll();
			} else {
				result = repository.findByCodeOrParentCode(srchVo.getDeptCode(), srchVo.getDeptCode());
			}
		}
		
		// 3. 부서 검색 파라미터 추가시 검색된 부서들과 관계된 상위 부서를 포함한 트리 구조로 응답
		// 4. 부서원 검색 파라미터 추가시 검색된 부서원과 부서원이 속한 부서, 관계된 상위 부서를 포함한 트리 구조로 응답
		if(srchVo.getSearchType() != null && srchVo.getSearchKeyword() != null) {
			if("dept".equals(srchVo.getSearchType())) {
				// 검색된 부서
				result = repository.findByTypeNotAndNameLike("Member", "%" + srchVo.getSearchKeyword() + "%");
				
				// 상위 부서
				for(int i = 0; i < result.size(); i++) {
					orgService.getParentOrg(result, result.get(i));
				}
				
				result = result.stream().distinct().collect(Collectors.toList());
				
			} else if("member".equals(srchVo.getSearchType())) {
				// 검색된 부서원
				result = repository.findByTypeAndNameLike("Member", "%" + srchVo.getSearchKeyword() + "%");
				
				// 검색된 부서원이 속한 부서
				List<Org> deptList = new ArrayList<Org>();
				for(int i = 0; i < result.size(); i++) {
					Org deptOrg = repository.findByCodeAndTypeNot(result.get(i).getCode(), "Member");
					deptList.add(deptOrg);
				}
				
				// 상위 부서
				for(int j = 0; j < deptList.size(); j++) {
					orgService.getParentOrg(deptList, deptList.get(j));
				}
				
				result.addAll(deptList);
				result = result.stream().distinct().collect(Collectors.toList());
				
			} else {
				throw new ErrorException(ErrorCode.BAD_REQUEST.getErrorCode(), ErrorCode.BAD_REQUEST.getErrorMessage());
			}
		}
		
		if(result == null) {
			throw new ErrorException(ErrorCode.BAD_REQUEST.getErrorCode(), ErrorCode.BAD_REQUEST.getErrorMessage());
		}
		
		// 트리구조로 변경 (미완성)
		//setOrgTree(result);
		
		return result;
	}
	
	@ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResult> errorException(ErrorException e) {

        ErrorResult er = new ErrorResult();
        er.setCode(e.getCode());
        er.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
    }
}
