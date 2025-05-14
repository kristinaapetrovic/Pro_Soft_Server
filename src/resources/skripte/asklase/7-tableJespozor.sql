CREATE TABLE jesponzor(
regBroj VARCHAR (10),
maticniBroj VARCHAR (8),
robni BOOLEAN,
novcani BOOLEAN,
iznos DOUBLE,
PRIMARY KEY (regBroj, maticniBroj),
FOREIGN KEY (regBroj) REFERENCES projektniugovor(regBroj),
FOREIGN KEY (maticniBroj) REFERENCES sponzor(maticniBroj) 
)