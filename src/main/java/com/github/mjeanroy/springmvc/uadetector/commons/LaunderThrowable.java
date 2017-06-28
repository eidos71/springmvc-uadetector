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

package com.github.mjeanroy.springmvc.uadetector.commons;

/**
 * StaticUtilities
 *
 * @author Brian Goetz and Tim Peierls
 */
public final class LaunderThrowable {

	private LaunderThrowable() {
	}

	/**
	 * Coerce an unchecked Throwable to a RuntimeException
	 * <p/>
	 * If the Throwable is an Error, throw it; if it is a
	 * RuntimeException return it, otherwise throw IllegalStateException
	 */
	public static RuntimeException launderThrowable(Throwable t) {
		if (t instanceof RuntimeException) {
			return (RuntimeException) t;
		}
		else if (t instanceof Error) {
			throw (Error) t;
		}
		else {
			throw new IllegalStateException("Not unchecked", t);
		}
	}
}
