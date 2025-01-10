DROP TABLE IF EXISTS atleta;

CREATE TABLE atleta(
    id bigint not null auto_increment,
    nome varchar(150),
    idade int,
    peso float,
    altura float,
    PRIMARY KEY(id)
);