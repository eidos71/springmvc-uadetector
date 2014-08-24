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

package com.github.mjeanroy.springmvc.uadetector.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

/**
 * Resolver that can be used to get user agent arguments.
 */
public class ReadableUserAgentResolver implements WebArgumentResolver {

	/**
	 * Parser used to read user-agent header and detect device and browsers.
	 * Parser is thread safe and can be used concurrently.
	 */
	private final UserAgentStringParser parser;

	/**
	 * Create resolver with a default parser.
	 */
	public ReadableUserAgentResolver() {
		this.parser = UADetectorServiceFactory.getResourceModuleParser();
	}

	/**
	 * Create resolver with a custom parser.
	 *
	 * @param parser Parser.
	 */
	public ReadableUserAgentResolver(UserAgentStringParser parser) {
		this.parser = parser;
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		final Class klass = methodParameter.getParameterType();

		if (ReadableUserAgent.class.isAssignableFrom(klass)) {
			final String header = webRequest.getHeader("User-Agent");
			return parser.parse(header);
		}

		return UNRESOLVED;
	}
}
