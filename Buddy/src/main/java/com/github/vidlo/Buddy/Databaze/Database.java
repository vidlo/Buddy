/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buddysystem.Databaze;

import buddysystem.Tridy.Akce;
import buddysystem.Tridy.StudentVSE;
import buddysystem.Tridy.Ucast;
import buddysystem.Tridy.ZahranicniStudent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class Database 
{  
    
private Connection con;
    private Statement st;
    private ResultSet rs;
   // konstruktor  třídy databáze, kterým se aplikace připojí k databázi
    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=yes&characterEncoding=UTF-8", "root", "");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

    }
    // Metoda pro nahrání všech studentů z VŠE
     public ObservableList<StudentVSE> listStudentuVSE()
     {
         try {
            String query = "SELECT * FROM `studentvse`";
            rs = st.executeQuery(query);
            ObservableList<StudentVSE>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                StudentVSE pom = new StudentVSE();
                pom.setID(rs.getInt(1));
                pom.setXname(rs.getString(2));
                pom.setJmeno(rs.getString(3));
                pom.setTitul(rs.getString(4));
                
                java.util.Date input = rs.getDate(5);
                pom.setNarozeni(input);
                
                pom.setPohlavi(rs.getString(6));
                pom.setPrislusnost(rs.getString(7));
                pom.setAdresa(rs.getString(8));
                pom.setTelefon(rs.getString(9));
                list.add(pom);
            }
            return list;
            } 
         catch(Exception ex) 
            {
     System.out.println(ex);
            }
         return null;
      }
    // Metoda pro nahrání všech zahraničních studentů
     public ObservableList<ZahranicniStudent> listZahranicnichStudentu()
    {
        try {
            String query = "SELECT * FROM `zahranicnistudent`";
            rs = st.executeQuery(query);
            ObservableList<ZahranicniStudent>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                ZahranicniStudent pom = new ZahranicniStudent();
                pom.setID_Student(rs.getInt(1));
                pom.setXname(rs.getString(2));
                pom.setJmeno(rs.getString(3));
                
                java.util.Date input = rs.getDate(4);
                pom.setNarozeni(input);
                
                pom.setPohlavi(rs.getString(5));
                pom.setPrislusnost(rs.getString(6));
                pom.setAdresa(rs.getString(7));
                pom.setTelefon(rs.getString(8));
                pom.setEmail(rs.getString(9));
                list.add(pom);
            }
            return list;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     // Metoda pro nahrání všech akcí
     public ObservableList<Akce> listAkci()
    {
        try {
            String query = "SELECT * FROM `akce`"; // SQL s dotazem
            rs = st.executeQuery(query); // Provedení dotazu
            ObservableList<Akce>list = FXCollections.observableArrayList(); // Vytvoření vraceného listu
            while(rs.next()) // Pro každý záznam
            {
                Akce pom = new Akce(); // Vytvoř novou akci a postupně naplň daty
                pom.setID_Akce(rs.getInt(1));
                pom.setNazev(rs.getString(2));
                pom.setDruh(rs.getString(3));
                
                java.util.Date input = rs.getDate(4); // pomocná proměná input pro datum
                LocalDate date = Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(); //konverze datového typu na javovský LocalDate
                pom.setDatum_od(date); // nahrání datumu do akce
                
                pom.setCas_od(rs.getString(5));
                
                input = rs.getDate(6);
                date = Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                pom.setDatum_do(date);
                
                pom.setCas_do(rs.getString(7));
                pom.setMisto(rs.getString(8));
                pom.setPopis(rs.getString(9));
                pom.setKapacita(rs.getInt(10));
                pom.setCena(rs.getInt(11));
                list.add(pom); // Přidaj akci do seznamu akcí
            }
            return list; // Vrať seznam akcí
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     //Metoda pro získání vyfiltrovaných akcí
     public ObservableList<Akce> filtrovaneAkce(String filtr)
    {
        String query = "";
        if(filtr.equals("Všechny"))
        query = "SELECT * FROM `akce`";
        if(filtr.equals("Budoucí"))
        query = "SELECT * FROM `akce` WHERE `datum_od` > NOW() AND `datum_do` > NOW()";
        if(filtr.equals("Minulé"))
        query = "SELECT * FROM `akce` WHERE `datum_do` < NOW() ";
        if(filtr.equals("Probíhající"))
        query = "SELECT * FROM `akce` WHERE `datum_od` < NOW() AND `datum_do` > NOW()";
        if(filtr.equals("Plné"))
        query = "SELECT * FROM `akce` WHERE `kapacita` <= (SELECT COUNT(*) FROM `ucast` JOIN akce USING (ID_Akce))";
        if(filtr.equals("Volné"))
        query = "SELECT * FROM `akce` WHERE `kapacita` > (SELECT COUNT(*) FROM `ucast` JOIN akce USING (ID_Akce))";
        
        try {
            rs = st.executeQuery(query); // Provedení dotazu
            ObservableList<Akce>list = FXCollections.observableArrayList(); // Vytvoření vraceného listu
            while(rs.next()) // Pro každý záznam
            {
                Akce pom = new Akce(); // Vytvoř novou akci a postupně naplň daty
                pom.setID_Akce(rs.getInt(1));
                pom.setNazev(rs.getString(2));
                pom.setDruh(rs.getString(3));
                
                java.util.Date input = rs.getDate(4); // pomocná proměná input pro datum
                LocalDate date = Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(); //konverze datového typu na javovský LocalDate
                pom.setDatum_od(date); // nahrání datumu do akce
                
                pom.setCas_od(rs.getString(5));
                
                input = rs.getDate(6);
                date = Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                pom.setDatum_do(date);
                
                pom.setCas_do(rs.getString(7));
                pom.setMisto(rs.getString(8));
                pom.setPopis(rs.getString(9));
                pom.setKapacita(rs.getInt(10));
                pom.setCena(rs.getInt(11));
                list.add(pom); // Přidaj akci do seznamu akcí
            }
            return list; // Vrať seznam akcí
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     
     
     // Metoda pro nahrání všech účastí na dané akci
    public ObservableList<Ucast> listUcasti(int id)
    {
        try {
            String query = "SELECT * FROM `ucast` WHERE ID_Akce = \"" + id + "\"";
            rs = st.executeQuery(query);
            ObservableList<Ucast>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Ucast pom = new Ucast();
                pom.setID_Student(rs.getInt(1));
                pom.setID_Akce(rs.getInt(2));
                pom.setPlaceno(rs.getString(3));
                list.add(pom);
            }
            return list;
            } 
         catch(Exception ex) 
            {
            System.out.println(ex);
            }
         return null;
    }
     //Metoda, která počítá obsazenou kapacitu u dané akce
    public int getObsazenaKapacita(int id)
    {
        int vysledek = 0;
        try 
        {
            String query = "SELECT COUNT(*) FROM `ucast` WHERE ID_Akce = \"" + id + "\"";
            rs = st.executeQuery(query);
            while(rs.next())
            {
                vysledek = rs.getInt(1);
            }
            vysledek = rs.getInt(1);
        } 
        catch(Exception ex) 
        {
            System.out.println(ex);
        }
         return vysledek;
    }
     // Metoda pro přidání akce
    public void PridejAkci(Akce akce)
    {
        try {
            String query = "Insert into `akce` SET `nazev`= \"" + akce.getNazev() 
                    + "\",`druh`= \"" + akce.getDruh() 
                    + "\",`datum_od`= \"" + akce.getDatum_od() 
                    + "\",`cas_od`= \"" + akce.getCas_od() 
                    + "\",`datum_do`= \"" + akce.getDatum_do() 
                    + "\",`cas_do`= \"" + akce.getCas_do() 
                    + "\",`misto`= \"" + akce.getMisto() 
                    + "\",`popis`= \"" + akce.getPopis() 
                    + "\",`kapacita`= \"" + akce.getKapacita() 
                    + "\",`cena`= \"" + akce.getCena() + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    } 
    // Metoda pro přidání studenta VŠE
    public void PridejStudentaVSE(StudentVSE student)
    {
        try {
            String query = "Insert into `studentvse` SET `xname`= \"" + student.getXname() 
                    + "\",`titul`= \"" + student.getTitul() 
                    + "\",`jmeno`= \"" + student.getJmeno()
                    + "\",`narozeni`= \"" + student.getNarozeni()
                    + "\",`pohlavi`= \"" + student.getPohlavi() 
                    + "\",`prislusnost`= \"" + student.getPrislusnost() 
                    + "\",`adresa`= \"" + student.getAdresa() 
                    + "\",`telefon`= \"" + student.getTelefon() + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    // Metoda pro přidání zahraničního studenta
    public void PridejZahranicnihoStudenta(ZahranicniStudent student)
    {
        try {
            String query = "Insert into `zahranicnistudent` SET `xname`= \"" + student.getXname()  
                    + "\",`jmeno`= \"" + student.getJmeno()
                    + "\",`narozeni`= \"" + student.getNarozeni()
                    + "\",`pohlavi`= \"" + student.getPohlavi() 
                    + "\",`prislusnost`= \"" + student.getPrislusnost() 
                    + "\",`adresa`= \"" + student.getAdresa() 
                    + "\",`telefon`= \"" + student.getTelefon()
                    + "\",`email`= \"" + student.getEmail() + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }   
    }
    // Metoda pro přidání účasti
    public void PridejUcast(Ucast ucast)
    {
        try {
            String query = "Insert into `ucast` SET `ID_Student`= \"" + ucast.getID_Student()
                    + "\",`ID_Akce`= \"" + ucast.getID_Akce()
                    + "\",`placeno`= \"" + ucast.getPlaceno() + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }   
    }
    // Metoda pro smazání akce
    public void SmazAkci(int id)
    {
        try {
            String query = "DELETE from `akce` WHERE ID_Akce = \"" + id + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    // Metoda pro smazání studenta VŠE
    public void SmazStudentaVSE(int id)
    {
        try {
            String query = "DELETE from `studentvse` WHERE ID_Student = \"" + id + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    // Metoda pro smazání zahraničního studenta
    public void SmazZahranicnihoStudenta(int id)
    {
        try {
            String query = "DELETE from `zahranicnistudent` WHERE ID_Student = \"" + id + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    // Metoda pro smazání účasti
    public void SmazUcast(int ID_Student,int ID_Akce)
    {
        try {
            String query = "DELETE from `ucast` WHERE ID_Student = \"" + ID_Student + "\" AND ID_Akce = \"" + ID_Akce + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    // Metoda pro úpravu akce
    public void UpravAkci(Akce akce)
    {
        try {
            String query = "UPDATE `akce` SET `nazev`= \"" + akce.getNazev() 
                    + "\",`druh`= \"" + akce.getDruh() 
                    + "\",`datum_od`= \"" + akce.getDatum_od() 
                    + "\",`cas_od`= \"" + akce.getCas_od() 
                    + "\",`datum_do`= \"" + akce.getDatum_do() 
                    + "\",`cas_do`= \"" + akce.getCas_do() 
                    + "\",`misto`= \"" + akce.getMisto() 
                    + "\",`popis`= \"" + akce.getPopis() 
                    + "\",`kapacita`= \"" + akce.getKapacita() 
                    + "\",`cena`= \"" + akce.getCena()
                    + "\" WHERE ID_Akce = \"" + akce.getID_Akce() + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }  
    }
    // Metoda pro úpravu studenta VŠE
    public void UpravStudentaVSE(StudentVSE student)
    {
        try {
            String query = "UPDATE `studentvse` SET `titul`= \"" + student.getTitul() 
                    + "\",`xname`= \"" + student.getXname()
                    + "\",`jmeno`= \"" + student.getJmeno()
                    + "\",`narozeni`= \"" + student.getNarozeni()
                    + "\",`pohlavi`= \"" + student.getPohlavi() 
                    + "\",`prislusnost`= \"" + student.getPrislusnost() 
                    + "\",`adresa`= \"" + student.getAdresa() 
                    + "\",`telefon`= \"" + student.getTelefon()
                    + "\" WHERE ID_Student = \"" + student.getID() + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) 
        {
            Error(ex.toString());
        }

    }
    // Metoda pro úpravu zahraničního studenta
    public void UpravZahranicnihoStudenta(ZahranicniStudent student)
    {
        try {
            String query = "UPDATE `zahranicnistudent` SET `xname`= \"" + student.getXname()  
                    + "\",`jmeno`= \"" + student.getJmeno()
                    + "\",`narozeni`= \"" + student.getNarozeni() 
                    + "\",`pohlavi`= \"" + student.getPohlavi() 
                    + "\",`prislusnost`= \"" + student.getPrislusnost() 
                    + "\",`adresa`= \"" + student.getAdresa() 
                    + "\",`telefon`= \"" + student.getTelefon()
                    + "\",`email`= \"" + student.getEmail()
                    + "\" WHERE ID_Student = \"" + student.getID_Student() + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }   
    }
    // metoda pro úpravu účasti
    public void UpravUcast(Ucast ucast)
    {
        try {
            String query = "UPDATE `ucast` SET `placeno`= \"" + ucast.getPlaceno()
                    + "\" WHERE ID_Student = \"" + ucast.getID_Student() + "\" AND ID_Akce = \"" + ucast.getID_Akce() + "\"";
            st.executeUpdate(query);
            Uspech();
        } catch (Exception ex) {
            Error(ex.toString());
        }   
    }
    
    
    
    
    // Metoda pro výpis errorové hlášky
    public void Error(String error)
     {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba!");
        alert.setHeaderText("Jedno nebo více políček je špatně");
        alert.setContentText(error);
        alert.showAndWait();
     }
    // Metoda pro výpis úspěchové hlášky
     public void Uspech()
     {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oznámení");
        alert.setHeaderText("Úspěch");
        alert.setContentText("Operace proběhla úspěšně");
        alert.showAndWait();
     }

    
}
