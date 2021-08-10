# project
project repository

[사용 라이브러리]
- Spring Boot
- H2 Database
- Maven

1. 소스 내려받기
2. 서버 구동
- Run As > Spring Boot App

3. H2 Console 접속
- http://localhost:8080/h2-console

4. 테스트 데이터 입력시
- 부서
insert into org(id, type, name, code, parent_code, manager) values(1, 'Company', 'ABC회사', 'ROOT', '', false);
insert into org(id, type, name, code, parent_code, manager) values(2, 'Division', '경영지원본부', 'A100', 'ROOT', false);
insert into org(id, type, name, code, parent_code, manager) values(3, 'Department', '인사팀', 'A101', 'A100', false);
insert into org(id, type, name, code, parent_code, manager) values(4, 'Department', '총무팀', 'A102', 'A100', false);
insert into org(id, type, name, code, parent_code, manager) values(5, 'Department', '법무팀', 'A103', 'A100', false);
insert into org(id, type, name, code, parent_code, manager) values(6, 'Division', 'SW 개발본부', 'B100', 'ROOT', false);
insert into org(id, type, name, code, parent_code, manager) values(7, 'Department', '플랫폼개발부', 'B101', 'B100', false);
insert into org(id, type, name, code, parent_code, manager) values(8, 'Department', '비즈서비스개발부', 'B102', 'B100', false);
insert into org(id, type, name, code, parent_code, manager) values(9, 'Department', '비즈플랫폼팀', 'B103', 'B101', false);
insert into org(id, type, name, code, parent_code, manager) values(10, 'Department', '비즈서비스팀', 'B104', 'B101', false);
insert into org(id, type, name, code, parent_code, manager) values(11, 'Department', '그룹웨어개발팀', 'B105', 'B101', false);
insert into org(id, type, name, code, parent_code, manager) values(12, 'Department', '플랫폼서비스팀', 'B106', 'B102', false);
insert into org(id, type, name, code, parent_code, manager) values(13, 'Department', '모바일개발팀', 'B107', 'B102', false);

- 사용자
insert into org(id, type, name, code, parent_code, manager) values(14, 'Member', '사장1', 'ROOT', '', true);
insert into org(id, type, name, code, parent_code, manager) values(15, 'Member', '경영1', 'A100', '', true);
insert into org(id, type, name, code, parent_code, manager) values(16, 'Member', 'SW1', 'B100', '', true);
insert into org(id, type, name, code, parent_code, manager) values(17, 'Member', '인사1', 'A101', '', false);
insert into org(id, type, name, code, parent_code, manager) values(18, 'Member', '인사2', 'A101', '', false);
insert into org(id, type, name, code, parent_code, manager) values(19, 'Member', '인사3', 'A101', '', false);
insert into org(id, type, name, code, parent_code, manager) values(20, 'Member', '총무1', 'A102', '', false);
insert into org(id, type, name, code, parent_code, manager) values(21, 'Member', '총무2', 'A102', '', false);
insert into org(id, type, name, code, parent_code, manager) values(22, 'Member', '법무1', 'A103', '', false);
insert into org(id, type, name, code, parent_code, manager) values(23, 'Member', '법무2', 'A103', '', false);
insert into org(id, type, name, code, parent_code, manager) values(24, 'Member', '플랫폼1', 'B101', '', true);
insert into org(id, type, name, code, parent_code, manager) values(24, 'Member', '플랫폼1', 'B103', '', false);
insert into org(id, type, name, code, parent_code, manager) values(25, 'Member', '서비스1', 'B102', '', true);
insert into org(id, type, name, code, parent_code, manager) values(26, 'Member', '개발1', 'B103', '', false);
insert into org(id, type, name, code, parent_code, manager) values(27, 'Member', '개발2', 'B103', '', false);
insert into org(id, type, name, code, parent_code, manager) values(28, 'Member', '개발3', 'B104', '', false);
insert into org(id, type, name, code, parent_code, manager) values(29, 'Member', '개발4', 'B104', '', false);
insert into org(id, type, name, code, parent_code, manager) values(30, 'Member', '개발5', 'B105', '', false);
insert into org(id, type, name, code, parent_code, manager) values(31, 'Member', '개발6', 'B105', '', false);
insert into org(id, type, name, code, parent_code, manager) values(32, 'Member', '개발7', 'B106', '', false);
insert into org(id, type, name, code, parent_code, manager) values(33, 'Member', '개발8', 'B106', '', false);
insert into org(id, type, name, code, parent_code, manager) values(34, 'Member', '개발9', 'B107', '', false);
insert into org(id, type, name, code, parent_code, manager) values(35, 'Member', '개발10', 'B107', '', false);
