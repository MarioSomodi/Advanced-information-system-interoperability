package com.msomodi.beers;

import javafx.fxml.FXML;
import java.net.URL;
import java.util.*;
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
    private TableView<Beer> beersTable;
    @FXML
    private TextField beerIdTextField;
    @FXML
    private TextField beerNameTextField;
    @FXML
    private TextField beerDescriptionTextField;
    @FXML
    private TextField beerAlcoholPercentageTextField;
    @FXML
    private TableColumn<Beer, Integer> idColumn;
    @FXML
    private TableColumn<Beer, String> nameColumn;
    @FXML
    private TableColumn<Beer, String> descriptionColumn;
    @FXML
    private TableColumn<Beer, Double> alcoholPercentageColumn;

    private <T> ResponseEntity<T> getResponseFromAPI(String restEndpointUrl, GenericClass<T> classToMap, HttpMethod method, T... objectToPostOrPut) {
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
                HttpEntity<T> requestEntity = new HttpEntity<T>(objectToPostOrPut[0], headers);
                hardwareArrayResponse = restTemplate.exchange(restEndpointUrl, method, requestEntity, classToMap.getMyType());
                break;
            }
        }
        return hardwareArrayResponse;
    }

    private Beer getBeerObjectFromInputs(){
        return new Beer(
                (!Objects.equals(beerIdTextField.getText(), "")) ? Integer.parseInt(beerIdTextField.getText()) : 0,
                beerNameTextField.getText(),
                beerDescriptionTextField.getText(),
                Double.parseDouble(beerAlcoholPercentageTextField.getText())
        );
    }

    private List<Beer> getBeer(){
        GenericClass<Beer[]> classToMapTo = new GenericClass<Beer[]>(Beer[].class);
        ResponseEntity<Beer[]> apiResponse = getResponseFromAPI("http://localhost:9191/beers", classToMapTo, HttpMethod.GET);

        return Arrays.asList(apiResponse.getBody());
    }

    @FXML
    protected void onGetAllButtonClick() {
        ObservableList<Beer> hardwareList = FXCollections.observableArrayList();
        hardwareList.addAll(getBeer());

        beersTable.setItems(hardwareList);
    }

    @FXML
    protected void onGetByIdButtonClick() {
        GenericClass<Beer> classToMapTo = new GenericClass<Beer>(Beer.class);
        ResponseEntity<Beer> apiResponse = getResponseFromAPI("http://localhost:9191/beer/" + beerIdTextField.getText(), classToMapTo, HttpMethod.GET);

        ObservableList<Beer> beersList = FXCollections.observableArrayList();
        List<Beer> beers = Arrays.asList(apiResponse.getBody());
        beersList.addAll(beers);

        beersTable.setItems(beersList);
    }

    @FXML
    protected void onDeleteButtonClick() {
        GenericClass<String> classToMapTo = new GenericClass<String>(String.class);
        ResponseEntity<String> apiResponse = getResponseFromAPI("http://localhost:9191/deleteBeer/" + beerIdTextField.getText(), classToMapTo, HttpMethod.DELETE);

        onGetAllButtonClick();
    }

    @FXML
    protected void onUpdateBeerButtonClick() {
        GenericClass<Beer> classToMapTo = new GenericClass<Beer>(Beer.class);
        ResponseEntity<Beer> apiResponse = getResponseFromAPI("http://localhost:9191/updateBeer", classToMapTo, HttpMethod.PUT, getBeerObjectFromInputs());

        onGetAllButtonClick();
    }

    @FXML
    protected void onCreateBeerButtonClick() {
        GenericClass<Beer> classToMapTo = new GenericClass<Beer>(Beer.class);
        ResponseEntity<Beer> apiResponse = getResponseFromAPI("http://localhost:9191/addBeer", classToMapTo, HttpMethod.POST, getBeerObjectFromInputs());

        onGetAllButtonClick();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Beer, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Beer, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Beer, String>("description"));
        alcoholPercentageColumn.setCellValueFactory(new PropertyValueFactory<Beer, Double>("alcoholPercentage"));
        onGetAllButtonClick();
    }
}
