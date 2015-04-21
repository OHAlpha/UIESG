package edu.fgcu.stesting.uiesg.browser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;



// class to output to the user the graphical information 
// gathered from the web browser

public class GraphicalOutput extends Application {

	/*
	 * The types are: click, hover, enter, and exit (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage stage) throws Exception {
		
		// TODO Auto-generated method stub
		final LineChart<Number, Number> lc = new LineChart<>(new NumberAxis(),
				new NumberAxis());
		lc.setTitle("UIESG Data");
		// series1 will consist of the points that
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Mouse events");
		// test points for mouse events
		series1.getData().add(new XYChart.Data(4.2, 193.2));
		series1.getData().add(new XYChart.Data(2.8, 33.6));
		series1.getData().add(new XYChart.Data(6.8, 23.6));

		// series2 will have the mouse x,y data
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Mouse movement");
		// test points for mouse movements
		series2.getData().add(new XYChart.Data(5.2, 229.2));
		series2.getData().add(new XYChart.Data(2.4, 37.6));
		series2.getData().add(new XYChart.Data(50, 15.6));

		lc.setAnimated(false);
		lc.setCreateSymbols(true);

		lc.getData().addAll(series1, series2);

		Scene scene = new Scene(lc, 500, 400);
		scene.getStylesheets().add(
				getClass().getResource("chart.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}

	public static void main(String[] args) {
		// run linechart
		launch(args);
		
	}
}
