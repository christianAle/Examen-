CREATE DATABASE Prematriculas;

USE Prematriculas;

CREATE TABLE Estudiante (
 Carnet varchar(10) NOT NULL,
 Nombre varchar(30)NOT NULL,
 Clave varchar(29)NOT NULL,
 Estado varchar(20),
 Telefono varchar(10),
 email varchaar(50),
 PRIMARY KEY (Carnet)
);

CREATE TABLE Curso (
  Codigo varchar(10) NOT NULL,
  Nombre varchar(30) NOT NULL,
  PRIMARY KEY (Codigo)  
);

CREATE TABLE Prematricula(
  Carnet varchar(10) NOT NULL,
  Codigo varchar(10) NOT NULL,
  Horario varchar(1),
  PRIMARY KEY (Carnet,Codigo),
  FOREIGN KEY (Carnet) REFERENCES Estudiante (Carnet),
  FOREIGN KEY (Codigo) REFERENCES Curso (Codigo) 

);