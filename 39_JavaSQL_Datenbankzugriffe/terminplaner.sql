DROP SCHEMA IF EXISTS terminplaner;
CREATE SCHEMA terminplaner DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE terminplaner;

CREATE TABLE termin
(
   termin_id INT AUTO_INCREMENT,
   datum DATE,
   zeit TIME,
   text VARCHAR(50) NOT NULL,
   PRIMARY KEY (termin_id)
);