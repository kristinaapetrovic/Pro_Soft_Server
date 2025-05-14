CREATE TABLE sponzor (
	maticniBroj VARCHAR (8) PRIMARY KEY,
nazivFirme VARCHAR (50),
vlasnik VARCHAR (50),
obrisanGI BOOLEAN,
postanskiBroj VARCHAR(5),
FOREIGN KEY (postanskiBroj) REFERENCES mesto(postanskiBroj)
)