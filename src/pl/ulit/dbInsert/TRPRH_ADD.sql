USE IMPORTER;
DROP PROCEDURE IF EXISTS TRPRH_ADD;
DELIMITER //

CREATE PROCEDURE TRPRH_ADD(IN AIDPR INT  ,IN AKKAT CHAR(2) ,IN ADTPO DATE ,IN ADTKO DATE ,IN ATBAZ DECIMAL(10,4),
  IN ALJRO DECIMAL(10.4) )

MODIFIES SQL DATA
BEGIN

INSERT INTO TRPRH(IDPR,KKAT,DTPO, DTKO,TBAZ,LJRO) VALUES(AIDPR,AKKAT,ADTPO, ADTKO,ATBAZ,ALJRO);

END;
//