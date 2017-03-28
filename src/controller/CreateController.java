package controller;

import com.sun.deploy.panel.RadioPropertyGroup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 * Created by Mina on 3/8/2017.
 */
public class CreateController {
    @FXML TextField nameTextField;
    @FXML ToggleGroup typeSelection;
    @FXML DatePicker calendarDatePicker;
    @FXML TextField startTimeTextField;
    @FXML TextField endTimeTextField;
    @FXML Button saveButton;
    @FXML Button discardButton;

    public void saveFormData(ActionEvent actionEvent) {
        // TODO Save the inputted form data to the model.
        String name = new String();
        String type = new String();
        String date = new String();
        String startTime = new String();
        String endTime = new String();

        if (nameTextField.getText() != "")
            name = nameTextField.getText();
        if (typeSelection.getSelectedToggle() != null)
            type = ((RadioButton)typeSelection.getSelectedToggle()).getText();
        if (calendarDatePicker.getValue() != null)
            date = calendarDatePicker.getValue().toString();
        if (startTimeTextField.getText() != "")
            startTime = startTimeTextField.getText();
        if (endTimeTextField.getText() != "")
            endTime = endTimeTextField.getText();

        System.out.println("Form data: ");
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);

        // TODO Determine type of data then save to model.
    }

    public void discardFormData(ActionEvent actionEvent) {
        // TODO Discard the inputted form data.
        nameTextField.setText("");
        calendarDatePicker.setValue(null);
        startTimeTextField.setText("");
        endTimeTextField.setText("");
    }

}
