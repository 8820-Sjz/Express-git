package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Client;

public class OrderP extends GridPane {
	private GridPane gr = this;
	private Button btnAdd;//下单
	private Button btnMoney;//算钱

	private TextField texConsignee;
	private TextField texPhone;
	private TextField texAddress;
	private TextField texGoods;
	private TextField texWeight;
	private TextField texValue;

	private RadioButton rbSetKind1;//small box
	private RadioButton rbSetKind2;//larger boxes
	private RadioButton rbSetKind3;//flat envelope
	private RadioButton rbTime1;//overnight
	private RadioButton rbTime2;//second day
	private RadioButton rbTime3;//third day

	private ToggleGroup tg1;
	private ToggleGroup tg2;

	private Label[] label;

	private double money = 0;
	public double weight;//向上取整值
	private int packing=0;
	private int deadline=2;

	private String[] set = {"small box","larger boxes","flat envelope"};
	private String[] time = {"overnight","second day","third day"};

	private Client client = new Client();

	public ArrayList<String> list = new ArrayList<>();

	public OrderP() {
		init();
		init2();
	}

	public void init2() {
		gr.add(label[0],0,0);gr.add(texConsignee,1,0);
		gr.add(label[1],0,1);gr.add(texPhone,1,1);
		gr.add(label[2],0,2);gr.add(texAddress,1,2);
		gr.add(label[3],0,3);gr.add(texGoods,1,3);
		gr.add(label[4],0,4);gr.add(texWeight,1,4);
		gr.add(label[5],0,5);gr.add(texValue,1,5);
		gr.add(label[6],0,6);gr.add(rbSetKind1,1,6);gr.add(rbSetKind2,2,6);gr.add(rbSetKind3,3,6);
		gr.add(label[7],0,7);gr.add(rbTime1,1,7);gr.add(rbTime2,2,7);gr.add(rbTime3,3,7);

		gr.add(btnMoney, 1, 8);
		gr.add(btnAdd, 2, 8);

		GridPane.setMargin(btnMoney, new Insets(30, 0, 0, 50));
		GridPane.setMargin(btnAdd, new Insets(30, 0, 0, 125));

		gr.setHgap(10);
		gr.setVgap(17);
		gr.setAlignment(Pos.CENTER);
	}

	public void init() {
		label = new Label[8];
		label[0] = new Label("Consignee :");
		label[1] = new Label("Phone :");
		label[2] = new Label("Address :");
		label[3] = new Label("Goods :");
		label[4] = new Label("Weight :");
		label[5] = new Label("Value :");
		label[6] = new Label("Packing :");//SetKind
		label[7] = new Label("Deadline :");//Timeliness


		texConsignee = new TextField();
		texPhone = new TextField();
		texAddress = new TextField();
		texGoods = new TextField();
		texWeight = new TextField();
		texValue = new TextField();
		texWeight.setPromptText("kg");
		texValue.setPromptText("( $ _ $ )");

		tg1 = new ToggleGroup();
		rbSetKind1 = new RadioButton("small box");
		rbSetKind2 = new RadioButton("larger boxes");
		rbSetKind3 = new RadioButton("flat envelope");
		rbSetKind1.setToggleGroup(tg1);
		rbSetKind2.setToggleGroup(tg1);
		rbSetKind3.setToggleGroup(tg1);

		tg1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				RadioButton setKind = (RadioButton)newValue;
				if(setKind.getText().equals(set[0])) packing = 0;
				if(setKind.getText().equals(set[1])) packing = 1;
				if(setKind.getText().equals(set[2])) packing = 2;
//				System.out.println("packing: "+packing);
			}
		});


		tg2 = new ToggleGroup();
		rbTime1 = new RadioButton("overnight");
		rbTime2 = new RadioButton("second day");
		rbTime3 = new RadioButton("third day");
		rbTime1.setToggleGroup(tg2);
		rbTime2.setToggleGroup(tg2);
		rbTime3.setToggleGroup(tg2);

		tg2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				RadioButton deadtime = (RadioButton)newValue;
				if(deadtime.getText().equals(time[0])) deadline = 0;
				if(deadtime.getText().equals(time[1])) deadline = 1;
				if(deadtime.getText().equals(time[2])) deadline = 2;
//				System.out.println("deadline: "+deadline);
			}
		});


		btnAdd = new Button("Add");//下单
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(isFull()==true) {
					calculate();
					if(money!=0) {
						list.clear();
						getData();
						for (String s:list) {
							System.out.println(s);
						}
						try {
							String s = client.newOrder(list);
							System.out.println("下单结果是: "+s);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else{
				    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("error input !");
                    alert.show();
                }
			}
		});

		btnMoney = new Button("Count");//算钱
		btnMoney.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				calculate();
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("express charges : "+money);
				alert.show();
				System.out.println(weight+" kg---"+set[packing]+"---"+time[deadline]+" : "+money+"元");
				//System.out.println(texConsignee.getText()==null);
			}
		});

	}

	//判断输入框是否为空
	public boolean isFull() {
		if(texConsignee.getText().length()<=0)return false;
		if(texAddress.getText().length()<=0)return false;
		if(texGoods.getText().length()<=0)return false;
		if(texPhone.getText().length()<=0)return false;
		if(texWeight.getText().length()<=0)return false;
		return true;
	}

	//判断字符串是否为纯数字
	public boolean isDigit(String s) {
	    boolean ifTrue = true;
		if(s.length()<=0)ifTrue = false;
		for(int i=0; i<s.length(); i++) {
			if(!Character.isDigit(s.charAt(i)) && s.charAt(i)!='.') {
				ifTrue = false;
				break;
			}
		}
		if(!ifTrue){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("error input !");
            alert.show();
        }
		return true;

	}

	//取数据
	public void getData() {
		String obj = texGoods.getText()+","+texValue.getText()+","+set[packing]+","+
				texWeight.getText()+","+nowTime()+","+time[deadline];
		String receiver = texConsignee.getText()+","+texPhone.getText();
		String destinaton = texAddress.getText();
		String mon = money+"";
		list.add(obj);
		list.add(receiver);
		list.add(destinaton);
		list.add(mon);
		list.add(Client.cid);
	}

	//算钱
	public void calculate() {
		String s =texWeight.getText();
		weight=0;
		if(isDigit(s)==true) {
			weight = Math.ceil(Double.parseDouble(s));
			money = weight*8.0;
			if(packing==1)money+=8;
			if(deadline==0)money+=20;
			else if(deadline==1)money+=4;
		}
	}

	//获取当前时间
	public String nowTime() {
		String format = "yyyy-MM-dd HH:mm:ss.SS";//2018-03-10 01:53:55.63
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String now = simpleDateFormat.format(System.currentTimeMillis());
		return now;
	}
	
	
}
