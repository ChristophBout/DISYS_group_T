module uiapp.uiapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.incubator.vector;


    opens uiapp.uiapp to javafx.fxml;
    exports uiapp.uiapp;
}