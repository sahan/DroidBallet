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
 * <p>A {@link RuntimeException} which represents erroneous conditions 
 * which are unexpected and cannot usually be recovered from.
 * 
 * @version 1.0.0
 * 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class DroidBalletRuntimeException extends RuntimeException {

	
	private static final long serialVersionUID = -664639202801246046L;

	
	/**
	 * See {@link RuntimeException#RuntimeException()}.
	 */
	public DroidBalletRuntimeException() {
		
		this("Locomotion has encountered a runtime exception.");
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)}.
	 */
	public DroidBalletRuntimeException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable))}.
	 */
	public DroidBalletRuntimeException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 */
	public DroidBalletRuntimeException(String detailMessage, Throwable throwable) {
		
		super(detailMessage, throwable);
	}
}
