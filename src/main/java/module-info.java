module com.example.snackladder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snackladder to javafx.fxml;
    exports com.example.snackladder;
}