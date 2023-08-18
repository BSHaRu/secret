DESC back_up_member;

SELECT * FROM tbl_member;

-- 등록할 회원 정보 입력
-- INSERT 후에 SELECT 반환하는 프로시저 추가
DELIMITER //
	CREATE PROCEDURE insert_member(
		IN _name VARCHAR(30),
        IN _id VARCHAR(100),
        IN _pw VARCHAR(100),
        IN _reg BIGINT
    )
BEGIN    
	INSERT INTO tbl_member VALUES(null, _name, _id, _pw, _reg);
    SELECT * FROM tbl_mebmer WHERE mNum = LAST_INSERT_ID();
END //    
DELIMITER ;

CALL insert_member('ppsp','zzz','zzz',0);
commit;

INSERT INTO tbl_member VALUES(null, 'ppp', 'zzz', 'zzz', 0);
SELECT * FROM tbl_member;

-- tbl_member table의 행정보가 DELETE 된후
-- back_up_member table에 삭제된 회원 정보 저장.
DELIMITER //
	CREATE TRIGGER delete_member
    AFTER DELETE
    ON tbl_member
    FOR EACH ROW
BEGIN
	INSERT INTO back_up_member
    VALUES(old.mNum, old.mName, old.mId, old.mPw, old.reg, now());
END //    
DELIMITER ;

DROP TRIGGER delet_member;