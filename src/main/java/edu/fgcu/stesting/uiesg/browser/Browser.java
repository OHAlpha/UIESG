package edu.fgcu.stesting.uiesg.browser;

// in this package put the classes to show the graphical output to the user....add a button to the web browser to 
// end the session and output a graphical interface....develop GI in javafx...

import java.awt.geom.Point2D;
import java.net.MalformedURLException;
import java.net.URL;

import edu.fgcu.stesting.uiesg.data.MAIDFactory;
import edu.fgcu.stesting.uiesg.data.MouseActionInputData;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyData;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * This is the user's interface to the UIESG. It is the browser that retrieves
 * and displays web pages and listens for mouse activity, sending notifications
 * to the currently active MAID instance.
 * 
 * @author oalpha
 *
 */
public class Browser extends Application {

	/**
	 * Relay modal information to the interface factories.
	 * 
	 * @param mode
	 *            the mode to initialize the factories in.
	 */
	public static void init(int mode) {
		MAIDFactory.init(mode);
	}

	/**
	 * The currently active SED instance.
	 */
	SiteEfficiencyData sed;

	/**
	 * The currently active MAID instance.
	 */
	MouseActionInputData maid;

	/**
	 * The WebEngine used by the WebView.
	 */
	WebEngine engine;

	@SuppressWarnings("javadoc")
	@Override
	public void start(Stage primaryStage) throws Exception {
		WebView browser = new WebView();
		browser.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (maid == null)
					return;
				EventType<? extends MouseEvent> type = arg0.getEventType();
				// System.out.println("Event( type: ( class: "
				// + type.getClass().getSimpleName() + ", name: "
				// + type.getName() + " ), position: " + pos(arg0)
				// + ", scene: " + scene(arg0) + ", screen: "
				// + screen(arg0) + ", source: " + arg0.getSource()
				// + ", target: " + arg0.getTarget());
				Point2D pos = new Point2D.Double(arg0.getX(), arg0.getY());
				switch (type.getName()) {
				case "MOUSE_ENTERED":
					maid.addPoint(pos, pos, System.currentTimeMillis(),
							java.awt.event.MouseEvent.MOUSE_ENTERED);
				case "MOUSE_MOVED":
					maid.addPoint(pos, pos, System.currentTimeMillis(),
							java.awt.event.MouseEvent.MOUSE_MOVED);
				case "MOUSE_PRESSED":
					maid.addPoint(pos, pos, System.currentTimeMillis(),
							java.awt.event.MouseEvent.MOUSE_PRESSED);
				case "MOUSE_DRAGGED":
					maid.addPoint(pos, pos, System.currentTimeMillis(),
							java.awt.event.MouseEvent.MOUSE_DRAGGED);
				case "MOUSE_RELEASED":
					maid.addPoint(pos, pos, System.currentTimeMillis(),
							java.awt.event.MouseEvent.MOUSE_RELEASED);
				case "MOUSE_CLICKED":
					maid.addPoint(pos, pos, System.currentTimeMillis(),
							java.awt.event.MouseEvent.MOUSE_CLICKED);
				case "MOUSE_EXITED":
					maid.addPoint(pos, pos, System.currentTimeMillis(),
							java.awt.event.MouseEvent.MOUSE_EXITED);
				}
				System.out.println("\t" + maid);

			}

			@SuppressWarnings("unused")
			private String pos(MouseEvent arg0) {
				return "( " + arg0.getX() + ", " + arg0.getY() + ", "
						+ arg0.getZ() + " )";
			}

			@SuppressWarnings("unused")
			private String screen(MouseEvent arg0) {
				return "( " + arg0.getScreenX() + ", " + arg0.getScreenY()
						+ " )";
			}

			@SuppressWarnings("unused")
			private String scene(MouseEvent arg0) {
				return "( " + arg0.getSceneX() + ", " + arg0.getSceneY() + " )";
			}

		});
		engine = browser.getEngine();
		engine.getLoadWorker().stateProperty()
				.addListener(new ChangeListener<Worker.State>() {

					@Override
					public void changed(ObservableValue<? extends State> arg0,
							State arg1, State arg2) {
						// TODO Auto-generated method stub
						// System.out.println("observable: " + arg0 + ", old: "
						// + arg1 + ", new: " + arg2);
						String url = (String) engine
								.executeScript("window.location.href");
						// System.out.println("\turl: " + url);
						try {
							updatePage(new URL(url));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				});
		// webEngine.load("http://10.0.0.1:80");
		GridPane grid = new GridPane();
		HBox toolbar = new HBox();
		Label addrL = new Label("Address:");
		final TextField addrT = new TextField();
		Button btn = new Button();
		btn.setText("Go");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String url = addrT.getText();
				// System.out.println("Loading "+url);
				URL u = null;
				for (int i = 0; i < 3; i++) {
					String u2 = null;
					switch (i) {
					case 0:
						u2 = url;
						break;
					case 1:
						u2 = "http://" + url;
						break;
					case 2:
						u2 = "https://" + url;
						break;
					}
					try {
						u = new URL(u2);
					} catch (MalformedURLException e0) {
						e0.printStackTrace();
					}
					if (url != null) {
						addrT.setText(u2);
						engine.load(u2);
						updatePage(u);
						break;
					}
				}
			}
		});
		Button b = new Button();
		b.setText("Graph");
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// create new scene in Graphical Output
				if (sed != null) {
					GraphicalOutput go = new GraphicalOutput();
					Stage stage = new Stage();
					
					// set the stage with the graph scene
					//stage.setScene(go.graph(sed));
					stage.setScene(go.sites());
					
					// show the stage
					stage.show();
				}
				else
					System.out.println("sed is null...");
			}

		});

		toolbar.getChildren().add(addrL);
		toolbar.getChildren().add(addrT);
		toolbar.getChildren().add(btn);
		toolbar.getChildren().add(b);
		grid.add(toolbar, 0, 0, 1, 1);
		grid.add(browser, 0, 1, 1, 1);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0));
		// screen size
		Scene scene = new Scene(grid, 600, 550);
		// set the browser title
		primaryStage.setTitle("*UIESG* - You're Being Monitored!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Loads the specified url and updates the SED and MAID instances.
	 * 
	 * @param url
	 *            the url to load
	 * @return whether or not a MAID was created successfully.
	 */
	public boolean updatePage(URL url) {

		String domain = url.getHost();
		if (sed == null || !sed.getDomain().equalsIgnoreCase(domain)) {
			if (sed != null)
				sed.unloadData();
			sed = SiteEfficiencyData.getForDomain(domain);
			if (!sed.isLoaded())
				sed.loadData();
			if (sed.isLoaded())
				maid = sed.newMouseData();
			else
				return false;
			System.out.println(sed);
			System.out.println(maid);
		}
		return true;
	}

	@SuppressWarnings("javadoc")
	public static void main(String[] args) {
		init(MAIDFactory.IMPLEMENTATION);
		launch();
	}

}