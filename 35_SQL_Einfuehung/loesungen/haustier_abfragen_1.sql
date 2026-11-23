USE haustier;

# Übung 1: Einfache Abfragen auf einer Tabelle (tier)

# a) Zeige die gesamte Tabelle tier an.
SELECT *
FROM tier;

# b) Zeige nur die Spalten name und tierart an.
SELECT name, tierart
FROM tier;

# c) Liste die Spalten name und tierart auf. Sortiere dabei in aufsteigender alphabetischer
#    Reihenfolge nach dem Namen. An zweiter Stelle soll nach der Tierart sortiert werden
#    (auch aufsteigend).
SELECT name, tierart
FROM tier
ORDER BY name ASC, tierart ASC;

# d) Zeige nur die Spalte geburtstag an. Sorge dafür, dass jeder Wert nur einmal angezeigt wird.
SELECT DISTINCT geburtstag
FROM tier;

# e) Zeige die Spalten name und tierart für alle Tiere an, die noch leben.
SELECT name, tierart
FROM tier
WHERE lebendig = 'ja';

# f) Liste die Namen aller Tiere auf, die vor dem Jahr 2004 geboren wurden und noch nicht
#    tot sind. Sortiere die Namen in absteigender alphabetischer Reihenfolge.
SELECT name
FROM tier
WHERE geburtstag < '2004-01-01'
  AND todestag IS NULL
ORDER BY name DESC;

# g) Liste alle Spalten der Tiere auf, die weder Hund noch Katze sind.
SELECT *
FROM tier
WHERE tierart != 'Hund'
  AND tierart != 'Katze';

# Alternativ mit NOT IN:
SELECT *
FROM tier
WHERE tierart NOT IN ('Hund', 'Katze');

# h) Zeige die Namen und die Geburtstage aller Tiere an, für die kein Todestag angegeben wurde.
SELECT name, geburtstag
FROM tier
WHERE todestag IS NULL;

# i) Zähle, wie viele Tiere nach dem 01.01.2003 geboren wurden.
SELECT COUNT(*) AS "Anzahl Tiere, die nach dem 1. Januar 2003 geboren wurden"
FROM tier
WHERE geburtstag > '2003-01-01';

# j) Liste auf, wie viele Tiere es von jeder Tierart gibt. Das Ergebnis soll eine Tabelle
#    mit der Anzahl und der jeweiligen Tierart sein.
SELECT COUNT(*) AS "Anzahl", tierart
FROM tier
GROUP BY tierart;

# k) Liste alle Tierarten auf, von denen es zwei oder mehr Tiere gibt.
SELECT tierart
FROM tier
GROUP BY tierart
HAVING COUNT(*) >= 2;

# l) Zähle die Anzahl der unterschiedlichen Tierarten, die in der Tabelle vorkommen.
SELECT COUNT(DISTINCT tierart) AS "Anzahl Tierarten"
FROM tier;

