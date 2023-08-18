CREATE TABLE IF NOT EXISTS board_test(
	board_num INT PRIMARY KEY auto_increment, -- 게시물 번호
	board_title VARCHAR(50) NOT NULL, 		  -- 게시물 제목
	board_auth VARCHAR(50) NOT NULL,		  -- 작성자
	board_content TEXT,						  -- 게시물 내용
	board_date TIMESTAMP DEFAULT now()		  -- 작성 시간
);

DESC board_test;

INSERT INTO board_test
VALUES(null,'ㅎㅇ염','홍길동','없음',now());

INSERT INTO board_test(board_title, board_auth, board_content)
VALUES('ㅂㅇ염','이순신','없음');

SELECT * FROM board_test ORDER by board_num DESC;
commit;

-- := 대입연산자 
-- mysql 5.x 버전에서 rownum 구하는 질의문
SELECT @ROWNUM := @ROWNUM +1 AS rnum, A.* FROM
(SELECT * FROM board_test WHERE (@ROWNUM:=0)=0
ORDER by board_num ASC) AS A ORDER by rnum DESC;

-- mysql 8.x 버전에서 추가된 ROW_NUMBER()
SELECT ROW_NUMBER() 
OVER(ORDER by board_num ASC) AS rnum,
board_test.* FROM board_test
ORDER by board_num DESC;
-- -> ROW_NUMBER()를 rnum이라는 별칭으로 실행시킬껀데
-- OVER를 통해서 board_num을 순서대로 정렬을 하고
-- 그 후 board_num을 역순으로 정렬 한다 
-- => 중간에 게시글이 삭제되어도 숫자 안날라가고 제대로 표시해줌


