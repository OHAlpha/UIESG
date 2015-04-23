package edu.fgcu.stesting.uiesg.browser;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;

import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyData;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatistic;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// class to output to the user the graphical information 
// gathered from the web browser

@SuppressWarnings( "javadoc" )
public class GraphicalOutput {

	SiteEfficiencyData sed;
	GraphOutputData god;

	/*
	 * The types are: click, hover, enter, and exit (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Scene graph(SiteEfficiencyData sed) {

		// define LineChart
		final LineChart<Number, Number> lineChart = new LineChart<>(new NumberAxis(),
				new NumberAxis());

		// set the title of the graph
		lineChart.setTitle("UIESG Data");

		
		// declare series
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Mouse movement");
		
		
		
		
		// compiles mouse data for selected SED
		sed.compileMouseData();
				
		/*** get the number of instances for the particular domain ***/
		int size = sed.size()-1;
		if (size>0)	god = sed.getGraphData(size);
		System.out.println(god);
		System.out.println(sed.getMouseData(size));
		/*** then get the number of actions god.numActions() ***/
		int actions = god.numActions();
				
		/*** loop through the actions and assign points ***/
		for(int i = 0; i < actions; i++) {
			MouseGraphAction a = god.getAction(i);
			if (a.getType() == GODFactory.NODE) {
				Point2D p = ((MouseGraphNode)a).getLocation();
				series2.getData().add(new XYChart.Data(p.getX(),p.getY()));
			}
		}
		
		// calculates stats for SED
		sed.calculateStatistics();
		
		NavigableMap<String, UIEfficiencyStatistic> stats = sed.getStatistics(size);
		ArrayList<String> tmp = new ArrayList<String>();
		
		for(NavigableMap.Entry<String, UIEfficiencyStatistic> entry:stats.entrySet()) {
			tmp.add(entry.getKey() + " :      " + entry.getValue());
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
								System.out.println("you selected" + data.get(i));
								SiteEfficiencyData sed = SiteEfficiencyData
										.getForDomain(data.get(i));
								Stage stage = new Stage();
								stage.setScene(graph(sed));
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
