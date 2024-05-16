package dto;

import java.util.Date;

/*
 * Fahrzeugklasse mit Parametern.
 */
public class Fahrzeug {
    private Integer id;
    private String anbieter;
    private String kennzeichen;
    private String art;
    private Float miete;
    private Date pruefungen;
    private Date tuef;
    private Integer kostenstelle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(String anbieter) {
        this.anbieter = anbieter;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public Float getMiete() {
        return miete;
    }

    public void setMiete(Float miete) {
        this.miete = miete;
    }

    public Date getPruefungen() {
        return pruefungen;
    }

    public void setPruefungen(Date pruefungen) {
        this.pruefungen = pruefungen;
    }

    public Date getTuef() {
        return tuef;
    }

    public void setTuef(Date tuef) {
        this.tuef = tuef;
    }

    public Integer getKostenstelle() {
        return kostenstelle;
    }

    public void setKostenstelle(Integer kostenstelle) {
        this.kostenstelle = kostenstelle;
    }

    @Override
    public String toString() {
        return kennzeichen;
    }
}
