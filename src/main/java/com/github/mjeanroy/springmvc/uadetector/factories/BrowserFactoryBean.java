/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 <mickael.jeanroy@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.mjeanroy.springmvc.uadetector.factories;

import com.github.mjeanroy.springmvc.uadetector.tools.Browser;
import com.github.mjeanroy.springmvc.uadetector.tools.UADetectorBrowser;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Factory used to create {@link com.github.mjeanroy.springmvc.uadetector.tools.Browser} bean with
 * scope request.
 *
 * An instance of {@link net.sf.uadetector.UserAgentStringParser} must be available
 * in spring context.
 */
public class BrowserFactoryBean implements FactoryBean<Browser> {

	@Autowired
	private UserAgentStringParser parser;

	@Autowired
	private HttpServletRequest request;

	@Override
	public Browser getObject() throws Exception {
		String ua = request.getHeader("User-Agent");
		ReadableUserAgent userAgent = parser.parse(ua);
		return new UADetectorBrowser(userAgent);
	}

	@Override
	public Class<?> getObjectType() {
		return Browser.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
