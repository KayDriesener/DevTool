import java.text.SimpleDateFormat;
import java.util.Date;

public class LieferscheinNummernGenerator {
    private static Integer auftragsnummer = 1;
    private static Integer letztesJahr = -1;

    public static String generiereNummer() {
        // Aktuelles Jahr abrufen
        SimpleDateFormat jahFormat = new SimpleDateFormat("yyyy");
        Integer aktuellesJahr = Integer.parseInt(jahFormat.format(new Date()));

        //Zurücksetzen der fortlaufenden Nummer bei Jahreswechsel
        if (aktuellesJahr > letztesJahr) {
            letztesJahr = aktuellesJahr;
            auftragsnummer = 1;
        }

        String nummerAuftrag = String.format("%04d", auftragsnummer++);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMyyyy");
        String datumsTeil = dateFormat.format(new Date());

        String lieferscheinnummer = nummerAuftrag + datumsTeil;

        return lieferscheinnummer;
    }
}
