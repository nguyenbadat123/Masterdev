-- --------------------------Create tables-------------------------------
CREATE TABLE `Student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `Teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE Class(
	id int PRIMARY KEY ,
	IdTeacher int NOT NULL,
	IdSubject int NOT NULL ,
	`group` int NOT NUll
);

CREATE TABLE ClassDetail(
	id int PRIMARY KEY ,
	idClass int,
	idStudent int NOT NULL ,
	score float UNSIGNED
	
);
------------------------------bai tap----------------------------------
-- C1

SELECT ROUND( AVG(cd.score),3) as avg_score
FROM ClassDetail cd ;


-- C2
SELECT (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd  
WHERE cd.score  <4.0) *100 / (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd ) as TLTruot

SELECT (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd  
WHERE cd.score  >4.0 and cd.score < 6.0) *100 / (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd ) as TLDiemTrungbinh

SELECT (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd  
WHERE cd.score  >6.0 and cd.score < 8.0) *100 / (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd ) as TLDiemKha;

SELECT (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd  
WHERE cd.score  >8.0 and cd.score < 9.0) *100 / (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd ) as TLDiemGioi;

SELECT (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd  
WHERE cd.score  >9.0 ) *100 / (SELECT COUNT(cd.score) 
from masterdev_chibm.ClassDetail cd ) as TLDiemXuatSac;


-- C3
SELECT s.id  ,s.name , AVG(cd.score) as max_avg_score
FROM Subject s , ClassDetail cd , Class c 
WHERE s.id = c.id 
AND c.id = cd.idClass 
GROUP BY s.name 
ORDER BY max_avg_score DESC 
LIMIT 1;

-- c4

SELECT cd.idClass , AVG(cd.score) AS max_avg_score
FROM ClassDetail cd , 
GROUP BY cd.idClass  
ORDER BY max_avg_score DESC
LIMIT 1;

-- C5 ban nao co diem tring binh cao nhat

SELECT st.name  AS student_name, AVG(cd.score) AS max_avg_score
FROM ClassDetail cd , Student st
WHERE cd.idStudent = st.id 
GROUP BY cd.idStudent 
ORDER BY max_avg_score DESC 
LIMIT 1;

-- C6 Mon nao co ti le truot cao nhat 

SELECT tmp1.nameSubject AS subject_name, tmp2.cnt/tmp1.cnt AS fail_ration FROM (
(SELECT sbj.id AS idSubject, sbj.name AS nameSubject, COUNT(sbj.id) AS cnt  
FROM ClassDetail sc, Class class, Subject sbj
WHERE sc.idClass = class.id AND class.idSubject = sbj.id
GROUP BY sbj.id) tmp1 
INNER JOIN 
(SELECT sbj.id AS idSubject, sbj.name AS nameSubject, COUNT(sbj.id) AS cnt
FROM ClassDetail  sc, Class class, Subject sbj
WHERE sc.idClass = class.id AND class.idSubject = sbj.id AND sc.score < 4.0
GROUP BY sbj.id) tmp2
ON tmp1.idSubject = tmp2.idSubject)
ORDER BY tmp2.cnt/tmp1.cnt DESC
LIMIT 1;