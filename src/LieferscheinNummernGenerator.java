import java.text.SimpleDateFormat;
import java.util.Date;

public class LieferscheinNummernGenerator {
    private static Integer auftragsnummer = 1;
    private static Integer letztesJahr = -1;

    public static String generiereNummer() {
        // Aktuelles Jahr abrufen
        SimpleDateFormat jahFormat = new SimpleDateFormat("yyyy");
        int aktuellesJahr = Integer.parseInt(jahFormat.format(new Date()));

        //Zurücksetzen der fortlaufenden Nummer bei Jahreswechsel
        if (aktuellesJahr > letztesJahr) {
            letztesJahr = aktuellesJahr;
            auftragsnummer = 1;
        }

        // Formatierung des String
        String nummerAuftrag = String.format("%04d", auftragsnummer++);

        // Formatierung des Datums
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMyyyy");
        String datumsTeil = dateFormat.format(new Date());

        // Zusammenfügen der Lieferscheinnummer
        return nummerAuftrag + datumsTeil;
    }
}
