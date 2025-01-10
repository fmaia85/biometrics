DROP TABLE IF EXISTS equipe;
CREATE TABLE equipe(
    id bigint not null auto_increment,
    nome varchar(150),
    local varchar(150),
    PRIMARY KEY(id)
);

ALTER TABLE atleta ADD COLUMN equipe bigint;

ALTER TABLE atleta ADD CONSTRAINT fk_equipe FOREIGN KEY(equipe) REFERENCES equipe(id);
