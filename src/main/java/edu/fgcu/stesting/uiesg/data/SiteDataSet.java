package edu.fgcu.stesting.uiesg.data;

import java.util.Collection;

public class SiteDataSet {

	private Collection<MouseActionInputData> mouseData;
	
	private Collection<GraphOutputData> graphData;
	
	private Collection<UIEfficiencyStatistic> statistics;
	
	/**
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(MouseActionInputData e) {
		return mouseData.add(e);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(GraphOutputData e) {
		return graphData.add(e);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(UIEfficiencyStatistic e) {
		return statistics.add(e);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(PageContext e) {
		return pages.add(e);
	}

	private Collection<PageContext> pages;
    
}