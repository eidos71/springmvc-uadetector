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

package com.github.mjeanroy.springmvc.uadetector.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.github.mjeanroy.springmvc.uadetector.configuration.factories.UADetectorFactoriesConfigurationSelector;
import com.github.mjeanroy.springmvc.uadetector.configuration.parsers.UserAgentStringParserConfigurationSelector;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({
		UserAgentStringParserConfigurationSelector.class,
		UADetectorFactoriesConfigurationSelector.class,
		UADetectorConfiguration.class
})
public @interface EnableUADetector {

	/**
	 * Disable cache or use provided implementation.
	 * Default is {UACacheProvider#AUTO}.
	 *
	 * @return Cache strategy to use.
	 */
	UACacheProvider cache() default UACacheProvider.AUTO;

	/**
	 * Enable autowiring of {@link net.sf.uadetector.ReadableUserAgent}
	 * and {@link com.github.mjeanroy.springmvc.uadetector.tools.Browser} instances.
	 *
	 * Since these objects required a request scope and proxy mode, it is disabled
	 * by default.
	 *
	 * @return True to enable autowiring, false otherwise.
	 */
	boolean autowired() default false;
}
