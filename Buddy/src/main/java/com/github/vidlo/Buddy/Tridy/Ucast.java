/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buddysystem.Tridy;


public class Ucast 
{
    private int ID_Student;
    private int ID_Akce;
    private String placeno;
    private String jmeno;

    public Ucast() {
    }

    public Ucast(int ID_Student, int ID_Akce, String placeno, String jmeno) {
        this.ID_Student = ID_Student;
        this.ID_Akce = ID_Akce;
        this.placeno = placeno;
        this.jmeno = jmeno;
    }

    public int getID_Student() {
        return ID_Student;
    }

    public void setID_Student(int ID_Student) {
        this.ID_Student = ID_Student;
    }

    public int getID_Akce() {
        return ID_Akce;
    }

    public void setID_Akce(int ID_Akce) {
        this.ID_Akce = ID_Akce;
    }

    public String getPlaceno() {
        return placeno;
    }

    public void setPlaceno(String placeno) {
        this.placeno = placeno;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    
    
    
}
