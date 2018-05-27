/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buddy.main;

import Buddy.Databaze.Database;
import Buddy.Tridy.Akce;
import Buddy.Tridy.StudentVSE;
import Buddy.Tridy.Ucast;
import Buddy.Tridy.ZahranicniStudent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class KontrolerHlavnihoOkna implements Initializable 
{
    /** Deklarace všech používaných položek grafiky, které se v
     *  kontrolleru nějakým způsobem upravují nebo se s nimi pracuje
     *  */
    @FXML
    private Label uNazev; // Název akce
    @FXML
    private AnchorPane pMenu,pVSE,pZahranicni,pAkce,pPracovniAkce,pUcast; // Jednotlivé panely
    @FXML
    private TextField tfNazev,tfMisto,tfDruh,tfCasOd,tfCasDo,tfKapacita,tfCena; // textfieldy
    @FXML
    private TextArea tfPopis,areaPopis;
    @FXML
    private DatePicker tfDatumOd,tfDatumDo; // datepickry pro datum
    @FXML
    private TableView<StudentVSE> tVSE; // tabulka
    @FXML
    private TableColumn<StudentVSE,String> vXname,vJmeno,vTitul,vPohlavi,vPrislusnost,vAdresa,vTelefon; // prvky v tabulce
    @FXML
    private TableColumn<StudentVSE,Date> vNarozeni;
    @FXML
    private TableView<ZahranicniStudent> tZahranicni;
    @FXML
    private TableColumn<ZahranicniStudent,String> zBuddy,zJmeno,zEmail,zPohlavi,zPrislusnost,zAdresa,zTelefon;
    @FXML
    private TableColumn<ZahranicniStudent,Date> zNarozeni;
    @FXML
    private TableColumn<ZahranicniStudent,Integer> zID;
    @FXML
    private TableView<Akce> tAkce;
    @FXML
    private TableColumn<Akce,String> aNazev,aDruh,aCod,aCdo,aMisto;
    @FXML
    private TableColumn<Akce,LocalDate> aDod,aDdo;
    @FXML
    private TableColumn<Akce,Integer> aID,aKapacita,aPrihlaseno,aCena;
    @FXML
    private TableView<ZahranicniStudent> tuZahranicni;
    @FXML
    private TableColumn<ZahranicniStudent,String> uzJmeno;
    @FXML
    private TableColumn<ZahranicniStudent,Integer> uzID;
    @FXML
    private TableView<Ucast> tUcast;
    @FXML
    private TableColumn<Ucast,String> uJmeno,uZaplaceno;
    @FXML
    private TableColumn<Ucast,Integer> uID;
    @FXML
    private ComboBox comboFiltruj;
    
    /** Jednotlivé seznamy */
    private ObservableList<StudentVSE> seznamStudentuVSE = FXCollections.observableArrayList();
    private ObservableList<ZahranicniStudent> seznamZahranicnichStudentu = FXCollections.observableArrayList();
    private ObservableList<Akce> seznamAkci = FXCollections.observableArrayList();
    private ObservableList<Ucast> seznamUcasti = FXCollections.observableArrayList();
    private ObservableList<String> seznamFiltru = FXCollections.observableArrayList();
    
    private Database databaze = new Database(); // Vytvoří instanci databáze
    
    private boolean pridani = true; // Pomocná proměná která značí zda se přidává nebo upravuje - přidání = true, úprava - false
    Akce upravovana = null; // Pomocná proměná pro úpravu akce
    
    private PopUpKontroler popOkno; // pomocná proměná pro uložení kontroleru dialogového okna
    
    
    /**Přechod do menu*/
    @FXML
    private void Menu() 
    {
        pMenu.setVisible(true);
        pVSE.setVisible(false);
        pZahranicni.setVisible(false);
        pAkce.setVisible(false);
        pPracovniAkce.setVisible(false);
        pUcast.setVisible(false);
    }
    /**Přechod do panelu studentů VŠE*/
    @FXML
    private void VSE() 
    {
        pMenu.setVisible(false); // Zavření panelu Menu
        pVSE.setVisible(true); // Otevření panelu studentů VŠE
        
        seznamStudentuVSE = databaze.listStudentuVSE(); // Načtení seznamu všech studentů
        tVSE.setItems(seznamStudentuVSE); // Nahrání seznamu do tabulky
    }
    /**Metoda pro přidání studenta VŠE*/
    @FXML
    private void PridejStudentaVSE()
    {
        StudentVSE novy = new StudentVSE(); // Vytvoření nového studenta
        databaze.PridejStudentaVSE(novy); // Přidání nového studenta do databáze
        VSE(); // Aktualizace tabulky
    }
    /**Metoda pro smazání studenta VŠE*/
    @FXML
    private void SmazStudentaVSE() 
    {
        StudentVSE mazany = tVSE.getSelectionModel().getSelectedItem(); // Vybere označeného studenta z tabulky
        
        if(mazany != null) // Pokuď uživatel někoho vybral
        {
            databaze.SmazStudentaVSE(mazany.getID()); // Smazání studenta z databáze
            VSE(); // Aktualizace tabulky
        }
        else // Pokuď uživatel nikoho nevybral
        {
            databaze.Error("Pro smazání studenta, je nutné ho nejprve označit v tabulce"); // Výpis erroru
        }
    }
    /** Metoda pro úpravu studenta VŠE*/
    @FXML
    private void UpravaStudentaVSE() 
    {
        StudentVSE upraveny = tVSE.getSelectionModel().getSelectedItem(); // Vybere označeného studenta z tabulky
        
        if(upraveny != null) // Pokuď uživatel někoho vybral
        {
            databaze.UpravStudentaVSE(upraveny); // Uložení změn u studenta do databáze
            VSE(); // Aktualizace tabulky
        }
        else // Pokuď uživatel nikoho nevybral
        {
            databaze.Error("Pro uložení změn u nějakého studenta, je nutné ho nejprve označit v tabulce."); // Výpis erroru
        }
    }
    /** Přechod do panelu zahraničních studentů*/
    @FXML
    private void Zahranicni() 
    {
        pMenu.setVisible(false);
        pZahranicni.setVisible(true);
        
        seznamZahranicnichStudentu = databaze.listZahranicnichStudentu();
        tZahranicni.setItems(seznamZahranicnichStudentu);
    }
    /** Výběr Buddyho pro nového zahraničního studenta*/
    @FXML
    private void VyberStudenta() throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUp.fxml")); // Otevření dialogového okna
        
        Parent root = loader.load();
        popOkno = loader.getController();
        popOkno.kontroler = this;
        popOkno.tVSE.setItems(databaze.listStudentuVSE());

        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        popOkno.stage = stage;
        stage.setScene(scene);
        stage.show();
    }
    /** Přidání nového zahraničního studenta*/
    @FXML
    public void PridejZahranicnihoStudenta(StudentVSE student) 
    {
        ZahranicniStudent novy = new ZahranicniStudent(student.getXname());
        databaze.PridejZahranicnihoStudenta(novy);
        Zahranicni();
    }
    /** Smazání zahraničního studenta*/
    @FXML
    private void SmazZahranicnihoStudenta() 
    {
        ZahranicniStudent mazany = tZahranicni.getSelectionModel().getSelectedItem();
        
        if(mazany != null)
        {
            databaze.SmazZahranicnihoStudenta(mazany.getID_Student());
            Zahranicni();
        }
        else
        {
            databaze.Error("Pro smazání studenta, je nutné ho nejprve označit v tabulce");
        }
    }
    /** Úprava zahraničního studenta*/
    @FXML
    private void UpravaZahranicnihoStudenta() 
    {
        ZahranicniStudent upraveny = tZahranicni.getSelectionModel().getSelectedItem();
        
        if(upraveny != null)
        {
            databaze.UpravZahranicnihoStudenta(upraveny);
            Zahranicni();
        }
        else
        {
            databaze.Error("Pro upravení studenta, je nutné ho nejprve označit v tabulce");
        }
    }
    /** Přechod do panelu akcí*/
    @FXML
    private void Akce() 
    {
        pMenu.setVisible(false);
        pAkce.setVisible(true);
        
        seznamAkci = databaze.listAkci();
        for(Akce akce : seznamAkci)
        {
            akce.setObsazenaKapacita(databaze.getObsazenaKapacita(akce.getID_Akce()));
        }
          
        tAkce.setItems(seznamAkci);
    }
    /** Přidání nové akce*/
    @FXML
    private void PridaniAkce() 
    {
        pAkce.setVisible(false);
        pPracovniAkce.setVisible(true);
        pridani = true;
    }
    /** Metoda která buďto přidá novou akci, nebo upraví stávající*/
    @FXML
    private void PotvrdAkci() 
    {
        try
        {
            Akce nova = new Akce();
            nova.setNazev(tfNazev.getText());
            nova.setDruh(tfDruh.getText());
            nova.setDatum_od(tfDatumOd.getValue());
            nova.setCas_od(tfCasOd.getText());
            nova.setDatum_do(tfDatumDo.getValue());
            nova.setCas_do(tfCasDo.getText());
            nova.setMisto(tfMisto.getText());
            nova.setPopis(tfPopis.getText());
            nova.setKapacita(Integer.parseInt(tfKapacita.getText()));
            nova.setCena(Integer.parseInt(tfCena.getText()));

            if(nova.getDatum_od().isBefore(nova.getDatum_do()))
            {
                if(pridani)
                {
                    databaze.PridejAkci(nova);
                    pPracovniAkce.setVisible(false);
                    Clear();
                    Akce();
                }
                else
                {
                    nova.setID_Akce(upravovana.getID_Akce());
                    databaze.UpravAkci(nova);
                    pPracovniAkce.setVisible(false);
                    Clear();
                    Akce();
                }
            }
            else
            {
               databaze.Error("Datum od musí být menší jak datum do");
            }
        }
        catch(Exception ex)
        {
            databaze.Error("Všechny políčka musí být správně vyplněné.");
        }
    }
    /** Metoda pro smazání akce*/
    @FXML
    private void SmazaniAkce() 
    {
        Akce mazana = tAkce.getSelectionModel().getSelectedItem();
        
        if(mazana != null)
        {
            databaze.SmazAkci(mazana.getID_Akce());
            Akce();
        }
        else
        {
            databaze.Error("Pro smazání akce, je nutné ji nejprve označit v tabulce");
        }
    }
    /** Metoda pro úpravu akce*/
    @FXML
    private void UpravaAkce() 
    {
        
        upravovana = tAkce.getSelectionModel().getSelectedItem();
        pridani = false;
        
        if(upravovana != null)
        {
            pAkce.setVisible(false);
            pPracovniAkce.setVisible(true);
            
            tfNazev.setText(upravovana.getNazev());
            tfMisto.setText(upravovana.getMisto());
            tfDruh.setText(upravovana.getDruh());
            tfCasOd.setText(upravovana.getCas_od());
            tfCasDo.setText(upravovana.getCas_do());
            tfKapacita.setText(upravovana.getKapacita()+"");
            tfCena.setText(upravovana.getCena()+"");
            tfPopis.setText(upravovana.getPopis());
            tfDatumOd.setValue(upravovana.getDatum_od());
            tfDatumDo.setValue(upravovana.getDatum_do());
        }
        else
        {
            databaze.Error("Pro úpravu akce je nutné označit v tabulce tu kterou chcete upravovat.");
        }
    }
    
    /** Metoda filtruje akce podle vybrané hodnoty*/
    @FXML
    private void Filtruj()
    {
        String filtr = comboFiltruj.getSelectionModel().getSelectedItem().toString();
        seznamAkci = databaze.filtrovaneAkce(filtr);
        tAkce.setItems(seznamAkci);
    }
    
    
    /** Metoda která čistí všechny textboxy a date pickry*/
    @FXML
    private void Clear() 
    {
            tfNazev.clear();
            tfMisto.clear();
            tfDruh.clear();
            tfCasOd.clear();
            tfCasDo.clear();
            tfKapacita.clear();
            tfCena.clear();
            tfPopis.clear();
            tfDatumOd.setValue(null);
            tfDatumDo.setValue(null);
    }
    /** Metoda pro přechod na detail akce*/
    @FXML
    private void Detail() 
    {
        upravovana = tAkce.getSelectionModel().getSelectedItem(); // Vybere vybranou akci z tabulky
        
        if(upravovana != null) // Pokuď uživatel vybral akci
        {
            seznamUcasti = databaze.listUcasti(upravovana.getID_Akce()); // načte se znam účastí k dané akci
            seznamZahranicnichStudentu = databaze.listZahranicnichStudentu(); // načte se seznam všech zahraničních studentů
            
            for(Ucast ucast : seznamUcasti) // Pro každou účast
            {
                for(ZahranicniStudent student : seznamZahranicnichStudentu) // Pro každého studenta
                {
                    if(ucast.getID_Student() == student.getID_Student())
                    {
                        ucast.setJmeno(student.getJmeno()); // Se přiřadí jméno studenta k účasti.
                        break;
                    }
                }
            }
            areaPopis.setText(upravovana.getPopis());
            tUcast.setItems(seznamUcasti); // nahraní seznamu do tabulky
            tuZahranicni.setItems(seznamZahranicnichStudentu); // nahraní seznamu do tabulky
            uNazev.setText(upravovana.getNazev());
            
            pAkce.setVisible(false); // Zavření panelu akce
            pUcast.setVisible(true); // otevření panelu detailu / účasti
        }
        else
        {
            databaze.Error("Pro zobrazení detailu akce je nutné nejprve označit danou akci v tabulce."); // výpis erroru v případě že uživatel žádnou akci nevybral
        }
    }
    /** Metoda pro přidání nové účasti*/
    @FXML
    private void PridejUcast() 
    {
        ZahranicniStudent pomocny = tuZahranicni.getSelectionModel().getSelectedItem();
        
        if(pomocny != null)
        {
            if(upravovana.getObsazenaKapacita() < upravovana.getKapacita())
            {
            Ucast nova = new Ucast();
            nova.setID_Akce(upravovana.getID_Akce());
            nova.setID_Student(pomocny.getID_Student());
            nova.setPlaceno("ne");
            databaze.PridejUcast(nova);
            upravovana.setObsazenaKapacita(upravovana.getObsazenaKapacita()+1);
            seznamUcasti.add(nova);
            Detail();
            }
            else
            {
            databaze.Error("Akce už je plná");
            }
        }
        else
        {
            databaze.Error("Pro přidání účasti je nutné označit studenta.");
        }
    }
    /** Metoda pro smazání účasti*/
    @FXML
    private void SmazUcast() 
    {
        Ucast mazana = tUcast.getSelectionModel().getSelectedItem();
        
        if(mazana != null)
        {
            databaze.SmazUcast(mazana.getID_Student(), mazana.getID_Akce());
            upravovana.setObsazenaKapacita(upravovana.getObsazenaKapacita()-1);
            Detail();
        }
        else
        {
            databaze.Error("Pro smazání účasti je nutné ji nejprve označit.");
        }    
    }
    /** Mtoda pro zaplacení účasti - překlopí stav zaplaceno z ano do ne, nebo z ne do ano.*/
    @FXML
    private void ZaplatUcast() 
    {
        Ucast ucast = tUcast.getSelectionModel().getSelectedItem();
        
        if(ucast != null)
        {
            if(ucast.getPlaceno().equals("ano"))
            {
                ucast.setPlaceno("ne");
            }
            else
            {
                ucast.setPlaceno("ano");
            }
            
            
            databaze.UpravUcast(ucast);
            Detail();
        }
        else
        {
            databaze.Error("Pro změnu stavu u platby je nutné vybrat danou účast.");
        }    
    }
    /** Metoda pro přechod zpět z panelu detailu / účastí do panelu akcí*/
    @FXML
    private void ZpetDoAkci() 
    {
         pAkce.setVisible(true);
         pUcast.setVisible(false);
         Akce();
    }
    /** Metoda pro přechod z panelu tvorby / úpravy akce zpět do panlu akcí*/
    @FXML
    private void Storno() 
    {
        pAkce.setVisible(true);
        pPracovniAkce.setVisible(false);
    }
    /** Metoda která se spustí ihned po spuštění kontroleru, která připraví všechny tabulky*/
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        /** Vytvoření filtrovacího listu a jeho zařazení do comboBoxu*/
        seznamFiltru.add("Všechny");
        seznamFiltru.add("Budoucí");
        seznamFiltru.add("Minulé");
        seznamFiltru.add("Probíhající");
        seznamFiltru.add("Plné");
        seznamFiltru.add("Volné");
        comboFiltruj.setItems(seznamFiltru);
        comboFiltruj.setValue("Všechny");
        
        /** Propojení grafiky s připravenými třídami
         * vlevo název v grafice - v pravo přesný název proměnné ze třídy
         * */
        vXname.setCellValueFactory(new PropertyValueFactory<StudentVSE,String>("xname")); 
        vJmeno.setCellValueFactory(new PropertyValueFactory<StudentVSE,String>("jmeno")); 
        vTitul.setCellValueFactory(new PropertyValueFactory<StudentVSE,String>("titul")); 
        vPohlavi.setCellValueFactory(new PropertyValueFactory<StudentVSE,String>("pohlavi")); 
        vPrislusnost.setCellValueFactory(new PropertyValueFactory<StudentVSE,String>("prislusnost")); 
        vAdresa.setCellValueFactory(new PropertyValueFactory<StudentVSE,String>("adresa")); 
        vTelefon.setCellValueFactory(new PropertyValueFactory<StudentVSE,String>("telefon")); 
        vNarozeni.setCellValueFactory(new PropertyValueFactory<StudentVSE,Date>("narozeni")); 
        
        /** Příprava klikacích polí v tabulce*/
        vXname.setCellFactory(TextFieldTableCell.forTableColumn());
        vXname.setOnEditCommit(
                (TableColumn.CellEditEvent<StudentVSE,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setXname(t.getNewValue())
                );
        vJmeno.setCellFactory(TextFieldTableCell.forTableColumn());
        vJmeno.setOnEditCommit(
                (TableColumn.CellEditEvent<StudentVSE,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setJmeno(t.getNewValue())
                );
        vTitul.setCellFactory(TextFieldTableCell.forTableColumn());
        vTitul.setOnEditCommit(
                (TableColumn.CellEditEvent<StudentVSE,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setTitul(t.getNewValue())
                );
        vPohlavi.setCellFactory(TextFieldTableCell.forTableColumn());
        vPohlavi.setOnEditCommit(
                (TableColumn.CellEditEvent<StudentVSE,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPohlavi(t.getNewValue())
                );
        vPrislusnost.setCellFactory(TextFieldTableCell.forTableColumn());
        vPrislusnost.setOnEditCommit(
                (TableColumn.CellEditEvent<StudentVSE,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPrislusnost(t.getNewValue())
                );
        vAdresa.setCellFactory(TextFieldTableCell.forTableColumn());
        vAdresa.setOnEditCommit(
                (TableColumn.CellEditEvent<StudentVSE,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setAdresa(t.getNewValue())
                );
        vTelefon.setCellFactory(TextFieldTableCell.forTableColumn());
        vTelefon.setOnEditCommit(
                (TableColumn.CellEditEvent<StudentVSE,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setTelefon(t.getNewValue())
                );
        vNarozeni.setCellFactory(TextFieldTableCell.<StudentVSE, Date>forTableColumn(new DateStringConverter()));
        vNarozeni.setOnEditCommit(
                (TableColumn.CellEditEvent<StudentVSE,Date> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNarozeni(t.getNewValue())
                );
        

        zID.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,Integer>("ID_Student")); 
        zJmeno.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,String>("jmeno"));
        zBuddy.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,String>("xname"));
        zEmail.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,String>("email"));
        zPohlavi.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,String>("pohlavi")); 
        zPrislusnost.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,String>("prislusnost")); 
        zAdresa.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,String>("adresa")); 
        zTelefon.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,String>("telefon"));  
        zNarozeni.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,Date>("narozeni")); 
        
        zID.setCellFactory(TextFieldTableCell.<ZahranicniStudent, Integer>forTableColumn(new IntegerStringConverter()));
        zID.setOnEditCommit(
                (TableColumn.CellEditEvent<ZahranicniStudent,Integer> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setID_Student(t.getNewValue())
                );
        zJmeno.setCellFactory(TextFieldTableCell.forTableColumn());
        zJmeno.setOnEditCommit(
                (TableColumn.CellEditEvent<ZahranicniStudent,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setJmeno(t.getNewValue())
                );
        zEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        zEmail.setOnEditCommit(
                (TableColumn.CellEditEvent<ZahranicniStudent,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setEmail(t.getNewValue())
                );
        zPohlavi.setCellFactory(TextFieldTableCell.forTableColumn());
        zPohlavi.setOnEditCommit(
                (TableColumn.CellEditEvent<ZahranicniStudent,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPohlavi(t.getNewValue())
                );
        zPrislusnost.setCellFactory(TextFieldTableCell.forTableColumn());
        zPrislusnost.setOnEditCommit(
                (TableColumn.CellEditEvent<ZahranicniStudent,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPrislusnost(t.getNewValue())
                );
        zAdresa.setCellFactory(TextFieldTableCell.forTableColumn());
        zAdresa.setOnEditCommit(
                (TableColumn.CellEditEvent<ZahranicniStudent,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setAdresa(t.getNewValue())
                );
        zTelefon.setCellFactory(TextFieldTableCell.forTableColumn());
        zTelefon.setOnEditCommit(
                (TableColumn.CellEditEvent<ZahranicniStudent,String> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setTelefon(t.getNewValue())
                );
        zNarozeni.setCellFactory(TextFieldTableCell.<ZahranicniStudent, Date>forTableColumn(new DateStringConverter()));
        zNarozeni.setOnEditCommit(
                (TableColumn.CellEditEvent<ZahranicniStudent,Date> t) ->
                    ( t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNarozeni(t.getNewValue())
                );
        
        aNazev.setCellValueFactory(new PropertyValueFactory<Akce,String>("nazev"));
        aDruh.setCellValueFactory(new PropertyValueFactory<Akce,String>("druh"));
        aCod.setCellValueFactory(new PropertyValueFactory<Akce,String>("cas_od"));
        aCdo.setCellValueFactory(new PropertyValueFactory<Akce,String>("cas_do"));
        aMisto.setCellValueFactory(new PropertyValueFactory<Akce,String>("misto"));
        aDod.setCellValueFactory(new PropertyValueFactory<Akce,LocalDate>("datum_od"));
        aDdo.setCellValueFactory(new PropertyValueFactory<Akce,LocalDate>("datum_do"));
        aID.setCellValueFactory(new PropertyValueFactory<Akce,Integer>("ID_Akce")); 
        aKapacita.setCellValueFactory(new PropertyValueFactory<Akce,Integer>("kapacita")); 
        aPrihlaseno.setCellValueFactory(new PropertyValueFactory<Akce,Integer>("obsazenaKapacita")); 
        aCena.setCellValueFactory(new PropertyValueFactory<Akce,Integer>("cena")); 
        
        
        uzJmeno.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,String>("jmeno"));
        uzID.setCellValueFactory(new PropertyValueFactory<ZahranicniStudent,Integer>("ID_Student"));
    
        uJmeno.setCellValueFactory(new PropertyValueFactory<Ucast,String>("jmeno"));
        uZaplaceno.setCellValueFactory(new PropertyValueFactory<Ucast,String>("placeno"));
        uID.setCellValueFactory(new PropertyValueFactory<Ucast,Integer>("ID_Student")); 
    }    
    
}
