USE haustier;

# ==============================================================================
# Übung 2, Aufgabe 1: Tabellen verknüpfen
# ==============================================================================

# 1a) Zeige ohne Verwendung von JOIN oder WHERE eine Tabelle mit allen Besitzern
#     (Nachname und Vorname) und Tieren (Name und Tierart) an.
#     Ergebnis: Es entsteht das Kartesische Produkt (jede Zeile aus besitzer wird
#     mit jeder Zeile aus tier kombiniert -> 9 * 9 = 81 Zeilen).
SELECT besitzer.nachname, besitzer.vorname, tier.name, tier.tierart
FROM besitzer, tier;

# 1b) Zeige eine Liste mit allen Besitzern und den zu ihnen gehörenden Tieren an.
#     Es sollen alle Spalten aus besitzer und tier angezeigt werden.
SELECT besitzer.*, tier.*
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id;

# 1c) Zeige eine Tabelle mit allen Besitzern (Vor- und Nachname) und ihren Tieren
#     (Name und Tierart) an. Sortiere die Liste absteigend nach dem Namen der Tiere.
SELECT besitzer.vorname, besitzer.nachname, tier.name, tier.tierart
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
ORDER BY tier.name DESC;

# 1d) Zeige eine Tabelle mit allen Besitzern (Nachname und Vorname) und ihren Tieren
#     (Name und Tierart) an. Sortiere die Liste in aufsteigender alphabetischer
#     Reihenfolge zunächst nach dem Nachnamen und dann nach dem Vornamen.
SELECT besitzer.nachname, besitzer.vorname, tier.name, tier.tierart
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
ORDER BY besitzer.nachname ASC, besitzer.vorname ASC;

# 1e) Zeige eine Tabelle mit allen Besitzern (Vor- und Nachname) und ihren lebendigen
#     Tieren an (Name und Tierart). Sortiere die Liste nach der Tierart.
SELECT besitzer.vorname, besitzer.nachname, tier.name, tier.tierart
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
WHERE lebendig = 'ja'
ORDER BY tier.tierart ASC;

# 1f) Zeige alle Tiere von Mirco Sandelmann an (Name, Tierart und lebendig).
SELECT tier.name, tier.tierart, tier.lebendig
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
WHERE besitzer.vorname = 'Mirco'
  AND besitzer.nachname = 'Sandelmann';

# 1g) Wähle alle Besitzer von Hunden aus. Zeige Vor- und Nachname der Besitzer sowie
#     Name, Geburtstag und Todestag des Hundes an.
SELECT besitzer.vorname, besitzer.nachname, tier.name, tier.geburtstag, tier.todestag
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
WHERE tier.tierart = 'Hund';

# 1h) Liste alle Besitzer von Maja auf (Vor- und Nachname).
SELECT besitzer.vorname, besitzer.nachname
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
WHERE tier.name = 'Maja';

# 1i) Zähle die Anzahl der Besitzer von Blacky.
SELECT COUNT(*) AS "Anzahl der Besitzer von Blacky"
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
WHERE tier.name = 'Blacky';

# 1j) Erstelle eine Liste, die angibt, wie viele Tiere jede einzelne Person besitzt
#     (Ausgabe: Anzahl, Vor- und Nachname). Sortiere die Liste in umgekehrter
#     alphabetischer Reihenfolge nach dem Nachnamen.
SELECT COUNT(*) AS "Anzahl Tiere", besitzer.vorname, besitzer.nachname
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
GROUP BY besitzer.besitzer_id
ORDER BY besitzer.nachname DESC;

# 1k) Zeige alle Besitzer mit Vor- und Nachnamen an, die zwei oder mehr Tiere besitzen.
SELECT besitzer.vorname, besitzer.nachname
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
GROUP BY besitzer.besitzer_id
HAVING COUNT(*) >= 2;

# 1l) Zähle die Anzahl der Besitzer, die einen Hund oder eine Katze besitzen.
SELECT COUNT(DISTINCT besitzer_id) AS "Anzahl Besitzer von Hunden und/oder Katzen"
FROM besitzer
JOIN beziehung ON besitzer_id = beziehung_besitzer_id
JOIN tier ON tier_id = beziehung_tier_id
WHERE tierart IN ('Hund', 'Katze');

# 1m) Liste die vollständigen Daten aller Besitzer auf, deren Telefonnummer nicht bekannt ist.
SELECT *
FROM besitzer
WHERE telefonnr IS NULL;

# 1n) Liste Straße, PLZ und Ort aller Besitzer auf. Dabei soll jede Adresse nur einmal
#     ausgegeben werden.
SELECT DISTINCT straße, plz, ort
FROM besitzer;


# ==============================================================================
# Übung 2, Aufgabe 2: LEFT JOIN und Unterabfragen
# ==============================================================================

# 2a) Liste die Personen, die momentan kein Tier besitzen, mit ihrem Vor- und Nachnamen auf.
SELECT besitzer.vorname, besitzer.nachname
FROM besitzer
LEFT JOIN beziehung ON beziehung_besitzer_id = besitzer_id
WHERE beziehung_tier_id IS NULL;

# 2b) Liste die Tiere mit Namen auf, die keinen Besitzer haben.
SELECT tier.name
FROM tier
LEFT JOIN beziehung ON beziehung_tier_id = tier_id
WHERE beziehung_besitzer_id IS NULL;

# 2c) Liste alle Personen, die mit Anka Anderson zusammenwohnen (d.h. alle, die dieselbe
#     Straße haben), mit ihren vollständigen Daten auf. Anka selbst darf auch in der
#     Liste erscheinen.
SELECT *
FROM besitzer
WHERE straße IN (
    SELECT straße
    FROM besitzer
    WHERE vorname = 'Anka'
      AND nachname = 'Anderson'
      AND ort = 'Bremen'
);

# 2d) Liste alle Tiere mit Namen auf, die am selben Tag geboren wurden wie Maja.
#     Maja selbst soll nicht in der Liste erscheinen.
SELECT name
FROM tier
WHERE geburtstag = (
    SELECT geburtstag
    FROM tier
    WHERE name = 'Maja'
)
AND name != 'Maja';

# 2e) Liste die Namen der Tiere auf, die am selben Tag geboren wurden wie Maja, sowie den
#     Vor- und Nachnamen ihrer Besitzer. Maja selbst soll nicht in der Liste erscheinen.
SELECT tier.name, besitzer.vorname, besitzer.nachname
FROM tier
JOIN beziehung ON tier_id = beziehung_tier_id
JOIN besitzer ON beziehung_besitzer_id = besitzer_id
WHERE tier.geburtstag = (
    SELECT geburtstag
    FROM tier
    WHERE name = 'Maja'
)
AND tier.name != 'Maja';

# 2f) Liste alle Tiere mit Namen und Tierart auf, die denselben Besitzer haben wie Hasso.
#     Hasso darf ebenfalls in der Liste erscheinen, aber kein Tier soll doppelt genannt werden.
SELECT DISTINCT tier.name, tier.tierart
FROM tier
JOIN beziehung ON tier_id = beziehung_tier_id
WHERE beziehung_besitzer_id IN (
    SELECT beziehung_besitzer_id
    FROM tier
    JOIN beziehung ON tier_id = beziehung_tier_id
    WHERE name = 'Hasso'
);
