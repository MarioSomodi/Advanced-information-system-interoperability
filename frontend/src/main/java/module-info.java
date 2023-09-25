module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    opens com.msomodi.beers to javafx.fxml;
    exports com.msomodi.beers;
}
