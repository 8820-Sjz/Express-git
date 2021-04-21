package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import obj.SingleInfo;
import obj.TransportInfo;
import sqlUtils.Search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DataView extends TableView {
    private TableView tableView = this;
    private ArrayList<SingleInfo> singleInfos;
    private Client client;
    private DetailPane root;
    private SingleInfo currentInfo;
    ArrayList<String> list;

    public DataView() throws Exception {
        singleInfos = new ArrayList<>();
        TableColumn<SingleInfo, Integer> CID = new TableColumn<>("Cid");
        CID.setCellValueFactory(new PropertyValueFactory<>("Cid"));
        CID.setMinWidth(100);

        TableColumn<SingleInfo, Integer> PID = new TableColumn<>("Pid");
        PID.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        PID.setMinWidth(100);

        TableColumn<SingleInfo, Integer> RID = new TableColumn<>("Rid");
        RID.setCellValueFactory(new PropertyValueFactory<>("Rid"));
        RID.setMinWidth(100);

        TableColumn<SingleInfo, String> CREATETIME = new TableColumn<>("createtime");
        CREATETIME.setCellValueFactory(new PropertyValueFactory<>("createtime"));
        CREATETIME.setMinWidth(200);

        TableColumn<SingleInfo, String> TIMELINESS = new TableColumn<>("timeliness");
        TIMELINESS.setCellValueFactory(new PropertyValueFactory<>("timeliness"));
        TIMELINESS.setMinWidth(100);

        TableColumn<SingleInfo, String> COMPANY = new TableColumn<>("company_name");
        COMPANY.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        COMPANY.setMinWidth(100);

        this.getColumns().addAll(CID, PID, RID, CREATETIME, TIMELINESS, COMPANY);

        showDatas(collectDatas(""));

        Stage stage = new Stage();
        this.setOnMousePressed(event -> {
            stage.close();
            if (event.getClickCount() == 2) {
                ArrayList<String> initList = new ArrayList<>();
//                AnchorPane root = new AnchorPane();
                root = new DetailPane();
                root.getPidInfo().setText(Integer.toString(currentInfo.getPid()));
                root.getCreatetimeInfo().setText(currentInfo.getCreatetime());
                root.getTransportcompanyInfo().setText(currentInfo.getCompanyName());
                try {
                    ArrayList<String> res = new ArrayList<>();
                    ArrayList<TransportInfo> outcome = new ArrayList<>();
                    for (String info : client.corespondingData(currentInfo.getPid(),"1")) {
                        res.add(info);
                    }
                    for (String info : client.corespondingData(currentInfo.getPid(),"2")) {
                        res.add(info);
                    }
                    for (String info : client.corespondingData(currentInfo.getPid(),"3")) {
                        res.add(info);
                    }
                    root.getPlaceInfo().getItems().add(client.corespondingData4(currentInfo.getPid()));
                    root.getDestinationInfo().setText(res.get(0));
                    root.getrPlaceInfo().setText(res.get(0));
                    root.getTypeInfo().setText(res.get(1));
                    root.getRecipientInfo().setText(res.get(2));
                    root.getrPhoneInfo().setText(res.get(3));
                    root.getSenderInfo().setText(res.get(4));
                    root.getsPhoneInfo().setText(res.get(5));
                    root.getsPlaceInfo().setText(res.get(6));
                    initList.add(res.get(5));
                    initList.add(res.get(2));
                    initList.add(res.get(3));
                    initList.add(res.get(0));
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
                root.setInitList(initList);
                Scene scene = new Scene(root, 600, 400);
                stage.setScene(scene);
                stage.setTitle("详细信息");
                stage.show();
            }
        });
        this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SingleInfo>() {
            @Override
            public void changed(ObservableValue<? extends SingleInfo> observable, SingleInfo oldValue, SingleInfo newValue) {
                currentInfo = newValue;
            }
        });
    }


    public ArrayList<String> function(DetailPane root) {
        ArrayList<String> res = new ArrayList<>();
        res.add(root.getsPhoneInfo().getText());
        res.add(root.getRecipientInfo().getText());
        res.add(root.getrPhoneInfo().getText());
        res.add(root.getrPlaceInfo().getText());
        return res;
    }

    public ArrayList<SingleInfo> collectDatas(String text) throws Exception {
        singleInfos.clear();
        try {
            client = new Client();
            if (text.equals("")) list = client.search("0");
            else list = client.search("1" + text);
            if (list.size() == 0) {
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
                SingleInfo singleInfo = new SingleInfo(cid, pid, rid, createtime, timeliness, companyName);
                singleInfos.add(singleInfo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return singleInfos;
    }

    public void showDatas(ArrayList<SingleInfo> arrayList) {
        for (SingleInfo a : arrayList) {
            this.getItems().add(a);
        }
    }

    public ArrayList<SingleInfo> getSingleInfos() {
        return singleInfos;
    }
}
