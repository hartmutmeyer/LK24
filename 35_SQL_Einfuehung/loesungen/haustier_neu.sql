DROP SCHEMA IF EXISTS haustier;
CREATE SCHEMA haustier DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE haustier;

CREATE TABLE tier
(
  tier_id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  tierart VARCHAR(20) NOT NULL,
  lebendig ENUM('ja', 'nein') DEFAULT 'ja',
  geschlecht ENUM('weiblich', 'männlich') DEFAULT 'weiblich',
  geburtstag DATE DEFAULT NULL,
  todestag DATE DEFAULT NULL,
  PRIMARY KEY (tier_id)
);

CREATE TABLE besitzer
(
  besitzer_id INT(11) NOT NULL AUTO_INCREMENT,
  anrede ENUM('Herr', 'Frau', 'Firma') DEFAULT 'Herr',
  vorname VARCHAR(20) DEFAULT NULL,
  nachname VARCHAR(20) NOT NULL,
  straße VARCHAR(30) DEFAULT NULL,
  plz INT(11) DEFAULT NULL,
  ort VARCHAR(30) DEFAULT NULL,
  telefonnr VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (besitzer_id)
);

CREATE TABLE beziehung
(
  beziehung_besitzer_id INT(11) NOT NULL,
  beziehung_tier_id INT(11) NOT NULL,
  PRIMARY KEY (beziehung_besitzer_id, beziehung_tier_id),
  FOREIGN KEY (beziehung_besitzer_id) REFERENCES besitzer (besitzer_id),
  FOREIGN KEY (beziehung_tier_id) REFERENCES tier (tier_id)
);

INSERT INTO tier
(name, tierart, lebendig, geschlecht, geburtstag, todestag)
VALUES
('Bello', 'Hund', 'ja', 'männlich', '2003-05-01', NULL),
('Daisy', 'Kanarienvogel', 'nein', 'weiblich', '1996-12-06', '2004-08-17'),
('Mausi', 'Katze', 'ja', 'weiblich', '2002-11-17', NULL),
('Daisy', 'Schildkröte', 'ja', 'weiblich', '2003-12-06', NULL),
('Lassie', 'Hund', 'ja', 'weiblich', '2004-04-23', NULL),
('Maja', 'Hund', 'ja', 'weiblich', '2004-04-23', NULL),
('Hasso', 'Hund', 'ja', 'männlich', '2004-04-23', NULL),
('Blacky', 'Katze', 'ja', 'männlich', '2004-04-23', NULL),
('Harald', 'Hamster', 'nein', 'männlich', '2001-07-29', '2003-09-15');

INSERT INTO besitzer
(anrede, vorname, nachname, straße, plz, ort, telefonnr)
VALUES
('Firma', NULL, 'Zoo Lilliput', 'Obernstraße 54', 20012, 'Hamburg', '0721/343412'),
('Frau', 'Sandra', 'Sandelmann', 'Unterstraße 17', 28232, 'Bremen', '0421/123456'),
('Herr', 'Mirco', 'Sandelmann', 'Unterstraße 17', 28232, 'Bremen', '0421/123456'),
('Herr', 'Tobias', 'Winkelmann', NULL, NULL, NULL, NULL),
('Frau', 'Sandra', 'Anderson', 'Wilhelminenweg 42', 28315, 'Bremen', NULL),
('Frau', 'Anka', 'Anderson', 'Wilhelminenweg 42', 28315, 'Bremen', NULL),
('Herr', 'Max', 'Anderson', 'Wilhelminenweg 42', 28315, 'Bremen', NULL),
('Herr', 'Kai', 'Anderson', 'Unsinnstraße 65', 28245, 'Bremen', NULL),
('Frau', 'Johanna', 'Sonntag', 'Glücksweg 13', 28333, 'Bremen', NULL);

INSERT INTO beziehung VALUES
(
    (SELECT besitzer_id FROM besitzer WHERE nachname = 'Zoo Lilliput'),
    (SELECT tier_id FROM tier WHERE name = 'Bello')
),
(
    (SELECT besitzer_id FROM besitzer WHERE nachname = 'Zoo Lilliput'),
    (SELECT tier_id FROM tier WHERE name = 'Lassie')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Sandra' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Daisy' AND tierart = 'Kanarienvogel')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Sandra' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Blacky')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Mirco' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Blacky')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Mirco' AND nachname = 'Sandelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Harald')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Tobias' AND nachname = 'Winkelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Daisy' AND tierart = 'Schildkröte')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Tobias' AND nachname = 'Winkelmann'),
    (SELECT tier_id FROM tier WHERE name = 'Hasso')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Sandra' AND nachname = 'Anderson'),
    (SELECT tier_id FROM tier WHERE name = 'Maja')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Anka' AND nachname = 'Anderson'),
    (SELECT tier_id FROM tier WHERE name = 'Maja')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Max' AND nachname = 'Anderson'),
    (SELECT tier_id FROM tier WHERE name = 'Maja')
),
(
    (SELECT besitzer_id FROM besitzer WHERE vorname = 'Kai' AND nachname = 'Anderson'),
    (SELECT tier_id FROM tier WHERE name = 'Maja')
);



