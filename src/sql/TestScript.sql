create database if not exists test;
use test;

create table if not exists user (
	id integer primary key auto_increment,
    vorname varchar(50),
    nachname varchar(50),
    email varchar(50),
    username varchar(50)
);

create table if not exists kunde (
	id integer primary key auto_increment,
    firma varchar(100),
    strasse varchar(100),
    nummer integer,
    postleitzahl varchar(10),
    ort varchar(50),
    abteilung varchar(100),
    ansprechpartner varchar(100),
    telefonnummer integer,
    email varchar(100),
    bemerkungen longtext
);

create table if not exists fahrzeug_zm (
	id integer primary key auto_increment,
    anbieter varchar(50),
    kennzeichen varchar(50),
    art varchar(50),
    miete float,
    pruefungen date,
    tuef date,
    kostenstelle integer
);

create table if not exists fahrzeug_t (
	id integer primary key auto_increment,
    anbieter varchar(50),
    kennzeichen varchar(50),
    art varchar(50),
    miete float,
    pruefungen date,
    tuef date,
    kostenstelle integer
);

create table if not exists transport (
	bdf_referenz int primary key,
    datum date,
    kn_referenz int,
    absender varchar(255),
    empfaenger varchar(255),
    beladung_s datetime,
    beladung_e datetime,
    entladen_s datetime,
    entladen_e datetime,
    stellplaetze integer,
    anzahl integer,
    liquid boolean,
    adr boolean,
    rundlauf boolean
);
