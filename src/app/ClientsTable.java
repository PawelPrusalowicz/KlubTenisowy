package app;

import app.login.DBConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.Set;

public class ClientsTable{

    public static ObservableList<Client> initializeClientsTable(TableView<Client> appClientsTable, Set<Integer> clientsChangedIds){

        ObservableList<Client> clients = Client.getClients( null);

        TableColumn<Client, Integer> tableColumnId = new TableColumn<>("Id");
        tableColumnId.setCellValueFactory( new PropertyValueFactory<>("Id_Klienta"));
        tableColumnId.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnId.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId_Klienta(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());
        });



        TableColumn<Client, String> tableColumnImie = new TableColumn<>("Imię");
        tableColumnImie.setCellValueFactory( new PropertyValueFactory<>("Imie"));
        tableColumnImie.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnImie.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnImie.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setImie(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });


        TableColumn<Client, String> tableColumnNazwisko = new TableColumn<>("Nazwisko");
        tableColumnNazwisko.setCellValueFactory( new PropertyValueFactory<>("Nazwisko"));
        tableColumnNazwisko.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnNazwisko.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnNazwisko.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNazwisko(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });

        TableColumn<Client, String> tableColumnPlec = new TableColumn<>("Płeć");
        tableColumnPlec.setCellValueFactory( new PropertyValueFactory<>("Plec"));
        tableColumnPlec.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnPlec.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPlec.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPlec(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });

        TableColumn<Client, String> tableColumnPESEL = new TableColumn<>("PESEL");
        tableColumnPESEL.setCellValueFactory( new PropertyValueFactory<>("PESEL"));
        tableColumnPESEL.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnPESEL.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPESEL.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPESEL(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });

        TableColumn<Client, String> tableColumnMiejscowosc = new TableColumn<>("Miejscowość");
        tableColumnMiejscowosc.setCellValueFactory( new PropertyValueFactory<>("Miejscowosc"));
        tableColumnMiejscowosc.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnMiejscowosc.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnMiejscowosc.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMiejscowosc(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });

        TableColumn<Client, String> tableColumnUlica = new TableColumn<>("Ulica");
        tableColumnUlica.setCellValueFactory( new PropertyValueFactory<>("Ulica"));
        tableColumnUlica.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnUlica.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnUlica.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setUlica(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });

        TableColumn<Client, String> tableColumnNrBudynku = new TableColumn<>("Nr Budynku");
        tableColumnNrBudynku.setCellValueFactory( new PropertyValueFactory<>("Nr_Budynku"));
        tableColumnNrBudynku.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnNrBudynku.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnNrBudynku.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNr_Budynku(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });

        TableColumn<Client, String> tableColumnNrLokalu = new TableColumn<>("Nr Lokalu");
        tableColumnNrLokalu.setCellValueFactory( new PropertyValueFactory<>("Nr_Lokalu"));
        tableColumnNrLokalu.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnNrLokalu.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnNrLokalu.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNr_Lokalu(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });

        TableColumn<Client, String> tableColumnKodPocztowy = new TableColumn<>("Kod Pocztowy");
        tableColumnKodPocztowy.setCellValueFactory( new PropertyValueFactory<>("Kod_Pocztowy"));
        tableColumnKodPocztowy.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnKodPocztowy.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnKodPocztowy.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setKod_Pocztowy(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });

        TableColumn<Client, Integer> tableColumnIdKlubu = new TableColumn<>("Id Klubu");
        tableColumnIdKlubu.setCellValueFactory( new PropertyValueFactory<>("Id_Klubu"));
        tableColumnIdKlubu.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnIdKlubu.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
        tableColumnIdKlubu.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId_Klubu(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });


        TableColumn<Client, String> tableColumnEmail = new TableColumn<>("Email");
        tableColumnEmail.setCellValueFactory( new PropertyValueFactory<>("Email"));
        tableColumnEmail.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnEmail.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });



        TableColumn<Client, String> tableColumnNrTelefonu = new TableColumn<>("Nr Telefonu");
        tableColumnNrTelefonu.setCellValueFactory( new PropertyValueFactory<>("Nr_Telefonu"));
        tableColumnNrTelefonu.setStyle("-fx-alignment: TOP-CENTER;");
        tableColumnNrTelefonu.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnNrTelefonu.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNr_Telefonu(e.getNewValue());
            appClientsTable.setItems(clients);
            clientsChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Klienta());

        });


        appClientsTable.getColumns().clear();
        appClientsTable.getColumns().add(tableColumnId);
        appClientsTable.getColumns().add(tableColumnImie);
        appClientsTable.getColumns().add(tableColumnNazwisko);
        appClientsTable.getColumns().add(tableColumnPlec);
        appClientsTable.getColumns().add(tableColumnPESEL);
        appClientsTable.getColumns().add(tableColumnEmail);
        appClientsTable.getColumns().add(tableColumnNrTelefonu);
        appClientsTable.getColumns().add(tableColumnMiejscowosc);
        appClientsTable.getColumns().add(tableColumnUlica);
        appClientsTable.getColumns().add(tableColumnNrBudynku);
        appClientsTable.getColumns().add(tableColumnNrLokalu);
        appClientsTable.getColumns().add(tableColumnKodPocztowy);
        appClientsTable.getColumns().add(tableColumnIdKlubu);

        appClientsTable.setStyle("-fx-font-size :10;");

        appClientsTable.setEditable(true);

        appClientsTable.setItems(clients);


        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemDelete = new MenuItem("Usuń");
        menuItemDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Object item = appClientsTable.getSelectionModel().getSelectedItem();
                clientsChangedIds.remove(((Client) item).getId_Klienta());
                Worker.deleteWorker(DBConnection.getConnection() ,((Client) item).getId_Klienta());
                ObservableList<Client> clients = Client.getClients( null);
                appClientsTable.setItems(clients);            }
        });

        contextMenu.getItems().add(menuItemDelete);

        appClientsTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(appClientsTable, t.getScreenX(), t.getScreenY());
                }
            }
        });

        return clients;
    }

}