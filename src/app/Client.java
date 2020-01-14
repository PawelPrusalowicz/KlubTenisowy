package app;

import app.login.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Set;

public class Client{

        private int Id_Klienta, Id_Klubu;
        private String Imie, Nazwisko, Plec, PESEL, Miejscowosc, Ulica, Nr_Budynku, Nr_Lokalu, Kod_Pocztowy, Email, Nr_Telefonu;

        Client (int IdKlienta){
            this.Id_Klienta = IdKlienta;
        }
        Client (String Imie){
            this.Imie = Imie;
        }


        public static void deleteClient(Connection connection, int clientId) {

            String sql = "Delete from Klienci where Id_Klienta=" + clientId;

            Statement statement = null;
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public static void saveAll (ObservableList<Client> clients, Set<Integer> clientChangedIds){

            for (Client client : clients){

                if ( clientChangedIds.contains(client.getId_Klienta())){
                    if (client.getId_Klienta() == 0){
                        insertClient(client);
                    }
                    else {
                        updateClient(client);
                    }
                }
            }

            clientChangedIds.clear();

        }

        public static void updateClient(Client client){

            String sql = "Update pracownicy set Imie='" + client.getImie()
                    + "', Nazwisko= '" + client.getNazwisko()
                    + "', Plec= '" + client.getPlec()
                    + "', PESEL= '" + client.getPESEL()
                    + "', Miejscowosc= '" + client.getMiejscowosc()
                    + "', Ulica= '" + client.getUlica()
                    + "', NR_BUDYNKU= '" + client.getNr_Budynku()
                    + "' ,NR_LOKALU= '" + client.getNr_Lokalu()
                    + "', KOD_POCZTOWY= '" + client.getKod_Pocztowy()
                    + ", ID_KLUBU= " + client.getId_Klubu()
                    + ", Email= '" + client.getEmail()
                    + "', Nr_telefonu= '" + client.getNr_Telefonu()
                    + "' where Id_pracownika=" + client.getId_Klienta();

            Connection connection = DBConnection.getConnection();

            Statement statement = null;

            try {
                System.out.println(sql);
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public static void insertClient(Client client){

            String sql = "Insert into Klienci (Imie, Nazwisko, plec, pesel, miejscowosc," +
                    "ulica, nr_budynku, nr_lokalu, kod_pocztowy, id_klubu, email, nr_telefonu) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";


            Connection connection = DBConnection.getConnection();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, client.getImie());
                preparedStatement.setString(2, client.getNazwisko());
                preparedStatement.setString(3, client.getPlec());
                preparedStatement.setString(4, client.getPESEL());
                preparedStatement.setString(5, client.getMiejscowosc());
                preparedStatement.setString(6, client.getUlica());
                preparedStatement.setString(7, client.getNr_Budynku());
                preparedStatement.setString(8, client.getNr_Lokalu());
                preparedStatement.setString(9, client.getKod_Pocztowy());
                preparedStatement.setInt(10, client.getId_Klubu());
                preparedStatement.setString(11, client.getEmail());
                preparedStatement.setString(12, client.getNr_Telefonu());



                System.out.println(generateActualSql(sql,new Object[] {
                        client.getImie(),
                        client.getNazwisko(),
                        client.getPlec(),
                        client.getPESEL(),
                        client.getMiejscowosc(),
                        client.getUlica(),
                        client.getNr_Budynku(),
                        client.getNr_Lokalu(),
                        client.getKod_Pocztowy(),
                        client.getId_Klubu(),
                        client.getEmail(),
                        client.getNr_Telefonu()
                }));

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public static ObservableList<Client> getClients(String searchParameter)  {

            ObservableList<Client> clients = FXCollections.observableArrayList();

            Connection connection = DBConnection.getConnection();

            if(connection == null){
                return clients;
            }

            Statement statement = null;
            ResultSet rs = null;

            String sql = "Select * from klienci";

            if (searchParameter != null){
                sql += " where Imie like '" + searchParameter +"%' or nazwisko like '" + searchParameter +"%'";
            }

            sql += " order by id_klienta asc";

            try {
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);

                while (rs.next()){

                    Client client = new Client(rs.getInt("Id_Klienta"));

                    client.setImie(rs.getString("Imie"));
                    client.setNazwisko(rs.getString("Nazwisko"));
                    client.setPlec(rs.getString("Plec"));
                    client.setPESEL(rs.getString("PESEL"));
                    client.setMiejscowosc(rs.getString("Miejscowosc"));
                    client.setUlica(rs.getString("Ulica"));
                    client.setNr_Budynku(rs.getString("Nr_Budynku"));
                    client.setNr_Lokalu(rs.getString("Nr_Lokalu"));
                    client.setKod_Pocztowy(rs.getString("Kod_Pocztowy"));
                    client.setId_Klubu(rs.getInt("Id_Klubu"));
                    client.setEmail(rs.getString("Email"));
                    client.setNr_Telefonu(rs.getString("Nr_Telefonu"));

                    clients.add(client);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }

            return clients;
        }

        public int getId_Klienta() {
            return Id_Klienta;
        }

        public void setId_Klienta(int id_Klienta) {
            Id_Klienta = id_Klienta;
        }

        public int getId_Klubu() {
            return Id_Klubu;
        }

        public void setId_Klubu(int id_Klubu) {
            Id_Klubu = id_Klubu;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNr_Telefonu() {
        return Nr_Telefonu;
    }

    public void setNr_Telefonu(String nr_Telefonu) {
        Nr_Telefonu = nr_Telefonu;
    }

    private static String generateActualSql(String sqlQuery, Object... parameters) {
            String[] parts = sqlQuery.split("\\?");
            StringBuilder sb = new StringBuilder();

            // This might be wrong if some '?' are used as litteral '?'
            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                sb.append(part);
                if (i < parameters.length) {
                    sb.append(formatParameter(parameters[i]));
                }
            }

            return sb.toString();
        }

        private static String formatParameter(Object parameter) {
            if (parameter == null) {
                return "NULL";
            } else {
                if (parameter instanceof String) {
                    return "'" + ((String) parameter).replace("'", "''") + "'";
                } else if (parameter instanceof Timestamp) {
                    return "to_timestamp('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS").
                            format(parameter) + "', 'mm/dd/yyyy hh24:mi:ss.ff3')";
                } else if (parameter instanceof Date) {
                    return "to_date('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
                            format(parameter) + "', 'mm/dd/yyyy hh24:mi:ss')";
                } else if (parameter instanceof Boolean) {
                    return ((Boolean) parameter).booleanValue() ? "1" : "0";
                } else {
                    return parameter.toString();
                }
            }
        }

}