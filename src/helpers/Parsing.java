package helpers;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
 * Ausgelagerte Parser klassen um die Wiederverwertung von Code zu gewährleisten und die Lesbarkeit in anderen Klassen
 * zu erhalten.
 */
public class Parsing {
    // Methoden zum Parsen der Zeit von String -> java.SQL.Time
    public static Time parseTime(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        java.util.Date utilDate = dateFormat.parse(dateString);
        return new Time(utilDate.getTime());
    }

    // Methoden zum Parsen des Datums von java.util.Date -> java.sql.Date
    public static java.sql.Date parseDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date utilDate = dateFormat.parse(dateString);
        return new java.sql.Date(utilDate.getTime());
    }
}
