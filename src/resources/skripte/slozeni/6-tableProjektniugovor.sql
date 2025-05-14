CREATE TABLE projektniugovor(
regBroj VARCHAR (10) PRIMARY KEY,
nazivProjekta VARCHAR (20),
opisProjekta VARCHAR (200),
budzet DOUBLE,
trajanje INT,
datumPocetka DATE,
datumZavrsetka DATE,
jmbg VARCHAR(13),
FOREIGN KEY (jmbg) REFERENCES menadzer(jmbg) 
)