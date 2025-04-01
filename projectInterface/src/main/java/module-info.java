module projectinterface.projectinterface {
    requires javafx.controls;
    requires javafx.fxml;


    opens projectinterface.projectinterface to javafx.fxml;
    exports projectinterface.projectinterface;
}