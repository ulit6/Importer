USE IMPORTER;
DROP PROCEDURE IF EXISTS LPRHA_ADD;
DELIMITER //

CREATE PROCEDURE LPRHA_ADD(IN AWTCH INT ,IN AKEAN CHAR(14) ,IN ASPRH CHAR(9) ,IN AKGRS SMALLINT  ,
 IN ANPRH VARCHAR(90) ,IN APPRH VARCHAR(90),IN ADPRH VARCHAR(90) ,IN AOPRH VARCHAR(90),
 IN APOPR VARCHAR(90) ,IN AIPRH DECIMAL(20,5),IN ALPRH DECIMAL(20,5),IN ASTPR CHAR(1) , IN AKPRH CHAR(6) )
MODIFIES SQL DATA
BEGIN

INSERT INTO LPRHA(WTCH ,KEAN,SPRH,KGRS,NPRH,PPRH,DPRH,OPRH,POPR,IPRH,LPRH,STPR,KPRH) 
VALUES(AWTCH ,AKEAN,ASPRH,AKGRS,ANPRH,APPRH,ADPRH,AOPRH,APOPR,AIPRH,ALPRH,ASTPR,AKPRH);



END;
//