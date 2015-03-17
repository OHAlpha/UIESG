package edu.fgcu.stesting.uiesg.data;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

public class SiteDataSet {
	
	public SiteDataSet( String domain ) {
		this.domain = domain;
		data = null;
		pages = null;
	}
	
	class DataSet {

		public MouseActionInputData mouseData;
		
		public GraphOutputData graphData;
		
		public UIEfficiencyStatistic statistics;
		
	}
	
	private final String domain;

	private Collection<DataSet> data;

	private Map<URL,PageContext> pages;
	
	public boolean loadData() {
        throw new RuntimeException("method not implemented");
	}
	
	public boolean unloadData() {
        throw new RuntimeException("method not implemented");
	}
	
	public boolean isLoaded() {
        throw new RuntimeException("method not implemented");
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	
	public MouseActionInputData newMouseData() {
        throw new RuntimeException("method not implemented");
	}
	
	public void compileMouseData() {
        throw new RuntimeException("method not implemented");
	}
	
	public void calculateStatistics() {
        throw new RuntimeException("method not implemented");
	}
	
	public PageContext getForURL( URL url ) {
        throw new RuntimeException("method not implemented");
		
	}
    
}