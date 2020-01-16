package app.login;

import app.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Connection connection;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Label label = new Label();

        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loginUser();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        Connection connection = DBConnection.getConnection();

    }

    private void loginUser() throws SQLException {
        if(!loginUsername.getText().toString().trim().equals("") &&
                !loginPassword.getText().toString().trim().equals("")){

            String sqlQuery="Select * from Uzytkownicy";
            Connection connection = DBConnection.getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sqlQuery);



            boolean flag=false;
            User user=User.getInstance();
            rs.next();
            do
            {
                String str=rs.getString("Login");
                System.out.println(str);
                if(rs.getString("Login").equals(loginUsername.getText()) && rs.getString("Haslo").equals(loginPassword.getText()))
                {

                    user.setLogin(loginUsername.getText());
                    user.setPassword(loginPassword.getText());
                    if(rs.getString("Typ").equals("Admin")) {
                        user.setUserType(User.UserType.Admin);
                    }
                    if(rs.getString("Typ").equals("Worker")) {
                        user.setUserType(User.UserType.Worker);
                    }
                    if(rs.getString("Typ").equals("Client")) {
                        user.setUserType(User.UserType.Client);
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("You have been logged in as a " + rs.getString("Typ"));
                    alert.show();
                    flag=true;
                    break;
                }
            }while(rs.next());
            if(!flag)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while connecting to database");
                alert.setContentText("Wrong login or password. Try again");
                alert.show();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                Stage programStage = new Stage();

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/app/login/login.fxml"));

                    Scene scene = new Scene(root);
                    programStage.setScene(scene);
                    programStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();


                Stage programStage = new Stage();

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/app/view/application.fxml"));

                    Scene scene = new Scene(root);
                    programStage.setScene(scene);
                    programStage.show();

                    User.getInstance().setClubId(1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

