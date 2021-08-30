SELECT USER(), DATABASE();

SHOW tables;

-- INSERT 
INSERT into title values(1, '사장');
INSERT into title values(2, '부장');

INSERT into department values(1, '기획', 10);
INSERT into department values(2, '영업', 9);

INSERT into employee values(4377, '정재현', 1, null, 5000000, 1);
INSERT into employee values(1003, '이민형', 2, 4377, 2500000, 2);


-- desc & SELECT 
desc title;
SELECT tno, tname from title;
SELECT tno, tname from title where tno = 2;

desc department;
SELECT deptno, deptname, floor from department;
SELECT deptno, deptname, floor from department where deptno = 2;

desc employee;
SELECT empno, empname, title, manager, salary, dno from employee;
SELECT empno, empname, title, manager, salary, dno from employee where empno = 1013;

-- UPDATE 
UPDATE title set tname = '사원' where tno = 3;

UPDATE department set deptname = '영업' where deptno = 2;

UPDATE employee 
	set empname = '이동혁', title = 2, manager = null, salary = 3300000, dno = 1 
where empno = 1003;

-- DELETE 
DELETE from title where tno = 2;

DELETE from department where deptno = 2;

DELETE from employee where empno = 1013;
