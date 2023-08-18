-- 제약 조건
CREATE TABLE test_usertbl(
	userID char(8), -- AUTO_INCREMENT : 정수값에만 사용 가능
    name VARCHAR(10) NOT NULL,
    email VARCHAR(50) NULL UNIQUE,
    birthYear INT CHECK(birthYear >= 1900 AND birthYear <= 2021),
    height INT CHECK(height > 0),
    mDate TIMESTAMP NULL DEFAULT now(),
    PRIMARY KEY(userID)
);

INSERT INTO test_usertbl(userID,name,email,birthYear,height)
VALUE('HGD','홍길동','hgd@naver.com',1999,175);
SELECT * FROM test_usertbl;

INSERT INTO test_usertbl(userID,name,email,birthYear,height)
VALUE('KYS','김유신','kys@naver.com',1997,170);

INSERT INTO test_usertbl(userID,name,email,birthYear,height)
VALUE('LCH','이춘향',null,1995,153);

INSERT INTO test_usertbl(userID,name,email,birthYear,height)
VALUE('LML','이몽룡',null,2000,'182');
SELECT * FROM test_usertbl;

CREATE TABLE test_prodtbl(
	code char(3) NOT NULL,
    id char(4) NOT NULL,
    pdate TIMESTAMP NOT NULL,
    PRIMARY KEY(code, id) -- PR KEY는 유일한거라 1개밖에 지정이 안되지만, 속성은 여러개 지정가능하다.
);						-- -> code랑 id가 둘다 동일할 경우에만 중복으로 인정

INSERT INTO test_prodtbl
VALUE('p_1','001',now());
SELECT * FROM test_prodtbl;

INSERT INTO test_prodtbl
VALUE('p_1','002',now());

INSERT INTO test_prodtbl
VALUE('p_2','001',now());
SELECT * FROM test_prodtbl;

/************************************************/
-- VIEW(가상의 테이블)
-- 물리적으로 존재하지 않지만 SELECT 문을 통해 생성된 구조와 정보를 가지고 있음
-- 일반적인 VIEW : CREATE VIEW view이름 AS SELECT 문을 통해 개체 생성 
-- inline VIEW : 질의 안에 FROM 테이블 위치에 직접 SELECT 문을 적용 
CREATE VIEW v_emp AS SELECT empno, ename FROM emp;
DESC v_emp;
SELECT * FROM v_emp;
DROP VIEW IF EXISTS v_emp;

-- SELECT 문의 결과를 이름을 가지는 view라는 개체 생성
-- 실제 데이터를 비추는 창문에 비유
-- 보안적인 목적으로 사용
-- 사용자는 사용되고 있는 데이터가 실제 테이블인지 view인지 구별하기 힘듬
-- 실제 테이블의 구조를 파악하기 힘듬

CREATE OR REPLACE VIEW v_usertbl AS 
SELECT userID, name FROM usertbl;
DESC usertbl;
DESC v_usertbl;

INSERT INTO v_usertbl
VALUE('ABC','고구마'); -- 실제테이블에는 NULL값이 아닌 값들이 들어가면 안된다고 설정해놨기때문에 해당값이 insert 안되는거임

INSERT INTO v_usertbl
VALUE('ABC','고구마',1970,'강원도',null,null,50,now()); -- v_usertbl은 ID랑 name 2개만 있기때문에 실제테이블 값들을 알아도 insert가 안됨

SELECT * FROM v_usertbl;
UPDATE v_usertbl SET name = '홍길동'
WHERE userID = 'BBK'; -- v_usertbl에 있는 값이 해당 값이 존재하기때문에 수정은 가능함
SELECT * FROM usertbl;

DELETE FROM v_usertbl WHERE userID = 'bbk';

-- view에 존재하지 않는 열은 사용 불가
UPDATE v_usertbl SET birthYear = 1990
WHERE userID = 'LSG'; -- birthYear가 없기때문에 이건 안됨
rollback;

-- 인라인 뷰
-- 서브쿼리가 FROM 절 안에서 사용되는 경우, 해당 서브쿼리를 인라인 뷰라고 함.
-- FROM 절에서 사용된 서브쿼리의 결과가 하나의 테이블에 대한 VIEW 처럼 사용

-- 인라인 뷰를 이용해서 부서별 평균 급여가 2500 이상인 부서의
-- 부서 번호, 평균 급여를 출력
SELECT * FROM (
	SELECT deptno, avg(sal) AS '평균급여' FROM emp
    GROUP by deptno
) AS temp				-- 이렇게 생성한 인라인 뷰는 무조건 별칭을 달아줘야된다.
WHERE 평균급여 >= 1000;

-- 부서별 평균 급여와 급여등급을 인라인 뷰를 이용해서 출력
SELECT e.deptno, 평균급여, grade FROM(
	SELECT deptno, avg(sal) AS '평균급여' FROM emp
    GROUP by deptno
) AS e, salgrade s			-- 인라인 뷰랑 salgrade와 join함 
WHERE 평균급여 BETWEEN s.losal AND s.hisal;

 -- Question
 -- 부서별 평균급여와 급여등급을 검색 
 -- 부서이름, 부서번호, 평균급여, 급여등급 형식으로 출력
 SELECT d.dname, e.deptno, e.평균급여, s.grade FROM(
	SELECT deptno, avg(sal) AS '평균급여' FROM emp
    GROUP BY deptno
) AS e, dept d, salgrade s			 
WHERE 평균급여 BETWEEN s.losal AND s.hisal 
AND e.deptno = d.deptno;

SELECT d.dname, e.deptno, e.평균급여, s.grade FROM(
	SELECT deptno, avg(sal) AS '평균급여' FROM emp
    GROUP by deptno
) AS e NATURAL JOIN dept d, salgrade s			 
WHERE 평균급여 BETWEEN s.losal AND s.hisal;

CREATE VIEW avg_group_emp AS 
SELECT deptno, avg(sal) AS '평균급여' FROM emp
GROUP by deptno;

SELECT d.dname, e.deptno, e.평균급여, s.grade 
FROM avg_group_emp e NATURAL JOIN dept d, salgrade s			 
WHERE 평균급여 BETWEEN s.losal AND s.hisal;


/*******************************************************************/
-- INDEX 색인 목록
-- data를 찾기 위한 데이터
-- 책의 목차에 비유
-- 특정 테이블의 특정 열을 지정하여 인덱스 생성
-- 지정한 인덱스를 이용하여 데이터를 검색
-- 목적은 빠른 검색
-- PRIMARY KEY를 지정한 열에 대해서는 자동으로 INDEX 생성
-- 나머지 열이 검색 조건이 된다면 직접 인덱스를 생성해서 사용.

-- index를 생성하는 방법
-- CREATE INDEX 인덱스 이름 ON table이름(열 이름);
-- ALTER TABLE table 이름 ADD INDEX 인덱스 이름(열 이름);
ALTER TABLE emp ADD INDEX idx_emp_sal(sal);
SHOW INDEXES FROM emp;
CREATE INDEX inx_emp_sal ON emp(sal);

-- index 삭제
-- DROP INDEX 인덱스 이름 ON database이름.table이름;
-- ALTER TABLE table이름 DROP INDEX 인덱스 이름;
DROP INDEX inx_emp_sal ON bigdata.emp;
SHOW INDEXES FROM emp;
ALTER TABLE emp DROP INDEX idx_emp_sal;

use employees;
show tables;
SELECT count(*) FROM employees;
DESC employees;

SELECT * FROM employees WHERE gender ='M';

CREATE INDEX idx_emp_gender ON employees(gender);
show INDEXES FROM employees;

-- index는 모든 열에 생성하는 것은 좋지 않음
-- 저장공간 차지
-- 인덱스가 생성된 열에 삽입 or 삭제가 일어나면
-- 인덱스 페이지를 새로 생성해야 할 수 있어서 성능에 악영향
-- 검색에 자주 사용되는 열에만 생성
-- 데이터 변경이 자주 일어나지 않는 테이블에 생성
-- 검색 쿼리에 영향을 주는게 인덱스

/****************************************************************/
-- 스토어 프로시저(Stored Procedure)
-- 여러개의 쿼리 혹은 동작을 프로시저라는 개체로 묶어서 저장
-- 프로시저 이름을 통해서 작동 시키므로 내부의 쿼리를 숨길 수 있음
-- 작성된 프로시저는 CALL 이라는 예약어를 활용해서 사용(호출)

-- DELIMITER : 구문 문자
SELECT * FROM emp;
SELECT * FROM dept;
/*
	DELIMITER //
	CREATE PROCEDURE 프로시저 이름(매개변수...)
		BEGIN
			실행 할 내용(쿼리문 작성)
		END // 
	DELIMITER ;
    CALL 프로시저이름(인자값...);
*/
commit;
use bigdata;

DELIMITER //
CREATE PROCEDURE readEMP()
	BEGIN
		SELECT * FROM EMP;
    END //
DELIMITER ;

CALL readEmp();

-- 매개변수를 넘겨 받는 프로시저
DELIMITER // 
CREATE PROCEDURE info_emp(IN _empno INT) 
	BEGIN
		SELECT * FROM emp WHERE empno = _empno;
    END //
DELIMITER ;

CALL info_emp(7902);

-- 입력값 이상의 급여를 받는 사원의 
-- 사원번호, 이름, 입사일, 급여를 검색하는 프로시저
DELIMITER $$
USE `bigdata`$$
CREATE PROCEDURE `info_sal_over` (IN _sal INT)
BEGIN
	SELECT empno, ename, hiredate, sal FROM emp
    WHERE sal >= _sal;
END$$
DELIMITER ;

CALL info_sal_over(1500);

-- 여러개의 매개변수를 전달받는 프로시저
-- 두개의 급여 금액을 입력받아 두 급여사이의 급여를 받는 사원에 대한 정보 검색

DELIMITER //
CREATE PROCEDURE info_sal_between(
	IN a_sal INT, IN b_sal INT
)
	BEGIN
		SELECT * FROM emp
        WHERE sal BETWEEN a_sal AND b_sal;
    END //
DELIMITER ;

CALL info_sal_between(2000,3000);

-- usertbl 회원 이름을 입력받아서
-- 회원의 나이에 따라 1980년 이전 출생 : 나이가 많으시네요
-- 1980년 이후 출생 : 아직 젊으시네요.
DELIMITER //
	CREATE PROCEDURE checkYear(
		IN uname VARCHAR(10)
    )
BEGIN
	-- DECLARE : 정수값을 저장하는 변수 yearBirth 선언
	DECLARE yearBirth INT;
    SELECT birthYear INTO yearBirth FROM usertbl
    WHERE name = uname;
    
    IF(yearBirth >= 1980) THEN
		SELECT '아직 젊으시네요...' AS ANSWER;
    ELSE 
		SELECT '나이가 많으시네요..' AS ANSWER;
    END IF; -- if문 종료
END //    
DELIMITER ;

CALL checkYear('이승기');


-- PROCEDURE roof
-- 구구단의 단수를 입력받아서 해당 단수를 출력하고 table에 저장
CREATE TABLE temp_tbl(txt TEXT);

DELIMITER //
	CREATE PROCEDURE whileTest(
		IN num INT
	)
BEGIN
	DECLARE str VARCHAR(100); -- 각 단을 문자열로 저장
    DECLARE i INT;			-- 구구단 뒷자리
    SET str = '';
    SET i = 1;
    -- 사용자에게 입력 받은 구구단 수 : num
    WHILE(i < 10) DO
		SET str = concat(str, ' ', num, 'X', i, '=', num*i);
        INSERT INTO temp_tbl VALUES(str);
        SET i = i + 1;			-- 뒷자리 수 증가
        SELECT str AS RESUlT;	-- 문자열 출력
    END WHILE; -- while문 종료
END //
DELIMITER ;

CALL whileTEst(8);
SELECT * FROM temp_tbl;

CREATE TABLE member_tbl(
	num INT PRIMARY KEY AUTO_INCREMENT,	-- 회원번호
    id VARCHAR(50) UNIQUE,				-- 사용자 id
    pw VARCHAR(50) NOT NULL,			-- 사용자 pw
    name VARCHAR(10),					-- 이름
    regDate TIMESTAMP default now()		-- 회원등록일
);

INSERT INTO member_tbl(id,pw,name)
VALUES('id001','pw001','홍길동');
SELECT * FROM member_tbl;

INSERT INTO member_tbl(id,pw,name)
VALUES('id002','pw002','김유신');

SELECT LAST_INSERT_ID();

UPDATE member_tbl SET id = 'id003'
WHERE num = LAST_INSERT_ID();

-- 매개변수로 id, pw 넘겨받아서 id랑 pw가 일치하면 true
-- 존재하지 않으면 false 출력 
DELIMITER $$
	CREATE PROCEDURE loginCheck(
		IN _id VARCHAR(50),
        IN _pw VARCHAR(50),
        OUT answer BOOLEAN
    )
BEGIN
	-- 검색결과를 문자열로 저장할 변수
    DECLARE result VARCHAR(10);
    SET result = (
		SELECT id FROM member_tbl
        WHERE id = _id AND pw = _pw
    );
    
    IF(result IS NOT NULL) THEN
		SELECT TRUE INTO answer;
	ELSE
		SELECT FALSE INTO answer;
	END IF;
END $$
DELIMITER ; 

CALL loginCheck('id001','pw001',@answer);
-- 0 : false | 1 : true
SELECT @answer;

/********************************************************/
-- 트리거
-- 특정 테이블 INSERT, UPDATE, DELETE 같은 DML이 수행되었을 때
-- DB에 등록된 쿼리문을 자동으로 동작하도록 작성된 프로그램.
-- 사용자의 직접 호출되는게 아니라 DB에 의해서 자동으로 호출되는것.
-- DML의 transaction과 주기를 같이 한다.

/*
delimiter $$
	CREATE TRIGGER trigger이름 
		{BEFORE | AFTER}
        {INSERT | UPDATE | DELETE}
        ON table이름
        FOR EACH ROW 
        BEGIN
			-- 트리거 실행 할 내용
		END $$
delimiter ;
*/
commit;

delimiter $$
	CREATE TRIGGER test_trg -- 트리거 이름 지정
    AFTER INSERT 			-- 삽입 이후에 실행
    ON member_tbl			-- 트리거를 부착 할 테이블
    FOR EACH ROW 			-- 각 행마다 적용
BEGIN
	SET @result = 'member insert';
END $$
delimiter ;

SET @result = '';
SELECT @result;
INSERT INTO member_tbl(id,pw,name)
VALUES('id002','pw002','이춘향');

UPDATE member_tbl SET name = '김유신'
WHERE pw = 'pw003';
SELECT @result;

DESC back_up_member_tbl;
CREATE TABLE back_up_member_tbl(
	SELECT * FROM member_tbl WHERE 1 = 0
);

delimiter $$
	CREATE TRIGGER backup_trg
    AFTER DELETE
    ON member_tbl
    FOR EACH ROW
BEGIN
	-- 삭제된 행의 정보 - 열(속성)으로 접근
    -- 삭제된 행정보 == OLD
    INSERT INTO back_up_member_tbl(num, id, pw, name, regDate)
    VALUES(OLD.num, OLD.id, OLD.pw, OLD.name, OLD.regDate);
END $$
delimiter ;

SELECT * FROM back_up_member_tbl;
SELECT * FROM member_tbl;

DELETE FROM member_tbl WHERE num = 3;

delimiter //
	CREATE TRIGGER before_usertbl
    BEFORE INSERT
    ON usertbl
    FOR EACH ROW
BEGIN
	-- OLD | UPDATE | DELETE - 기존정보
    -- NEW | UPDATE | INSERT - 새로운 정보
    IF NEW.birthYear < 1900 THEN
		SET NEW.birthYear = 0;
    ELSEIF NEW.birthYear > YEAR(now()) THEN
		SET NEW.birthYEAR = YEAR(now());
    END IF;
END //
delimiter ;

INSERT INTO usertbl
VALUES('PJS','박지성',1981,'터키','050','12345678','170','2020-12-25');

SELECT * FROM usertbl WHERE userID = 'PSJ';

INSERT INTO usertbl
VALUES('GGD','고길동',2988,'평양','02','00000000','164','2012-04-25');

SELECT * FROM usertbl WHERE userID = 'GGD';













