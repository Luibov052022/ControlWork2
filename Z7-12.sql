DROP SCHEMA IF EXISTS attest2;
CREATE SCHEMA attest2;
USE attest2;
DROP DATABASE IF EXISTS my_friends;
CREATE DATABASE my_friends;

DROP TABLE IF EXISTS type_animal1;
CREATE TABLE type_animal1 (
	id1 SERIAL PRIMARY KEY  NOT NULL AUTO_INCREMENT UNIQUE,
    name_type1 VARCHAR(50)    
);

DROP TABLE IF EXISTS type_animal2;
CREATE TABLE type_animal2 (
	id2 SERIAL PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    name_type2 VARCHAR(50),
    id1 BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (id1) REFERENCES type_animal1(id1) ON UPDATE CASCADE ON DELETE CASCADE    
);

DROP TABLE IF EXISTS animal;
CREATE TABLE animal (
	id SERIAL PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    name VARCHAR(50),  
    id2 BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (id2) REFERENCES type_animal2(id2) ON UPDATE CASCADE ON DELETE CASCADE,
    birthday DATE,
    commands VARCHAR(100)
);

INSERT INTO type_animal1 VALUES 
(1, 'Домашние животные'),
(2, 'Вьючные животные');

INSERT INTO type_animal2 VALUES 
(1, 'Cобака', 1),
(2, 'Кошка', 1),
(3, 'Хомяк', 1),
(4, 'Лошадь', 2),
(5, 'Осел', 2),
(6, 'Верблюд', 2);

INSERT INTO animal VALUES
(1, 'Рыжик', 2, '2022-07-01', "12222 5555 ууеуеыавыаыа"),
(2, 'Раак', 2, '2020-05-01', "12222 5555 ууеуеыавыаыа"),
(3, 'пппввп', 1, '2021-08-01', "12222 5555 ууеуеыавыаыа"),
(4, 'рооор', 1, '2023-01-01', "12222 5555 ууеуеыавыаыа"),
(5, 'впвпвп', 3, '2015-08-01', "12222 5555 ууеуеыавыаыа"),
(6, '1222', 3, '2021-03-01', "12222 5555 ууеуеыавыаыа"),
(7, 'рара', 4, '2021-08-01', "12222 5555 ууеуеыавыаыа"),
(8, 'рллр', 4, '2013-10-01', "12222 5555 ууеуеыавыаыа"),
(9, 'мсмс', 5, '2008-02-01', "12222 5555 ууеуеыавыаыа"),
(10, 'тмтмтм', 5, '2023-04-01', "12222 5555 ууеуеыавыаыа"),
(11, 'ыывывв', 6, '2023-05-01', "12222 5555 ууеуеыавыаыа"),
(12, 'оддоод', 6, '2019-10-01', "12222 5555 ууеуеыавыаыа");

# Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу

DELETE FROM type_animal2
WHERE name_type2 = 'Верблюд';

/* Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце 
с точностью до месяца подсчитать возраст животных в новой таблице */

DELIMITER $$
CREATE FUNCTION age (birthday DATE)
RETURNS TEXT
DETERMINISTIC
BEGIN
    DECLARE res TEXT DEFAULT '';
	SET res = CONCAT(
            TIMESTAMPDIFF(YEAR, birthday, CURDATE()),
            ' года ',
            TIMESTAMPDIFF(MONTH, birthday, CURDATE()) % 12,
            ' месяцев'
        );
	RETURN res;
END $$
DELIMITER ;

SELECT name, birthday, age(birthday) FROM animal AS yang_animals
WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 1 AND 3
ORDER BY age(birthday);

# Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам. 

SELECT name, birthday, commands, name_type2, name_type1  FROM animal
LEFT JOIN type_animal2 AS t2 ON animal.id2 = t2.id2
LEFT JOIN type_animal1 AS t1 ON t1.id1 = t2.id1;




