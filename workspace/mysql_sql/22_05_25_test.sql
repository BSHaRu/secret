CREATE SCHEMA bigdata_test;
use bigdata_test;

SELECT * FROM dept;	

SELECT * FROM emp;

INSERT INTO emp
VALUES(8000,'MASTER','SALESMAN',7839,'1982-06-07',1200,500,40);
SELECT * FROM emp WHERE empno = 8000;

UPDATE emp SET comm = 600 WHERE empno = 7566;
SELECT * FROM emp WHERE empno = 7566;

DELETE FROM emp WHERE empno = 8000;
SELECT *FROM emp ORDER by empno DESC;

SELECT @@autocommit;
SET autocommit = 0;

DELETE FROM emp;
SELECT * FROM emp;

rollback;
SELECT * FROM emp;

SELECT deptno, count(*) AS '부서별인원' FROM emp
GROUP by deptno WITH ROLLUP;

CREATE USER testUser@'localhost' IDENTIFIED BY '12345';
SELECT * FROM mysql.USER WHERE user = 'testUser';

GRANT SELECT,INSERT,UPDATE,DELETE ON bigdata_test.* TO testUser@'localhost';
SHOW GRANTS FOR testUser@'localhost';


DELIMITER //
CREATE PROCEDURE test3_1()
	BEGIN
		SELECT e.empno, e.ename, d.dname 
		FROM emp e NATURAL JOIN dept d
		WHERE e.sal >= 2000 ORDER by dname;
	END //
DELIMITER ;
CALL test3_1();


SELECT * FROM emp WHERE empno = 7369;

DELIMITER //
CREATE PROCEDURE tset3_2(
	IN _empno INT, IN _deptno INT
)
	BEGIN
		UPDATE emp SET deptno = 10
        WHERE empno = 7369;
    END //
DELIMITER ;

call tset3_2(7369, 10);
SELECT * FROM emp WHERE empno = 7369;
commit;

CREATE TABLE back_up_emp LIKE emp;
SELECT * FROM back_up_emp;

DELIMITER //
	CREATE TRIGGER emp_trg
    AFTER DELETE
    ON emp
    FOR EACH ROW
    BEGIN
		INSERT INTO back_up_emp(empno, ename, job, mgr, hiredate, sal, comm, deptno)
        VALUES(old.empno, old.ename, old.job, old.mgr, old.hiredate, old.sal, old.comm, old.deptno);
    END //
DELIMITER ;

DELETE FROM emp WHERE empno = 7369;
SELECT * FROM back_up_emp;

-- CREATE INDEX 인덱스 이름 ON table이름(열 이름);
CREATE INDEX idx_emp_ename ON emp(ename);

SHOW INDEXES FROM emp;

-- DROP INDEX 인덱스 이름 ON database이름.table이름;
DROP INDEX idx_emp_ename ON bigdata_test.emp;
SHOW INDEXES FROM emp;
