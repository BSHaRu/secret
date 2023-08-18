CREATE DATABASE bigdata;

SELECT * FROM dept;
SELECT * FROM emp;

-- 변수 선언과 초기화
-- 변수를 사용할라면 반드시 실행시켜서 등록해놔야됨
-- 여러개 동시에 실행시킬 때 : 블록지정 후 crtl + shift + 엔터
SET @val1 = 10;
SET @val2 = 3;
SET @val3 = 3.14;
SET @val4 = 'hey';

-- @를 통해서 변수를 표시 
-- @ 사용자 변수 | @@ 시스템 변수
-- := 대입연산자 | = 비교연산자 or 대입연산자
SET @val5 := 10;

SELECT @val1 FROM DUAL;

SELECT @val2 + @val3;

/***********************************************************/
-- MySQL 내장 함수
-- dual table : 데이터가 없고, 함수계산을 위해 사용하는 가상의 테이블
-- 				모든 RDBMS에 존재하는 테스트용 테이블 | 생략가능

SELECT 1+1 FROM dual;

-- 현재 날짜와 시간에 대해 알려주는 함수
SELECT sysdate(), now(), curdate(), curtime(), current_timestamp();

-- 현재 사용자 정보
SELECT current_user(); 
SELECT user();

-- 문자열 관련된 함수
-- 대소문자 처리
SELECT 'Welcome to Mysql', upper('Welcome to Mysql'), lower('Welcome to Mysql');

-- 문자열 길이 : byte단위로 길이를 표시
SELECT length('MySQL'), length('마이에스큐엘'); 

SET @temp = 'Welcome to MySQL';

-- 문자열 추출
-- (문자열, 시작위치, 개수)
SELECT substr(@temp,4,3);
-- 음수는 뒤에서 부터 
SELECT substr(@temp,-5,3);
/******************************************************/

SELECT hiredate FROM emp;
-- 사원정보를 사원번호, 사원명, 입사년도, 월로 출력
-- hiredate(입사년도)  0000-00-00
DESC emp;
SELECT empno, ename, substr(hiredate,1,4) AS '입사년도',
substr(hiredate,6,2) AS '월' FROM emp;

-- 특정 문자의 위치 찾기
SELECT instr('WELCOME TO MYSQL', 'O'); -- instr(찾고자 하는 문자열, 찾고 싶은 글자)
SELECT instr('Welcome To MySQL', 'O'); -- 대소문자 구분 안함
SELECT instr('이것이 MySQL이다','다');

-- 문자열에 공백을 제거하는 함수 
-- '				MySQL'
SELECT '                    MySQL';
SELECT ltrim('              MySQL'); --  왼쪽 공백 제거
SELECT rtrim('          MySQL'     ); -- 오른쪽 공백 제거

-- 양쪽에서 특정문자 제거
SELECT trim('a' FROM 'aaaaMySQLaaaMyaaaa');
SELECT trim(' ' FROM '       MySQL      ');

-- concat() : 문자열을 넘겨받은 매개변수들을 묶어주는 함수
SELECT concat(ename,'은',sal,'를 받습니다.') FROM emp ORDER by sal DESC;

/*********************************************************/
-- 날짜 관련 함수
SELECT sysdate();
SELECT sysdate() - INTERVAl 1 day AS '어제';

SELECT sysdate() - INTERVAl 1 day AS '어제',
	   sysdate() AS '오늘',
       sysdate() + INTERVAL 1 day AS '내일';

SELECT sysdate() - INTERVAL 1 month AS '한달전';
SELECT sysdate() - INTERVAL 1 year AS '1년전',
		sysdate() + INTERVAL 10 year AS '10년후';

-- date_add(기준시간,계산 할 시간) : 기준시간 + 계산 할 시간을 출력해줌
SELECT now(), date_add(now(), INTERVAL 1 MINUTE) AS '1분후';
SELECT now(), date_add(now(), INTERVAL -1 MINUTE) AS '1분전';

-- date_sub(기준시간,계산 할 시간) : 기준시간 - 계산 할 시간을 출력해줌
SELECT now(), date_sub(now(), INTERVAL 1 MINUTE) AS '1분전';
SELECT now(), date_sub(now(), INTERVAL -1 MINUTE) AS '1분후';

-- timestampdiff(시간,비교할열,시간) : 두 시간 사이의 간격(차이)를 계산
SELECT empno, ename, hiredate, now(),
timestampdiff(year,hiredate,now()) FROM emp;

-- 날짜를 형식에 맞는 문자열로 반환하는 함수
-- data_format
/*
	%Y : 4자리 년도 ex)1993 | %y : 2자리 연도 ex)93
	%m 숫자 월(2자리) 		| %c 월 (1자리) 
    %M 긴 월 (영문)  		| %b 짧은 월(영문)
    %d 일자(두자리) 		| %e 일자 (한자리)
	%W(요일 이름 영문)		| %a (짧은 영문 요일)
    &I 시간 (12)			| %H 시간 (24)
    %r hh:mm:ss AM,PM	| %T HH:mm:ss
    %i 분				| %s 초
*/
SELECT now(), date_format(now(), '%Y/%m/%d');
SELECT now(), date_format(now(), '%y/%m/%d %T');

-- CASE문
-- 사원이름 부서번호 부서명을 출력
DESC emp;
SELECT ename, deptno,
	CASE WHEN deptno = 10 THEN 'ACCOUNTING'
		WHEN deptno = 20 THEN 'RESEARCH'
        WHEN deptno = 30 THEN 'SALES'
        WHEN deptno = 40 THEN 'OPERAITINGS'
	END AS dname 
FROM emp ORDER by deptno;

-- Query문을 미리 등록 시켜놓고 필요한 것을 후에 전달함
-- PREPARE | EXECUTE
-- PREPARE 이름 FROM 'Query문';
-- EXECUTE 이름 USING 전달데이터;
PREPARE mQuery FROM
'SELECT ename, sal FROM emp ORDER by sal LIMIT ?'; -- ? : PREPARE을 실행하려면 있어야 하지만 아직 ?의 값은 execute할때 받겠다.

SET @tempVal = 3;
EXECUTE mQuery USING @tempVal;
SET @tempVal = 10;
EXECUTE mQuery USING @tempVal;
/***********************************************************/

-- JOIN : 다수의 테이블 정보를 합쳐 원하는 정보를 검색 하는 것
-- cross join
SELECT * FROM emp, dept;
SELECT count(*) FROM emp;		-- 14;
SELECT count(*) FROM dept;		--  4;
SELECT count(*) FROM emp, dept;	-- 14 * 4 = 56


-- INNER JOIN : 일반적으로 가장 많이 사용 되는 join 형태 | INNER는 생략 가능함
-- 기준 테이블과 조인 테이블 모두 데이터가 존재해야 조회가 됨.
SELECT * FROM emp INNER JOIN dept;
SELECT count(*) FROM emp JOIN dept;	-- 따로 조건이 없으면 cross join이나 inner join이나 똑같다.

SELECT * FROM emp, dept
WHERE emp.deptno = dept.deptno;

SELECT emp.*, dept.* FROM emp INNER JOIN dept
ON emp.deptno = dept.deptno;		-- ON 대신 WHERE 써도 됨

-- natural join : 중복되는 속성을 기준으로 조인
SELECT * FROM emp NATURAL JOIN dept
ORDER BY emp.empno;

-- 조인을 사용해서 뉴욕에서 근무하는 사원의 이름과 급여 출력
SELECT emp.ename, emp.sal			-- 한 테이블에만 있다면 emp를 생략해도 됨. but 둘 이상의 테이블이라면 반드시 명시해줘야됨
FROM emp, dept
WHERE emp.deptno = dept.deptno
AND dept.loc = 'NEW YORK';

-- 조인을 이용해서 SCOTT이 근무하는 부서이름 출력
SELECT dname FROM emp, dept
WHERE emp.deptno = dept.deptno
AND emp.ename = 'SCOTT';

SELECT dname FROM emp NATURAL JOIN dept
WHERE emp.ename = 'SCOTT';

-- 위에껄 서브 쿼리로 한다면..
SELECT dname FROM dept 
WHERE deptno = (
	SELECT deptno FROM emp WHERE ename = 'SCOTT'
);

-- SELF JOIN 
-- 사원의 이름과 그 사원의 매니저 이름을 출력하기
SELECT * FROM emp;

SELECT a.ename AS 사원, b.ename AS 매니저 
FROM emp AS a, emp AS b
WHERE a.mgr = b.empno;

-- SCOTT 이랑 동일한 근무지에 근무하는 사람의 사원명 출력
-- SCOTT은 제외
SELECT a.ename, b.ename
FROM emp AS a, emp AS b
WHERE a.deptno = b.deptno
AND a.ename = 'SCOTT'
AND b.ename != 'SCOTT';

-- outer join 
-- LEFT | RIGHT
-- 일치하지 않는 값이라도 남아 있는 테이블 값이 존재하면 검색에 포함.
-- 사원의 이름과 매니저의 이름을 출력
-- 매니저가 아닌 사람의 목록도 같이 출력
SELECT * FROM emp;

SELECT A.ename AS '매니저', B.ename AS '사원'
FROM emp A RIGHT JOIN emp B 	--  별칭달때 AS 생략 가능 // RIGHT JOIN : 
ON A.mgr = B.empno ORDER by A.ename DESC;

-- 매니저가 없는 사원의 정보도 출력
SELECT A.ename, B.ename
FROM emp A LEFT JOIN emp B  
ON A.mgr = B.empno ORDER by A.ename DESC;

/*******************************************************/
-- emp table과 salgrad table을 join하여
-- 각 사원의 급여 등급을 사원명, 급여, 급여등급으로 출력
SELECT e.ename 사원, e.sal 급여, s.grade 급여등급
FROM emp e, salgrade s
WHERE e.sal BETWEEN s.losal AND s.hisal;

-- 급여 등급별 인원수와 평균 급여 출력
-- 급여 등급, 인원수 , 평균 급여 
SELECT s.grade 급여등급, count(*) 인원수, avg(e.sal) 평균급여
FROM emp e, salgrade s
WHERE e.sal BETWEEN s.losal AND s.hisal
GROUP by s.grade ORDER by s.grade DESC; 












