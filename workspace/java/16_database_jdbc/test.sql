-- alt + x || alt + c를 눌러서 실행(실행할 블록 지정하고 실행해야됨) // ctrl + 엔터 아님
SELECT * FROM member_tbl; -- 실행 안되면 연결되었는지 아닌지 확인해보고, 그래도 안되면 sql 껏다 다시켜래

show tables;

CREATE TABLE student(
	stuNo INT PRIMARY KEY auto_increment,
	stuName VARCHAR(50),
	stuDept VARCHAR(50),
	stuGrade INT,
	stuClass char(1) DEFAULT 'A',
	stuGender char(1) DEFAULT 'F',
	stuDate TIMESTAMP DEFAULT now()
);

show tables;

commit;
SELECT * FROM student;

call checkYear('이승기');

SELECT * FROM userTbl;