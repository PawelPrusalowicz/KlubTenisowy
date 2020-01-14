package app;

import app.login.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Set;

public class Worker {

    private int Id_Pracownika, Id_Klubu, Id_Placowki, Id_Stanowiska, Licencja;
    private String Imie, Nazwisko, Plec, PESEL, Miejscowosc, Ulica, Nr_Budynku, Nr_Lokalu, Kod_Pocztowy, Staz, Osiagniecia;
    private Date Data_Zatrudnienia;
    


    Worker (int IdPracownika){
        this.Id_Pracownika = IdPracownika;
    }
    Worker (String Imie){
        this.Imie = Imie;
        this.Data_Zatrudnienia = new Date(0);
        //this.setLicencja(null);
    }


    public static void deleteWorker(Connection connection, int workerId) {

        String sql = "Delete from pracownicy where Id_pracownika=" + workerId;
        String sql2 = "Delete from trenerzy where Id_Pracownika=" + workerId;

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void saveAll (ObservableList<Worker> workers, Set<Integer> workerChangedIds){

        for (Worker worker : workers){

            if ( workerChangedIds.contains(worker.getId_Pracownika())){
                if (worker.getId_Pracownika() == 0){
                    insertWorker(worker);
                }
                else {
                    updateWorker(worker);
                }
            }
        }

        workerChangedIds.clear();

    }

    public static void updateWorker(Worker worker){

        String sqlPracownik = "Update pracownicy set Imie='" + worker.getImie()
                + "', Nazwisko= '" + worker.getNazwisko()
                + "', Plec= '" + worker.getPlec()
                + "', PESEL= '" + worker.getPESEL()
                + "', Miejscowosc= '" + worker.getMiejscowosc()
                + "', Ulica= '" + worker.getUlica()
                + "', NR_BUDYNKU= '" + worker.getNr_Budynku()
                + "' ,NR_LOKALU= '" + worker.getNr_Lokalu()
                + "', KOD_POCZTOWY= '" + worker.getKod_Pocztowy()
                + "', DATA_ZATRUDNIENIA= TO_DATE('" + worker.getData_Zatrudnienia() + "', 'yyyy-mm-dd')"
                + ", ID_KLUBU= " + worker.getId_Klubu()
                + ", ID_PLACOWKI= " + worker.getId_Placowki()
                + ", ID_STANOWISKA= " + worker.getId_Stanowiska()
                + " where Id_pracownika=" + worker.getId_Pracownika();

        String sqlTrener = "Update trenerzy set Licencja= " + worker.getLicencja()
                + ", Staz= '" + worker.getStaz()
                + "', Osiagniecia= '" + worker.getOsiagniecia()
                + "' where Id_pracownika=" + worker.getId_Pracownika();

        Connection connection = DBConnection.getConnection();

        Statement statement = null;

        try {
            System.out.println(sqlPracownik);
            statement = connection.createStatement();
            statement.executeUpdate(sqlPracownik);
            statement.executeUpdate(sqlTrener);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertWorker(Worker worker){

        String sqlPracownik = "Insert into pracownicy (Imie, Nazwisko, plec, pesel, miejscowosc," +
                "ulica, nr_budynku, nr_lokalu, kod_pocztowy, data_zatrudnienia, id_klubu, id_placowki," +
                "id_stanowiska) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        String findIdSql = "Select Id_Pracownika from pracownicy where PESEL ='" + worker.getPESEL() +"'";


        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlPracownik);

            preparedStatement.setString(1, worker.getImie());
            preparedStatement.setString(2, worker.getNazwisko());
            preparedStatement.setString(3, worker.getPlec());
            preparedStatement.setString(4, worker.getPESEL());
            preparedStatement.setString(5, worker.getMiejscowosc());
            preparedStatement.setString(6, worker.getUlica());
            preparedStatement.setString(7, worker.getNr_Budynku());
            preparedStatement.setString(8, worker.getNr_Lokalu());
            preparedStatement.setString(9, worker.getKod_Pocztowy());
            preparedStatement.setDate(10, worker.getData_Zatrudnienia());
            preparedStatement.setInt(11, worker.getId_Klubu());
            preparedStatement.setInt(12, worker.getId_Placowki());
            preparedStatement.setInt(13, worker.getId_Stanowiska());

            System.out.println(generateActualSql(sqlPracownik,new Object[] { worker.getImie(),
                                                                                worker.getNazwisko(),
                    worker.getPlec(),
                    worker.getPESEL(),
                    worker.getMiejscowosc(),
                    worker.getUlica(),
                    worker.getNr_Budynku(),
                    worker.getNr_Lokalu(),
                    worker.getKod_Pocztowy(),
                    worker.getData_Zatrudnienia(),
                    worker.getId_Klubu(),
                    worker.getId_Placowki(),
                    worker.getId_Stanowiska()}));

            preparedStatement.executeUpdate();

            if(worker.getLicencja() != 0){

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(findIdSql);
                rs.next();
                worker.setId_Pracownika(rs.getInt("Id_Pracownika"));
                insertTrainer(worker);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTrainer (Worker worker) {

        String sqlTrener = "Insert into trenerzy (Id_pracownika, Licencja, Staz, Osiagniecia)" +
                "Values (?,?,?,?)";

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlTrener);
            preparedStatement = connection.prepareStatement(sqlTrener);
            preparedStatement.setInt(1, worker.getId_Pracownika());
            preparedStatement.setInt(2, worker.getLicencja());
            preparedStatement.setString(3, worker.getStaz());
            preparedStatement.setString(4, worker.getOsiagniecia());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static ObservableList<Worker> getWorkers( String searchParameter)  {

        ObservableList<Worker> workers = FXCollections.observableArrayList();

        Connection connection = DBConnection.getConnection();

        if(connection == null){
            return workers;
        }

        Statement statement = null;
        ResultSet rs = null;

        String sql = "Select * from pracownicy p left join trenerzy t on p.id_Pracownika = t.id_pracownika";

        if (searchParameter != null){
            sql += " where Imie like '" + searchParameter +"%' or nazwisko like '" + searchParameter +"%'";
        }

        sql += " order by p.id_pracownika asc";

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
                worker.setLicencja(rs.getInt("Licencja"));
                worker.setStaz(rs.getString("Staz"));
                worker.setOsiagniecia(rs.getString("Osiagniecia"));

                workers.add(worker);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
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

    public int getLicencja() {
        return Licencja;
    }

    public void setLicencja(int licencja) {
        Licencja = licencja;
    }

    public String getStaz() {
        return Staz;
    }

    public void setStaz(String staz) {
        Staz = staz;
    }

    public String getOsiagniecia() {
        return Osiagniecia;
    }

    public void setOsiagniecia(String osiagniecia) {
        Osiagniecia = osiagniecia;
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
