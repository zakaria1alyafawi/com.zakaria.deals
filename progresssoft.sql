/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.28 : Database - progresssoft
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`progresssoft` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `progresssoft`;

/*Table structure for table `deals` */

DROP TABLE IF EXISTS `deals`;

CREATE TABLE `deals` (
  `deal_id` int NOT NULL AUTO_INCREMENT,
  `from_currency_code` varchar(4) NOT NULL,
  `to_currency_code` varchar(4) NOT NULL,
  `deal_time` datetime NOT NULL,
  `deal_amount` double NOT NULL,
  `creation_date` datetime NOT NULL,
  `modification_date` datetime DEFAULT NULL,
  PRIMARY KEY (`deal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `deals` */

insert  into `deals`(`deal_id`,`from_currency_code`,`to_currency_code`,`deal_time`,`deal_amount`,`creation_date`,`modification_date`) values 
(6,'','usd','2022-02-22 02:00:00',12,'2022-02-22 21:50:46','2022-02-22 22:38:12'),
(7,'jod','eur','2022-02-22 02:00:00',5000,'2022-02-22 21:59:19','2022-02-22 22:12:54');

/*Table structure for table `transaction_logs` */

DROP TABLE IF EXISTS `transaction_logs`;

CREATE TABLE `transaction_logs` (
  `transaction_id` int NOT NULL,
  `procedure_name` varchar(60) NOT NULL,
  `error_code` varchar(15) DEFAULT NULL,
  `error_message` varchar(300) DEFAULT NULL,
  `transaction_date` datetime NOT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `transaction_logs` */

/* Procedure structure for procedure `DEALS_ADD_NEW_DEAL` */

/*!50003 DROP PROCEDURE IF EXISTS  `DEALS_ADD_NEW_DEAL` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `DEALS_ADD_NEW_DEAL`(
		OUT P_ERR_CODE 				VARCHAR(4000),
		OUT P_ERR_MESSAGE			VARCHAR(4000),
		OUT P_DEAL_ID 				INT(25),
		IN 	P_FROM_CURRENCY_CODE 	VARCHAR(4),
		IN 	P_TO_CURRENCY_CODE 		VARCHAR(4),
		IN 	P_DEAL_TIME				DATETIME,
		IN 	P_DEAL_AMOUNT 			DOUBLE(24,8)
	)
BEGIN
	
		DECLARE V_ERR_CODE 				VARCHAR(4000);
		DECLARE V_ERR_MESSAGE		 	VARCHAR(4000);
		DECLARE V_TRANSACTION_LOG_ID 	INT(15);
		
		DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN 
			
			GET DIAGNOSTICS CONDITION 1 @SQLCODE = RETURNED_SQLSTATE, @SQLERRM = MESSAGE_TEXT;
			ROLLBACK;
			
			CALL TRANSACTION_LOGS_ADD_TRANSACTION_LOG(
				V_ERR_CODE,
				V_ERR_MESSAGE,
				V_TRANSACTION_LOG_ID,
				'DEALS_ADD_NEW_DEAL',
				@SQLCODE,
				@SQLERRM,
				SYSDATE()
			);
			
			SET P_ERR_CODE = CONCAT(@SQLCODE ,'/',V_ERR_CODE);
			SET P_ERR_MESSAGE = CONCAT(@SQLERRM,'/',V_ERR_MESSAGE,'/ THE TRANSACTION ID = ',V_TRANSACTION_LOG_ID);
		END;
		
		DECLARE EXIT HANDLER FOR NOT FOUND BEGIN 
			
			GET DIAGNOSTICS CONDITION 1 @SQLCODE = RETURNED_SQLSTATE, @SQLERRM = MESSAGE_TEXT;
			ROLLBACK;
			
			CALL TRANSACTION_LOGS_ADD_TRANSACTION_LOG(
				V_ERR_CODE,
				V_ERR_MESSAGE,
				V_TRANSACTION_LOG_ID,
				'DEALS_ADD_NEW_DEAL',
				@SQLCODE,
				@SQLERRM,
				SYSDATE()
			);
			
			SET P_ERR_CODE = CONCAT(@SQLCODE ,'/',V_ERR_CODE);
			SET P_ERR_MESSAGE = CONCAT(@SQLERRM,'/',V_ERR_MESSAGE,'/ THE TRANSACTION ID = ',V_TRANSACTION_LOG_ID);
		END;
		
		INSERT INTO DEALS(
				FROM_CURRENCY_CODE,
				TO_CURRENCY_CODE,
				DEAL_TIME,
				DEAL_AMOUNT,
				CREATION_DATE
			)
		VALUES
			(
				P_FROM_CURRENCY_CODE,
				P_TO_CURRENCY_CODE,
				P_DEAL_TIME,
				P_DEAL_AMOUNT,
				SYSDATE()
			);
		SELECT LAST_INSERT_ID()
		INTO P_DEAL_ID;
			
	END */$$
DELIMITER ;

/* Procedure structure for procedure `DEALS_DELETE_DEAL` */

/*!50003 DROP PROCEDURE IF EXISTS  `DEALS_DELETE_DEAL` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `DEALS_DELETE_DEAL`(
	OUT P_ERR_CODE 				VARCHAR(4000),
	OUT P_ERR_MESSAGE			VARCHAR(4000),
	IN  P_DEAL_ID 				INT
)
BEGIN
	
		DECLARE V_ERR_CODE 				VARCHAR(4000);
		DECLARE V_ERR_MESSAGE		 	VARCHAR(4000);
		DECLARE V_TRANSACTION_LOG_ID 	INT(15);
		
		DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN 
			
			GET DIAGNOSTICS CONDITION 1 @SQLCODE = RETURNED_SQLSTATE, @SQLERRM = MESSAGE_TEXT;
			ROLLBACK;
			
			CALL TRANSACTION_LOGS_ADD_TRANSACTION_LOG(
				V_ERR_CODE,
				V_ERR_MESSAGE,
				V_TRANSACTION_LOG_ID,
				'DEALS_DELETE_DEAL',
				@SQLCODE,
				@SQLERRM,
				SYSDATE()
			);
			
			SET P_ERR_CODE = CONCAT(@SQLCODE ,'/',V_ERR_CODE);
			SET P_ERR_MESSAGE = CONCAT(@SQLERRM,'/',V_ERR_MESSAGE,'/ THE TRANSACTION ID = ',V_TRANSACTION_LOG_ID);
		END;
		
		DECLARE EXIT HANDLER FOR NOT FOUND BEGIN 
			
			GET DIAGNOSTICS CONDITION 1 @SQLCODE = RETURNED_SQLSTATE, @SQLERRM = MESSAGE_TEXT;
			ROLLBACK;
			
			CALL TRANSACTION_LOGS_ADD_TRANSACTION_LOG(
				V_ERR_CODE,
				V_ERR_MESSAGE,
				V_TRANSACTION_LOG_ID,
				'DEALS_DELETE_DEAL',
				@SQLCODE,
				@SQLERRM,
				SYSDATE()
			);
			
			SET P_ERR_CODE = CONCAT(@SQLCODE ,'/',V_ERR_CODE);
			SET P_ERR_MESSAGE = CONCAT(@SQLERRM,'/',V_ERR_MESSAGE,'/ THE TRANSACTION ID = ',V_TRANSACTION_LOG_ID);
		END;
		
		DELETE FROM DEALS
		WHERE DEAL_ID = P_DEAL_ID;
	END */$$
DELIMITER ;

/* Procedure structure for procedure `DEALS_GET_DEAL` */

/*!50003 DROP PROCEDURE IF EXISTS  `DEALS_GET_DEAL` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `DEALS_GET_DEAL`(
	OUT P_ERR_CODE 				VARCHAR(4000),
	OUT P_ERR_MESSAGE			VARCHAR(4000),
	IN  P_DEAL_ID 				INT
)
BEGIN
		
		DECLARE V_ERR_CODE 				VARCHAR(4000);
		DECLARE V_ERR_MESSAGE		 	VARCHAR(4000);
		DECLARE V_TRANSACTION_LOG_ID 	INT(15);
		
		DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN 
			
			GET DIAGNOSTICS CONDITION 1 @SQLCODE = RETURNED_SQLSTATE, @SQLERRM = MESSAGE_TEXT;
			ROLLBACK;
			
			CALL TRANSACTION_LOGS_ADD_TRANSACTION_LOG(
				V_ERR_CODE,
				V_ERR_MESSAGE,
				V_TRANSACTION_LOG_ID,
				'DEALS_GET_DEAL',
				@SQLCODE,
				@SQLERRM,
				SYSDATE()
			);
			
			SET P_ERR_CODE = CONCAT(@SQLCODE ,'/',V_ERR_CODE);
			SET P_ERR_MESSAGE = CONCAT(@SQLERRM,'/',V_ERR_MESSAGE,'/ THE TRANSACTION ID = ',V_TRANSACTION_LOG_ID);
		END;
		
		DECLARE EXIT HANDLER FOR NOT FOUND BEGIN 
			
			GET DIAGNOSTICS CONDITION 1 @SQLCODE = RETURNED_SQLSTATE, @SQLERRM = MESSAGE_TEXT;
			ROLLBACK;
			
			CALL TRANSACTION_LOGS_ADD_TRANSACTION_LOG(
				V_ERR_CODE,
				V_ERR_MESSAGE,
				V_TRANSACTION_LOG_ID,
				'DEALS_GET_DEAL',
				@SQLCODE,
				@SQLERRM,
				SYSDATE()
			);
			
			SET P_ERR_CODE = CONCAT(@SQLCODE ,'/',V_ERR_CODE);
			SET P_ERR_MESSAGE = CONCAT(@SQLERRM,'/',V_ERR_MESSAGE,'/ THE TRANSACTION ID = ',V_TRANSACTION_LOG_ID);
		END;
		
		SELECT DISTINCT DEAL_ID,FROM_CURRENCY_CODE,TO_CURRENCY_CODE,DEAL_TIME,DEAL_AMOUNT,CREATION_DATE,MODIFICATION_DATE
		FROM DEALS
		WHERE DEAL_ID = IFNULL(P_DEAL_ID,DEAL_ID);
	END */$$
DELIMITER ;

/* Procedure structure for procedure `DEALS_UPDATE_DEAL` */

/*!50003 DROP PROCEDURE IF EXISTS  `DEALS_UPDATE_DEAL` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `DEALS_UPDATE_DEAL`(
	OUT P_ERR_CODE 				VARCHAR(4000),
	OUT P_ERR_MESSAGE			VARCHAR(4000),
	IN  P_DEAL_ID 				INT,
	IN 	P_FROM_CURRENCY_CODE 	VARCHAR(4),
	IN 	P_TO_CURRENCY_CODE 		VARCHAR(4),
	IN 	P_DEAL_TIME				DATETIME,
	IN 	P_DEAL_AMOUNT 			DOUBLE(24,8)
)
BEGIN
	
		DECLARE V_ERR_CODE 				VARCHAR(4000);
		DECLARE V_ERR_MESSAGE		 	VARCHAR(4000);
		DECLARE V_TRANSACTION_LOG_ID 	INT(15);
		
		DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN 
			
			GET DIAGNOSTICS CONDITION 1 @SQLCODE = RETURNED_SQLSTATE, @SQLERRM = MESSAGE_TEXT;
			ROLLBACK;
			
			CALL TRANSACTION_LOGS_ADD_TRANSACTION_LOG(
				V_ERR_CODE,
				V_ERR_MESSAGE,
				V_TRANSACTION_LOG_ID,
				'DEALS_UPDATE_DEAL',
				@SQLCODE,
				@SQLERRM,
				SYSDATE()
			);
			
			SET P_ERR_CODE = CONCAT(@SQLCODE ,'/',V_ERR_CODE);
			SET P_ERR_MESSAGE = CONCAT(@SQLERRM,'/',V_ERR_MESSAGE,'/ THE TRANSACTION ID = ',V_TRANSACTION_LOG_ID);
		END;
		
		DECLARE EXIT HANDLER FOR NOT FOUND BEGIN 
			
			GET DIAGNOSTICS CONDITION 1 @SQLCODE = RETURNED_SQLSTATE, @SQLERRM = MESSAGE_TEXT;
			ROLLBACK;
			
			CALL TRANSACTION_LOGS_ADD_TRANSACTION_LOG(
				V_ERR_CODE,
				V_ERR_MESSAGE,
				V_TRANSACTION_LOG_ID,
				'DEALS_UPDATE_DEAL',
				@SQLCODE,
				@SQLERRM,
				SYSDATE()
			);
			
			SET P_ERR_CODE = CONCAT(@SQLCODE ,'/',V_ERR_CODE);
			SET P_ERR_MESSAGE = CONCAT(@SQLERRM,'/',V_ERR_MESSAGE,'/ THE TRANSACTION ID = ',V_TRANSACTION_LOG_ID);
		END;
		
		UPDATE DEALS
		SET from_currency_code = P_FROM_CURRENCY_CODE,
			to_currency_code = P_TO_CURRENCY_CODE,
			deal_time = P_DEAL_TIME,
			deal_amount = P_DEAL_AMOUNT,
			modification_date = SYSDATE()
		WHERE DEAL_ID = P_DEAL_ID;
	END */$$
DELIMITER ;

/* Procedure structure for procedure `TRANSACTION_LOGS_ADD_TRANSACTION_LOG` */

/*!50003 DROP PROCEDURE IF EXISTS  `TRANSACTION_LOGS_ADD_TRANSACTION_LOG` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`ROOT`@`localhost` PROCEDURE `TRANSACTION_LOGS_ADD_TRANSACTION_LOG`(
	OUT P_ERR_CODE 			VARCHAR(4000),
	OUT P_ERR_MESSAGE		VARCHAR(4000),
	OUT P_TRANSACTION_ID	INT(10),
	IN 	P_PROCEDURE_NAME	VARCHAR(60),
	IN 	P_ERROR_CODE 		VARCHAR(15),
	IN	P_ERROR_MESSAGE		VARCHAR(300),
	IN  P_TRANSACTION_DATE	DATETIME
)
BEGIN
		DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN
		
			GET DIAGNOSTICS CONDITION 1 @SQLCODE = RETURNED_SQLSTATE, @SQLERRM = MESSAGE_TEXT;
			ROLLBACK;
			SET P_ERR_CODE 		= @SQLCODE;
			SET P_ERR_MESSAGE 	= @SQLERRM;
		END;
		
		INSERT INTO TRANSACTION_LOGS
				(	PROCEDURE_NAME,
					ERROR_CODE,
					ERROR_MESSAGE,
					TRANSACTION_DATE
				)
		VALUES (	P_PROCEDURE_NAME,
					P_ERROR_CODE,
					P_ERROR_MESSAGE,
					P_TRANSACTION_DATE
				);
		SELECT LAST_INSERT_ID()
		INTO P_TRANSACTION_ID;
	END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;