package Application.controllers.diskScheduling;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Vector;

import static java.lang.Math.*;
import static java.util.Collections.max;

public class DiskFCFSController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/WelcomeScreen.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/DiskScheduling.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField queueInput;
    @FXML
    private TextField pointerInput;
    @FXML
    private Label output;

    Vector<Integer> queueArray = new Vector<Integer>();


    public void submitButton(ActionEvent actionEvent) {
        String headPointer = pointerInput.getText();
        String queue = queueInput.getText();
        if(headPointer.isEmpty()){
            output.setText("Invalid Input: Pointer is empty!");
            return;
        }
        else if(queue.isEmpty()){
            output.setText("Invalid Input: queue is empty!");
            return;
        }
        int pointer = Integer.parseInt(headPointer);
        String[] tokenQueue = queue.split(" ");
        for(String token: tokenQueue){
            queueArray.add(Integer.parseInt(token));
        }
        int seekDistance = abs(queueArray.elementAt(0) - pointer);
        for(int i = 1; i < queueArray.size(); i++){
            seekDistance+=abs(queueArray.elementAt(i)- queueArray.elementAt(i-1));
        }
        output.setText(String.valueOf(seekDistance));

        queueArray.clear();
    }
}
