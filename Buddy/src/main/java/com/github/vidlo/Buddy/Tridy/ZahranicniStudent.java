/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buddysystem.Tridy;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static javax.xml.bind.DatatypeConverter.parseDate;


public class ZahranicniStudent 
{
   private int ID_Student;
   private String xname;
   private String jmeno;
   private Date narozeni;
   private String pohlavi;
   private String prislusnost;
   private String adresa;
   private String telefon;
   private String email;

   public ZahranicniStudent() 
    {
        this.xname = "---";
        this.jmeno = "---";
        this.narozeni = java.sql.Date.valueOf(LocalDate.parse("1990-11-11"));
        this.pohlavi = "---";
        this.prislusnost = "---";
        this.adresa = "---";
        this.telefon = "---";
        this.email = "---";
    }
   
    public ZahranicniStudent(String xname) 
    {
        this.xname = xname;
        this.jmeno = "---";
        this.narozeni = java.sql.Date.valueOf(LocalDate.parse("1990-11-11"));
        this.pohlavi = "---";
        this.prislusnost = "---";
        this.adresa = "---";
        this.telefon = "---";
        this.email = "---";
    }

    public ZahranicniStudent(int ID_Student, String xname, String jmeno, Date narozeni, String pohlavi, String prislusnost, String adresa, String telefon, String email) 
    {
        this.ID_Student = ID_Student;
        this.xname = xname;
        this.jmeno = jmeno;
        this.narozeni = narozeni;
        this.pohlavi = pohlavi;
        this.prislusnost = prislusnost;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
    }

    public int getID_Student() {
        return ID_Student;
    }

    public void setID_Student(int ID_Student) {
        this.ID_Student = ID_Student;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
   
   
   
   
   
}
