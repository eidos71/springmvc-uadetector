package com.github.mjeanroy.springmvc.uadetector.utils;

import com.github.mjeanroy.springmvc.uadetector.tools.Browser;

/**
 * Check that given browser is exactly Safari Browser (no matter
 * which version or which platform).
 * Used for tests only.
 */
public class SafariCondition implements Condition {

	/**
	 * Static Factory.
	 *
	 * @param browser Target Browser.
	 * @return Condition class.
	 */
	public static SafariCondition safariCondition(Browser browser) {
		return new SafariCondition(browser);
	}

	/** Browser to check. */
	private final Browser browser;

	/**
	 * Private Constructor.
	 * Use static factory instead.
	 *
	 * @param browser Target Browser.
	 */
	private SafariCondition(Browser browser) {
		this.browser = browser;
	}

	@Override
	public boolean check() {
		return browser.isSafari();
	}
}
