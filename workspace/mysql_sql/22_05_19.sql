-- auto commit 설정
SELECT @@autocommit; -- @@가 붙으면 mysql에 등록된 시스템 변수를 사용.
-- 0 : autocommit 실행 안함.
-- 1 : autocommit 실행
set autocommit = 0;

use sqldb;

SELECT * FROM buytbl4;
UPDATE buytbl4 SET price = 0;
rollback;

set autocommit = 1;
SELECT @@autocommit;
UPDATE buytbl4 SET price = price + 10;
SELECT * FROM buytbl4;
rollback;

set autocommit = 0;

-- 계정 생성 권한 부여 & 회수
-- 사용자 계정 생성
-- CREATE USER 계정명@접속위치 IDENTIFIED BY 비밀번호;
-- 접속위치 : 컴퓨터 주소(IP) | % - 어디서나 접근가능 | localhost : 현재 컴퓨터에서만 접근 가능

use mysql;
-- 사용자 계정 정보
SELECT * FROM user;
SELECT user, host FROM user;

-- 사용자 id : user1 | pw : 1234
CREATE USER user1@'localhost' IDENTIFIED BY '1234';
show GRANTS FOR user1@'localhost';
show GRANTS FOR root@'localhost';
-- user1 계정 삭제
DROP user user1@'localhost';

CREATE USER 'pm'@'%' IDENTIFIED by '1234';
-- 권한부여
GRANT ALL ON *.* To pm@'%' WITH GRANT OPTION; -- WITH GRANT OPTION : 해당 계정으로 다른 사용자한테도 권한을 부여 할 수 권한을 줌.
SHOW GRANTS FOR pm@'%';

CREATE USER 'staff'@'%' IDENTIFIED by '1234';
GRANT SELECT,UPDATE,INSERT,DELETE ON sqldb.* TO staff@'%'; 
SHOW GRANTS FOR staff@'%';

CREATE USER 'ceo'@'%' IDENTIFIED by '1234';
GRANT SELECT,INSERT,UPDATE ON sqldb.* TO ceo@'%';

SELECT user,host FROM user;














