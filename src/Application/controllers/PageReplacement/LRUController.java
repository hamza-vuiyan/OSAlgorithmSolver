package Application.controllers.PageReplacement;

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
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class LRUController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/PageReplacement.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/WelcomeScreen.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField frameInput;
    @FXML
    private TextField pagerefInput;
    @FXML
    private Label output;
    @FXML
    private Label miss;
    @FXML
    private Label ratio;

    Vector<Integer> pageRefArray = new Vector<Integer>();


    public void submitButton(ActionEvent event)  {
        try {
            String frames = frameInput.getText();
            String references = pagerefInput.getText();
            if (frames.isEmpty()) {
                output.setText("Invalid Input: frames size is empty!");
                return;
            } else if (references.isEmpty()) {
                output.setText("Invalid Input: references is empty!");
                return;
            }

            String[] tokenReferences = references.split(" ");

            int frameNumber = Integer.parseInt(frames);

            for (String token : tokenReferences) {
                pageRefArray.add(Integer.parseInt(token));
            }

//            ------------Main Operation ------------

            int hitCount = 0;
            int lastIndex = 0;

            Queue<Integer> ramQ = new LinkedList<>();

            for (int i = 0; i < pageRefArray.size() && ramQ.size() < frameNumber; i++) {
                System.out.println(ramQ);
                    lastIndex = i;
                    if(ramQ.contains(pageRefArray.get(i))){
                        hitCount++;
                        ramQ.remove(pageRefArray.get(i));
                        ramQ.add(pageRefArray.get(i));
                    }
                    else {
                        ramQ.add(pageRefArray.get(i));
                    }
            }

            for (int i = lastIndex+1; i < pageRefArray.size(); i++) {
                System.out.println(ramQ);
                if (ramQ.contains(pageRefArray.get(i))) {
                    hitCount++;
                    ramQ.remove(pageRefArray.get(i));
                    ramQ.add(pageRefArray.get(i));
                } else {
                    ramQ.poll();
                    ramQ.add(pageRefArray.get(i));
                }
            }
            System.out.println(ramQ);

//            ----------output Section-------------

            output.setText(String.valueOf(hitCount));
            miss.setText(String.valueOf(pageRefArray.size()-hitCount));
            ratio.setText(String.valueOf((double) hitCount/(double) pageRefArray.size()));

            pageRefArray.clear();
        }catch (Exception e) {
            System.out.println(e);
        }

    }
}
