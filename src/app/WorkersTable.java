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
import javafx.util.Callback;
import java.util.Set;

public class WorkersTable{


    public static ObservableList<Worker> initializeWorkersTable(TableView<Worker> appWorkersTable, Set<Integer> workersChangedIds){

        ObservableList<Worker> workers = Worker.getWorkers( null);

        TableColumn<Worker, Integer> workersTableColumnId = new TableColumn<>("Id");
        workersTableColumnId.setCellValueFactory( new PropertyValueFactory<>("Id_Pracownika"));
        workersTableColumnId.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnId.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId_Pracownika(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());
        });



        TableColumn<Worker, String> workersTableColumnImie = new TableColumn<>("Imię");
        workersTableColumnImie.setCellValueFactory( new PropertyValueFactory<>("Imie"));
        workersTableColumnImie.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnImie.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnImie.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setImie(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });


        TableColumn<Worker, String> workersTableColumnNazwisko = new TableColumn<>("Nazwisko");
        workersTableColumnNazwisko.setCellValueFactory( new PropertyValueFactory<>("Nazwisko"));
        workersTableColumnNazwisko.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnNazwisko.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnNazwisko.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNazwisko(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, String> workersTableColumnPlec = new TableColumn<>("Płeć");
        workersTableColumnPlec.setCellValueFactory( new PropertyValueFactory<>("Plec"));
        workersTableColumnPlec.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnPlec.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnPlec.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPlec(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, String> workersTableColumnPESEL = new TableColumn<>("PESEL");
        workersTableColumnPESEL.setCellValueFactory( new PropertyValueFactory<>("PESEL"));
        workersTableColumnPESEL.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnPESEL.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnPESEL.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPESEL(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, String> workersTableColumnMiejscowosc = new TableColumn<>("Miejscowość");
        workersTableColumnMiejscowosc.setCellValueFactory( new PropertyValueFactory<>("Miejscowosc"));
        workersTableColumnMiejscowosc.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnMiejscowosc.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnMiejscowosc.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMiejscowosc(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, String> workersTableColumnUlica = new TableColumn<>("Ulica");
        workersTableColumnUlica.setCellValueFactory( new PropertyValueFactory<>("Ulica"));
        workersTableColumnUlica.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnUlica.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnUlica.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setUlica(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, String> workersTableColumnNrBudynku = new TableColumn<>("Nr Budynku");
        workersTableColumnNrBudynku.setCellValueFactory( new PropertyValueFactory<>("Nr_Budynku"));
        workersTableColumnNrBudynku.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnNrBudynku.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnNrBudynku.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNr_Budynku(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, String> workersTableColumnNrLokalu = new TableColumn<>("Nr Lokalu");
        workersTableColumnNrLokalu.setCellValueFactory( new PropertyValueFactory<>("Nr_Lokalu"));
        workersTableColumnNrLokalu.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnNrLokalu.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnNrLokalu.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNr_Lokalu(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, String> workersTableColumnKodPocztowy = new TableColumn<>("Kod Pocztowy");
        workersTableColumnKodPocztowy.setCellValueFactory( new PropertyValueFactory<>("Kod_Pocztowy"));
        workersTableColumnKodPocztowy.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnKodPocztowy.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnKodPocztowy.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setKod_Pocztowy(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, String> workersTableColumnData = new TableColumn<>("Data Zatrudnienia");
        workersTableColumnData.setCellValueFactory( new PropertyValueFactory<>("Data_Zatrudnienia"));
        workersTableColumnData.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnData.setCellFactory(new Callback<TableColumn<Worker, String>, TableCell<Worker, String>>() {
            @Override
            public TableCell call(TableColumn p) {
                DatePickerCell datePick = new DatePickerCell(workers);
                return datePick;
            }
        });


        workersTableColumnData.setEditable(true);

        workersTableColumnData.setOnEditCommit(e-> {
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());
        });

        TableColumn<Worker, Integer> workersTableColumnIdKlubu = new TableColumn<>("Id Klubu");
        workersTableColumnIdKlubu.setCellValueFactory( new PropertyValueFactory<>("Id_Klubu"));
        workersTableColumnIdKlubu.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnIdKlubu.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
        workersTableColumnIdKlubu.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId_Klubu(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });


        TableColumn<Worker, Integer> workersTableColumnIdPlacowki = new TableColumn<>("Id Placówki");
        workersTableColumnIdPlacowki.setCellValueFactory( new PropertyValueFactory<>("Id_Placowki"));
        workersTableColumnIdPlacowki.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnIdPlacowki.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
        workersTableColumnIdPlacowki.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId_Placowki(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, Integer> workersTableColumnIdStanowiska = new TableColumn<>("Id Stanowiska");
        workersTableColumnIdStanowiska.setCellValueFactory( new PropertyValueFactory<>("Id_Stanowiska"));
        workersTableColumnIdStanowiska.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnIdStanowiska.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
        workersTableColumnIdStanowiska.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId_Stanowiska(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, Integer> workersTableColumnLicencja = new TableColumn<>("Licencja");
        workersTableColumnLicencja.setCellValueFactory( new PropertyValueFactory<>("Licencja"));
        workersTableColumnLicencja.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnLicencja.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
        workersTableColumnLicencja.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLicencja(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });

        TableColumn<Worker, String> workersTableColumnStaz = new TableColumn<>("Staż");
        workersTableColumnStaz.setCellValueFactory( new PropertyValueFactory<>("Staz"));
        workersTableColumnStaz.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnStaz.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnStaz.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setStaz(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });



        TableColumn<Worker, String> workersTableColumnOsiagniecia = new TableColumn<>("Osiągnięcia");
        workersTableColumnOsiagniecia.setCellValueFactory( new PropertyValueFactory<>("Osiagniecia"));
        workersTableColumnOsiagniecia.setStyle("-fx-alignment: TOP-CENTER;");
        workersTableColumnOsiagniecia.setCellFactory(TextFieldTableCell.forTableColumn());
        workersTableColumnOsiagniecia.setOnEditCommit(e-> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setOsiagniecia(e.getNewValue());
            appWorkersTable.setItems(workers);
            workersChangedIds.add(e.getTableView().getItems().get(e.getTablePosition().getRow()).getId_Pracownika());

        });


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
        appWorkersTable.getColumns().add(workersTableColumnLicencja);
        appWorkersTable.getColumns().add(workersTableColumnStaz);
        appWorkersTable.getColumns().add(workersTableColumnOsiagniecia);


        appWorkersTable.setStyle("-fx-font-size :10;");

        appWorkersTable.setEditable(true);

        appWorkersTable.setItems(workers);


        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemDelete = new MenuItem("Usuń");
        menuItemDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Object item = appWorkersTable.getSelectionModel().getSelectedItem();
                workersChangedIds.remove(((Worker) item).getId_Pracownika());
                Worker.deleteWorker(DBConnection.getConnection() ,((Worker) item).getId_Pracownika());
                ObservableList<Worker> workers = Worker.getWorkers( null);
                appWorkersTable.setItems(workers);

                //initializeWorkersTable(appWorkersTable, workersChangedIds);
            }
        });

        contextMenu.getItems().add(menuItemDelete);

        appWorkersTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(appWorkersTable, t.getScreenX(), t.getScreenY());
                }
            }
        });

        return workers;
    }

}
