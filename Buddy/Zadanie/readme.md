# **"Krýmska pětka"** - Semestrálna práca

## *Seznam členů týmu*


Člen tímu | GitHub
--- | ---
Alexandra Melnykova | @github/Melo00
Pavel Pivovarčík | @github/Pavel4444
Tomáš Procash | @github/Trinnitti
Aleš Sedlák | @github/Hryzalka
Ondrej Vitko | @github/vidlo

## *Zadání úlohy*
#### Akce pro zahraniční studenty (3-5 řešitelů)

Aplikace slouží pro podporu činnosti Buddy systému na VŠE.
Aplikace je desktopová. Aplikace umožňuje evidovat studenty VŠE,
kteří jsou přihlášeni v Buddy systému – xname,jméno, příjmení,titul,
datum narození, pohlaví, státní příslušnost, adresa v ČR, telefon,email.
Aplikace umožňuje evidovat zahraniční studenty – jméno, příjmení,
datum narození, pohlaví, stát, adresa v ČR, telefon, email, fotka,
přidělený Buddy student. Pracovníci Buddy systému evidují akce,
které se pro zahraniční studenty pořádají – identifikace akce,
druh (předem dané možnosti - druhy se mohou přidávat), název,
datum od, čas od, datum do, čas do, místo, popis, cena,
maximální počet účastníků. Aplikace umožňuje filtrovat seznam
akcí podle různých kritérií - druh, termín, volná kapacita atd.
Zahraniční studenti si mohou prohlížet přehledy akcí i detaily
jednotlivých akcí. Pokud mají o akci zájem, sdělí to pracovníkům
Buddy systému a ti je na akci přihlásí. Pracovníci Buddy systému
si mohou vypisovat seznamy účastníků pro jednotlivé akce, zadávat,
zda student akci zaplatil.

## *Seznam úkolů a jejich přiřazení členům týmu*

Člen tímu | Úkoly | Triedy
:---: | :---: | :---:
Alexandra Melnykova | Use Case Diagram; Class Diagram; Základné triedy | Akce.java; StudentVSE.java; Ucast.java; ZahranicniStudent.java;
Pavel Pivovarčík | Use Case Diagram; Class Diagram; Kontroler | BuddySystem.java; PopUpKontroler.java;
Tomáš Procash | Use Case Diagram; Class Diagram; GUI; | HlavniOkno.fxml; PopUp.fxml;
Aleš Sedlák | Use Case Diagram; Class Diagram; Databáza; | Database.java;
Ondrej Vitko | Use Case Diagram; Class Diagram; Scenáre; Návrh GUI; Úprava Markdownu; | KontrolerHlavnihoOkna.java;

## *Use Case Diagram*
![usecase](https://raw.githubusercontent.com/vidlo/Buddy/dev_vidlo/Buddy/Zadanie/Obr%C3%A1zky/usecase.PNG)

## *Popis Use Case*
*[Popis](Scenáre.md)*

## *Class Diagram*
![classdiagram](https://raw.githubusercontent.com/vidlo/Buddy/dev_vidlo/Buddy/Zadanie/Obr%C3%A1zky/classdiagram.PNG)

## *Struktúra databáze*
Databáza je relizovaná pomocou MySQL. Ide o lokálnu databázu vytvorenú aplikáciou XAMPP.

## *Návrh uživatelského rozhraní*
*[Návrh GUI](gui.md)*

## *Konvence*
Náš tím sa bude držat konvenie uvedenej na [stránke predmetu](https://java.vse.cz/4it101/Konvence)

___

***Autor si vyhradzuje právo meniť obsah. Konečný výsledok práce 
nemusí plne odpovedať uvedeným faktom a môže sa teda zmeniť v priebehu vývoja.**