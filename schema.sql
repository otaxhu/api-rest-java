DROP DATABASE IF EXISTS api_rest_java;

CREATE DATABASE api_rest_java;

CREATE TABLE movies(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(80) NOT NULL,
    date DATE,
    cover_url VARCHAR(255) NOT NULL
);