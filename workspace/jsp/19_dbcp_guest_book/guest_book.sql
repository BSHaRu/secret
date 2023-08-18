-- 이름, 비밀번호, 메세지
CREATE TABLE IF NOT EXISTS test_guestbook(
	id INT PRIMARY KEY auto_increment,	-- 방명록 번호
	guestName VARCHAR(20) NOT NULL,		-- 방명록 작성자
	password VARCHAR(30) NOT NULL,		-- 비밀번호
	message Text
);

-- message를 messgae로 적어놔서 table에 있는걸 message로 이름 바꿔줌 
ALTER TABLE test_guestbook CHANGE COLUMN messgae message TEXT;

show tables;

commit;

SELECT * FROM test_guestbook ORDER by id DESC;

-- 기존에 table에 저장된 내용을 그대로 복사해서 추가해 주는 작업
INSERT INTO test_guestbook(guestName,password,message)
SELECT guestName,password,message FROM test_guestbook;

-- mysql에서만 사용가능한 2가지 기능 : auto_increment / limit
-- limit a, b : 인덱스a번째부터 b개수까지 출력
-- mysql은 limit 가지고 페이징 처리를 함.
SELECT * FROM test_guestbook
ORDER by id DESC limit 0, 10;

SELECT count(id) FROM test_guestbook; 









