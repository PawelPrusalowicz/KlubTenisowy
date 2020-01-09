package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Publisher {

    public ObservableList<Publisher> getAll (Connection connenction)  {

        ObservableList<Worker> workers = FXCollections.observableArrayList();



        Statement statement = null;
        ResultSet rs = null;

        String sql = "SELECT...";

        try {
            statement = connenction.createStatement();
            rs = statement.executeQuery(sql);


            while (rs.next()){

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insertPublisher (Connection connection, Integer publisherOriginYear) {

        PreparedStatement statement;
        Integer rs;

        String sql = "INSERT INTO...";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, publisherOriginYear);
            statement.setString(2, "lala");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
