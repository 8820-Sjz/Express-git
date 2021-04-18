package model;

import javafx.geometry.Pos;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class InformationPane {
    private HBox informationBox = new HBox();
    private MenuButton cidMenu;
    private MenuButton pidMenu;
    private MenuButton ridMenu;
    private MenuButton createtimeMenu;
    private MenuButton timelinessMenu;

    public InformationPane() {

        informationBox.setAlignment(Pos.BASELINE_LEFT);

        MenuItem cidItem1 = new MenuItem("从大到小");
        MenuItem cidItem2 = new MenuItem("从小到大");
        cidMenu = new MenuButton("顾客的ID",null,cidItem1,cidItem2);

        MenuItem pidItem1 = new MenuItem("从大到小");
        MenuItem pidItem2 = new MenuItem("从小到大");
        pidMenu = new MenuButton("包裹的ID",null,pidItem1,pidItem2);


        MenuItem ridItem1 = new MenuItem("从大到小");
        MenuItem ridItem2 = new MenuItem("从小到大");
        ridMenu = new MenuButton("接受人的ID",null,ridItem1,ridItem2);

        MenuItem createtimeItem1 = new MenuItem("较新创建的订单");
        MenuItem createtimeItem2 = new MenuItem("较早创建的订单");
        createtimeMenu = new MenuButton("创建订单的时间",null,createtimeItem1,createtimeItem2);

        MenuItem timelinessItem1 = new MenuItem("较快结束的订单");
        MenuItem timelinessItem2 = new MenuItem("较慢结束的订单");
        timelinessMenu = new MenuButton("订单的期限",null,timelinessItem1,timelinessItem2);

        informationBox.getChildren().addAll(cidMenu,pidMenu,ridMenu,createtimeMenu,timelinessMenu);

    }

    public HBox info(){
        return informationBox;
    }
}
