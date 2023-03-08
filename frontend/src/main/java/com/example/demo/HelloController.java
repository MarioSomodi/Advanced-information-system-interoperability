package com.example.demo;

import hr.algebra.dujmovic.confapp.model.Hardware;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Hardware> hardwareTable;

    @FXML
    private TableColumn<Hardware, String> nameColumn;

    @FXML
    private TableColumn<Hardware, String> priceColumn;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.ALL);
        converter.setSupportedMediaTypes(mediaTypes);
        messageConverters.add(converter);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);

        String restEndpointUrl = "http://localhost:8081/hardware";

        ResponseEntity<Hardware[]> hardwareArrayResponse =
                restTemplate.getForEntity(restEndpointUrl, Hardware[].class);

        for(Hardware hardware : hardwareArrayResponse.getBody()) {
            System.out.println("Hardware name: " + hardware.getName());
            System.out.println("Hardware type: " + hardware.getPrice());
        }

        ObservableList<Hardware> hardwareList = FXCollections.observableArrayList();
        Hardware[] hardwareArray = hardwareArrayResponse.getBody();
        List<Hardware> hardwares = Arrays.asList(hardwareArray);
        hardwareList.addAll(hardwares);

        hardwareTable.setItems(hardwareList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Hardware, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Hardware, String>("price"));
    }
}
