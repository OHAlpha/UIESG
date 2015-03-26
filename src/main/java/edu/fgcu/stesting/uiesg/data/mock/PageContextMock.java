package edu.fgcu.stesting.uiesg.data.mock;

import java.awt.Shape;
import java.util.Arrays;
import java.util.Collection;

import edu.fgcu.stesting.uiesg.data.MouseTarget;
import edu.fgcu.stesting.uiesg.data.PageContext;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyData;

/**
 * This mock simply holds predefined data instead of computing data. No data is
 * mutable.
 * 
 * @author oalpha
 *
 */
public class PageContextMock implements PageContext {

	/**
	 * The clickable region defined by the constructor.
	 */
	private final Shape clickableRegion;

	/**
	 * The mouse targets defined by the constructor.
	 */
	private final MouseTarget[] targets;

	/**
	 * The SED defined by the constructor.
	 */
	private final SiteEfficiencyData domain;

	/**
	 * Constructs a mock with predefined data.
	 * 
	 * @param clickableRegion
	 *            the clickable region
	 * @param targets
	 *            the array of targets
	 * @param domain
	 *            the SED
	 */
	public PageContextMock( Shape clickableRegion, MouseTarget[] targets,
			SiteEfficiencyData domain ) {
		super();
		this.clickableRegion = clickableRegion;
		this.targets = targets;
		this.domain = domain;
	}

	/**
	 * Returns the field, clickableRegion, supplied by constructor.
	 */
	@Override
	public Shape getClickableRegion() {
		return clickableRegion;
	}

	/**
	 * Returns the field, targets, supplied by constructor, as a list.
	 */
	@Override
	public Collection<MouseTarget> getTargets() {
		return Arrays.asList(targets);
	}

	/**
	 * Returns the field, domain, supplied by constructor.
	 */
	@Override
	public SiteEfficiencyData getDomain() {
		return domain;
	}

}