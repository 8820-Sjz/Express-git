package model;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import obj.SingleInfo;

public class DataPane extends BorderPane{

    private BorderPane bp = this;
    private HBox toolBox;//顶部工具栏
    private TextField txf;//搜索栏;
    private Button searchBut;
    private Button freshBut;
    private DataView dataView;

    public DataPane() throws Exception {
        buildtoolBox();
        BorderPane borderPane = new BorderPane();
        dataView =new DataView();
//        borderPane.setTop(new InformationPane().info());
        borderPane.setCenter(dataView);
        bp.setCenter(borderPane);
        bp.setTop(toolBox);

    }
    private void buildtoolBox() {
        toolBox = new HBox();

        txf = new TextField();
        txf.setPromptText("例如：关键字 = 查询内容");
//        txf.setOnKeyPressed(event -> {
//            txf.deleteText(0,txf.getText().length());
//            System.out.println(1);
//        });

        searchBut = new Button("搜索");
        searchBut.setMinWidth(50);
        searchBut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                for(SingleInfo a : dataView.getSingleInfos()){
                    dataView.getItems().remove(a);
                }
                String text = txf.getText();
                System.out.println(text);
                try {
					dataView.showDatas(dataView.collectDatas(text));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        freshBut = new Button("刷新");
        freshBut.setMinWidth(50);
        freshBut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
            	for(SingleInfo a : dataView.getSingleInfos()){
                    dataView.getItems().remove(a);
                }
                try {
					dataView.showDatas(dataView.collectDatas(""));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        toolBox.getChildren().addAll(txf,searchBut,freshBut);
        toolBox.setHgrow(txf, Priority.ALWAYS);
        toolBox.setPadding(new Insets(10,10,10,10));//设置子控件（与父级）上 右 下 左 的边距
    }
}
