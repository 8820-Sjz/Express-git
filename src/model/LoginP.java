package model;

import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginP extends GridPane{
	
	private GridPane gr = this;
	private Label name;
	private TextField tName;
	private Label password;
	private PasswordField pp;
	private Button btnLogin;
	private Button btnSign;
	private Client client = new Client();
	
	private Stage stage = new Stage();
	
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
		gr.setHgap(10);
		gr.setVgap(17);
		GridPane.setMargin(btnLogin, new Insets(30, 0, 0, 150));//top，right，bottom，left
		GridPane.setMargin(btnSign, new Insets(30, 0, 0, 50));//top，right，bottom，left
		gr.setAlignment(Pos.CENTER);
	}
	
	public void init() {
		name= new Label("\t\tID:");
		password = new Label("PASSWORD:");
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
					String s = client.login(request);
					System.out.println("登录结果:"+s);
					if(s.equals("true")) {
						
						newShow();
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
					String s = client.login(request);
					System.out.println("注册结果:"+s);
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
