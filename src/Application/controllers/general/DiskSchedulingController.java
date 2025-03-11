package Application.controllers.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DiskSchedulingController {
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

    public void goToFCFS(ActionEvent event) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/DiskSchedulingUi/DiskFCFS.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public void goToSSTF(ActionEvent event) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/DiskSchedulingUi/SSTF.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void goToSCAN(ActionEvent event) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/DiskSchedulingUi/SCAN.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void goToCSCAN(ActionEvent event) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/DiskSchedulingUi/CSCAN.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void goToLOOK(ActionEvent event) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/DiskSchedulingUi/LOOK.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void goToCLOOK(ActionEvent event) throws IOException {
        try{
            root = FXMLLoader.load(getClass().getResource("/Application/resources/fxmls/DiskSchedulingUi/CLOOK.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
