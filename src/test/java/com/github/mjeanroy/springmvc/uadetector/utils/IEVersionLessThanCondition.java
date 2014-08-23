package com.github.mjeanroy.springmvc.uadetector.utils;

import com.github.mjeanroy.springmvc.uadetector.tools.Browser;

/**
 * Class that check that given browser is IE in lower or equals than a specific version.
 * Used for tests only.
 */
public class IEVersionLessThanCondition implements Condition {

	/**
	 * Static factory.
	 *
	 * @param browser Target Browser.
	 * @param version Target version.
	 * @return Condition class.
	 */
	public static IEVersionLessThanCondition ieVersionLessThanCondition(Browser browser, int version) {
		return new IEVersionLessThanCondition(browser, version);
	}

	/** Browser to check. */
	private final Browser browser;

	/** IE Version to check. */
	private final int version;

	/**
	 * Private constructor, use factory instead.
	 *
	 * @param browser Target Browser.
	 * @param version Target version.
	 */
	private IEVersionLessThanCondition(Browser browser, int version) {
		this.browser = browser;
		this.version = version;
	}

	@Override
	public boolean check() {
		return isVersionSupported() && checkVersion();
	}

	private boolean isVersionSupported() {
		return version >= 6 && version <= 11;
	}

	private boolean checkVersion() {
		return browser.isIELessThan6() == (version <= 6)
				&& browser.isIELessThan7() == (version <= 7)
				&& browser.isIELessThan8() == (version <= 8)
				&& browser.isIELessThan9() == (version <= 9)
				&& browser.isIELessThan10() == (version <= 10);
	}
}
