CREATE TABLE tbl_book(
	num INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(300) NOT NULL,
	author VARCHAR(300) NOT NULL,
	regDate TIMESTAMP default now()
);

DESC tbl_book;

INSERT INTO tbl_book(title, author)
VALUES('혼자공부하는 자바','신용권');

SELECT * FROM tbl_book;
rollback;

-- table 상태 정보 확인
show table status WHERE name = 'tbl_book';
-- auto_incremnet 현재값 수정 방법
ALTER TABLE bigdata.tbl_book AUTO_INCREMENT = 1;

