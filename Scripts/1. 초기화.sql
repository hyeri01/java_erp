-- 내 스키마
DROP SCHEMA IF EXISTS Java_erp;

-- 내 스키마
CREATE SCHEMA Java_erp;

-- 직책
CREATE TABLE Java_erp.title (
	tno   INTEGER     NOT NULL COMMENT '직책번호', -- 직책번호
	tname VARCHAR(50) NOT NULL COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE Java_erp.title
	ADD CONSTRAINT PK_title -- 직책 기본키
		PRIMARY KEY (
			tno -- 직책번호
		);

-- 부서
CREATE TABLE Java_erp.department (
	deptno   INTEGER     NOT NULL COMMENT '부서번호', -- 부서번호
	deptname VARCHAR(50) NULL     COMMENT '부서명', -- 부서명
	floor    INTEGER     NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE Java_erp.department
	ADD CONSTRAINT PK_department -- 부서 기본키
		PRIMARY KEY (
			deptno -- 부서번호
		);

-- 사원
CREATE TABLE Java_erp.employee (
	empno   INTEGER     NOT NULL COMMENT '사원번호', -- 사원번호
	empname VARCHAR(50) NOT NULL COMMENT '사원명', -- 사원명
	title   INTEGER     NULL     COMMENT '직책번호', -- 직책번호
	manager INTEGER     NULL     COMMENT '직속상사', -- 직속상사
	salary  INTEGER     NULL     COMMENT '급여', -- 급여
	dno     INTEGER     NULL     COMMENT '부서번호' -- 부서번호
)
COMMENT '사원';

-- 사원
ALTER TABLE Java_erp.employee
	ADD CONSTRAINT PK_employee -- 사원 기본키
		PRIMARY KEY (
			empno -- 사원번호
		);

-- 사원
ALTER TABLE Java_erp.employee
	ADD CONSTRAINT FK_title_TO_employee -- 직책 -> 사원
		FOREIGN KEY (
			title -- 직책번호
		)
		REFERENCES Java_erp.title ( -- 직책
			tno -- 직책번호
		);

-- 사원
ALTER TABLE Java_erp.employee
	ADD CONSTRAINT FK_employee_TO_employee -- 사원 -> 사원
		FOREIGN KEY (
			manager -- 직속상사
		)
		REFERENCES Java_erp.employee ( -- 사원
			empno -- 사원번호
		);

-- 사원
ALTER TABLE Java_erp.employee
	ADD CONSTRAINT FK_department_TO_employee -- 부서 -> 사원
		FOREIGN KEY (
			dno -- 부서번호
		)
		REFERENCES Java_erp.department ( -- 부서
			deptno -- 부서번호
		);
		
	
-- 사용자 계정
Drop user 'user_java_erp@localhost';

GRANT ALL PRIVILEGES
   ON java_erp.*
   TO 'user_java_erp@localhost' IDENTIFIDE by 'rootroot';
	
