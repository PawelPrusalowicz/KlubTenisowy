package app;

import javafx.collections.ObservableList;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Timestamp;

public class ServicesTable {

    public static ObservableList<Service> initializeServicesTable(TableView<Service> servicesTable){

        ObservableList<Service> services = Service.getServices( null);

        TableColumn<Service, Integer> tableColumnId = new TableColumn<>("Id");
        tableColumnId.setCellValueFactory( new PropertyValueFactory<>("Id_Realizacji_Uslugi"));
        tableColumnId.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<Service, Timestamp> workersTableColumnTerminOd = new TableColumn<>("Termin od");
        workersTableColumnTerminOd.setCellValueFactory( new PropertyValueFactory<>("Termin_Od"));
        workersTableColumnTerminOd.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<Service, Timestamp> workersTableColumnTerminDo = new TableColumn<>("Termin do");
        workersTableColumnTerminDo.setCellValueFactory( new PropertyValueFactory<>("Termin_Od"));
        workersTableColumnTerminDo.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<Service, Integer> tableColumnIdPracownika = new TableColumn<>("Id pracownika");
        tableColumnIdPracownika.setCellValueFactory( new PropertyValueFactory<>("Id_Pracownika"));
        tableColumnIdPracownika.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<Service, Integer> tableColumnIdUslugi = new TableColumn<>("Id Uslugi");
        tableColumnIdUslugi.setCellValueFactory( new PropertyValueFactory<>("Id_Uslugi"));
        tableColumnIdUslugi.setStyle("-fx-alignment: TOP-CENTER;");

        TableColumn<Service, String> tableColumnNazwaUslugi = new TableColumn<>("Nazwa Uslugi");
        tableColumnNazwaUslugi.setCellValueFactory( new PropertyValueFactory<>("Nazwa_Uslugi"));
        tableColumnNazwaUslugi.setStyle("-fx-alignment: TOP-CENTER;");

        servicesTable.getColumns().clear();
        servicesTable.getColumns().add(tableColumnId);
        servicesTable.getColumns().add(workersTableColumnTerminOd);
        servicesTable.getColumns().add(workersTableColumnTerminDo);
        servicesTable.getColumns().add(tableColumnIdPracownika);
        servicesTable.getColumns().add(tableColumnNazwaUslugi);
        servicesTable.getColumns().add(tableColumnIdUslugi);


        servicesTable.setStyle("-fx-font-size :10;");

        servicesTable.setItems(services);


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

        return services;
    }
}
