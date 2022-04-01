CREATE USER 'zavadski'@'localhost' IDENTIFIED BY 'zavadski';
GRANT ALL PRIVILEGES ON * . * TO 'zavadski'@'localhost';

CREATE DATABASE football_teams;

DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS player;

CREATE TABLE Team(
team_id INT NOT NULL auto_increment,
team_name VARCHAR(30) NOT NULL UNIQUE,
PRIMARY KEY (team_id)
);

insert into Team (team_name) values ('Liverpool');
insert into Team (team_name) values ('Arsenal');
insert into Team (team_name) values ('Lester');

CREATE TABLE player (
    player_id int NOT NULL auto_increment,
    first_name varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    birthday date NOT NULL,
    team_id int NOT NULL,
PRIMARY KEY (player_id),
FOREIGN KEY (team_id) REFERENCES team(team_id)
);

insert into PLAYER (first_name, surname, birthday, team_id) values ('Sadio', 'Mane', '1992-4-10', 1);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Roberto', 'Firmino', '1991-10-2', 1);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Mohamed', 'Salah', '1992-06-15', 1);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Alexandre', 'Lacazette', '1991-05-28', 2);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Bukayo', 'Saka', '2001-09-05', 2);