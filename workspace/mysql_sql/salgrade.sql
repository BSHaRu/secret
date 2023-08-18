DROP TABLE IF EXISTS `salgrade`;

CREATE TABLE `salgrade` (
  `grade` int DEFAULT NULL,
  `losal` int DEFAULT NULL,
  `hisal` int DEFAULT NULL
);

INSERT INTO `salgrade` VALUES (1,700,1200),(2,1201,1400),(3,1401,2000),(4,2001,3000),(5,3001,9999);
