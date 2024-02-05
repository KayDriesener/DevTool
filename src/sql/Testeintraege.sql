INSERT INTO user (vorname, nachname, email, username)
VALUES
    ('John', 'Doe', 'john.doe@example.com', 'john_doe'),
    ('Jane', 'Smith', 'jane.smith@example.com', 'jane_smith');
INSERT INTO kunde (firma, strasse, nummer, postleitzahl, ort, abteilung, ansprechpartner, telefonnummer, email, bemerkungen)
VALUES
    ('ABC Firma', 'Hauptstraße', 123, '12345', 'Stadtville', 'Vertrieb', 'Herr Manager', 987654321, 'info@abc.com', 'Zusätzliche Anmerkungen hier'),
    ('XYZ Corporation', 'Broadway', 456, '67890', 'Stadtstadt', 'Marketing', 'Frau Koordinator', 123456789, 'info@xyzcorp.com', 'Weitere Bemerkungen');

INSERT INTO fahrzeug_zm (anbieter, kennzeichen, art, miete, pruefungen, tuef, kostenstelle)
VALUES
    ('Autovermietung', 'ABC123', 'Zugmaschine', 50.00, '2024-02-01', '2024-03-01', 101),
    ('Transportdienstleistungen', 'XYZ789', 'Zugmaschine', 150.00, '2024-02-15', '2024-03-15', 102);
INSERT INTO fahrzeug_t (anbieter, kennzeichen, art, miete, pruefungen, tuef, kostenstelle)
VALUES
    ('Stadt Autovermietung', 'DEF456', 'Trailer', 40.00, '2024-02-10', '2024-03-10', 103),
    ('Logistik AG', 'GHI789', 'Trailer', 80.00, '2024-02-20', '2024-03-20', 104);
INSERT INTO transport (bdf_referenz, datum, kn_referenz, absender, empfaenger, beladung_s, beladung_e, entladen_s, entladen_e, stellplaetze, anzahl, liquid, adr, rundlauf)
VALUES
    (1, '2024-02-05', 201, 'Absender A', 'Empfänger X', '2024-02-05 08:00:00', '2024-02-05 10:00:00', '2024-02-05 15:00:00', '2024-02-05 18:00:00', 5, 100, TRUE, FALSE, TRUE),
    (2, '2024-02-12', 202, 'Absender B', 'Empfänger Y', '2024-02-12 09:00:00', '2024-02-12 11:00:00', '2024-02-12 16:00:00', '2024-02-12 19:00:00', 8, 150, FALSE, TRUE, FALSE);