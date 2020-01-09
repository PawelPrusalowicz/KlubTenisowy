package app;

import app.login.DBConnection;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ApplicationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label reservationCourseNameLabel;

    @FXML
    private Label reservationCourtNoLabel;

    @FXML
    private Label reservationDateLabel;

    @FXML
    private Label reservationHourFromLabel;

    @FXML
    private JFXTimePicker reservationTimeToPicker;

    @FXML
    private JFXDatePicker reservationDatePicker;

    @FXML
    private Button reservationButton;

    @FXML
    private JFXComboBox<?> reservationAgencyComboBox;

    @FXML
    private JFXTextField reservationCourtNoTextField;

    @FXML
    private JFXComboBox<?> reservationServiceTypeComboBox;

    @FXML
    private JFXComboBox<?> reservationCourseNameComboBox;

    @FXML
    private Label reservationHourToLabel;

    @FXML
    private JFXTimePicker reservationTimeFromPicker;

    @FXML
    private Label reservationTrainerLabel;

    @FXML
    private JFXComboBox<?> reservationTrainerComboBox;

    @FXML
    private TableView<?> appServiceReservationTable;

    @FXML
    private TableView<?> appAgencyTable;

    @FXML
    private TableView<Worker> appWorkersTable;

    @FXML
    private TableView<?> appCourtTable;

    @FXML
    private TextField mydataUsernameTextField;

    @FXML
    private TextField mydataNameTextField;

    @FXML
    private TextField mydataLastNameTextField;

    @FXML
    private TextField mydataSexTextField;

    @FXML
    private TextField mydataPeselTextField;

    @FXML
    private TextField mydataCityTextField;

    @FXML
    private TextField mydataPostalCodeTextField;

    @FXML
    private TextField mydataStreetTextField;

    @FXML
    private TextField mydataBuildingNoTextField;

    @FXML
    private TextField mydataApartmentNoTextField;

    @FXML
    private Button mydataEditButton;

    @FXML
    private TextField mydataEmailTextField;

    @FXML
    private Label mydataHireDateLabel;

    @FXML
    private TextField mydataHireDateTextField;

    @FXML
    private Label mydataLicenceLabel;

    @FXML
    private TextField mydataLicenceTextField;

    @FXML
    private Button mydataSaveButton;

    @FXML
    private Button mydataChangePsswdButton;

    @FXML
    private Label mydataWorkExpLabel;

    @FXML
    private TextField mydataWorkExpTextField;

    @FXML
    private Label mydataAchievementsLabel;

    @FXML
    private TextField mydataAchievementsTextField;

    @FXML
    private Label logoutLabel;

    @FXML
    void initialize() {

        logoutLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Stage stage = (Stage) logoutLabel.getScene().getWindow();
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
        });


        displayWorkers();

    }

    void displayWorkers() {

        TableColumn<Worker, Integer> workersTableColumnId = new TableColumn<>("Id");
        workersTableColumnId.setCellValueFactory( new PropertyValueFactory<>("Id_Pracownika"));

        TableColumn<Worker, String> workersTableColumnImie = new TableColumn<>("Imię");
        workersTableColumnImie.setCellValueFactory( new PropertyValueFactory<>("Imie"));

        TableColumn<Worker, String> workersTableColumnNazwisko = new TableColumn<>("Nazwisko");
        workersTableColumnNazwisko.setCellValueFactory( new PropertyValueFactory<>("Nazwisko"));

        TableColumn<Worker, String> workersTableColumnPlec = new TableColumn<>("Płeć");
        workersTableColumnPlec.setCellValueFactory( new PropertyValueFactory<>("Plec"));

        TableColumn<Worker, String> workersTableColumnPESEL = new TableColumn<>("PESEL");
        workersTableColumnPESEL.setCellValueFactory( new PropertyValueFactory<>("PESEL"));

        TableColumn<Worker, String> workersTableColumnMiejscowosc = new TableColumn<>("Miejscowość");
        workersTableColumnMiejscowosc.setCellValueFactory( new PropertyValueFactory<>("Miejscowosc"));

        TableColumn<Worker, String> workersTableColumnUlica = new TableColumn<>("Ulica");
        workersTableColumnUlica.setCellValueFactory( new PropertyValueFactory<>("Ulica"));

        TableColumn<Worker, String> workersTableColumnNrBudynku = new TableColumn<>("Nr Budynku");
        workersTableColumnNrBudynku.setCellValueFactory( new PropertyValueFactory<>("Nr_Budynku"));

        TableColumn<Worker, String> workersTableColumnNrLokalu = new TableColumn<>("Nr Lokalu");
        workersTableColumnNrLokalu.setCellValueFactory( new PropertyValueFactory<>("Nr_Lokalu"));

        TableColumn<Worker, String> workersTableColumnKodPocztowy = new TableColumn<>("Kod Pocztowy");
        workersTableColumnKodPocztowy.setCellValueFactory( new PropertyValueFactory<>("Kod_Pocztowy"));

        TableColumn<Worker, Date> workersTableColumnData = new TableColumn<>("Data Zatrudnienia");
        workersTableColumnData.setCellValueFactory( new PropertyValueFactory<>("Data_Zatrudnienia"));

        TableColumn<Worker, Integer> workersTableColumnIdKlubu = new TableColumn<>("Id Klubu");
        workersTableColumnIdKlubu.setCellValueFactory( new PropertyValueFactory<>("Id_Klubu"));

        TableColumn<Worker, Integer> workersTableColumnIdPlacowki = new TableColumn<>("Id Placówki");
        workersTableColumnIdPlacowki.setCellValueFactory( new PropertyValueFactory<>("Id_Placowki"));

        TableColumn<Worker, Integer> workersTableColumnIdStanowiska = new TableColumn<>("Id Stanowiska");
        workersTableColumnIdStanowiska.setCellValueFactory( new PropertyValueFactory<>("Id_Stanowiska"));



        appWorkersTable.getColumns().clear();
        appWorkersTable.getColumns().add(workersTableColumnId);
        appWorkersTable.getColumns().add(workersTableColumnImie);
        appWorkersTable.getColumns().add(workersTableColumnNazwisko);
        appWorkersTable.getColumns().add(workersTableColumnPlec);
        appWorkersTable.getColumns().add(workersTableColumnPESEL);
        appWorkersTable.getColumns().add(workersTableColumnMiejscowosc);
        appWorkersTable.getColumns().add(workersTableColumnUlica);
        appWorkersTable.getColumns().add(workersTableColumnNrBudynku);
        appWorkersTable.getColumns().add(workersTableColumnNrLokalu);
        appWorkersTable.getColumns().add(workersTableColumnKodPocztowy);
        appWorkersTable.getColumns().add(workersTableColumnData);
        appWorkersTable.getColumns().add(workersTableColumnIdKlubu);
        appWorkersTable.getColumns().add(workersTableColumnIdPlacowki);
        appWorkersTable.getColumns().add(workersTableColumnIdStanowiska);



        //TableColumn workersTableColumnString = new TableColumn("Imię2"); // np jak chcemy przechowywać inta dla workera
        // tabela - dodawanie, usuwanie, update, filtorwanie, niech odświeża


        ObservableList<Worker> workers = Worker.getAll(DBConnection.getConnection());

        appWorkersTable.setItems(workers); //workersList jak publishers
    }

}

//cellFactoryProperty