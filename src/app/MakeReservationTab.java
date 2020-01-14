package app;

import app.login.DBConnection;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;

import java.sql.*;

public class MakeReservationTab {

    public static void initializeReservationAgencyComboBox(JFXComboBox reservationAgencyComboBox){

        Connection connection = DBConnection.getConnection();

        if(connection == null){
            return;
        }

        Reservation reservation = Reservation.getInstance();
        User user = User.getInstance();

        Statement statement = null;
        ResultSet rs = null;

        String sql = "Select * from placowki";

        if (User.getInstance().getUserType() == User.UserType.Client && user.getClubId() != 0){
            sql += " where Id_Klubu =" + user.getClubId();
        }

        sql += " order by Id_Placowki asc";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                reservationAgencyComboBox.getItems().add(new Label(
                        rs.getString("Id_Placowki") + ", " +
                        rs.getString("ULICA") + " " +
                        rs.getString("Nr_budynku") + ", " +
                        rs.getString("Kod_Pocztowy") + " " +
                        rs.getString("MIEJSCOWOSC")

                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void initializeReservationServiceTypeComboBox(JFXComboBox reservationServiceTypeComboBox){

        reservationServiceTypeComboBox.getItems().clear();

        Connection connection = DBConnection.getConnection();

        if(connection == null){
            return;
        }

        Reservation reservation = Reservation.getInstance();

        Statement statement = null;
        ResultSet rs = null;

        String sql = "Select * from uslugi";

        if (reservation.getAgencyId() != 0){
            sql += " where Id_Klubu in(Select Id_Klubu from Placowki where id_placowki=" + reservation.getAgencyId() + ")";
        }

        sql += " order by Id_Uslugi asc";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                reservationServiceTypeComboBox.getItems().add(new Label(
                                rs.getString("Nazwa_Uslugi")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void initializeReservationTrainerComboBox(JFXComboBox reservationTrainerComboBox, Integer agencyId) {

        Connection connection = DBConnection.getConnection();

        if(connection == null){
            return;
        }

        Statement statement = null;
        ResultSet rs = null;

        Reservation reservation = Reservation.getInstance();

        String sql = "Select * from pracownicy p left join trenerzy t on p.id_Pracownika = t.id_pracownika";

        if (agencyId != null){
            sql += " where p.Id_Placowki =" + reservation.getAgencyId();
        }

        sql += " order by p.Id_Pracownika asc";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                reservationTrainerComboBox.getItems().add(new Label(
                        "Id: " + rs.getInt("Id_pracownika") + ", " +
                        rs.getString("Imie") + " " +
                        rs.getString("Nazwisko")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public static void initializeReservationCourseNameComboBox(JFXComboBox reservationCourseNameComboBox) {

        Connection connection = DBConnection.getConnection();

        if(connection == null){
            return;
        }

        Statement statement = null;
        ResultSet rs = null;

        Reservation reservation = Reservation.getInstance();
        Integer serviceId = reservation.getServiceTypeId();

        String sql = "Select * from realizacje_uslug";

        if (serviceId != null){
            sql += " where Id_realizacji_uslugi =" + serviceId;
        }

        sql += " order by Id_Pracownika asc";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                Date dateFrom = new Date(rs.getTimestamp("Termin_od").getTime());
                Date dateTo = new Date(rs.getTimestamp("Termin_do").getTime());

                reservationCourseNameComboBox.getItems().add(new Label(
                        "Id:" +
                        rs.getInt("Id_realizacji_uslugi") + ", od " +
                        dateFrom + " do " + dateTo
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
