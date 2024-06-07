# DevTool
prog.DispositionWindow Tool

- Frontend abschließen.
  - Corporate design
- Backend fertigstellen.

  1. [ ] User Layout überarbeiten! (Wird diese Kategorie benötigt?)
     - [x] Insert
     - [ ] Alter
     - [x] Delete
  2. [ ] Kunde
     - [x] Insert
     - [ ] Alter
     - [x] Delete
  3. [ ] Fahrzeug
     - [x] Insert
     - [ ] Alter
     - [ ] Delete (Abfrage Löschen wird korrekt angezeigt, löscht aber nicht)
  4. [ ] Transport
     - [x] Insert (Datumsformat (Uhrzeit anzeigen) Absender & Empfänger abrufen[x])
     - [ ] Alter
     - [x] Delete 
  5. [ ] Dispo
    - [ ] Insert
    - [ ] Alter
    - [ ] Delete
        - Dispo wird korrekt angezeigt.
        - Problem bei der Speicherung. Lieferscheingenerator implementieren. 
        - Sql PK prüfen!!!
          - kn_referenz ist PK. Keine eindeutige Nummer bei zusammenführung von Transporten. (Wechsel auf bdf_referenz für Eindeutigkeit)
 
- Tabellen nach Eingabe oder Löschen von Datensätzen automatisch aktualisieren. [x]
- Tooltips für die Eingabefelder & Felder der Tabellen [x]

- Preise Paletten
    - anzeige als Tabelle.

- Entfernungen
    - Anzeige als Tabelle.
    X | A | B | C | D
    A | 0 | 1 | 2 | 3
    B | 1 | 0 | 4 | 5
    C | 2 | 4 | 0 | 6
    D | 3 | 5 | 6 | 0

    - Entfernungen werden von Hand eingegeben.

- Auswertung der Transporte
    - Eingabe Kennzeichen oder Name: VARCHAR(255) (Kennzeichen via ComboBox)
    - Ausgabe: JTable
        - Fahrzeug | EPal gesamt | Einnahmen | Ausgaben gesamt |
        - Kunde | EPal gesamt | Gewinn | Frachtkosten | Anzahl der Transport

- Passwort vergessen & Change Password für das login fenster

- Für
  - Auswertung
  - Entfernungen
  - Preise
  - Rechnung
    - dto Daten- variablen, typen festlegen.
    - Datenbank um Tables erweitern.
    - Prep Statements erstellen.
    - GUI erstellen.

- MySql
  - Tables prüfen und ggf. anpassen.
  - User erstellen (Passwortsicherheit(siehe Passwort vergessen/ ändern))
  - Schlüssel überprüfen ggf. anpassen.

- MySqlConnector
  - Adresse zur Datenbank ändern.

- FINAL
  - Was wird benötigt? Nur App oder Setup?
    - In eine ausführbare .exe überführen.