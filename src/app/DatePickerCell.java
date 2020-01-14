//
package app;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;

public class DatePickerCell<S, T> extends TableCell<Worker, Date> {

    private DatePicker datePicker;
    private ObservableList<Worker> workers;

    public DatePickerCell(ObservableList<Worker> listWorkers) {

        super();

        this.workers = listWorkers;

        if (datePicker == null) {
            createDatePicker();
        }
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                datePicker.requestFocus();
            }
        });
    }

    @Override
    public void updateItem(Date item, boolean empty) {

        super.updateItem(item, empty);

        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");

        if (null == this.datePicker) {
            System.out.println("datePicker is NULL");
        }

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (isEditing()) {
                setContentDisplay(ContentDisplay.TEXT_ONLY);

            } else {
                setDatepikerDate(smp.format(item));
                setText(smp.format(item));
                setGraphic(this.datePicker);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    private void setDatepikerDate(String dateAsStr) {

        LocalDate ld = null;
        int jour, mois, annee;

        jour = mois = annee = 0;
        try {
            jour = Integer.parseInt(dateAsStr.substring(0, 2));
            mois = Integer.parseInt(dateAsStr.substring(3, 5));
            annee = Integer.parseInt(dateAsStr.substring(6, dateAsStr.length()));
        } catch (NumberFormatException e) {
            System.out.println("setDatepikerDate / unexpected error " + e);
        }

        ld = LocalDate.of(annee, mois, jour);
        datePicker.setValue(ld);
    }

    private void createDatePicker() {
        this.datePicker = new DatePicker();
        datePicker.setPromptText("jj/mm/aaaa");
        datePicker.setEditable(true);

        datePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = datePicker.getValue();
                int index = getIndex();

                SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
                cal.set(Calendar.MONTH, date.getMonthValue() - 1);
                cal.set(Calendar.YEAR, date.getYear());

                setText(smp.format(cal.getTime()));
                commitEdit(cal.getTime());

                if (null != getWorkers()) {
                    getWorkers().get(index).setData_Zatrudnienia(java.sql.Date.valueOf(datePicker.getValue()));
                }
            }
        });

        setAlignment(Pos.CENTER);
    }

    @Override
    public void startEdit() {
        super.startEdit();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    public ObservableList<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(ObservableList<Worker> workers) {
        this.workers = workers;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

}



//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.input.KeyCode;
//import javafx.util.Callback;
//
///**
// * Provides a date picker implementation of a {@link TableCell}. When cell editing begins, this shows
// * a date picker instead of a normal text field.
// *
// * @param <S> - The type of the TableView generic type (TableView&lt;S&gt;).
// * This should also match the first generic type in TableColumn (TableColumn&lt;S,T&gt;).
// */
//public class DatePickerTableCell<S> extends TableCell<S,String> {
//
//    /**
//     * Provides a table cell containing a date picker that show up when the cell is double-clicked
//     * or {@link TableView#edit} is called. The callback works on string-type {@link TableColumn} instances.
//     *
//     * @param datePattern The date pattern format used to parse the content of this cell
//     * @return A {@link Callback} for a {@link TableColumn#cellFactoryProperty} of a TableColumn, that
//     * will allow editing the content with a DatePicker widget.
//     * @param <S> Type of the TableColumn
//     */
//    public static <S> Callback<TableColumn<S,String>, TableCell<S,String>> forTableColumn(String datePattern) {
//        return (TableColumn<S,String> col) -> { return new DatePickerTableCell<S>(datePattern); };
//    }
//
//    private DatePicker datePicker;
//    private String datePattern;
//    private DateTimeFormatter dateFormatter;
//
//    public DatePickerTableCell(String datePattern) {
//        this.getStyleClass().add("text-field-table-cell");
//        this.datePattern = datePattern;
//        this.dateFormatter = DateTimeFormatter.ofPattern(datePattern);
//    }
//
//
//    @Override
//    public void startEdit() {
//        if (!isEditable()
//                || !getTableView().isEditable()
//                || !getTableColumn().isEditable()) {
//            return;
//        }
//        super.startEdit();
//
//        if(isEditing()) {
//            if(datePicker == null)
//                createDatePicker();
//
//            datePicker.setValue(getDate());
//            setText(null);
//            setGraphic(datePicker);
//            datePicker.requestFocus();
//        }
//    }
//
//    @Override
//    public void cancelEdit() {
//        super.cancelEdit();
//        setText(getItem() == null ? "" : getItem());
//        setGraphic(null);
//    }
//
//    @Override
//    public void updateItem(String item, boolean empty) {
//        super.updateItem(item, empty);
//
//        if(empty) {
//            setText(null);
//            setGraphic(null);
//        } else {
//            if (isEditing()) {
//                if (datePicker != null) {
//                    datePicker.setValue(getDate());
//                }
//                setText(null);
//                setGraphic(datePicker);
//            } else {
//                setText(getItem() == null ? "" : getItem());
//                setGraphic(null);
//            }
//        }
//    }
//
//    private void createDatePicker() {
//        datePicker = new DatePicker(getDate());
//        //FXInput.setDateFormat(datePicker, datePattern);
//        // datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
//        datePicker.setOnKeyReleased(keyEvent -> {
//            if(keyEvent.getCode() == KeyCode.ENTER) {
//                commitEdit(datePicker.getValue().format(dateFormatter)); // TODO check for empty/null values
//            } else if(keyEvent.getCode() == KeyCode.ESCAPE) {
//                cancelEdit();
//            }
//        });
//
//        datePicker.focusedProperty().addListener((obsValue, oldValue, newValue) -> {
//            if(oldValue == true && newValue == false)
//                commitEdit(datePicker.getValue().format(dateFormatter));
//        });
//    }
//
//    /**
//     * Transforms the current <code>String</code> value of this table cell in a <code>LocalDate</code>
//     *
//     * @return The LocalDate representation of the cell value.
//     */
//    private LocalDate getDate() {
//        return getItem() == null ? LocalDate.now() : LocalDate.parse(getItem(), dateFormatter);
//    }
//}
//
//
//
//
//
//
//
////
////import javafx.beans.property.ObjectProperty;
////import javafx.beans.property.SimpleObjectProperty;
////import javafx.scene.control.DatePicker;
////import javafx.scene.control.TableCell;
////import javafx.scene.control.TableColumn;
////import javafx.scene.input.KeyCode;
////import javafx.util.Callback;
////import javafx.util.StringConverter;
////
////import java.time.LocalDate;
////
////public class DatePickerTableCell<S> extends TableCell<S, LocalDate> {
////    private ObjectProperty<StringConverter<LocalDate>> converter = new SimpleObjectProperty<>(this, "converter");
////    private DatePicker datePicker;
////
////    public DatePickerTableCell(StringConverter<LocalDate> converter) {
////        setConverter(converter);
////        this.getStyleClass().add("datepicker-table-cell");
////    }
////
////    public static <S> Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> forTableColumn() {
////        return column -> new DatePickerTableCell<>(new DefaultLocalDateConverter());
////    }
////
////    public static <S> Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> forTableColumn(StringConverter<LocalDate> converter) {
////        return column -> new DatePickerTableCell<>(converter);
////    }
////
////    public void startEdit() {
////        if (!isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable())
////            return;
////
////        super.startEdit();
////
////        if (isEditing()) {
////            if (datePicker == null)
////                createDatePicker();
////
////            setText(null);
////            setGraphic(datePicker);
////            datePicker.requestFocus();
////        }
////    }
////
////    public void cancelEdit() {
////        super.cancelEdit();
////        setText(getItemText());
////        setGraphic(null);
////    }
////
////    private String getItemText() {
////        return getConverter().toString(getItem());
////    }
////
////    private void createDatePicker() {
////        datePicker = new DatePicker(getItem());
////        datePicker.converterProperty().bind(converterProperty());
////
////        datePicker.setOnAction(event -> {
////            commitEdit(datePicker.getValue());
////            event.consume();
////        });
////
////        datePicker.setOnKeyReleased(t -> {
////            if (t.getCode() == KeyCode.ESCAPE) {
////                cancelEdit();
////                t.consume();
////            }
////        });
////    }
////
////    protected void updateItem(LocalDate item, boolean empty) {
////        super.updateItem(item, empty);
////
////        if (isEmpty()) {
////            setText(null);
////            setGraphic(null);
////        } else {
////            if (isEditing()) {
////                datePicker.setValue(getItem());
////                setText(null);
////                setGraphic(datePicker);
////            } else {
////                setText(getItemText());
////                setGraphic(null);
////            }
////        }
////    }
////
////    public StringConverter<LocalDate> getConverter() {
////        return converter.get();
////    }
////
////    public ObjectProperty<StringConverter<LocalDate>> converterProperty() {
////        return converter;
////    }
////
////    public void setConverter(StringConverter<LocalDate> converter) {
////        this.converter.set(converter);
////    }
////
////    public DatePicker getDatePicker() {
////        return datePicker;
////    }
////
////    public void setDatePicker(DatePicker datePicker) {
////        this.datePicker = datePicker;
////    }
////
////    public static class DefaultLocalDateConverter extends StringConverter<LocalDate> {
////        public String toString(LocalDate date) {
////            return date != null ? date.toString() : "";
////        }
////
////        public LocalDate fromString(String string) {
////            return string == null || string.isEmpty() ? null : LocalDate.parse(string);
////        }
////    }
////}