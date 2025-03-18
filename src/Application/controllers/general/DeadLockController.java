package Application.controllers.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class DeadLockController {
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

//
//    @FXML
//    private TextField rowInput;
//    @FXML
//    private TextField colInput;
//    @FXML
//    private TextField allocated;
//    @FXML
//    private TextField maximum;
//
//    int row, col;
//    public void submitRC(ActionEvent event) {
//        String rowS = rowInput.getText().trim();
//        String colS = colInput.getText().trim();
//        row = Integer.parseInt(rowS);
//        col = Integer.parseInt(colS);
//    }
//
//    Vector<Vector<Integer>> matAllocated = new Vector<>();
//    int counter = 0;
//    public void submitAllocated(ActionEvent event) {
//        if(counter > row) return;
//        String allRow = allocated.getText().trim();
//        for(int i = 0; i < row; i++){}
//
//        counter++;
//    }
}
