CREATE USER 'zavadski'@'localhost' IDENTIFIED BY 'zavadski';
GRANT ALL PRIVILEGES ON * . * TO 'zavadski'@'localhost';

CREATE DATABASE football_teams;

DROP TABLE IF EXISTS team;

CREATE TABLE Team(
team_id INT NOT NULL auto_increment,
team_name VARCHAR(30) NOT NULL UNIQUE,
PRIMARY KEY (team_id)
);

insert into Team (team_name) values ('Liverpool');
insert into Team (team_name) values ('Arsenal');
insert into Team (team_name) values ('Lester');