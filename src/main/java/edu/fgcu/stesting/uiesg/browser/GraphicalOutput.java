package edu.fgcu.stesting.uiesg.browser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableSet;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// class to output to the user the graphical information 
// gathered from the web browser

public class GraphicalOutput {

	/*
	 * The types are: click, hover, enter, and exit (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Scene graph(SiteEfficiencyData sed) {

		final LineChart<Number, Number> lc = new LineChart<>(new NumberAxis(),
				new NumberAxis());

		// set the title of the graph
		lc.setTitle("UIESG Data");

		// ******commented out series 1 which showed unconnected points*********
		/*
		 * // series1 will consist of just points that // are considered an
		 * "event" XYChart.Series series1 = new XYChart.Series();
		 * series1.setName("Mouse events"); //
		 * //SiteEfficiencyData.getForDomain(domain); // test points for mouse
		 * events series1.getData().add(new XYChart.Data(4.2, 193.2));
		 * series1.getData().add(new XYChart.Data(2.8, 33.6));
		 * series1.getData().add(new XYChart.Data(6.8, 23.6));
		 */

		// series2 will have the mouse x,y data and be a line
		// graph
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Mouse movement");

		// multiple GODs for each SED...get the SED by calling
		// SiteEffiencyData.getForDomain()...that'll give
		// me the domain and then use the domain to get the GODs...by calling
		// getGraphData()...
		// call compile on the SED which will create the GODs.
		// retrieve the GOD
		// god = sed.getGraphData(i);

		sed.compileMouseData();

		GraphOutputData god = sed.getGraphData(0);

		// test points for mouse movements
		series2.getData().add(new XYChart.Data(5.2, 229.2));
		series2.getData().add(new XYChart.Data(2.4, 37.6));
		series2.getData().add(new XYChart.Data(50, 15.6));

		lc.setAnimated(false);
		lc.setCreateSymbols(true);

		// add points to the LineChart
		lc.getData().addAll(series2);

		// initialize the Scene with points and specify size
		Scene scene = new Scene(lc, 500, 400);

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

	public Scene sites(final SiteEfficiencyData sed) {

		//ArrayList<Button> buttonArr = new ArrayList<Button>();
		//Iterator<Button> it = buttonArr.iterator();
		Object[] arr = SiteEfficiencyData.getAvailableDomains().toArray();
		
		Button b1 = new Button();
		b1.setText(arr[1].toString());
		

		b1.setOnAction(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent event) {
					// handle the button click
					Stage stage = new Stage();
					
					stage.setScene(graph(sed));
					stage.show();
					
				}

			});
		
		StackPane root = new StackPane();
			
		root.getChildren().add(b1);
			
		Scene s = new Scene(root, 300, 250);

		return s;
	}

}
