package app;

import app.login.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Service {

    private int Id_Pracownika, Id_Uslugi, Id_Realizacji_Uslugi;
    private Timestamp Termin_Od, Termin_Do;
    private String Nazwa_Uslugi;

    Service(int Id_Realizacji_Uslugi){
        this.Id_Realizacji_Uslugi = Id_Realizacji_Uslugi;
    }

    public static ObservableList<Service> getServices(Integer clientId)  {

        ObservableList<Service> services = FXCollections.observableArrayList();

        Connection connection = DBConnection.getConnection();

        if(connection == null){
            return services;
        }

        Statement statement = null;
        ResultSet rs = null;

        String sql = "Select * from realizacje_uslug ru inner join uslugi u on ru.id_uslugi = u.id_uslugi";

//        if (clientId != null){
//            sql += " where Imie like '" + searchParameter +"%' or nazwisko like '" + searchParameter +"%'";
//        }

        sql += " order by ru.id_realizacji_uslugi asc";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()){

                Service service = new Service(rs.getInt("Id_Realizacji_Uslugi"));

                service.setId_Pracownika(rs.getInt("Id_Pracownika"));
                service.setId_Uslugi(rs.getInt("Id_Uslugi"));
                service.setTermin_Od(rs.getTimestamp("Termin_Od"));
                service.setTermin_Od(rs.getTimestamp("Termin_Do"));
                service.setNazwa_Uslugi(rs.getString("Nazwa_Uslugi"));

                services.add(service);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        return services;
    }


    public int getId_Pracownika() {
        return Id_Pracownika;
    }

    public void setId_Pracownika(int id_Pracownika) {
        Id_Pracownika = id_Pracownika;
    }

    public int getId_Uslugi() {
        return Id_Uslugi;
    }

    public void setId_Uslugi(int id_Uslugi) {
        Id_Uslugi = id_Uslugi;
    }

    public int getId_Realizacji_Uslugi() {
        return Id_Realizacji_Uslugi;
    }

    public void setId_Realizacji_Uslugi(int id_Realizacji_Uslugi) {
        this.Id_Realizacji_Uslugi = id_Realizacji_Uslugi;
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
