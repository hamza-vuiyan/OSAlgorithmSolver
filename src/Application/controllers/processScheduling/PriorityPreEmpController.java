
package Application.controllers.processScheduling;

import Application.modelClass.processScheduling.PriorityPreEmpModel;
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
import java.util.stream.Collectors;

import static java.util.Collections.sort;
class ProcessPre {
    String job;
    int at;
    int bt;
    int priority;

    public ProcessPre (String job, int at, int bt, int priority) {
        this.job = job;
        this.at = at;
        this.bt = bt;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return job + " " + at + " " + bt + " " + priority;
    }
}

//class TableP{
//    String job;
//    int at;
//    int bt;
//    int priority;
//    int ct;
//    int tat;
//    int wt;
//
//    public TableP(String job, int at, int bt, int priority, int ct, int tat, int wt) {
//        this.job = job;
//        this.at = at;
//        this.bt = bt;
//        this.priority = priority;
//        this.ct = ct;
//        this.tat = tat;
//        this.wt= wt;
//    }
////    @Override
////    public String toString() {
////        return String.format("Job: %s, AT: %d, BT: %d, Priority: %d, CT: %d, TAT: %d, WT: %d",
////                job, at, bt, priority, ct, tat, wt);
////    }
//}


public class PriorityPreEmpController implements Initializable{
    @FXML
    private Button submitButton;
    @FXML
    private TextField arrivalTextfield;
    @FXML
    private TextField priorityTextfield;
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

    public  TableView <PriorityPreEmpModel> tableView;
    public  TableColumn<PriorityPreEmpModel, String> processCol;
    public  TableColumn<PriorityPreEmpModel, Integer> arrivalCol;
    public  TableColumn<PriorityPreEmpModel, Integer> burstCol;
    public  TableColumn<PriorityPreEmpModel, Integer> priorityCol;
    public  TableColumn<PriorityPreEmpModel, Integer> ctCol;
    public  TableColumn<PriorityPreEmpModel, Integer> tatCol;
    public  TableColumn<PriorityPreEmpModel, Integer> wtCol;

    ObservableList<PriorityPreEmpModel> observableList = FXCollections.observableArrayList(); // for putting values into table

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        processCol.setCellValueFactory(new PropertyValueFactory<>("Process"));
        arrivalCol.setCellValueFactory(new PropertyValueFactory<>("Arrival"));
        burstCol.setCellValueFactory(new PropertyValueFactory<>("Burst"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        ctCol.setCellValueFactory(new PropertyValueFactory<>("Completion"));
        tatCol.setCellValueFactory(new PropertyValueFactory<>("Turn"));
        wtCol.setCellValueFactory(new PropertyValueFactory<>("Waiting"));
        tableView.setItems(observableList);
    }

    public void submitInput(ActionEvent event) {

        Vector<Integer> arrivalArray;
        Vector<Integer> burstArray;
        Vector<Integer> tempburstArray;
        Vector<Integer> priorityArray;
        Vector<Integer> completionArray = new Vector<Integer>();
        Vector<Integer> grantChart = new Vector<Integer>();
        Vector<Integer> turnAround = new Vector<>();
        Vector<Integer> waiting = new Vector<>();

        //getting the input from text field
        String arrivalString = arrivalTextfield.getText();
        String burstString = burstTextfield.getText();
        String priorityString = priorityTextfield.getText();

        if(arrivalString.isEmpty()) {
            outputLabel.setText("Invalid Input!");
            return;
        }
        else if(burstString.isEmpty()) {
            outputLabel.setText("Invalid Input!");
            return;
        }
        else if(priorityString.isEmpty()) {
            outputLabel.setText("Invalid Input!");
            return;
        }

        String[] tokensArrival = arrivalString.split(" ");
        String[] tokensBurst = burstString.split(" ");
        String[] tokensPriority = priorityString.split(" ");

        //putting the inputs into arrival, burst, priority arrays
        arrivalArray = Arrays.stream(tokensArrival).map(Integer::parseInt).collect(Collectors.toCollection(Vector::new));
        burstArray = Arrays.stream(tokensBurst).map(Integer::parseInt).collect(Collectors.toCollection(Vector::new));
        priorityArray = Arrays.stream(tokensPriority).map(Integer::parseInt).collect(Collectors.toCollection(Vector::new));

        //checking incorrect format of inputs
        if(arrivalArray.size() != burstArray.size()) {
            outputLabel.setText("Invalid Input!");
            return;
        }

        tempburstArray = burstArray;

        waiting.setSize(arrivalArray.size());
        turnAround.setSize(arrivalArray.size());
        completionArray.setSize(arrivalArray.size());

       // grantChart.add(0);

        List<ProcessPre> processes = new ArrayList<>();
        List<Table> rows = new ArrayList<>();
        List<String> grantChartString = new ArrayList<>();

        for (int i = 0; i < arrivalArray.size(); i++) {
            String jobName = "P" + (i + 1);
            processes.add(new ProcessPre(jobName, arrivalArray.get(i), burstArray.get(i), priorityArray.get(i)));
        }

        processes.sort((p1, p2) -> Integer.compare(p1.at, p2.at));



        //System.out.println(processes);

//        System.out.println(arrivalArray);
//        System.out.println(burstArray);
//        System.out.println(priorityArray);

//------------MAIN OPERATION ----------

        int currentTime = 0;
        while (!processes.isEmpty()){
            List<ProcessPre> readyQueue = new ArrayList<>();
            for (ProcessPre p: processes){
                if(p.at <= currentTime){
                    readyQueue.add(p);
                }
            }
            if(readyQueue.isEmpty()){
                currentTime = processes.get(0).at;
                continue;
            }

            readyQueue.sort((p1, p2) -> Integer.compare(p1.priority, p2.priority));
            ProcessPre currentProcess = readyQueue.get(0);

            currentTime+=1;
            currentProcess.bt-=1;
            if (currentProcess.bt == 0) {
                rows.add(new Table(currentProcess.job, currentProcess.at, 0, currentProcess.priority, currentTime, 0, 0));
                grantChartString.add(currentProcess.job);
                processes.removeIf(p -> p.job.equals(currentProcess.job));

            } else {
                grantChartString.add(currentProcess.job);
            }
        }

        //  System.out.println(completionArray);
//
//        System.out.println(grantChartString);
//       System.out.println(completionArray);
//
//        // making the completion array
//        for(int i = 0;  i < grantChartString.size(); i++) {
//
//        }
//
//
//        Collections.sort(rows, new Comparator<Table>() {
//            @Override
//            public int compare(Table t1, Table t2) {
//                // Extract numeric part from job (e.g., P1 -> 1)
//                int num1 = Integer.parseInt(t1.job.substring(1));
//                int num2 = Integer.parseInt(t2.job.substring(1));
//                return Integer.compare(num1, num2);
//            }
//        });
//
////        String grantString = new String();
////        grantString = "";
////        outputLabel.setText("Result is shown on the table..");
////        for(int i = 0; i < grantChart.size(); i++){
////            grantString+=(String.valueOf(grantChart.get(i)));
////            grantString+=" ";
////        }
//
//     grantChartLabel.setText(String.valueOf(grantChartString));
//
//     observableList.clear();
//        for (Table row : rows) {
//            observableList.add(new PriorityPreEmpModel(row.job, row.at, row.bt, row.priority, row.ct, row.tat, row.wt));
//        }

    }
}


