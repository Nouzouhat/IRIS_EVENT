drop database if exists scolarite_iris_2024; 
create database scolarite_iris_2024; 
use scolarite_iris_2024; 

create table classe (
	idclasse int(3) not null auto_increment, 
	nom varchar(100), 
	salle varchar(100), 
	diplome varchar(100), 
	primary key (idclasse)
);

create table etudiant (
	idetudiant int(3) not null auto_increment, 
	nom varchar(100), 
	prenom varchar(100), 
	email varchar(100), 
	dateNais date , 
	statut enum ("alternant", "initial"),
	idclasse int(3) not null,
	primary key (idetudiant), 
	foreign key (idclasse) references classe(idclasse)
);

create table professeur (
	idprofesseur int(3) not null auto_increment, 
	nom varchar(100),
	prenom varchar(100), 
	email varchar(100),
	diplome varchar(100), 
	primary key(idprofesseur)
);

create table enseignement (
	idenseignement int(3) not null auto_increment, 
	matiere varchar(100), 
	nbheures int(5), 
	coeff int(2), 
	idclasse int(3) not null, 
	idprofesseur int(3) not null, 
	primary key (idenseignement), 
	foreign key (idclasse) references classe(idclasse), 
	foreign key (idprofesseur) references professeur(idprofesseur)
); 


create table user (
	iduser int(3) not null auto_increment,
	nom varchar(50),
	prenom varchar(50),
    email varchar(50), 
    mdp varchar(255),
	role enum("admin", "user"),
	primary key (iduser)
	);
insert into user values 
	(null, "Olivier","Paul", "a@gmail.com", "123", "admin"),
	(null, "Marie", "lucie", "b@gmail.com", "456", "user");



create view inscription as (
    select e.nom, e.prenom, c.nom as classe, c.diplome, ens.matiere
    from etudiant e, classe c, enseignement ens
    where e.idclasse = c.idclasse
    and c.idclasse = ens.idclasse
);
