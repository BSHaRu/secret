DROP TABLE IF EXISTS big_member;
CREATE TABLE IF NOT EXISTS big_member(
	u_num INT primary key auto_increment, 	-- 회원번호
	u_id VARCHAR(30) unique,			  	-- 회원아이디
	u_pass VARCHAR(30) NOT NULL,			-- 비밀번호
	u_age int,								-- 나이
	u_gender VARCHAR(10), 				    -- 성별
	u_join char(1) default 'Y',				-- 회원탈퇴 여부 Y == 가입상태
	u_regdate TIMESTAMP default now() 		-- 회원가입일	 
);

-- 회원 가입 시
INSERT INTO big_member(u_id,u_pass,u_age,u_gender) 
VALUES('admin', 'admin' , 20, 'male');

-- 회원탈퇴 시
-- UPDATE big_member SET u_join = 'N' WHERE u_num = 회원번호;
commit;

-- 관리자가 아니고 탈퇴한 회원이 아닌 정보 출력
SELECT * FROM big_member 
WHERE u_id != 'admin' AND u_join = 'Y' 
ORDER BY u_num DESC

SELECT * FROM big_member ORDER BY u_num DESC;

SELECT count(*) AS cnt FROM big_member
WHERE u_id != 'admin' AND u_join = 'Y';

-- 현재시간 : now() | sysdate()
-- now() : 한개의 쿼리안에 동일 시간을 출력
-- sysdate(): 동일 쿼리라도 호출 되는 시점에 시간 출력
SELECT now(), sysdate();

-- 공지사항 notice_board
CREATE TABLE notice_board(
	n_num INT PRIMARY KEY auto_increment,	-- 공지 번호
	n_category VARCHAR(20),					-- 공지 분류
	n_author VARCHAR(50), 					-- 작성자
	n_title VARCHAR(50),					-- 제목
	n_content TEXT,							-- 내용
	n_date TIMESTAMP default now()			-- 작성 시간
);

commit;

SELECT * FROM notice_board;

INSERT INTO notice_board(n_category, n_title, n_author, n_content)
SELECT n_category, n_title, n_author, n_content FROM notice_board;


-- 답변형 게시판 Q & A
CREATE TABLE qna_board(
	qna_num INT PRIMARY KEY auto_increment, -- 글번호
	qna_name VARCHAR(20) NOT NULL,	-- 작성자 이름
	qna_title VARCHAR(50) NOT NULL,	-- 글 제목
	qna_content TEXT NOT NULL,		-- 글 내용
	qna_re_ref	INT NOT NULL,		-- 원본 글 번호 -> 이 친구를 통해서 답변글을 묶어서 같이 표현하게함
	qna_re_lev	INT NOT NULL,		-- 답변 뷰 깊이 -> 해당 답변 글에 "들여쓰기" or "ㄴ>" 이거 표시해주는 친구
	qna_re_seq	INT NOT NULL,		-- 답변글 정렬 순서 -> 이 친구는 원본 글에 답변 할 수 있게 해주는 친구
	qna_writer_num INT,				-- 회원번호
	qna_readcount INT default 0,	-- 조회수
	qna_date TIMESTAMP default now() -- 글작성 시간
);

DESC qna_board;

INSERT INTO qna_board
VALUES(null,'나','제발 단체 휴가좀..','제곧내',0,0,0,1,0,now());

SELECT * FROM qna_board;

SELECT LAST_INSERT_ID();

UPDATE qna_board SET qna_re_ref = LAST_INSERT_ID()
WHERE qna_num = LAST_INSERT_ID();

commit;

ALTER TABLE qna_board
ADD qna_delete char(1) DEFAULT 'N' -- 삭제 할 때 게시글은 그대로 나두면서 삭제
AFTER qna_readcount;			-- -> 해당게시글이 삭제되었습니다 하면서 내용은 못보고 해당 글이 존재했던것만 보이게 ㅇㅇ




