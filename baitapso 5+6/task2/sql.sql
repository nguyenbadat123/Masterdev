
---------------------------Create Table Users-------------------------------------
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  `Fullname` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  `Province` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  `Age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  FULLTEXT KEY `index_fulltext_Province` (`Province`)
) ENGINE=InnoDB AUTO_INCREMENT=1014402 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



-- ---------------------bai 2--------------------------
-- c1 : tim kiem user ghtk
EXPLAIN
SELECT *
FROM users u 
WHERE u.Username = 'GHTK';




-- c2 tim tio 10 user 'SG' lon tuoi nhat
EXPLAIN 
SELECT *
FROM users u 
WHERE u.Province = 'SG'
ORDER BY u.Age DESC 
LIMIT 10;

-- index
-- index
ALTER TABLE  users 
DROP INDEX index_Province ;

ALTER TABLE users  ADD INDEX index_Province (Province);

EXPLAIN 
SELECT *
FROM users u 
WHERE u.Province = 'SG'
ORDER BY u.Age DESC 
LIMIT 10;
-- index fulltext
ALTER TABLE  users 
DROP INDEX  index_fulltext_Province ;

ALTER TABLE users  ADD FULLTEXT index_fulltext_Province (Province);

EXPLAIN 
SELECT *
FROM users u 
WHERE MATCH (Province) AGAINST('SG')
ORDER BY u.Age DESC 
LIMIT 10;