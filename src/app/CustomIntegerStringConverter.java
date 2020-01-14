package app;
import javafx.scene.control.Alert;
import javafx.util.converter.IntegerStringConverter;


public class CustomIntegerStringConverter extends IntegerStringConverter {
    private final IntegerStringConverter converter = new IntegerStringConverter();

    @Override
    public String toString(Integer object) {
        try {

            if (object == 0){
                return "";
            } else {
                return converter.toString(object);
            }

        } catch (NumberFormatException e) {

        }
        return null;
    }

    @Override
    public Integer fromString(String string) {
        try {
            if (string == ""){
                return 0;
            } else {
                return converter.fromString(string);
            }


        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setContentText("Proszę wprowadzić liczbę!");
            alert.show();
        }
        return -1;
    }
}