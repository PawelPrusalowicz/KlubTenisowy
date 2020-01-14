package app.login;

import app.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
                loginUser();
            }
        });
        Connection connection = DBConnection.getConnection();

    }

    private void loginUser(){
        if(!loginUsername.getText().toString().trim().equals("") &&
                !loginPassword.getText().toString().trim().equals("")){


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

