module projectinterface.projectinterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens projectinterface to javafx.fxml;
    exports projectinterface;
}