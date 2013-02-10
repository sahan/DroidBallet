package com.lonepulse.droidballet;

/*
 * #%L
 * DroidBallet
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 * <p>An {@link Exception} which represents trivial erroneous conditions which 
 * may be expected and can usually be recovered from.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class DroidBalletException extends Exception {

	
	private static final long serialVersionUID = 7884288050077484699L;

	
	/**
	 * See {@link Exception#Exception()}.
	 */
	public DroidBalletException() {
		
		this("Locomotion has encountered an exception.");
	}

	/**
	 * See {@link Exception#Exception(String))}.
	 */
	public DroidBalletException(String detailMessage) {

		super(detailMessage);
	}

	/**
	 * See {@link Exception#Exception(Throwable))}.
	 */
	public DroidBalletException(Throwable throwable) {

		super(throwable);
	}

	/**
	 * See {@link Exception#Exception(String, Throwable)}.
	 */
	public DroidBalletException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
