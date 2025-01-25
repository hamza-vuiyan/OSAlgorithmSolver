package Application.controllers.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProcessSchedulingController {
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

    public void switchFCFS(ActionEvent event) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/processSchedulingUi/FCFS.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public void switchPriNon(ActionEvent event) {
        try{
            root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/processSchedulingUi/PriorityNonPre.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    // for going to page priority scheduling non pre-emtive
    public void switchPri(ActionEvent event) {
        try{
            root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/processSchedulingUi/PriorityPre.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }
    }



}
