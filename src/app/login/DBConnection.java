package app.login;
import java.sql.*;
import javafx.scene.control.Alert;

public class DBConnection {

        private static Connection conn;

        public static Connection getConnection() {

            String DB_URL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
            String DB_USER = "pprusalo";
            String DB_PASS = "pprusalo";

            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setContentText("Connected to database");
//                alert.show();

            } catch (Exception exc) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while connecting to database");
                alert.setContentText("Details: "+ exc.getMessage());
                alert.show();
            }
            return conn; }
}



