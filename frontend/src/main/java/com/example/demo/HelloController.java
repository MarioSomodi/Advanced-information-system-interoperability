package com.example.demo;

import hr.algebra.dujmovic.confapp.model.Hardware;
import hr.algebra.dujmovic.confapp.model.Type;
import javafx.fxml.FXML;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HelloController implements Initializable {

    @FXML
    private TableView<Hardware> hardwareTable;
    @FXML
    private TextField hardwareCodeTextField;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField hardwareNameTextField;
    @FXML
    private TextField hardwareIdTextField;
    @FXML
    private TextField hardwareStockTextField;
    @FXML
    private TextField hardwarePriceTextField;
    @FXML
    private ComboBox<Type> typeComboBox;
    @FXML
    private TableColumn<Hardware, String> nameColumn;
    @FXML
    private TableColumn<Hardware, String> priceColumn;
    @FXML
    private TableColumn<Hardware, Type> typeColumn;
    @FXML
    private TableColumn<Hardware, Long> stockColumn;
    @FXML
    private TableColumn<Hardware, String> codeColumn;
    @FXML
    private TableColumn<Hardware, Long> idColumn;

    private <T> ResponseEntity<T> getResponseFromAPI(String restEndpointUrl, GenericClass<T> classToMap, HttpMethod method, Hardware... hardware) {
        warningLabel.setText("");
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.ALL);
        converter.setSupportedMediaTypes(mediaTypes);
        messageConverters.add(converter);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> hardwareArrayResponse = new ResponseEntity<T>(HttpStatus.OK);
        restTemplate.setMessageConverters(messageConverters);

        switch(method){
            case GET:{
                hardwareArrayResponse = restTemplate.getForEntity(restEndpointUrl, classToMap.getMyType());
                break;
            }
            case DELETE:{
                restTemplate.delete(restEndpointUrl);
                break;
            }
            case PUT, POST:{
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Hardware> requestEntity = new HttpEntity<Hardware>(hardware[0], headers);
                hardwareArrayResponse = restTemplate.exchange(restEndpointUrl, method, requestEntity, classToMap.getMyType());
                break;
            }
        }
        return hardwareArrayResponse;
    }

    private Hardware getHardwareObjectFromInputs(){
        return new Hardware(
                null,
                hardwareNameTextField.getText(),
                typeComboBox.getValue(),
                hardwareCodeTextField.getText(),
                Long.parseLong(hardwareStockTextField.getText()),
                new BigDecimal(hardwarePriceTextField.getText())
        );
    }

    @FXML
    protected void onGetAllButtonClick() {
        GenericClass<Hardware[]> classToMapTo = new GenericClass<Hardware[]>(Hardware[].class);
        ResponseEntity<Hardware[]> apiResponse = getResponseFromAPI("http://localhost:8081/hardware", classToMapTo, HttpMethod.GET);

        ObservableList<Hardware> hardwareList = FXCollections.observableArrayList();
        List<Hardware> hardwareObjects = Arrays.asList(apiResponse.getBody());
        hardwareList.addAll(hardwareObjects);

        hardwareTable.setItems(hardwareList);
    }

    @FXML
    protected void onGetByCodeButtonClick() {
        GenericClass<Hardware> classToMapTo = new GenericClass<Hardware>(Hardware.class);
        ResponseEntity<Hardware> apiResponse = getResponseFromAPI("http://localhost:8081/hardware/" + hardwareCodeTextField.getText(), classToMapTo, HttpMethod.GET);

        ObservableList<Hardware> hardwareList = FXCollections.observableArrayList();
        List<Hardware> hardwareObjects = Arrays.asList(apiResponse.getBody());
        hardwareList.addAll(hardwareObjects);

        hardwareTable.setItems(hardwareList);
    }
    @FXML
    protected void onGetByIDButtonClick() {
        GenericClass<Hardware[]> classToMapTo = new GenericClass<Hardware[]>(Hardware[].class);
        ResponseEntity<Hardware[]> apiResponse1 = getResponseFromAPI("http://localhost:8081/hardware", classToMapTo, HttpMethod.GET);

        List<Hardware> hardwareObjects = Arrays.asList(apiResponse1.getBody());
        GenericClass<Hardware> classToMapTo2 = new GenericClass<Hardware>(Hardware.class);
       try {
           ResponseEntity<Hardware> apiResponse2;
           apiResponse2 = getResponseFromAPI("http://localhost:8081/hardware/" +
                   hardwareObjects.stream().filter(hardware -> hardware.getId() == Long.parseLong(hardwareIdTextField.getText())).findFirst().orElse(null).getCode(), classToMapTo2, HttpMethod.GET);
           ObservableList<Hardware> foundHardware = FXCollections.observableArrayList();
           List<Hardware> foundHardwareObjects = Arrays.asList(apiResponse2.getBody());
           foundHardware.addAll(foundHardwareObjects);

           hardwareTable.setItems(foundHardware);
       }catch(Exception e){
           warningLabel.setText("Hardware with that ID does not exist");
       }
    }

    @FXML
    protected void onDeleteButtonClick() {
        GenericClass<String> classToMapTo = new GenericClass<String>(String.class);
        ResponseEntity<String> apiResponse = getResponseFromAPI("http://localhost:8081/hardware/" + hardwareCodeTextField.getText(), classToMapTo, HttpMethod.DELETE);

        onGetAllButtonClick();
    }

    @FXML
    protected void onUpdateHardwareButtonClick() {
        GenericClass<Hardware> classToMapTo = new GenericClass<Hardware>(Hardware.class);
        ResponseEntity<Hardware> apiResponse = getResponseFromAPI("http://localhost:8081/hardware/" + hardwareCodeTextField.getText(), classToMapTo, HttpMethod.PUT, getHardwareObjectFromInputs());

        onGetAllButtonClick();
    }

    @FXML
    protected void onCreateHardwareButtonClick() {
        GenericClass<Hardware> classToMapTo = new GenericClass<Hardware>(Hardware.class);
        ResponseEntity<Hardware> apiResponse = getResponseFromAPI("http://localhost:8081/hardware/", classToMapTo, HttpMethod.POST, getHardwareObjectFromInputs());

        onGetAllButtonClick();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pattern charLimit = Pattern.compile(".{0,7}");
        TextFormatter charLimitFormatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> charLimit.matcher(change.getControlNewText()).matches() ? change : null);
        hardwareCodeTextField.setTextFormatter(charLimitFormatter);
        idColumn.setCellValueFactory(new PropertyValueFactory<Hardware, Long>("id"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<Hardware, String>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Hardware, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Hardware, String>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Hardware, Type>("type"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<Hardware, Long>("stock"));
        typeComboBox.getItems().setAll(Type.values());
    }
}
