package app;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.util.converter.DateStringConverter;


import java.util.Date;


public class CustomDateStringConverter extends DateStringConverter {
    private final DateStringConverter converter = new DateStringConverter();

//    @Override
//    public String toDate(Date object) {
//        try {
//            return converter.toString(object);
//        } catch (NumberFormatException e) {
//
//        }
//        return null;
//    }

    @Override
    public Date fromString(String string) {
        try {
            return converter.fromString(string);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setContentText("Proszę wprowadzić liczbę!");
            alert.show();
        }
        return new Date();
    }

}