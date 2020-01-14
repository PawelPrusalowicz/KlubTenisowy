package app;

import app.login.DBConnection;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ApplicationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab makeReservationTab;

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
    private JFXComboBox<Label> reservationAgencyComboBox;

    @FXML
    private JFXTextField reservationCourtNoTextField;

    @FXML
    private JFXComboBox<Label> reservationServiceTypeComboBox;

    @FXML
    private JFXComboBox<Label> reservationCourseNameComboBox;

    @FXML
    private Label reservationHourToLabel;

    @FXML
    private JFXTimePicker reservationTimeFromPicker;

    @FXML
    private Label reservationTrainerLabel;

    @FXML
    private JFXComboBox<Label> reservationTrainerComboBox;

    @FXML
    private Tab servicesTab;

    @FXML
    private TableView<Service> appServiceReservationTable;

    @FXML
    private Tab courtReservationsTab;

    @FXML
    private TableView<CourtReservation> appCourtTable;

    @FXML
    private Tab clientsTab;

    @FXML
    private TableView<Client> appClientsTable;

    @FXML
    private TextField clientsSearchTextLabel;

    @FXML
    private Button clientsSearchButton;

    @FXML
    private Button clientsSaveButton;

    @FXML
    private Button clientsAddButton;

    @FXML
    private Tab workersTab;

    @FXML
    private TableView<Worker> appWorkersTable;

    @FXML
    private TextField workersSearchTextLabel;

    @FXML
    private Button workersSearchButton;

    @FXML
    private Button workersSaveButton;

    @FXML
    private Button workersAddButton;

    @FXML
    private Tab myDataTab;

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

    ObservableList<Worker> workers;
    ObservableList<CourtReservation>courtReservations;
    ObservableList<Service> services;
    ObservableList<Client> clients;


    Set<Integer> workersChangedIds, clientChangedIds;

    @FXML
    void initialize() {


        workersChangedIds = new HashSet<>();
        clientChangedIds = new HashSet<>();

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

        workersAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Worker worker = new Worker("Przykładowe Imie");
                workers.add(worker);
                appWorkersTable.setItems(workers);

            }
        });

        clientsAddButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Client client = new Client("Imię");
                clients.add(client);
                appClientsTable.setItems(clients);

            }
        });


        workersSaveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Worker.saveAll(workers, workersChangedIds);
            }
        });

        clientsSaveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Client.saveAll(clients, clientChangedIds);
            }
        });

        workersSearchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                workers = Worker.getWorkers(workersSearchTextLabel.getText());
                appWorkersTable.setItems(workers);

            }
        });

        clientsSearchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                clients = Client.getClients(clientsSearchTextLabel.getText());
                appClientsTable.setItems(clients);
            }
        });

        //mydataAchievementsTextField.getProperties()

        //displayWorkers();

        workers = WorkersTable.initializeWorkersTable(appWorkersTable, workersChangedIds);
        clients = ClientsTable.initializeClientsTable(appClientsTable, clientChangedIds);


//        courtReservationsTab.setOnSelectionChanged(new EventHandler<Event>() {
//            @Override
//            public void handle(Event event) {
//
//            }
//        });

        ServicesTable.initializeServicesTable(appServiceReservationTable);

        courtReservations = CourtReservationTable.initializeCourtReservationsTable(appCourtTable);


//        servicesTab.setOnSelectionChanged(new EventHandler<Event>() {
//            @Override
//            public void handle(Event event) {
//                ServicesTable.initializeCourtReservationsTable(appServiceReservationTable);
//            }
//        });

        reservationCourseNameComboBox.setDisable(true);
        reservationTrainerComboBox.setDisable(true);
        reservationCourtNoTextField.setDisable(true);
        reservationDatePicker.setDisable(true);
        reservationTimeFromPicker.setDisable(true);
        reservationTimeToPicker.setDisable(true);
        reservationServiceTypeComboBox.setDisable(true);
        MakeReservationTab.initializeReservationAgencyComboBox(reservationAgencyComboBox);

        reservationAgencyComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String value= reservationAgencyComboBox.getValue().getText();
                String kept = value.substring( 0, value.indexOf(","));
                Integer agencyId = Integer.parseInt(value.substring(kept.indexOf(",")+1, kept.length()));
                Reservation.getInstance().setAgencyId(agencyId);
                System.out.println(Reservation.getInstance().info());

                reservationServiceTypeComboBox.setDisable(false);
                MakeReservationTab.initializeReservationServiceTypeComboBox( reservationServiceTypeComboBox);

            }
        });

        reservationServiceTypeComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                String sql = "Select id_uslugi from uslugi where nazwa_uslugi='" +
                        reservationServiceTypeComboBox.getValue().getText()+ "' and Id_klubu=" + User.getInstance().getClubId();

                Connection connection = DBConnection.getConnection();

                if(connection == null){
                    return;
                }

                Integer serviceId = null;
                Statement statement = null;
                ResultSet rs = null;


                try {
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    rs.next();

                    Reservation.getInstance().setServiceTypeId(rs.getInt("id_uslugi"));
                    System.out.println(Reservation.getInstance().info());


                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (!reservationTrainerComboBox.getItems().isEmpty()) {
                    reservationTrainerComboBox.getSelectionModel().clearSelection();
                    reservationTrainerComboBox.getItems().removeAll();
                    reservationTrainerComboBox.getItems().clear();
                }

                if (reservationServiceTypeComboBox.getValue().getText().equals("Kurs grupowy")) {

                    reservationCourtNoTextField.setText(null);
                    reservationDatePicker.setValue(null);
                    reservationTimeFromPicker.setValue(null);
                    reservationTimeToPicker.setValue(null);
                    Reservation.getInstance().setTrainerId(0);
                    Reservation.getInstance().setTimeTo(null);
                    Reservation.getInstance().setTimeFrom(null);
                    Reservation.getInstance().setDate(null);
                    Reservation.getInstance().setCourtsNumber(0);
                    Reservation.getInstance().setServiceTypeName("Kurs grupowy");

                    reservationCourseNameComboBox.setDisable(false);
                    reservationTrainerComboBox.setDisable(true);
                    reservationCourtNoTextField.setDisable(true);
                    reservationDatePicker.setDisable(true);
                    reservationTimeFromPicker.setDisable(true);
                    reservationTimeToPicker.setDisable(true);


                    //String value= reservationServiceTypeComboBox.getValue().getText();
                    //String kept = value.substring( 4, value.indexOf(","));
                    //Integer agencyId = Integer.parseInt(value.substring(kept.indexOf(",")+1, kept.length()));

                    MakeReservationTab.initializeReservationCourseNameComboBox(reservationCourseNameComboBox);
                }
                else if (reservationServiceTypeComboBox.getValue().getText().equals("Lekcja z trenerem")){

                    Reservation.getInstance().setTrainerId(0);
                    Reservation.getInstance().setServiceTypeName("Lekcja z trenerem");

                    //reservationCourseNameComboBox.getValue().setText("");
                    //reservationCourseNameComboBox.getItems().removeAll();
                    reservationCourseNameComboBox.getItems().clear();
                    reservationCourseNameComboBox.getSelectionModel().clearSelection();


                    reservationCourseNameComboBox.setDisable(true);
                    reservationTrainerComboBox.setDisable(false);
                    reservationCourtNoTextField.setDisable(false);
                    reservationDatePicker.setDisable(false);
                    reservationTimeFromPicker.setDisable(false);
                    reservationTimeToPicker.setDisable(false);



                    MakeReservationTab.initializeReservationTrainerComboBox( reservationTrainerComboBox,  null);
                }
                else if (reservationServiceTypeComboBox.getValue().getText().equals("Pojedyńcza gra")){


                    Reservation.getInstance().setServiceRealisationId(0);
                    Reservation.getInstance().setTrainerId(0);
                    Reservation.getInstance().setServiceTypeName("Pojedyńcza gra");

                    reservationCourseNameComboBox.getSelectionModel().clearSelection();
                    reservationCourseNameComboBox.getItems().removeAll();
                    reservationCourseNameComboBox.getItems().clear();

                    reservationCourseNameComboBox.setDisable(true);
                    reservationTrainerComboBox.setDisable(true);
                    reservationCourtNoTextField.setDisable(false);
                    reservationDatePicker.setDisable(false);
                    reservationTimeFromPicker.setDisable(false);
                    reservationTimeToPicker.setDisable(false);
                }

                validateReservationButton();
            }
        });


        reservationTrainerComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!reservationTrainerComboBox.getItems().isEmpty()
                        && reservationTrainerComboBox.getValue() != null
                        && !reservationTrainerComboBox.getValue().getText().equals("")
                        ) {

                    System.out.println();

                    String value = reservationTrainerComboBox.getValue().getText();
                    String kept = value.substring(4, value.indexOf(","));
                    Integer trainerId = Integer.parseInt(kept);
                    //Integer trainerId = Integer.parseInt(value.substring(kept.indexOf(",")+1, kept.length()));
                    Reservation.getInstance().setTrainerId(trainerId);
                    System.out.println(Reservation.getInstance().info());

                    validateReservationButton();
                }
            }
        });


        reservationCourseNameComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!reservationTrainerComboBox.getItems().isEmpty()) {
                    reservationTrainerComboBox.getSelectionModel().clearSelection();
                    reservationTrainerComboBox.getItems().removeAll();
                    reservationTrainerComboBox.getItems().clear();
                }

                if(reservationCourseNameComboBox.getValue() == null){
                    return;
                }

                String value= reservationCourseNameComboBox.getValue().getText();
                String kept = value.substring( 3, value.indexOf(","));
                Integer serviceRealizationId = Integer.parseInt(kept); //Integer.parseInt(value.substring(kept.indexOf(",")+1, kept.length()));
                Reservation.getInstance().setServiceRealisationId(serviceRealizationId);
                System.out.println(Reservation.getInstance().info());


                String findTrainerSql = "Select Id_pracownika, Imie, Nazwisko from pracownicy where id_pracownika in" +
                        "(Select Id_pracownika from realizacje_uslug where id_realizacji_uslugi=" +
                        Reservation.getInstance().getServiceRealisationId()+ ")";

                Connection connection = DBConnection.getConnection();

                if(connection == null){
                    return;
                }

                Statement statement = null;
                ResultSet rs = null;

                try {
                    statement = connection.createStatement();
                    rs = statement.executeQuery(findTrainerSql);
                    rs.next();

                    //Reservation.getInstance().setServiceTypeId(rs.getInt("id_uslugi"));
                    reservationTrainerComboBox.getItems().add(new Label(
                            "Id: " +
                                    rs.getInt("Id_pracownika") + ", " +
                                    rs.getString("Imie") + " " + rs.getString("Nazwisko")

                    ));

                    reservationTrainerComboBox.getSelectionModel().select(0);


                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //reservationTrainerComboBox.getItems().add(new Label())

                validateReservationButton();

            }
        });


        reservationCourtNoTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue == null || newValue.equals("")){
                Reservation.getInstance().setCourtsNumber(0);
            }
            else {
                Reservation.getInstance().setCourtsNumber(Integer.parseInt(newValue));
            }
            System.out.println(Reservation.getInstance().info());
            validateReservationButton();
        });

        reservationDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue==null) {
                Reservation.getInstance().setDate(null);
            }
            else {
                Reservation.getInstance().setDate(Date.valueOf(newValue));
            }
            System.out.println(Reservation.getInstance().info());
            validateReservationButton();
        });

        reservationTimeFromPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue==null) {
                Reservation.getInstance().setTimeFrom(null);
            }
            else {
                Reservation.getInstance().setTimeFrom(Time.valueOf(newValue));
            }
            System.out.println(Reservation.getInstance().info());
            validateReservationButton();
        });

        reservationTimeToPicker.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue==null) {
                Reservation.getInstance().setTimeTo(null);
            }
            else {
                Reservation.getInstance().setTimeTo(Time.valueOf(newValue));
            }
            System.out.println(Reservation.getInstance().info());
            validateReservationButton();
        });

        reservationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Reservation.getInstance().saveReservation();
            }
        });

        validateReservationButton();
    }

    public void validateReservationButton(){

        reservationButton.setDisable(true);

        if (Reservation.getInstance().getServiceTypeName() == null){
            return;
        }

        if ( Reservation.getInstance().getServiceTypeName().equals("Lekcja z trenerem")){

            if (reservationTrainerComboBox.getValue() !=null &&
                    !reservationTrainerComboBox.getValue().getText().equals("") &&
                    !reservationCourtNoTextField.getText().equals("") &&
                    reservationDatePicker.getValue() != null &&
                    reservationTimeFromPicker.getValue() != null &&
                    reservationTimeToPicker.getValue() != null) {
                reservationButton.setDisable(false);
            }

        }
        else if (Reservation.getInstance().getServiceTypeName().equals("Pojedyńcza gra")){

            if (!reservationCourtNoTextField.getText().equals("") &&
                    reservationDatePicker.getValue() != null &&
                    reservationTimeFromPicker.getValue() != null &&
                    reservationTimeToPicker.getValue() != null) {
                reservationButton.setDisable(false);
            }

        }
        else if (Reservation.getInstance().getServiceTypeName().equals("Kurs grupowy")){

            if (reservationCourseNameComboBox.getValue() != null &&
                    !reservationCourseNameComboBox.getValue().getText().equals("")){
                reservationButton.setDisable(false);
            }

        }

    }

}
