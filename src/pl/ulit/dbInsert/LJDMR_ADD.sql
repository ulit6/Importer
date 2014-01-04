DELIMITER ;
USE IMPORTER;
DROP PROCEDURE IF EXISTS LJDMR_ADD ;
DELIMITER //
CREATE PROCEDURE LJDMR_ADD(IN AKDJM TINYINT,IN ANZJM VARCHAR(4),IN ASTJM CHAR(1))
MODIFIES SQL DATA
BEGIN

DECLARE VKDJM TINYINT;
DECLARE ALREADY_EXISTS INT DEFAULT 0;
DECLARE CONTINUE HANDLER FOR SQLSTATE '23000' SET ALREADY_EXISTS=1;

SELECT KDJM INTO VKDJM FROM LJDMR WHERE KDJM=AKDJM AND NZJM=ANZJM AND STJM=ASTJM;
IF VKDJM IS  NULL THEN
    INSERT INTO LJDMR(KDJM,NZJM,STJM) VALUES(AKDJM,ANZJM,ASTJM);
 --   IF ALREADY_EXISTS = 0 THEN    
    ELSE
        UPDATE LJDMR SET NZJM=ANZJM,STJM=ASTJM WHERE KDJM=AKDJM;
END IF;
END;
//
DELIMITER ;