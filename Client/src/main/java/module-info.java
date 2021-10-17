module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
    requires javafx.graphics;

    opens com.example.client to javafx.fxml;
    exports com.example.client;
}
