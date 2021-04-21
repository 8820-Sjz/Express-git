package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AmendPane extends Alert {
    private Alert alert = this;

    public AmendPane(AlertType alertType) {
        super(alertType);
        this.setHeaderText("Do you want to save?");
        this.setContentText("Save the changes you just made!");
        Optional<ButtonType> result = this.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("success");
        }
    }
}
