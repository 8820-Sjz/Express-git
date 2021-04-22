package model;

import java.sql.Connection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginP extends BorderPane{

	private GridPane gr = new GridPane();
	private Label name;
	private TextField tName;
	private Label password;
	private PasswordField pp;
	private Button btnLogin;
	private Button btnSign;
	private RadioButton rb;
	public static boolean isRoot = false;

	private Client client = new Client();

	private Stage stage = new Stage();

	private Label log;
	private HBox top;

	public LoginP() {
		init();
		init2();
	}

	public void init2() {
		gr.add(name, 0, 0);
		gr.add(tName, 1, 0);
		gr.add(password, 0, 1);
		gr.add(pp, 1, 1);
		gr.add(btnLogin, 1, 2);
		gr.add(btnSign, 0, 2);
        gr.add(rb, 1, 3);
		gr.setHgap(10);
		gr.setVgap(17);
		GridPane.setMargin(btnLogin, new Insets(30, 0, 0, 150));//top，right，bottom，left
        GridPane.setMargin(rb, new Insets(10, 0, 0, 150));//top，right，bottom，left
		GridPane.setMargin(btnSign, new Insets(30, 0, 0, 50));//top，right，bottom，left
		gr.setAlignment(Pos.CENTER);

		top.getChildren().add(log);
		top.setAlignment(Pos.CENTER);
		this.setCenter(gr);
		this.setTop(top);
		BorderPane.setMargin(top, new Insets(30, 0, 0, 0));
	}

	public void init() {

        log = new Label("登录");
        log.setFont(new Font(40));
        top = new HBox();
        rb = new RadioButton("root");
        rb.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                isRoot = newValue;
                System.out.println("Root: "+isRoot);
            }
        });


		name= new Label("\t\tID:");
		password = new Label("PASSWORD:");
		log = new Label("登录");
		log.setFont(new Font(33));
		top = new HBox();
		tName = new TextField();
		pp = new PasswordField();

		//登录按钮
		btnLogin = new Button("login");
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String id = tName.getText();
				String pass = pp.getText();
				String request = "2"+id+","+pass;
				try {
					String s = client.login(request,isRoot);
					System.out.println("登录结果:"+s);
					if(s!=null && s.equals("true")) {
						Client.cid = id;
						newShow();
					}else {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setContentText("账号/密码错误!");
						alert.show();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		//注册按钮
		btnSign = new Button("sign");
		btnSign.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String id = tName.getText();
				String pass = pp.getText();
				String request = "3"+id+","+pass;
				try {
					String s = client.login(request,isRoot);
					System.out.println("注册结果:"+s);

					if(s.equals("true")) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setContentText("注册成功!");
						alert.show();
					}else {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setContentText("账号已存在!");
						alert.show();
					}
					//if(s.equals("true")) {newShow();}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


	}


	public void newShow() throws Exception {
		SplitPane root = new MainView();
		Scene scene = new Scene(root,980,800);
		stage.setScene(scene);
		stage.setTitle("Express");
		stage.setMinWidth(980);
		stage.setMinHeight(800);
		stage.setResizable(false);
		stage.show();
	}

}
