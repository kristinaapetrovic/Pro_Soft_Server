CREATE TABLE menadzer (
    jmbg VARCHAR(13) PRIMARY KEY,
    imePrezime VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    lozinka VARCHAR(255) NOT NULL,
    datumRodjenja DATE NOT NULL,
    aktivanNalog BOOL NOT NULL,
    prviLog BOOL NOT NULL
)



