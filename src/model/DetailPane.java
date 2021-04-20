package model;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import obj.TransportInfo;
import sqlUtils.Search;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DetailPane extends BorderPane {
    private BorderPane borderPane = this;
    private TableView placeInfo;
    private GridPane centralGP;

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
        TableColumn<TransportInfo, String> currentPos = new TableColumn<>("Current Place");
        currentPos.setCellValueFactory(new PropertyValueFactory<>("currentPos"));
        currentPos.setPrefWidth(100);

        TableColumn<TransportInfo, String> previousPos = new TableColumn<>("Previous Place");
        previousPos.setCellValueFactory(new PropertyValueFactory<>("previousPos"));
        previousPos.setPrefWidth(100);

        TableColumn<TransportInfo, String> arrivalTime = new TableColumn<>("Arrival Time");
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime.setPrefWidth(100);

        TableColumn<TransportInfo, String> parcelStatus = new TableColumn<>("Parcel Status");
        parcelStatus.setCellValueFactory(new PropertyValueFactory<>("parcelStatus"));
        parcelStatus.setPrefWidth(100);

        TableColumn<TransportInfo, String> transportionWay = new TableColumn<>("Transportation");
        transportionWay.setCellValueFactory(new PropertyValueFactory<>("transportation"));
        transportionWay.setPrefWidth(200);

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
        type = new Label("type:");
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


        recipient = new Label("recipient:");

        recipientInfo = new TextField();

        rPhone = new Label("phone:");

        rPhoneInfo = new TextField();

        rPlace = new Label("place:");

        rPlaceInfo = new TextField();
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


}
