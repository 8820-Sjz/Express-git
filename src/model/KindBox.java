package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class KindBox extends VBox {
    private VBox kb = this;
    private Button foodBut;
    private Button colthesBut;

    public KindBox() {
        buildKindBox();
    }
    private void buildKindBox() {

        foodBut = new Button("食品");
        foodBut.setPrefSize(60, 60);
        foodBut.setMinWidth(60);
        foodBut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
            }
        });
        colthesBut = new Button("服装");
        colthesBut.setPrefSize(60, 60);
        colthesBut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
            }
        });

        kb.getChildren().addAll(foodBut,colthesBut);
        kb.setPadding(new Insets(10,5,10,5));//设置子控件（与父级）上 右 下 左 的边距
        kb.setSpacing(10);//设置子控件之间的间距
        kb.setAlignment(Pos.CENTER);
    }
}
