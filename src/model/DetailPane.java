package model;


import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class DetailPane extends VBox {
    private VBox vBox = this;
    private Label PID;
    private Text Pid;
    private Text Rid;
    private Text Cid;

    public DetailPane() {
        this.setSpacing(10);
        PID = new Label("sda");
        Pid = new Text("包裹的ID");
        Rid = new Text("接受人的ID");
        Cid = new Text("顾客的ID");
        HBox hBox = new HBox();
        hBox.setSpacing(30);
        hBox.getChildren().addAll(PID,Pid);
        this.getChildren().addAll(hBox,Rid,Cid);

    }
}
