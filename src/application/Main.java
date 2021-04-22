package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.LoginP;
import model.MainView;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            LoginP login = new LoginP();
//            SplitPane root = new MainView();
//            Scene scene = new Scene(root,980,800);
            Scene scene = new Scene(login,550,350);
            primaryStage.setScene(scene);
            primaryStage.setTitle("express");
//            primaryStage.setMinWidth(980);
//            primaryStage.setMinHeight(800);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
