package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainView extends SplitPane{

    private SplitPane sp = this;
    private KindBox kindBox;
    private DataPane dataPane;

    public MainView() throws Exception{
        kindBox = new KindBox();
        dataPane = new DataPane();
        sp.getItems().addAll(kindBox,dataPane);
        sp.setDividerPosition(0, 0.08);
    }


    private void init() {

    }
}
