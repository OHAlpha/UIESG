package edu.fgcu.stesting.uiesg.browser;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;

import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyData;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatistic;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// class to output to the user the graphical information 
// gathered from the web browser

@SuppressWarnings("javadoc")
public class GraphicalOutput {

	// SiteEfficiencyData sed;
	GraphOutputData god;

	/*
	 * The types are: click, hover, enter, and exit (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Scene graph(SiteEfficiencyData sed) {

		// define LineChart
		final LineChart<Number, Number> lineChart = new LineChart<>(
				new NumberAxis(), new NumberAxis());

		// set the title of the graph
		lineChart.setTitle("UIESG Data");

		// declare series
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Mouse movement");

		// methods to use for Stats:
		// sed.calculateStatistics();
		// sed.getStatistics(i);

		// compiles mouse data for selected SED
		sed.compileMouseData();

		// NavigableSet<String> stats = sed.calculateStatistics();

		/*** get the number of instances for the particular domain ***/
		int size = sed.size()-1;
		
		System.out.println("SED.size() = " + size);
		god = sed.getGraphData(size);

		/*** then get the number of actions god.numActions() ***/
		int actions = god.numActions();

		/*** loop through the actions and assign points ***/
		for (int i = 0; i < actions; i++) {
			MouseGraphAction a = god.getAction(i);
			System.out.println(a);
			if (a.getType() == GODFactory.NODE) {
				Point2D p = ((MouseGraphNode) a).getLocation();
				series2.getData().add(new XYChart.Data(p.getX(), p.getY()));
			}
		}

		// calculates stats for SED
		sed.calculateStatistics();
		
		ArrayList<String> tmp = new ArrayList<String>();

		NavigableMap<String, UIEfficiencyStatistic> stats = sed.getStatistics(size);

		
		for(NavigableMap.Entry<String, UIEfficiencyStatistic> entry:stats.entrySet()) {
			tmp.add(entry.getKey() + " :  " + entry.getValue().getUIValue());
		}

		// putting domains in observable list 'data'
		final ObservableList<String> data = FXCollections
				.observableArrayList(tmp);

		final ListView<String> listView = new ListView<String>(data);
		listView.setItems(data);

		// setup display and add lineChart and statBar to the grid
		GridPane grid = new GridPane();
		VBox vBox = new VBox();
		Text title = new Text("Statistics");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		vBox.getChildren().add(title);
		vBox.getChildren().add(listView);
		grid.add(vBox, 0, 0, 1, 1);
		grid.add(lineChart, 0, 1, 1, 1);

		lineChart.setAnimated(false);
		lineChart.setCreateSymbols(true);

		// add points to the LineChart
		lineChart.getData().addAll(series2);

		// initialize the Scene with points and specify size
		Scene scene = new Scene(grid, 800, 750);

		// use custom .css to hide one of the lines so that only the
		// points are visible
		scene.getStylesheets().add(
				getClass().getResource("chart.css").toExternalForm());

		// display graph
		// stage.setScene(scene);
		// stage.show();
		return scene;
	}
	
	/***
	 * 
	 * Canvas creates a graph based on the mouse movements to display to the user. It uses canvas
	 * to create the visual representation.
	 * 
	 * @param sed
	 * @return
	 */
	
	public Scene canvas(SiteEfficiencyData sed) {
		
		//Group root = new Group();
		Canvas canvas = new Canvas (900,900);
		
		//GridLayout gl = new GridLayout(0,2);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// begin path??
		
		gc.beginPath();
		
		//gc.moveTo(0,0);
		//gc.lineTo(1,3);
		//gc.lineTo(25, 50);
		//gc.stroke(); //stroke the lines that were made???
	
		// creates a line from x1, y1 to x2, y2 strokeLine(x1, y1, x2, y2)
		sed.compileMouseData();
		int size = sed.size()-1;
		god = sed.getGraphData(size);
		int actions = god.numActions();
		// create the points on the canvas
		for(int i = 1; i < actions; i++) {
			MouseGraphAction a = god.getAction(i);
			if(a.getType() == GODFactory.NODE) {
					Point2D p = ((MouseGraphNode) a).getLocation();
					
					gc.lineTo(p.getX(), p.getY());
					
			}
			else{
					MouseGraphEdge e = a.asEdge();
					Point2D source = e.getSource(), dest = e.getDest();
					int pt = e.getPathType();
					double [] ps = e.getPathParameters();
					
			}
			
		}
		
		gc.stroke();
		
		//calcuate the statistics
		sed.calculateStatistics();
		// tmp array to hold the stats from "stats"
		ArrayList<String> tmp = new ArrayList<String>();
		// getting the calcualted statistics
		NavigableMap<String, UIEfficiencyStatistic> stats = sed.getStatistics(size);
		// loop through "stats" to add everything to tmp
		for (NavigableMap.Entry<String, UIEfficiencyStatistic> entry:stats.entrySet()) {
			tmp.add(entry.getKey()+ " : " + entry.getValue().getUIValue());
			System.out.println(entry.getKey() + " : " + entry.getValue().getUIValue());
		}
		// create new observable list for the list display
		final ObservableList<String> data = FXCollections.observableArrayList(tmp);
		// new listView 
		final ListView<String> listView = new ListView<String>(data);
		
		// create gridpane to hold the elements for the scene
		GridPane grid = new GridPane();
		VBox vBox = new VBox(10);
		// set title for the statistics
		//grid.setStyle("-fx-background-color: red;");
		
		Text title = new Text("Statistics and Graphical Analysis");
		title.setFont(Font.font("Arial", 20));
		
		vBox.getChildren().add(title);
		vBox.getChildren().add(listView);
				
		//vBox.getChildren().add(canvas);
		// add constructor
		// add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
		grid.add(vBox, 0, 0, 2, 3);
		grid.add(canvas, 2, 2, 1, 1);
		
		
		// create canvas and add to new scene
		
		Scene scene = new Scene(grid, 1200, 900);
		
		
		
		return scene;
				
	}

	/***
	 * method to display to the user the websites that they can choose from in
	 * order to display the graph
	 * 
	 * @return
	 */

	public Scene sites() {
		// Scene scene = null;

		// get the domains
		NavigableSet<String> keySet = SiteEfficiencyData.getAvailableDomains();
		ArrayList<String> doms = new ArrayList<String>();
		// put domains in list
		doms.addAll(keySet);

		// putting domains in observable list 'data'
		final ObservableList<String> data = FXCollections
				.observableArrayList(doms);

		final ListView<String> listView = new ListView<String>(data);

		listView.setPrefSize(200, 250);
		listView.setEditable(true);
		listView.setItems(data);

		listView.getSelectionModel().getSelectedIndices()
				.addListener(new ListChangeListener<Integer>() {
					@Override
					public void onChanged(Change<? extends Integer> change) {

						for (int i = 0; i < data.size(); i++) {
							if (change.getList().contains(i)) {
								// send the domain name to getForDomain to get
								// the SED
								SiteEfficiencyData sed = SiteEfficiencyData
										.getForDomain(data.get(i));
								Stage stage = new Stage();
								stage.setScene(canvas(sed));
								// code to set a new scene here:
								//stage.setFullScreen(true);
								stage.show();
								
							}
						}

					}

				});

		StackPane root = new StackPane();
		root.getChildren().add(listView);
		Scene scene = new Scene(root, 200, 250);

		return scene;
	}

}
