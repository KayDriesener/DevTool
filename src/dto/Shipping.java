package dto;

import java.sql.Date;

public class Shipping {
    private int bdf_referenz;
    private Date datum;
    private int kn_referenz;
    private String absender;
    private String empfaenger;
    private Date beladung_s;
    private Date beladung_e;
    private Date entladen_s;
    private Date entladen_e;
    private int stellplaetze;
    private int anzahl;
    private boolean liquid;
    private  boolean adr;
    private  boolean rundlauf;
    private String bemerkung;

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public int getBdf_referenz() {
        return bdf_referenz;
    }

    public void setBdf_referenz(int bdf_referenz) {
        this.bdf_referenz = bdf_referenz;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getKn_referenz() {
        return kn_referenz;
    }

    public void setKn_referenz(int kn_referenz) {
        this.kn_referenz = kn_referenz;
    }

    public String getAbsender() {
        return absender;
    }

    public void setAbsender(String absender) {
        this.absender = absender;
    }

    public String getEmpfaenger() {
        return empfaenger;
    }

    public void setEmpfaenger(String empfaenger) {
        this.empfaenger = empfaenger;
    }

    public Date getBeladung_s() {
        return beladung_s;
    }

    public void setBeladung_s(Date beladung_s) {
        this.beladung_s = beladung_s;
    }

    public Date getBeladung_e() {
        return beladung_e;
    }

    public void setBeladung_e(Date beladung_e) {
        this.beladung_e = beladung_e;
    }

    public Date getEntladen_s() {
        return entladen_s;
    }

    public void setEntladen_s(Date entladen_s) {
        this.entladen_s = entladen_s;
    }

    public Date getEntladen_e() {
        return entladen_e;
    }

    public void setEntladen_e(Date entladen_e) {
        this.entladen_e = entladen_e;
    }

    public int getStellplaetze() {
        return stellplaetze;
    }

    public void setStellplaetze(int stellplaetze) {
        this.stellplaetze = stellplaetze;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public boolean isLiquid() {
        return liquid;
    }

    public void setLiquid(boolean liquid) {
        this.liquid = liquid;
    }

    public boolean isAdr() {
        return adr;
    }

    public void setAdr(boolean adr) {
        this.adr = adr;
    }

    public boolean isRundlauf() {
        return rundlauf;
    }

    public void setRundlauf(boolean rundlauf) {
        this.rundlauf = rundlauf;
    }

}
