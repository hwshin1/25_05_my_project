DROP DATABASE IF EXISTS test;
CREATE DATABASE test;
USE test;

DROP TABLE IF EXISTS kakao;
CREATE TABLE kakao (
	kakao_id BIGINT UNIQUE PRIMARY KEY NOT NULL,
	access_token TEXT,
	refresh_token TEXT,
	kakao_email VARCHAR(50) NOT NULL,
	kakao_nickname VARCHAR(30) NOT NULL,
	kakao_regDate VARCHAR(100) NOT NULL
);

SELECT * FROM Kakao;