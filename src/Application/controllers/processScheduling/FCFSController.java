package Application.controllers.processScheduling;

import Application.modelClass.processScheduling.FCFSModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.util.Collections.sort;


public class FCFSController implements Initializable {
    @FXML
    private Button submitButton;
    @FXML
    private TextField arrivalTextfield;
    @FXML
    private TextField burstTextfield;
    @FXML
    private Label outputLabel;
    @FXML
    private Label grantChartLabel;
    private  Stage stage;
    private  Scene scene;
    private Parent root;

    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/WelcomeScreen.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void backToProcess(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/ProcessScheduling.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public  TableView <FCFSModel> tableView;
    public  TableColumn<FCFSModel, Integer> processCol;
    public  TableColumn<FCFSModel, Integer> arrivalCol;
    public  TableColumn<FCFSModel, Integer> burstCol;
    public  TableColumn<FCFSModel, Integer> ctCol;
    public  TableColumn<FCFSModel, Integer> tatCol;
    public  TableColumn<FCFSModel, Integer> wtCol;

    ObservableList<FCFSModel> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        processCol.setCellValueFactory(new PropertyValueFactory<>("Process"));
        arrivalCol.setCellValueFactory(new PropertyValueFactory<>("Arrival"));
        burstCol.setCellValueFactory(new PropertyValueFactory<>("Burst"));
        ctCol.setCellValueFactory(new PropertyValueFactory<>("Completion"));
        tatCol.setCellValueFactory(new PropertyValueFactory<>("Turn"));
        wtCol.setCellValueFactory(new PropertyValueFactory<>("Waiting"));
        tableView.setItems(observableList);
    }


    public void submitInput(ActionEvent event) {
        String arrivalString;
        String burstString;
        Vector<Integer> arrivalArray = new Vector<Integer>();
        Vector<Integer> burstArray = new Vector<Integer>();
        Vector<Integer> completionArray = new Vector<Integer>();
        Vector<Integer> grantChart = new Vector<Integer>();
        Vector<Integer> turnAround = new Vector<>();
        Vector<Integer> waiting = new Vector<>();
        Vector<Integer> temp = new Vector<>(); // for sorting purposes

        arrivalString = arrivalTextfield.getText();
        burstString = burstTextfield.getText();
        if(arrivalString.isEmpty()) {
            outputLabel.setText("Invalid Input!");
            return;
        }
        else if(burstString.isEmpty()) {
            outputLabel.setText("Invalid Input!");
            return;
        }

        String[] tokensArrival = arrivalString.split(" ");
        String[] tokensBurst = burstString.split(" ");

        for(String token: tokensArrival){
            arrivalArray.add(Integer.parseInt(token));
        }
        for(String token: tokensBurst){
            burstArray.add(Integer.parseInt(token));
        }

        System.out.println(arrivalArray);
        System.out.println(burstArray);

        if(arrivalArray.size() != burstArray.size()) {
            outputLabel.setText("Invalid Input!");
            return;
        }
        waiting.setSize(arrivalArray.size());
        turnAround.setSize(arrivalArray.size());
        completionArray.setSize(arrivalArray.size());
        grantChart.setSize(arrivalArray.size()+1);

        outputLabel.setText("Processing...");

        for (int i = 0; i < arrivalArray.size(); i++) {
            temp.add(arrivalArray.get(i));
        }

        // sorting purposes
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            indices.add(i);
        }
        indices.sort((i1, i2) -> Integer.compare(temp.get(i1), temp.get(i2))); // sort the indices of temp vector according their values


        grantChart.set(0, 0);

        // making the completion array
        for(int i = 0; i < arrivalArray.size(); i++) {
            if(arrivalArray.get(indices.get(i)) > grantChart.get(i)) {
                grantChart.set(i+1, arrivalArray.get(indices.get(i)) +burstArray.get(indices.get(i)));
                completionArray.set(indices.get(i), arrivalArray.get(indices.get(i)) +burstArray.get(indices.get(i)));
            }
            else{
                grantChart.set(i+1, grantChart.get(i)+burstArray.get(indices.get(i)));
                completionArray.set(indices.get(i), grantChart.get(i)+burstArray.get(indices.get(i)));

            }
        }

        String grantString = new String();
        grantString = "";
        outputLabel.setText("Result is shown on the table..");
        for(int i = 0; i < grantChart.size(); i++){
            grantString+=(String.valueOf(grantChart.get(i)));
            grantString+=" ";
        }
        grantChartLabel.setText(grantString);

        // for calculating turn around times
        for(int i = 0; i < arrivalArray.size(); i++){
            turnAround.set(i, completionArray.get(i) - arrivalArray.get(i));
        }

        // for calculating waiting times
        for(int i = 0; i < arrivalArray.size(); i++){
            waiting.set(i, turnAround.get(i) - burstArray.get(i));
        }

        // putting the values into table
        observableList.clear();
        for(int i = 0; i < burstArray.size(); i++){
            observableList.add(new FCFSModel(i, arrivalArray.get(i), burstArray.get(i), completionArray.get(i), turnAround.get(i), waiting.get(i)));
        }

    }
}

