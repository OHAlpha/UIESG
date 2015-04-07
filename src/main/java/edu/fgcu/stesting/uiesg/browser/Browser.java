package edu.fgcu.stesting.uiesg.browser;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Browser extends Application {
	
	TextField addrT;

	@Override
	public void start( Stage primaryStage ) throws Exception {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		//webEngine.load("http://10.0.0.1:80");
		GridPane grid = new GridPane();
		HBox toolbar = new HBox();
		Label addrL = new Label("Address:");
		addrT = new TextField();
		Button btn = new Button();
		btn.setText("Go");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				webEngine.load(addrT.getText());
			}
		});
		toolbar.getChildren().add(addrL);
		toolbar.getChildren().add(addrT);
		toolbar.getChildren().add(addrT);
		grid.add(toolbar, 0, 0, 1, 1);
		grid.add(browser, 0, 1, 1, 1);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main( String[] args ) {
		launch();
	}

}