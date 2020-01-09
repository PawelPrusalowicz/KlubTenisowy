package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Worker {

    private int Id_Pracownika, Id_Klubu, Id_Placowki, Id_Stanowiska;
    private String Imie, Nazwisko, Plec, PESEL, Miejscowosc, Ulica, Nr_Budynku, Nr_Lokalu, Kod_Pocztowy;
    private Date Data_Zatrudnienia;


    Worker (int IdPracownika){
        this.Id_Pracownika = IdPracownika;
    }



    public ObservableList<Worker> getRestricted (Connection connenction, String namePattern){

        //Stat

        // zapytanie

//        while (rs.next()){
//
//        }

        return null;
    }

    public static ObservableList<Worker> getAll (Connection connection)  {

        ObservableList<Worker> workers = FXCollections.observableArrayList();


        Statement statement = null;
        ResultSet rs = null;

        String sql = "SELECT * from Pracownicy";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);


            while (rs.next()){

                Worker worker = new Worker(rs.getInt("Id_Pracownika"));

                worker.setImie(rs.getString("Imie"));
                worker.setNazwisko(rs.getString("Nazwisko"));
                worker.setPlec(rs.getString("Plec"));
                worker.setPESEL(rs.getString("PESEL"));
                worker.setMiejscowosc(rs.getString("Miejscowosc"));
                worker.setUlica(rs.getString("Ulica"));
                worker.setNr_Budynku(rs.getString("Nr_Budynku"));
                worker.setNr_Lokalu(rs.getString("Nr_Lokalu"));
                worker.setKod_Pocztowy(rs.getString("Kod_Pocztowy"));
                worker.setData_Zatrudnienia(rs.getDate("Data_Zatrudnienia"));
                worker.setId_Klubu(rs.getInt("Id_Klubu"));
                worker.setId_Placowki(rs.getInt("Id_Placowki"));
                worker.setId_Stanowiska(rs.getInt("Id_Stanowiska"));


                workers.add(worker);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workers;
    }

    public int getId_Pracownika() {
        return Id_Pracownika;
    }

    public void setId_Pracownika(int id_Pracownika) {
        Id_Pracownika = id_Pracownika;
    }

    public int getId_Klubu() {
        return Id_Klubu;
    }

    public void setId_Klubu(int id_Klubu) {
        Id_Klubu = id_Klubu;
    }

    public int getId_Placowki() {
        return Id_Placowki;
    }

    public void setId_Placowki(int id_Placowki) {
        Id_Placowki = id_Placowki;
    }

    public int getId_Stanowiska() {
        return Id_Stanowiska;
    }

    public void setId_Stanowiska(int id_Stanowiska) {
        Id_Stanowiska = id_Stanowiska;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }

    public String getPlec() {
        return Plec;
    }

    public void setPlec(String plec) {
        Plec = plec;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getMiejscowosc() {
        return Miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        Miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return Ulica;
    }

    public void setUlica(String ulica) {
        Ulica = ulica;
    }

    public String getNr_Budynku() {
        return Nr_Budynku;
    }

    public void setNr_Budynku(String nr_Budynku) {
        Nr_Budynku = nr_Budynku;
    }

    public String getNr_Lokalu() {
        return Nr_Lokalu;
    }

    public void setNr_Lokalu(String nr_Lokalu) {
        Nr_Lokalu = nr_Lokalu;
    }

    public String getKod_Pocztowy() {
        return Kod_Pocztowy;
    }

    public void setKod_Pocztowy(String kod_Pocztowy) {
        Kod_Pocztowy = kod_Pocztowy;
    }

    public Date getData_Zatrudnienia() {
        return Data_Zatrudnienia;
    }

    public void setData_Zatrudnienia(Date data_Zatrudnienia) {
        Data_Zatrudnienia = data_Zatrudnienia;
    }
}
