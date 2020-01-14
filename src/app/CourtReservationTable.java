package app;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Timestamp;

public class CourtReservationTable {

    public static ObservableList<CourtReservation> initializeCourtReservationsTable(TableView<CourtReservation> courtreservationsTable){

        ObservableList<CourtReservation> courtReservations = CourtReservation.getCourtReservations();

        TableColumn<CourtReservation, Integer> tableColumnId = new TableColumn<>("Id");
        tableColumnId.setCellValueFactory( new PropertyValueFactory<>("Id_Rezerwacji_Kortu"));
        tableColumnId.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<CourtReservation, Timestamp> workersTableColumnTerminOd = new TableColumn<>("Termin od");
        workersTableColumnTerminOd.setCellValueFactory( new PropertyValueFactory<>("Termin_Od"));
        workersTableColumnTerminOd.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<CourtReservation, Timestamp> workersTableColumnTerminDo = new TableColumn<>("Termin do");
        workersTableColumnTerminDo.setCellValueFactory( new PropertyValueFactory<>("Termin_Od"));
        workersTableColumnTerminDo.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<CourtReservation, Integer> tableColumnIdKortu = new TableColumn<>("Id kortu");
        tableColumnIdKortu.setCellValueFactory( new PropertyValueFactory<>("Id_Kortu"));
        tableColumnIdKortu.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<CourtReservation, Integer> tableColumnIdRealizacjiUslugi = new TableColumn<>("Id Realizacji Uslugi");
        tableColumnIdRealizacjiUslugi.setCellValueFactory( new PropertyValueFactory<>("Id_Realizacji_Uslugi"));
        tableColumnIdRealizacjiUslugi.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<CourtReservation, Integer> tableColumnNazwaUslugi = new TableColumn<>("Nazwa Uslugi");
        tableColumnNazwaUslugi.setCellValueFactory( new PropertyValueFactory<>("Nazwa_Uslugi"));
        tableColumnNazwaUslugi.setStyle("-fx-alignment: TOP-CENTER;");

        courtreservationsTable.getColumns().clear();
        courtreservationsTable.getColumns().add(tableColumnId);
        courtreservationsTable.getColumns().add(workersTableColumnTerminOd);
        courtreservationsTable.getColumns().add(workersTableColumnTerminDo);
        courtreservationsTable.getColumns().add(tableColumnIdKortu);
        courtreservationsTable.getColumns().add(tableColumnNazwaUslugi);
        courtreservationsTable.getColumns().add(tableColumnIdRealizacjiUslugi);


        courtreservationsTable.setStyle("-fx-font-size :10;");

        courtreservationsTable.setItems(courtReservations);


//        ContextMenu contextMenu = new ContextMenu();
//        MenuItem menuItemDelete = new MenuItem("Usuń");
//        menuItemDelete.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//                Object item = servicesTable.getSelectionModel().getSelectedItem();
//                workersChangedIds.remove(((Worker) item).getId_Klienta());
//                Worker.deleteClient(DBConnection.getConnection() ,((Worker) item).getId_Klienta());
//                initializeWorkersTable(servicesTable, workersChangedIds);
//            }
//        });

        //contextMenu.getItems().add(menuItemDelete);

//        servicesTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent t) {
//                if(t.getButton() == MouseButton.SECONDARY) {
//                    contextMenu.show(servicesTable, t.getScreenX(), t.getScreenY());
//                }
//            }
//        });

        return courtReservations;
    }
}
