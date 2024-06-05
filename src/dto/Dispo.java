package dto;

public class Dispo {
    private boolean disponiert;
    private String kn_Referenz;
    private String bdf_referenz;
    private String fahrzeugzm;
    private String fahrzeugt;

    public Dispo() {
    }

    public boolean isDisponiert() {
        return disponiert;
    }

    public void setDisponiert(boolean i) {
    }

    public String getKn_Referenz() {
        return kn_Referenz;
    }

    public void setKn_Referenz(String kn_Referenz) {
        this.kn_Referenz = kn_Referenz;
    }

    public String getBdf_referenz() {
        return bdf_referenz;
    }

    public void setBdf_referenz(String bdf_referenz) {
        this.bdf_referenz = bdf_referenz;
    }

    public String getFahrzeugzm() {
        return fahrzeugzm;
    }

    public void setFahrzeugzm(String fahrzeugzm) {
        this.fahrzeugzm = fahrzeugzm;
    }

    public String getFahrzeugt() {
        return fahrzeugt;
    }

    public void setFahrzeugt(String fahrzeugt) {
        this.fahrzeugt = fahrzeugt;
    }
}
