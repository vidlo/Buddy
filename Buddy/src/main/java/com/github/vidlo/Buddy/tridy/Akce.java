/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buddysystem.Tridy;

import java.time.LocalDate;


public class Akce 
{
   private int ID_Akce;
   private String nazev;
   private String druh;
   private LocalDate datum_od;
   private String cas_od;
   private LocalDate datum_do;
   private String cas_do;
   private String misto;
   private String popis;
   private int kapacita;
   private int cena;
   private int obsazenaKapacita; 

    public Akce() 
    {
        obsazenaKapacita = 0;
    }

    public Akce(int ID_Akce, String nazev, String druh, LocalDate datum_od, String cas_od, LocalDate datum_do, String cas_do, String misto, String popis, int kapacita, int cena, int obsazenaKapacita) {
        this.ID_Akce = ID_Akce;
        this.nazev = nazev;
        this.druh = druh;
        this.datum_od = datum_od;
        this.cas_od = cas_od;
        this.datum_do = datum_do;
        this.cas_do = cas_do;
        this.misto = misto;
        this.popis = popis;
        this.kapacita = kapacita;
        this.cena = cena;
        this.obsazenaKapacita = obsazenaKapacita;
    }

    public int getID_Akce() {
        return ID_Akce;
    }

    public void setID_Akce(int ID_Akce) {
        this.ID_Akce = ID_Akce;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getDruh() {
        return druh;
    }

    public void setDruh(String druh) {
        this.druh = druh;
    }

    public LocalDate getDatum_od() {
        return datum_od;
    }

    public void setDatum_od(LocalDate datum_od) {
        this.datum_od = datum_od;
    }

    public String getCas_od() {
        return cas_od;
    }

    public void setCas_od(String cas_od) {
        this.cas_od = cas_od;
    }

    public LocalDate getDatum_do() {
        return datum_do;
    }

    public void setDatum_do(LocalDate datum_do) {
        this.datum_do = datum_do;
    }

    public String getCas_do() {
        return cas_do;
    }

    public void setCas_do(String cas_do) {
        this.cas_do = cas_do;
    }

    public String getMisto() {
        return misto;
    }

    public void setMisto(String misto) {
        this.misto = misto;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getObsazenaKapacita() {
        return obsazenaKapacita;
    }

    public void setObsazenaKapacita(int obsazenaKapacita) {
        this.obsazenaKapacita = obsazenaKapacita;
    }
    
    
    
    
    
}
