package com.lonepulse.droidballet.core;

/*
 * #%L
 * DroidBallet Library
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


import com.lonepulse.droidballet.DroidBalletRuntimeException;

/**
 * <p>This runtime exception is thrown due to an unrecoverable failure in 
 * the {@link HiggsField}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class HiggsFieldException extends DroidBalletRuntimeException {


	private static final long serialVersionUID = -7733562133899460260L;

	
	/**
	 * <p>See {@link DroidBalletRuntimeException#DroidBalletRuntimeException()}.
	 */
	public HiggsFieldException() {}

	/**
	 * <p>See {@link DroidBalletRuntimeException#DroidBalletRuntimeException(String)}.
	 */
	public HiggsFieldException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * <p>See {@link DroidBalletRuntimeException#DroidBalletRuntimeException(Throwable)}.
	 */
	public HiggsFieldException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * <p>See {@link DroidBalletRuntimeException#DroidBalletRuntimeException(String, Throwable)}.
	 */
	public HiggsFieldException(String detailMessage, Throwable throwable) {

		super(detailMessage, throwable);
	}
}
