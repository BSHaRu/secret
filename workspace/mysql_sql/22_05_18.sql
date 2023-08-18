show databases;
use sqldb;
show tables;

SELECT * FROM usertbl;
SELECT * FROM `buytbl`; -- 공백을 포함하려면 ``을 쓰지만, 공백자체를 잘 안씀
						-- ->프로그램 언어는 공백을 인식 제대로 못하기때문에 공백대신 _를 씀.
DESC usertbl;
INSERT INTO usertbl 
VALUES('PJS','박지성',1981,'부산',null,null,173,'2002-06-06');

INSERT INTO usertbl 
VALUES('LSS','이순신',1685,'전남',null,null,170,'2002-06-06');
                        
-- 경남에 사는 사람과 키가 동일한 사람 검색
SELECT height FROM usertbl WHERE addr = '경남'; -- 173 | 170
SELECT name, height, addr FROM usertbl WHERE
height IN(170, 173);

DELETE FROM usertbl WHERE userId = 'LSS';

commit;	-- save 포인트 만들기

rollback; -- 마지막 commit 지점으로 돌아가기

SELECT name, height, addr FROM usertbl WHERE 
height IN (										-- IN 대신 비교연산자를 쓸 경우 오류 생김
	SELECT height FROM usertbl WHERE addr = '경남' -- -> 비교연산자만 쓰면 단일만 가져올 수 있기때문에 IN으로 다연산한것
);

-- usertbl에서 경남에 사는 사람보다 키가 같거나 큰 모든 사람들 검색
SELECT name, height, addr FROM usertbl
WHERE height >= ANY(								-- 비교연산자만 쓰면 단일만 가져올 수 있기때문에 ANY를 써서 모두 가져오는거임
	SELECT height FROM usertbl WHERE addr = '경남' -- -> ANY는 ()안에 있는 조건 중 1개라도 만족하면 그거 기준으로 가져옴
);													-- => 170이상인 사람들 검색

-- 경남에 사는 사람중 가장 키가 큰 사람과 키가 같거나 큰 사람의 정보 검색
SELECT name, height, addr FROM usertbl
WHERE height >= All(								-- ALL은 ()안에 있는 조건을 모두 만족해야 됨
	SELECT height FROM usertbl WHERE addr = '경남'	-- -> 173이상인 사람들 검색
);

-- 검색된 행의 정보를 정렬하는 ORDER BY 절
-- ASC : 오름차순 | DESC : 내림차순 // dfault값은 ASC라서 생략가능
-- 거주 지역별로 오름차순 정렬
SELECT * FROM usertbl ORDER BY addr ASC;

-- 가장 최근에 가입한 순서대로 정렬
SELECT * FROM usertbl ORDER BY mDate DESC;

-- 거주지역 순으로 정렬하고 동일 지역이면 나이순으로 정렬
SELECT * FROM usertbl ORDER BY addr, birthyear;

-- distinct : 검색 결과에 중복된 행의 정보를 제거 
-- 키가 175이상인 사람이 사는 곳 검색
SELECT DISTINCT addr FROM usertbl 
WHERE height >= 175 ORDER by addr;

SELECT addr, height FROM usertbl 
WHERE height >= 175 ORDER by addr;

SELECT DISTINCT addr, height FROM usertbl 
WHERE height >= 175 ORDER by addr; -- 조건을 여러개 하면 그 해당 조건을 모두 만족하는 중복을 제거해줌

-- 검색된 결과 내에서 제공되는 개수를 제한하는 LIMIT
-- MySQL에서만 존재하고 다른 DBMS보다 속도가 빠르다.
SELECT * FROM usertbl ORDER BY mDate LIMIT 5; -- 오름차순으로 정렬된 데이터중에서 5개를 가지고 옴

SELECT * FROM usertbl 
ORDER by mDate LIMIT 2, 4; -- LIMIT a, b : a인덱스부터 b개까지 가지고옴.
							-- cf) LIMIT a OFFSET b : b인덱스부터 a개 까지 가지고옴.
SELECT * FROM usertbl
ORDER by mDate LIMIT 4 OFFSET 2;

-- GROUP BY HAVING 절과 집계함수(여러개의 반환된 값들을 계산해주는 함수)
-- buytlb에서 구매한 총 물량의 개수
SELECT amount FROM buytbl;

SElECT sum(amount) AS '구매개수' FROM buytbl; -- a AS b : a를 b이름으로 바꿔줌(별칭)

-- buytlb에서 사용자마다 구매한 개수를 검색
SELECT userID, sum(amount) AS '개수' FROM buytbl GROUP by userID;
-- -> userID를 GROUP BY해서 sum(amount)를 나타내라.

-- count() table에 존재하는 행의 개수를 검색
-- count(열이름) 해당 속성에 "유효한" 값을 가진 행의 개수를 반환
SELECT * FROM usertbl;
SELECT count(*) FROM usertbl;

SELECT count(mobile1) FROM usertbl;

SELECT count(*) FROM usertbl
WHERE mobile1 is NOT NULL; -- 이거랑 위에 구문이랑 동일한 의미

SELECT count(userID) FROM usertbl; -- 보통 count는 PR key를 넣어서 중복없이 전체 몇갠지 확인한다고함.

SELECT addr FROM usertbl ORDER by addr;
SELECT count(distinct addr) FROM usertbl;

-- min(), max() : 동일 속성에 저장된 최소값과 최대값을 검색
-- usertbl에서 키가 가장 작은 사람
SELECT min(height) FROM usertbl;
-- usertbl에서 키가 가장 큰 사람
SELECT max(height) FROM usertbl;

-- usertbl에서 키가 가장 작은 사람과 키가 가장 큰사람의 이름과 키 검색
SELECT name, height FROM usertbl 
WHERE height IN(
	(SELECT min(height) FROM usertbl),
    (SELECT max(height) FROM usertbl)
);

-- buytbl에 등록된 상품 중 가격이 가장 낮은것과 높은것에 이름(prodName)과 가격(price) 검색
SELECT prodName, price FROM buytbl
WHERE price IN(
	(SELECT min(price) FROM buytbl),
    (SELECT max(price) FROM buytbl)
)GROUP by prodName;

-- 전체 판매 금액
SELECT sum(price * amount) AS '총 판매 금액' FROM buytbl;

-- 그룹별 판매 금액
SELECT groupName, sum(price * amount) AS '총 판매 금액' FROM buytbl
GROUP by groupName ORDER by sum(price * amount) DESC;

SELECT avg(height) AS 'user키 평균' FROM usertbl;

-- 지역별 평균키 - 역순으로
SELECT addr, avg(height) AS '평균키' FROM usertbl
GROUP by addr ORDER by 평균키 DESC; -- avg(height)을 바로 넣게되면 위에 지정한 AS(평균키)를 다른곳에서 활용을 못함

-- ROUND() : 실수값을 소수점 첫째자리에서 반올림
SELECT addr, ROUND(avg(height)) AS '평균키' FROM usertbl
GROUP by addr;

-- CEIL() : 올림
SELECT CEIL(192.166) FROM DUAL; -- FROM DUAL : 관계형 DB를 바로 확인 할 수 있는 임시 table
								-- mysql에서는 FROM DUAL 생략가능함
-- FLOOR() : 내림
SELECT FLOOR(192.166);

-- 그룹별로 합계를 구할 때 사용 GROUP BY
-- WITH ROLLUP : 항목별 합계에 항목별 합계가 아니라 항목별 합계와 전체합계를 같이 검색해주는 키워드
SELECT addr, ROUND(avg(height)) AS '평균키' FROM usertbl
GROUP by addr WITH ROLLUP; -- null 값이 전체 평균임

-- 그룹별 총 판매 금액 / 상품별 총 판매 금액 / 전체 판매 금액 검색
-- 그룹 이름(groupName) 상품 이름(prodName), 판매 금액(price * amount) 검색
SELECT prodName AS '상품 이름', sum(price * amount) AS '판매금액' FROM buytbl
GROUP by prodName;

SELECT groupName, prodName, sum(price * amount) FROM buytbl
GROUP by groupName, prodName 
WITH ROLLUP							-- 이걸 해 줌으로써 해당 groupName의 총 합계도 같이 나온다.
ORDER by groupName DESC, prodName DESC;

/********************************************************************/
-- 임시 테이블을 생성하여 기존 테이블의 정보를 가져와서 저장
DESC usertbl;
-- userID, name, mDate

CREATE TABLE temp_user_tbl(
	userID char(8) PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    mDate date
);

-- INSERT INTO table명(컬럼 명) VALUES(열값, 열값);
INSERT INTO temp_user_tbl
SELECT userID, name, mDate FROM usertbl;

SELECT * FROM temp_user_tbl;

-- buytbl 구조를 복사하여 테이블 생성하고
-- SELECT 문에서 검색된 구조와 데이터를 저장
CREATE TABLE buytbl2(
	SELECT * FROM buytbl
); -- 구조만 복제하는거지 PR KEY나 기타 등등은 복사가 안됨
DESC buytbl2;
SELECT * FROM buytbl2;

CREATE TABLE buytbl3(
	SELECT userID FROM buytbl
);
DESC buytbl3;
SELECT * FROM buytbl3;

-- 데이터는 백업하지 않고 구조만 일치하는 새로운 테이블 생성
CREATE TABLE buytbl4(
	SELECT * FROM buytbl WHERE 1 = 0
); -- 1 = 0은 무조건 false니깐 동일한 구조만 생성되고 데이터는 없음
DESC buytbl4;
SELECT * FROM buytbl4;

-- GROUP BY HAVING - HAVING은 무조건 GROUP BY 다음에 와야됨.
-- 주문횟수가 3번 이상인 회원의 정보를 검색
SELECT userID, count(*) AS '구매횟수' FROM buytbl 
GROUP by userID HAVING 구매횟수 >= 3; -- HAVING은 WHERE이랑 비슷하게 사용하면된다.
									-- -> GROUP by 뒤에 조건을 추가할 때 HAVING이라고 보면됨

-- JYP가 구매한 총 금액 검색
SELECT sum(price * amount) FROM buytbl WHERE userID = 'JYP';

-- 구매금액이 JYP보다 크거나 같은 회원 정보 검색
SELECT userID, sum(price * amount) AS '구매금액' FROM buytbl
GROUP by userID HAVING 구매금액 >= (
	SELECT sum(price * amount) FROM buytbl WHERE userID = 'JYP'
);

-- DML - 데이터 조작 언어
-- SELECT, INSERT INTO, UPDATE, DELETE FROM
-- INSERT INTO table명 VALUES(컬럼명..)
DESC buytbl4;
SELECT count(*) FROM buytbl4;

INSERT INTO buytbl4 VALUES(1,'PSY','RT3080','전자',1000,1);
SELECT * FROM buytbl4;

INSERT INTO buytbl4(num, userID, prodName, price, amount) -- ()안에 있는 값만 넣고 싶을땐 이렇게 적어도 됨.
VALUES(2, 'HGD','청소기',1500,2);

commit;
rollback;

-- UPDATE table명 SET 수정 할 열이름 = 수정할 값, 수정 할 열이름 = 수정할 값...
-- WHERE 조건을 비교할 속성이름 = 비교할 값;
-- buytbl4 talbe에 num 2번인 행의 정보를 groupName을 전자로 수정

UPDATE buytbl4 SET groupName = '전자' WHERE num = 2;
SELECT * FROM buytbl4;
rollback;
commit;

SELECT * FROM buytbl2;
-- buytbl2의 모니터 가격을 250으로 변경
UPDATE buytbl2 SET price = 250 
WHERE prodName = '모니터';

-- buytbl2의 상품 중 청바지 가격을 60으로 변경하고
-- 판매개수를 5로 변경
UPDATE buytbl2 SET price = 60, amount = 5
WHERE prodName = '청바지';

-- num이 8번인 행의 상품명을 면바지로 변경,
-- 가격을 40으로 변경, 개수는 6개로 변경,
-- groupName을 의류로 변경
UPDATE buytbl2 SET prodName = '면바지',
price = 40, amount = 6, groupName = '의류'
WHERE num = 8;

-- 조건절이 없는 UPDATE
-- 모든 상품의 가격이 5,000원씩 인상했을 경우.
UPDATE buytbl2 SET price = price + 5; 

-- 전자제품의 가격이 10,000원 인상 
UPDATE buytbl2 SET price = price + 10
WHERE groupName = '전자';

commit;
-- DELETE FROM : table에 삽입되어 있는 행의 정보를 삭제함
DELETE FROM buytbl2;
SELECT * FROM buytbl2;
rollback;

-- buytbl2에서 userid가 bbk인 정보 삭제
DELETE FROM buytbl2 WHERE userID = 'bbk'; 

-- TRUNCATE : table에 모든 정보를 초기화 시킨다. | auto commit임
TRUNCATE buytbl2;

DESC buytbl2;
INSERT INTO buytbl2(userID,amount) VALUE('PJS',5);
INSERT INTO buytbl2(userID,amount) VALUE('CEJ',2);

SELECT * FROM buytbl2;
DELETE FROM buytbl2;

TRUNCATE buytbl2;








