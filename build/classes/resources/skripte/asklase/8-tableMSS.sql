CREATE TABLE mss(
jmbg VARCHAR (13),
idStrucnaSprema INT,
obrisanaMSS BOOLEAN,
datumMSS DATE,
PRIMARY KEY(jmbg, idStrucnaSprema),
FOREIGN KEY (jmbg) REFERENCES menadzer(jmbg),
FOREIGN KEY (idStrucnaSprema) REFERENCES strucnasprema(idStrucnaSprema) 
)