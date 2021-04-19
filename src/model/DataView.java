package model;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import obj.SingleInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DataView extends TableView {
    private TableView tableView = this;
    private ArrayList<SingleInfo> singleInfos;
    private Client client;
    ArrayList<String> list ;
    
    public DataView() throws Exception  {
        singleInfos = new ArrayList<>();
        TableColumn<SingleInfo,Integer> CID = new TableColumn<>("Cid");
        CID.setCellValueFactory(new PropertyValueFactory<>("Cid"));
        CID.setMinWidth(100);

        TableColumn<SingleInfo,Integer> PID = new TableColumn<>("Pid");
        PID.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        PID.setMinWidth(100);

        TableColumn<SingleInfo,Integer> RID = new TableColumn<>("Rid");
        RID.setCellValueFactory(new PropertyValueFactory<>("Rid"));
        RID.setMinWidth(100);

        TableColumn<SingleInfo,String> CREATETIME = new TableColumn<>("createtime");
        CREATETIME.setCellValueFactory(new PropertyValueFactory<>("createtime"));
        CREATETIME.setMinWidth(200);

        TableColumn<SingleInfo,String> TIMELINESS = new TableColumn<>("timeliness");
        TIMELINESS.setCellValueFactory(new PropertyValueFactory<>("timeliness"));
        TIMELINESS.setMinWidth(100);

        TableColumn<SingleInfo,String> COMPANY = new TableColumn<>("company_name");
        COMPANY.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        COMPANY.setMinWidth(100);

        this.getColumns().addAll(CID,PID,RID,CREATETIME,TIMELINESS,COMPANY);

        showDatas(collectDatas(""));

        Stage stage = new Stage();
        this.setOnMousePressed(event -> {
            stage.close();
            if (event.getClickCount()==2){
//                AnchorPane root = new AnchorPane();
                DetailPane root = new DetailPane();
                Scene scene = new Scene(root,600,400);
                stage.setScene(scene);
                stage.setTitle("详细信息");
                stage.setX(600);
                stage.setY(400);
                stage.show();
            }
        });
    }

    public ArrayList<SingleInfo> collectDatas(String text) throws Exception{
        singleInfos.clear();
        try {
            client = new Client();
            if(text.equals("")) list = client.search("0");
            else list = client.search("1"+text);
            if(list.size()==0){
                tableView.setPlaceholder(new Label("No Search"));
                return singleInfos;
            }
        for (String string : list) {
        	String[] s = string.split(",");
        	int cid = Integer.parseInt(s[0]);
            int pid = Integer.parseInt(s[1]);
            int rid = Integer.parseInt(s[2]);
            String createtime = s[3];
            String timeliness = s[4];
            String companyName = s[5];
            SingleInfo singleInfo = new SingleInfo(cid,pid,rid,createtime,timeliness,companyName);
            singleInfos.add(singleInfo);
		}
        
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return singleInfos;
    }
    public void showDatas(ArrayList<SingleInfo> arrayList){
        for(SingleInfo a:arrayList){
            this.getItems().add(a);
        }
    }

    public ArrayList<SingleInfo> getSingleInfos() {
        return singleInfos;
    }
}