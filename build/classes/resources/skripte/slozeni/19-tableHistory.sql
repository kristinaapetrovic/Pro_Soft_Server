CREATE TABLE HISTORY(
id INT AUTO_INCREMENT PRIMARY KEY,
vreme DATE,
menadzer VARCHAR(13),
vrstaOperacije VARCHAR(25),
objekatBaze VARCHAR(50),
staraVrednost JSON,
novaVrednost JSON,
FOREIGN KEY (menadzer) REFERENCES menadzer(jmbg)
)