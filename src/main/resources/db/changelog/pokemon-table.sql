--liquibase formatted sql

--changeset aman:pokemon-table
CREATE TABLE pokemon (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	power varchar(100) NULL,
	imageUrl varchar(100) NULL,
	CONSTRAINT pokemon_pk PRIMARY KEY (id),
	CONSTRAINT pokemon_name_unique UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;
