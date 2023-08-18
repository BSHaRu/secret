-- num(int) / id / pass / name / addr / phone / gender / age
CREATE TABLE test_member(
	num INT PRIMARY KEY auto_increment,
	id VARCHAR(30) UNIQUE NOT NULL,
	pass VARCHAR(30) NOT NULL,
	name VARCHAR(50),
	addr TEXT,
	phone VARCHAR(20),
	gender VARCHAR(10),
	age int(3)
); 

-- 관리자 계정 추가 
-- id : admin | pass : admin | name : MASTER
INSERT INTO test_member
VALUES(null,'admin','admin','MASTER','지구','01012345678','남성',30);

SELECT * FROM test_member;

commit;

DESC test_member;