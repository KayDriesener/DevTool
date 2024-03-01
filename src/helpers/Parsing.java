package helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parsing {
    // Methoden zum Parsen des Datums von java.util.Date -> java.sql.Date
    public static java.sql.Date parseDateTime(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        java.util.Date utilDate = dateFormat.parse(dateString);
        return new java.sql.Date(utilDate.getTime());
    }

    // Methoden zum Parsen des Datums von java.util.Date -> java.sql.Date
    public static java.sql.Date parseDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date utilDate = dateFormat.parse(dateString);
        return new java.sql.Date(utilDate.getTime());
    }
}
