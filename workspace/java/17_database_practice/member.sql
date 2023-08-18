use bigdata;

CREATE TABLE tbl_member(
	mNum INT PRIMARY KEY auto_increment,
	mName VARCHAR(50),
	mId VARCHAR(20) NOT NULL UNIQUE,
	mPw VARCHAR(30) NOT NULL,
	reg BIGINT DEFAULT 0
);

INSERT INTO tbl_member(mName,mId,mPw)
VALUES('관리자','root','root');

SELECT * FROM tbl_member;

commit;

-- 탈퇴한 회원의 정보를 임시 저장할 back_up table 생성

--CREATE TABLE back_up_member
--SELECT * FROM tbl_member WHERE 1 = 0; 이거나 LIKE나 동일함

CREATE TABLE back_up_member LIKE tbl_member;

DESC back_up_member;

ALTER TABLE back_up_member
ADD COLUMN deleteDate TIMESTAMP DEFAULT now();





