create table aktivnost(
redniBroj int,
regBroj VARCHAR(10),
nazivAktivnosti varchar(30),
opisAktivnosti varchar (100),
obavljena boolean,
datumRealizacije date,
idVrstaAktivnosti int,
primary key (redniBroj, regBroj),
FOREIGN KEY (regBroj) REFERENCES projektniugovor(regBroj),
FOREIGN KEY (idVrstaAktivnosti) REFERENCES vrstaaktivnosti(idVrstaAktivnosti)
)
