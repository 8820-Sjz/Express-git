package model;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import obj.SingleInfo;
import obj.TransportInfo;

import java.util.ArrayList;


public class DetailPane extends BorderPane {
    private BorderPane borderPane = this;
    private TableView placeInfo;
    private GridPane centralGP;
    private ArrayList<String> saveList;
    private ArrayList<String> initList;

    private Label sender;
    private Label senderInfo;
    private Label sPhone;
    private TextField sPhoneInfo;
    private Label sPlace;
    private Label sPlaceInfo;
    private Label recipient;
    private TextField recipientInfo;
    private Label rPhone;
    private TextField rPhoneInfo;
    private Label rPlace;
    private TextField rPlaceInfo;

    private Label pid;
    private Label createtime;
    private Label type;
    private Label transportcompany;
    private Label pidInfo;
    private Label createtimeInfo;
    private Label typeInfo;
    private Label transportcompanyInfo;
    private Label destination;
    private Label destinationInfo;
    private Button saveButton;
    private Button restoreButton;


    public DetailPane() {
        init();
        centralGP.add(sender, 0, 0);
        centralGP.add(senderInfo, 1, 0);
        centralGP.add(sPhone, 0, 1);
        centralGP.add(sPhoneInfo, 1, 1);
        centralGP.add(sPlace, 0, 2);
        centralGP.add(sPlaceInfo, 1, 2);

        centralGP.add(recipient, 2, 0);
        centralGP.add(recipientInfo, 3, 0);
        centralGP.add(rPhone, 2, 1);
        centralGP.add(rPhoneInfo, 3, 1);
        centralGP.add(rPlace, 2, 2);
        centralGP.add(rPlaceInfo, 3, 2);

        centralGP.add(pid, 0, 3);
        centralGP.add(pidInfo, 1, 3);

        centralGP.add(restoreButton,2,6);
        centralGP.add(saveButton,3,6);

        centralGP.add(destination, 0, 4);
        centralGP.add(destinationInfo, 1, 4);
        centralGP.add(createtime, 2, 4);
        centralGP.add(createtimeInfo, 3, 4);
        centralGP.add(type, 0, 5);
        centralGP.add(typeInfo, 1, 5);
        centralGP.add(transportcompany, 2, 5);
        centralGP.add(transportcompanyInfo, 3, 5);

    }

    private void init() {
        saveList = new ArrayList<>();
        TableColumn<TransportInfo, String> currentPos = new TableColumn<>("Current Place");
        currentPos.setCellValueFactory(new PropertyValueFactory<>("currentPos"));
        currentPos.setPrefWidth(130);

        TableColumn<TransportInfo, String> previousPos = new TableColumn<>("Previous Place");
        previousPos.setCellValueFactory(new PropertyValueFactory<>("previousPos"));
        previousPos.setPrefWidth(130);

        TableColumn<TransportInfo, String> arrivalTime = new TableColumn<>("Arrival Time");
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime.setPrefWidth(100);

        TableColumn<TransportInfo, String> parcelStatus = new TableColumn<>("Parcel Status");
        parcelStatus.setCellValueFactory(new PropertyValueFactory<>("parcelStatus"));
        parcelStatus.setPrefWidth(130);

        TableColumn<TransportInfo, String> transportionWay = new TableColumn<>("Transportation");
        transportionWay.setCellValueFactory(new PropertyValueFactory<>("transportation"));
        transportionWay.setPrefWidth(200);

        saveButton = new Button("save");
        restoreButton = new Button("restore");
        placeInfo = new TableView();
        placeInfo.getColumns().addAll(currentPos, previousPos, arrivalTime, parcelStatus, transportionWay);
        centralGP = new GridPane();
        this.setCenter(centralGP);
        centralGP.setAlignment(Pos.CENTER);
        centralGP.setPadding(new Insets(10));
        centralGP.setHgap(10);
        centralGP.setVgap(10);
        centralGP.setStyle("-fx-background-color: antiquewhite");
        this.setBottom(placeInfo);
        placeInfo.setStyle("-fx-background-color: azure");
        destination = new Label("destination:");
        destinationInfo = new Label();
        pid = new Label("pid:");
        createtime = new Label("createtime:");
        type = new Label("goods:");
        transportcompany = new Label("transportcompany:");
        pidInfo = new Label();
        createtimeInfo = new Label();
        typeInfo = new Label();
        transportcompanyInfo = new Label();

        sender = new Label("sender:");
        senderInfo = new Label();

        sPhone = new Label("phone:");

        sPhoneInfo = new TextField();

        sPlace = new Label("place:");

        sPlaceInfo = new Label();


        recipient = new Label("ecipient:");

        recipientInfo = new TextField();

        rPhone = new Label("phone:");

        rPhoneInfo = new TextField();

        rPlace = new Label("place:");

        rPlaceInfo = new TextField();


        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveList.add(sPhoneInfo.getText());
                saveList.add(recipientInfo.getText());
                saveList.add(rPhoneInfo.getText());
                saveList.add(rPlaceInfo.getText());
                saveData(pidInfo.getText(),saveList);
            }
        });


        restoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                restore(initList);
            }
        });
    }

    public TableView getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(TableView placeInfo) {
        this.placeInfo = placeInfo;
    }

    public Label getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(Label senderInfo) {
        this.senderInfo = senderInfo;
    }

    public TextField getsPhoneInfo() {
        return sPhoneInfo;
    }

    public void setsPhoneInfo(TextField sPhoneInfo) {
        this.sPhoneInfo = sPhoneInfo;
    }


    public Label getsPlaceInfo() {
        return sPlaceInfo;
    }

    public void setsPlaceInfo(Label sPlaceInfo) {
        this.sPlaceInfo = sPlaceInfo;
    }

    public TextField getRecipientInfo() {
        return recipientInfo;
    }

    public void setRecipientInfo(TextField recipientInfo) {
        this.recipientInfo = recipientInfo;
    }

    public TextField getrPhoneInfo() {
        return rPhoneInfo;
    }

    public void setrPhoneInfo(TextField rPhoneInfo) {
        this.rPhoneInfo = rPhoneInfo;
    }

    public TextField getrPlaceInfo() {
        return rPlaceInfo;
    }

    public void setrPlaceInfo(TextField rPlaceInfo) {
        this.rPlaceInfo = rPlaceInfo;
    }

    public Label getPidInfo() {
        return pidInfo;
    }

    public void setPidInfo(Label pidInfo) {
        this.pidInfo = pidInfo;
    }

    public Label getCreatetimeInfo() {
        return createtimeInfo;
    }

    public void setCreatetimeInfo(Label createtimeInfo) {
        this.createtimeInfo = createtimeInfo;
    }

    public Label getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(Label typeInfo) {
        this.typeInfo = typeInfo;
    }

    public Label getTransportcompanyInfo() {
        return transportcompanyInfo;
    }

    public void setTransportcompanyInfo(Label transportcompanyInfo) {
        this.transportcompanyInfo = transportcompanyInfo;
    }

    public Label getDestinationInfo() {
        return destinationInfo;
    }

    public void setDestinationInfo(Label destinationInfo) {
        this.destinationInfo = destinationInfo;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    public Button getRestoreButton() {
        return restoreButton;
    }

    public void setRestoreButton(Button restoreButton) {
        this.restoreButton = restoreButton;
    }

    public void restore(ArrayList<String>initList){
        for(String each:initList){
            System.out.println(each);
        }
        sPhoneInfo.setText(initList.get(0));
        recipientInfo.setText(initList.get(1));
        rPhoneInfo.setText(initList.get(2));
        rPlaceInfo.setText(initList.get(3));
    }

    public ArrayList<String> getInitList() {
        return initList;
    }

    public void setInitList(ArrayList<String> initList) {
        this.initList = initList;
    }

    public void saveData(String pid,ArrayList<String> res){
        String sql1 = "UPDATE reciever NATURAL JOIN delivery NATURAL JOIN packages " +
                "SET reciever_name = '"+res.get(1)+"',phone = '"+res.get(2)+"' WHERE Pid = "+pid;
        String sql2 = "UPDATE packages NATURAL JOIN orders NATURAL JOIN customer " +
                "SET phone = '"+res.get(0)+"' WHERE Pid = "+pid;
        String sql3 = "UPDATE packages NATURAL JOIN pdetail NATURAL JOIN detail " +
                "SET destination = '"+res.get(3)+"' WHERE Pid = "+pid;
        try {
            Client client = new Client();
            String line = "true";
            line = client.changeData(sql1);
            line = client.changeData(sql2);
            line = client.changeData(sql3);
            Alert alert;
            if(line.equals("true")){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("success to save !");
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("fail to save !");
            }
            alert.show();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
