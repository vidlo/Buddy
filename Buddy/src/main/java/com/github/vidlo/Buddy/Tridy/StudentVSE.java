/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buddysystem.Tridy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class StudentVSE 
{
   private int ID;
   private String xname;
   private String jmeno;
   private String titul;
   private Date narozeni;
   private String pohlavi;
   private String prislusnost;
   private String adresa;
   private String telefon;

    public StudentVSE() 
    {
        this.xname = "---";
        this.jmeno = "---";
        this.titul = "---";
        this.narozeni = java.sql.Date.valueOf(LocalDate.parse("1990-11-11"));
        this.pohlavi = "---";
        this.prislusnost = "---";
        this.adresa = "---";
        this.telefon = "---"; 
    }

    public StudentVSE(int ID, String xname, String jmeno, String titul, Date narozeni, String pohlavi, String prislusnost, String adresa, String telefon) {
        this.ID = ID;
        this.xname = xname;
        this.jmeno = jmeno;
        this.titul = titul;
        this.narozeni = narozeni;
        this.pohlavi = pohlavi;
        this.prislusnost = prislusnost;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getXname() {
        return xname;
    }

    public void setXname(String xname) {
        this.xname = xname;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    public Date getNarozeni() {
        return narozeni;
    }
    
    public void setNarozeni(Date narozeni)
    {
        LocalDate date = Instant.ofEpochMilli(narozeni.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        this.narozeni = java.sql.Date.valueOf(date);
    }

    public String getPohlavi() {
        return pohlavi;
    }

    public void setPohlavi(String pohlavi) {
        this.pohlavi = pohlavi;
    }

    public String getPrislusnost() {
        return prislusnost;
    }

    public void setPrislusnost(String prislusnost) {
        this.prislusnost = prislusnost;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
