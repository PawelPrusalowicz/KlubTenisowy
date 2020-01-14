package app;

import app.login.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CourtReservation {

    private int Id_Realizacji_Uslugi, Id_Kortu, Id_Rezerwacji_Kortu;
    private String Nazwa_Uslugi;
    private Timestamp Termin_Od, Termin_Do;


    CourtReservation (Integer Id_Rezerwacji_Kortu){
        this.Id_Rezerwacji_Kortu = Id_Rezerwacji_Kortu;
    }


    public static ObservableList<CourtReservation> getCourtReservations()  {

        ObservableList<CourtReservation> courtReservations = FXCollections.observableArrayList();

        Connection connection = DBConnection.getConnection();

        if(connection == null){
            return courtReservations;
        }

        Statement statement = null;
        ResultSet rs = null;

        String sql = "Select * from Rezerwacje_Kortow rk " +
                "left join realizacje_uslug ru on rk.id_realizacji_uslugi = ru.id_realizacji_uslugi " +
                "left join uslugi u on ru.id_uslugi = u.id_uslugi";

//        if (searchParameter != null){
//            sql += " where Imie like '" + searchParameter +"%' or nazwisko like '" + searchParameter +"%'";
//        }

        sql += " order by id_rezerwacji_kortu asc";


        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()){


                CourtReservation courtReservation = new CourtReservation(rs.getInt("Id_Rezerwacji_Kortu"));
                courtReservation.setId_Realizacji_Uslugi(rs.getInt("Id_Realizacji_Uslugi"));
                courtReservation.setId_Kortu(rs.getInt("Id_Kortu"));
                courtReservation.setTermin_Od(rs.getTimestamp("Termin_Od"));
                courtReservation.setTermin_Od(rs.getTimestamp("Termin_Do"));
                courtReservation.setNazwa_Uslugi(rs.getString("Nazwa_Uslugi"));

                courtReservations.add(courtReservation);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        return courtReservations;
    }

    public int getId_Realizacji_Uslugi() {
        return Id_Realizacji_Uslugi;
    }

    public void setId_Realizacji_Uslugi(int id_Realizacji_Uslugi) {
        Id_Realizacji_Uslugi = id_Realizacji_Uslugi;
    }

    public int getId_Kortu() {
        return Id_Kortu;
    }

    public void setId_Kortu(int id_Kortu) {
        Id_Kortu = id_Kortu;
    }

    public int getId_Rezerwacji_Kortu() {
        return Id_Rezerwacji_Kortu;
    }

    public void setId_Rezerwacji_Kortu(int id_Rezerwacji_Kortu) {
        Id_Rezerwacji_Kortu = id_Rezerwacji_Kortu;
    }

    public Timestamp getTermin_Od() {
        return Termin_Od;
    }

    public void setTermin_Od(Timestamp termin_Od) {
        Termin_Od = termin_Od;
    }

    public Timestamp getTermin_Do() {
        return Termin_Do;
    }

    public void setTermin_Do(Timestamp termin_Do) {
        Termin_Do = termin_Do;
    }

    public String getNazwa_Uslugi() {
        return Nazwa_Uslugi;
    }

    public void setNazwa_Uslugi(String nazwa_Uslugi) {
        Nazwa_Uslugi = nazwa_Uslugi;
    }
}
