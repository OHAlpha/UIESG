package edu.fgcu.stesting.uiesg.data;

import java.awt.Shape;
import java.util.Collection;

/**
 * This class is a container for structural information for pages. It holds data
 * for all "static" elements of interest, i.e. hyperlinks, buttons, etc.. There
 * shall exist, for every page of a domain visited, an PageContext instance in
 * the owning SED instance.
 * 
 * @author oalpha
 *
 */
public class PageContextImp implements PageContext {
	
	/**
	 * The collection of MouseTargets contained in this instance.
	 */
	@SuppressWarnings("unused")
	private Collection<MouseTarget> targets;

	/**
	 * Returns a Shape instance who's area includes the area of each of the
	 * child MouseTarget instances of this page.
	 * 
	 * @return the clickable region
	 */
	public Shape getClickableRegion() {
		throw new RuntimeException("method not implemented");
		
	}

	/**
	 * Returns a collection of MouseTargets contained in this instance.
	 * 
	 * @return the targets
	 */
	public Collection<MouseTarget> getTargets() {
		throw new RuntimeException("method not implemented");
	}

	/**
	 * Returns the SED instance of the domain of this page.
	 * 
	 * @return the SED instance
	 */
	public SiteEfficiencyData getDomain() {
		throw new RuntimeException("method not implemented");
	}
    
}