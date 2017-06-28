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

import com.github.mjeanroy.springmvc.uadetector.commons.ClassUtils;
import com.github.mjeanroy.springmvc.uadetector.configuration.parsers.cache.ehcache.EhCacheParserConfiguration;
import com.github.mjeanroy.springmvc.uadetector.configuration.parsers.cache.simple.SimpleCacheParserConfiguration;
import com.github.mjeanroy.springmvc.uadetector.configuration.parsers.cache.guava.GuavaCacheParserConfiguration;
import com.github.mjeanroy.springmvc.uadetector.configuration.parsers.NoCacheParserConfiguration;

/**
 * Cache strategies.
 * At this time, following strategies are available:
 * - No cache.
 * - Cache implemented with Guava cache objects.
 * - Cache implemented with ehcache library.
 * - Auto: try to find the best cache implementation available on classpath.
 */
public enum UACacheProvider {

	/**
	 * Do not use any cache.
	 */
	NONE {
		@Override
		public Class getConfigurationClass() {
			return NoCacheParserConfiguration.class;
		}
	},

	/**
	 * Use default cache, using concurrent map.
	 */
	DEFAULT {
		@Override
		public Class getConfigurationClass() {
			return SimpleCacheParserConfiguration.class;
		}
	},

	/**
	 * Use Guava Cache implementation.
	 */
	GUAVA {
		@Override
		public Class getConfigurationClass() {
			return GuavaCacheParserConfiguration.class;
		}
	},

	/**
	 * Use EhCache implementation.
	 */
	EH_CACHE {
		@Override
		public Class getConfigurationClass() {
			return EhCacheParserConfiguration.class;
		}
	},

	/**
	 * Auto Detect cache implementation:
	 * - If EhCache is on classpath, use ehcache
	 * - Then, if Guava is on classpath, use guava cache loader.
	 * - Otherwise use default cache implementation.
	 */
	AUTO {
		@Override
		public Class getConfigurationClass() {
			if (ClassUtils.isPresent("net.sf.ehcache.Cache")) {
				// First try to find ehcache
				return EH_CACHE.getConfigurationClass();
			}
			else if (ClassUtils.isPresent("com.google.common.cache.CacheBuilder")) {
				// Then Guava
				return GUAVA.getConfigurationClass();
			}
			else {
				// Then default
				return DEFAULT.getConfigurationClass();
			}
		}
	};

	/**
	 * Return configuration class to use associated with this cache
	 * strategy.
	 *
	 * @return Configuration class.
	 */
	public abstract Class getConfigurationClass();
}
