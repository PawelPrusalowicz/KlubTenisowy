CREATE TABLE Users(
id INT,
Login VARCHAR(15) NOT NULL,
Password VARCHAR(15) NOT NULL,
TYP VARCHAR(10) NOT NULL CHECK (TYP IN('Client','Admin','Worker'))
);
INSERT INTO Uzytkownicy(Login, Haslo, TYP) VALUES ('Admin', 'Admin', 'Admin');
INSERT INTO Uzytkownicy(Login, Haslo, TYP) VALUES ('Worker', 'Worker', 'Worker');
INSERT INTO Uzytkownicy(Login, Haslo, TYP) VALUES ('Client', 'Client', 'Client');