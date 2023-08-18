-- ctrl + enter : 해당 db에서 실행시키는 방법
/*
	Create 명령어를 이용하여
    database또는 table 등 데이터를 정의하는
    구조를 생성
*/
-- CREATE SCHEMA `sqldb` ;
-- CREATE SCHEMA sqldb ; // 두개 같은말이지만, 공백이 들어가면 ``(~키)을 통해서 
use sqldb;

CREATE TABLE `sqldb`.`member_tbl` (
  `member_id` VARCHAR(50) NOT NULL COMMENT '회원을 구분짓는 아이디',
  `member_name` VARCHAR(45) NULL DEFAULT NULL COMMENT '회원의 이름',
  `member_addr` VARCHAR(45) NULL COMMENT '회원 배송 주소',
  PRIMARY KEY (`member_id`))
COMMENT = '회원정보에 대한 정보';

/*
table 이름 : product_tblmember_tblproduct_tblproduct_tbl
상품이름 : product_name varchar(50),
상품가격 : cost Int,
제조날짜 : make_date VARCHAR(20),
남은수량 : amount INT NULL,product_tbl
*/

CREATE TABLE `sqldb`.`product_tbl` (
  `product_name` VARCHAR(50) NOT NULL COMMENT '상품이름',
  `cost` INT NULL COMMENT '상품가격',
  `make_date` VARCHAR(20) NULL COMMENT '제조날짜',
  `amount` INT NULL DEFAULT NULL COMMENT '남은수량',
  PRIMARY KEY (`product_name`))
COMMENT = '제품 테이블';

-- 지정된 데이터베이스의 테이블 이름 정보 확인
show tables;

-- product_tbl의 모든 열의 정보 검색
SELECT * FROM product_tbl;	-- SELECT 열 FROM -> *로 해서 모두 나오는거임

-- sqldb schema or database 삭제
DROP DATABASE sqldb;

-- ----------------------------------------
-- sqldb schema 생성
CREATE DATABASE sqldb;

-- database랑 schema랑 같은의미임.
CREATE SCHEMA testdb;

-- testdb가 존재하지 않으면 create 해줘라
CREATE SCHEMA IF NOT EXISTS testdb;

-- testdb가 존재하면 삭제해줘라
DROP DATABASE IF EXISTS testdb;

CREATE SCHEMA IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE IF NOT EXISTS userTbl(
	userID char(8) NOT NULL PRIMARY KEY, -- primary key 기준으로 자동 정렬 된다. | 중복되면 안된다. | 값이 비어있으면 안된다.
    name VARCHAR(10) NOT NULL,
    birthyear INT NOT NULL,
    addr char(2) NOT NULL,
    mobile1 char(3),
    mobile2 char(8),
    height smallint,
    mDate date
);

show tables;

DESCRIBE usertbl;	-- describe : tbl에 대한 정보를 알려줌
DESC usertbl;		-- 줄여서 사용도 가능

-- 상품 정보를 저장하는 테이블
CREATE TABLE IF NOT EXISTS prodoctTbl(
	num INT(8) NOT NULL PRIMARY KEY,
    userID char(8) NOT NULL,
    prodName char(6),
    groupName char(4),
    price char(5),
    count smallint NOT NULL
);

-- 정의된 정보를 수정하는 keyword - ALTER
-- table 이름 수정
ALTER TABLE prodoctTbl RENAME buyTBL;

show tables;

-- buyTBL에 있는 price 속성에 있는 dataType을 변경
ALTER TABLE buyTBL MODIFY price INT;

DESC buytbl;

-- buyTBL의 num 속성 변경
ALTER TABLE buyTBL modify 
num INT AUTO_INCREMENT; -- AUTO_INCREMENT : 숫자 && PRIMARY KEY이어야 된다 | mySQL에서만 제공, 오라클에선 안됨
							-- -> 데이터 추가 할 때 마다 자동으로 해당 열 추가
-- buyTBL count열의 이름과 속성 변경
-- amount로 변경 
ALTER TABLE buyTBL CHANGE 
count amount smallint NOT NULL;

DESC buyTBL;

-- 테이블에 행의 정보를 추가하는 DML
-- INSERT INTO values
INSERT INTO userTbl
VALUES('LCH','이춘향',1999,'경주',null,null,153,'1999-07-05');

INSERT INTO userTbl
VALUES('LCH','이초홍',1998,'광주',null,null,158,'1998-04-09'); -- userID가 겹쳐서 추가가 안됨

DESC userTbl;

SELECT * FROM userTbl;

DESC buyTBL;

INSERT INTO buyTBL
VALUES(null,'LCH',"운동화",'의류',30,1); -- aotu_increment라서 null로 지정해줘도 자동으로 채워짐

SELECT * FROM buyTBL;

INSERT INTO buyTBL
VALUES(null,'HGD',"세탁기",'전자',40,1);

SELECT * FROM userTbl;

-- buyTBL의 userID 열 정보를 
-- userTBL에 userID의 외래키로 지정buytblbuytbl
ALTER TABLE buyTBL ADD CONSTRAINT FOREIGN KEY(userID)
REFERENCES userTBL(userID); -- A add constraint foreign key(a) references B(a) 
							-- -> A 테이블에 외래키를 추가할껀데, B테이블에있는 a를 외래키로 참조할꺼다.
commit;

TRUNCATE buyTbl;
TRUNCATE userTBL;
-- 테이블에 존재하는 특정 행의 정보를 삭제하는 DML
-- delete from table명 
DELETE FROM userTBL WHERE 0 = 1; 
DELETE FROM userTBL; -- 이건 해당 테이블 모든 데이터를 삭제하는거라 sql에서 기본적으로 막아둠
-- 그래서 설정을 통해서 그 설정 풀었음

-- SELECT column명 FROM table 명 WHERE 조건절
SELECT * FROM userTBL WHERE name = '김범수'; -- name이 김범수에 대한 모든것

-- userTBL에서 userID가 BBK인 사람을 검색
SELECT * FROM userTBL WHERE userID = 'BBK';

-- 1970년 이후에 출생하고 키가 182 이상인 사람 검색
SELECT * FROM userTBL WHERE 
birthyear > 1970 AND height >= 182;

-- userTBL에서 경기에 사는 사람의 이름과 주소만 검색
SELECT name, addr FROM userTBL WHERE addr = '경기'; 

-- userTBL에서 1970 ~ 1979년 사이에 태어난 사람의 이름 검색
SELECT name FROM userTBL WHERE 
birthyear >= 1970 AND birthyear <= 1979;

-- 키가 180이상 183이하의 사람의 이름 주소 키 정보 검색
SELECT name, addr, height FROM userTBL WHERE
height >= 180 AND height <= 183; 

-- BETWEEN 범위값 && AND 범위값
SELECT * FROM userTBL WHERE
height BETWEEN 180 AND 183;

-- userTBL에서 주소가 '경남'이거나 '전남'이거나 '경북'인 사람 검색
SELECT * FROM userTBL WHERE
addr = '경남' OR addr = '전남 ' OR addr = '경북';

-- where in() : 동일한 속성에서 특정 조건들을 만족한는 정보를 검색 
SELECT * FROM userTBL WHERE
addr IN('경북','전남','경남');

-- LIKE wildcard[ % || _ ]를 활용한 검색
-- 와일드 카드를 이용하여 1970년대 출생한 사람 찾기 (1970~1979)
SELECT * FROM userTBL WHERE
birthyear LIKE '197_';	-- _ : _에 뭐가 들어갔던간에 _이전의 데이터가 들어 있는것을 검색함. 

-- 성이 '김'씨인 모든 사용자 정보 검색
SELECT * FROM userTBL WHERE 
name LIKE '김__';

-- 이름 사이에 '시'가 들어가는 사용자 검색
SELECT * FROM userTBL WHERE
name LIKE '_시_';

-- 김으로 시작하고 뒤에 무슨값이든 상관 없음.
SELECT * FROM userTBL WHERE
name LIKE '김%'; -- % : 어떤 값이든 상관없이 모두 검색

SELECT * FROM userTBl;
-- userTBL에서 mobile1 핸드폰 번호가 없는 사람 검색
SELECT * FROM userTBL WHERE mobile1 = null;
-- NULL 비교 연산 할때는 WHERE IS를 써야됨
SELECT * FROM userTBL WHERE mobile1 IS NULL;

-- 전화번호가 존재 하는 사용자
SELECT * FROM userTBL WHERE mobile1 IS NOT NULL;

-- 거주지(주소)가 서울이 아닌 사람 검색
SELECT * FROM userTBL WHERE addr != '서울';
SELECT * FROM userTBL WHERE NOT addr = '서울';
SELECT * FROM userTBL WHERE addr <> '서울';

-- 전화번호 시작(mobile1) 016, 018, 019 인 사람들 검색
SELECT * FROM userTBL WHERE
mobile1 IN (016, 018, 019);

-- 위에꺼 아닌 사람들 검색
SELECT * FROM userTBL WHERE
mobile1 NOT IN (016, 018, 019);

-- height 키가 170이상 사람검색
SELECT * FROM userTBL WHERE height >= 170;

-- 이름이 '김경호'인 사람의 '키'검색
SELECT height FROM userTBL WHERE name = '김경호'; 

-- 김경호 보다 키가 같거나 큰 사람의 이름과 키를 검색
SELECT name, height FROM userTBL WHERE 
height >= (SELECT height FROM userTBL WHERE name = '김경호') AND name != '김경호'; 

